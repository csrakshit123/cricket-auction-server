package com.cricket.server.service.user;

import com.cricket.server.dao.user.UserRepo;
import com.cricket.server.exceptions.UserAlreadyExistsException;
import com.cricket.server.mappers.UserMapper;
import com.cricket.server.model.user.User;
import com.cricket.server.model.user.UserRole;
import com.cricket.server.model.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepo userRepo;

    public UserDto create(User user) throws UserAlreadyExistsException {

        if (userRepo.existsById(user.getUsername())){
            throw new UserAlreadyExistsException("User with given username exists.");
        }

        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        System.out.println(user.getUsername() + " " + user.getUserProfile().getEmail());
        User savedUser = userRepo.save(user);

        return userMapper.entityToDto(savedUser);
    }

    private boolean checkUserIsAdminOrSuperUser(String username){
        return userRepo.checkExistingUserWithRole(username, UserRole.ADMIN) ||
                userRepo.checkExistingUserWithRole(username, UserRole.SUPER_USER);
    }

    private boolean checkUserIsSuperUser(String username){
        return userRepo.checkExistingUserWithRole(username, UserRole.SUPER_USER);
    }
    public boolean checkExistingUserWithRole(String username, UserRole userRole){
        if (userRole == UserRole.ADMIN){
            return checkUserIsAdminOrSuperUser(username);
        }
        else if(userRole == UserRole.SUPER_USER){
            return checkUserIsSuperUser(username);
        }
        else{
            return false;
        }
    }
}
