package com.hobbs.tv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email
{
    private String to;
    private String subject;
    private String text;
//    private String name;
//    private String email;
//    private String mobile;
}
