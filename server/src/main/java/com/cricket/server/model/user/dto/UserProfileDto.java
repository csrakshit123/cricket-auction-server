package com.cricket.server.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {
    private String profileId;

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String email;
    private String phone;
}
