package com.hobbs.tv.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "userLogin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User
{
    @Id
    private long userId;
    private String email;
    private String userName;
    private String password;
}
