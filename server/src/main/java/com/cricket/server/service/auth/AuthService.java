package com.cricket.server.service.auth;

import com.cricket.server.config.JWTUtil;
import com.cricket.server.config.MyUserDetailsService;
import com.cricket.server.dao.user.UserRepo;
import com.cricket.server.exceptions.InvalidExistingUserRequest;
import com.cricket.server.exceptions.UserAlreadyExistsException;
import com.cricket.server.mappers.UserMapper;
import com.cricket.server.mappers.UserProfileMapper;
import com.cricket.server.model.auth.AuthResponse;
import com.cricket.server.model.auth.RegisterRequest;
import com.cricket.server.model.user.User;
import com.cricket.server.model.auth.AuthRequest;
import com.cricket.server.model.user.UserProfile;
import com.cricket.server.model.user.UserRole;
import com.cricket.server.model.user.dto.UserDto;
import com.cricket.server.model.user.dto.UserProfileDto;
import com.cricket.server.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserRepo userRepo;

    public UserDto register(RegisterRequest registerRequest) throws UserAlreadyExistsException, InvalidExistingUserRequest {
        String existingUser = registerRequest.getExistingUser();
        UserRole newUserRole = registerRequest.getUserRole();
        if ((newUserRole != UserRole.USER) && (!userService.checkExistingUserWithRole(existingUser, newUserRole))){
            throw new InvalidExistingUserRequest("Invalid existing user provided: " + existingUser + ".");
        }
        User newUser = userMapper.registerRequestToUserEntity(registerRequest);
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        UserProfile newUserProfile = userProfileMapper.dtoToEntity(registerRequest.getUserProfile());
        newUser.setUserProfile(newUserProfile);

        return userService.create(newUser);
    }

    public AuthResponse login(AuthRequest loginRequest) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        User user = userRepo.getById(userDetails.getUsername());
        UserProfileDto userProfileDto = userProfileMapper.entityToDto(user.getUserProfile());
        return new AuthResponse(jwt, user.getUsername(), user.getUserRole(), userProfileDto);

    }
}
