package com.cricket.server.model.auth;

import com.cricket.server.model.user.UserRole;
import com.cricket.server.model.user.dto.UserProfileDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String jwt;
    private String username;
    private UserRole userRole;
    private UserProfileDto userProfile;
}
