package com.hobbs.tv.service;

import com.hobbs.tv.dto.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService
{
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }

    @Override
    public String sendRegMail(Email emailDto)
    {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try{
            mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setTo(emailDto.getTo());
            mimeMessageHelper.setSubject("Welcome to Hobbs TV !!");
            mimeMessageHelper.setText(emailDto.getText());

            //then send
            javaMailSender.send(mimeMessage);

            return "Mail Sent Successfully...";

        } catch (MessagingException e) {
            return "Error while sending mail!!!";
        }
    }
}
