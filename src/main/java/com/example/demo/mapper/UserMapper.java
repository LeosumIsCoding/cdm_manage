package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Insert("insert into users(id, username, password, type, email, header) values(#{id}, #{username}, #{password}, #{type}, #{email}, #{header})")
    public Integer insertUser(User user);

}
