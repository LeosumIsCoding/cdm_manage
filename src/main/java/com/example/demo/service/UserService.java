package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.ArrayList;


public interface UserService {
    User userLogin(String username, String password, Integer type);

   int sendEmailByUsername(String username,String email);


   Integer userRegister(String username,String password,String email,Integer type,String header);

   Integer changeUserInfo(User user);

   ArrayList<User> getAllUser();
}
