package com.hobbs.tv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegConsumeDto
{
    private long id;
    private String email;
    private String userName;
    private String password;
}
