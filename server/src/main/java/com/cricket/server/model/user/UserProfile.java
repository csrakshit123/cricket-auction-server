package com.cricket.server.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserProfile {
    @TableGenerator(name = "profile_gen", table = "id_generator",
            pkColumnName = "generator_name", valueColumnName = "generator_value",
            pkColumnValue="profile_gen", initialValue=1000, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "profile_gen")
    @Column(name = "profile_id")
    @Id
    private Integer profileId;

    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String email;
    private String phone;
}
