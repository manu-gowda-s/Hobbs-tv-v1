package com.hobbs.tv.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hobbs-tv-users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User
{
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long id;

    private String name;

    private String email;

    private String mobile;

    private String userName;

    private String password;

    private Gender gender;

    private Country country;

    private boolean primeUser;

}
