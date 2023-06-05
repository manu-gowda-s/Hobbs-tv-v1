package com.hobbs.tv.service.security;

import com.hobbs.tv.entity.User;

import java.util.Map;

public interface JwtGeneratorInterface
{

    Map<String, String> generateToken(User user);
}
