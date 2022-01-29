package com.cricket.server.controller.auth;

import com.cricket.server.dao.user.UserRepo;
import com.cricket.server.exceptions.InvalidExistingUserRequest;
import com.cricket.server.exceptions.UserAlreadyExistsException;
import com.cricket.server.model.auth.AuthRequest;
import com.cricket.server.model.auth.AuthResponse;
import com.cricket.server.model.auth.RegisterRequest;
import com.cricket.server.model.user.UserRole;
import com.cricket.server.model.user.dto.UserDto;
import com.cricket.server.service.auth.AuthService;
import com.cricket.server.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/authenticate")
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        try {
            System.out.println(registerRequest);
            UserDto registeredUser = authService.register(registerRequest);
            return ResponseEntity.status(HttpStatus.OK).body(registeredUser);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is taken: " + registerRequest.getUsername());
        } catch (InvalidExistingUserRequest invalidExistingUserRequest) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidExistingUserRequest.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest loginRequest){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            AuthResponse authResponse = authService.login(loginRequest);
            return ResponseEntity.status(HttpStatus.OK).body(authResponse);
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/checkUsername/{username}")
    public ResponseEntity<?> checkDuplicateUsername(@PathVariable(value = "username") String username){
        return ResponseEntity.status(HttpStatus.OK).body(userRepo.existsById(username));
    }

    @GetMapping("/existingUser/{username}/{userRole}")
    public ResponseEntity<?> checkExistingUser(@PathVariable String username, @PathVariable UserRole userRole){
        return ResponseEntity.status(HttpStatus.OK).body(userService.checkExistingUserWithRole(username, userRole));
    }
}
