package com.hobbs.tv.service;

import com.hobbs.tv.dto.UserDto;
import com.hobbs.tv.entity.User;
import com.hobbs.tv.exception.UserIDNotFoundException;
import com.hobbs.tv.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService
{
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDto saveLoginDetails(UserDto userDto) {

        User user = modelMapper.map(userDto, User.class);
        User user1 = userRepository.save(user);
        return modelMapper.map(user1, UserDto.class);
    }

    @Override
    public User getUserByNameAndPassword(String userName, String password) throws UserIDNotFoundException {
        User user = userRepository.findByUserNameAndPassword(userName, password);
        if(user == null){
            throw new UserIDNotFoundException("Invalid id and password");
        }
        return user;
    }
}
