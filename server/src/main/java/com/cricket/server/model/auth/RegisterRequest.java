package com.cricket.server.model.auth;

import com.cricket.server.model.user.UserRole;
import com.cricket.server.model.user.dto.UserProfileDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterRequest {
    private String username;
    private String password;
    private UserRole userRole;
    private String existingUser;
    private UserProfileDto userProfile;
}
