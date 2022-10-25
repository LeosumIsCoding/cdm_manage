package com.example.demo.service;

import com.example.demo.config.Result;
import com.example.demo.entity.User;


public interface UserService {
    User userLogin(String username, String password, Integer type);

   int sendEmailByUsername(String username,String email);

   Integer userRegister(String username,String password,String email,Integer type,String header);

   Integer changeUserInfo(User user);
}
