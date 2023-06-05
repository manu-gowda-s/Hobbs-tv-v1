package com.hobbs.tv.service;

import com.hobbs.tv.dto.UserDto;
import com.hobbs.tv.entity.User;

public interface UserService {

    UserDto saveLoginDetails(UserDto userDto);

    public User getUserByNameAndPassword(String userName, String password);
}

