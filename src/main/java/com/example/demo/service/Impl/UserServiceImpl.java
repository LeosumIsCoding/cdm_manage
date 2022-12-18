package com.example.demo.service.Impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.config.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.utils.MapCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


@Service
@DS("CDM")   //使用cdm 数据库
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //    邮箱
    @Autowired
    private JavaMailSender javaMailSender;

    @Override  //用户登录
    public User userLogin(String username, String password, Integer type) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        queryWrapper.eq(User::getPassword, password);
        queryWrapper.eq(User::getType, type);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }


    //    用户注册
    @Override
    public int sendEmailByUsername(String username, String email) {

//        查询用户名
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        int count = userMapper.selectCount(queryWrapper);

//        用户名不存在
        if (count != 0) {
            return -1;
        }

//        根据用户名查询邮箱
        QueryWrapper queryWrapperEmail = new QueryWrapper();
//        查找username
        queryWrapperEmail.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);

//        随机生成信息
        int emailCode = (int) (Math.random() * 9000 + 1000);

        //        保存验证码
        MapCodeUtils.putCode(username, emailCode + "");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            //将邮件对象修饰一下，方便设置
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            //设置邮件的相关属性
            //邮件标题
            mimeMessageHelper.setSubject("CDM找回邮件");
            //邮件的发送方
            mimeMessageHelper.setFrom("h3321566631@sina.com");
            //邮件的接收方
            mimeMessageHelper.setTo(email);//设置普通接收方
            //邮件的内容
            mimeMessageHelper.setText(
                    "<h1>注册帐号</h1>注册帐号的验证码：" + emailCode + "，如果非本人操作，请忽略", true);
            //邮件的发送时间
            mimeMessageHelper.setSentDate(new Date());

            //发送邮件
            javaMailSender.send(mimeMessage);
            return emailCode;

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return -2;
    }


    /**
     * 用户注册
     */
    @Override
    public Integer userRegister(String username, String password, String email, Integer type,String header){
//        String header = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/"
        User user = new User();
        String uuid = UUID.randomUUID().toString();
        user.setId(uuid);
        user.setUsername(username);
        user.setType(type);
        user.setPassword(password);
        user.setHeader(header);
        user.setEmail(email);
        System.out.println(user);
        Integer insert =userMapper.insert(user);
        return insert;
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Override
    public Integer changeUserInfo(User user){
        int i = userMapper.updateById(user);
        return i;
    }

    @Override
    public ArrayList<User> getAllUser() {
        ArrayList<User> users = userMapper.getAllUser();
        return users;
    }


}
