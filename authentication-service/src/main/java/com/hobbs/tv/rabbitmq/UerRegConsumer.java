package com.hobbs.tv.rabbitmq;

import com.hobbs.tv.dto.UserDto;
import com.hobbs.tv.dto.UserRegConsumeDto;
import com.hobbs.tv.dto.UserRegDto;
import com.hobbs.tv.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UerRegConsumer
{

    private static final Logger LOGGER = LoggerFactory.getLogger(UerRegConsumer.class);

    private UserService userService;
    private ModelMapper modelMapper;

    @RabbitListener(queues = "${rabbitmq.queue.user.name}")
    public void consumeUserRegDetails(UserRegDto userRegDto)
    {
        LOGGER.info(String.format("User Details consumed for Login --> %s", userRegDto.toString()));

        //convert producer to consumer dto objects
        UserRegConsumeDto regConsumeDto = modelMapper.map(userRegDto, UserRegConsumeDto.class);

        UserDto userDto = modelMapper.map(regConsumeDto, UserDto.class);
        userService.saveLoginDetails(userDto);
    }

}
