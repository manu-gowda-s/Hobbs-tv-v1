package com.hobbs.tv.service;

import com.hobbs.tv.dto.UserAndContentsDTO;
import com.hobbs.tv.dto.UserDto;
import com.hobbs.tv.entity.Country;
import com.hobbs.tv.entity.User;
import com.hobbs.tv.exception.EmailAlreadyRegisteredException;

import java.util.List;
import java.util.Map;

public interface UserService
{
    UserDto regUser(UserDto userDto) throws EmailAlreadyRegisteredException;

    UserAndContentsDTO findByUerId(Long id);

    List<User> findAllUsersByCountry(Country country);

    User updateUserMobileNum(User user, Long id);

    User updateUserEmail(User user, Long id);

    void deleteUserById(Long id);

   List<User> getUserByCountry();

   Map<Country, List<User>> getGroupUserByCountry();
}
