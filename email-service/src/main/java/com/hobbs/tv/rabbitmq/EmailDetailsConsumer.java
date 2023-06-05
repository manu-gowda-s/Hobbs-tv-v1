package com.hobbs.tv.rabbitmq;


import com.hobbs.tv.dto.Email;
import com.hobbs.tv.dto.EmailConsumeDto;
import com.hobbs.tv.dto.EmailDto;
import com.hobbs.tv.dto.UserRegDto;
import com.hobbs.tv.service.EmailService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailDetailsConsumer
{

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailDetailsConsumer.class);

//    private EmailService emailService;
//
//    private ModelMapper modelMapper;

    @RabbitListener(queues = "${rabbitmq.queue.email.name}")
    public void consumeEmailDetails(EmailDto emailDto)
    {
        LOGGER.info(String.format("User Email Details Consumed --> %s" + emailDto.toString()));

//        EmailConsumeDto consumeDto = modelMapper.map(emailDto, EmailConsumeDto.class);
//
//        Email email = new Email();
//        email.setTo(consumeDto.getEmail());
//        email.setSubject("Welcome to Hobbs TV !!");
//        email.setText("Hi " + consumeDto.getName().toString() + System.lineSeparator()
//                       + " Dear User your account is created successfully, now you can enjoy thousands of " +
//                " Movies and shows " + " What are you waiting for start now !!!" + System.lineSeparator()
//                + "Team" + System.lineSeparator() + "HOBBS TV+");
//
//        emailService.sendRegMail(email);

    }


}
