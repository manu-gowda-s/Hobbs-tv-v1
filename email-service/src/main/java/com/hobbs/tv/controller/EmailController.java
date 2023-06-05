package com.hobbs.tv.controller;

import com.hobbs.tv.dto.Email;
import com.hobbs.tv.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class EmailController
{
    @Autowired
    private EmailService emailService;

    @PostMapping("/sample/mail")
    public ResponseEntity<String> sendEmail(@RequestBody Email emailDto)
    {
            emailService.sendRegMail(emailDto);
            return new ResponseEntity<>("Email sent", HttpStatus.OK);
    }
}
