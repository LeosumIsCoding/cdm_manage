package com.example.demo.controller;


import com.example.demo.config.Result;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.ResultUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    DefaultKaptcha Kaptcha;
    @Autowired
    private UserService userService;
    //    codeMap
    private Map<String, String> codeMap = new HashMap<>();

    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    public final static String UPLOAD_PATH_PREFIX = "static/";

    private String tempImg;


    /**
     * 验证码
     *
     * @return
     */
//    验证码
    @RequestMapping("/code")
    public void createrCode(HttpServletResponse response, String keyCode) throws IOException {
        if (keyCode != null) {
            String code = Kaptcha.createText();
            codeMap.put(keyCode, code);

//            System.out.println("code:"+code);
//        生成一个图片
            BufferedImage image = Kaptcha.createImage(code);

            ImageIO.write(image, "jpg", response.getOutputStream());
        }
    }


    /**
     * 用户登录
     * @param username
     * @param password
     * @param keyCode
     * @param type
     * @param code
     * @return
     */
    @PostMapping("/login")
    public User userLogin(String username, String password, String keyCode, Integer type, String code) {
        String codeVal = "4536";
        User loginUser = userService.userLogin(username, password, type);
        System.out.println(loginUser);

        if (loginUser != null) {
            if (codeVal.equals(code)) {
                return loginUser;
            } else {
                User errUser = new User();
                errUser.setUsername("-1");
                return errUser;
            }

        } else {
            return null;
        }
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result<Integer> register(String username,String password,String email,Integer type,HttpServletRequest request){
        String header =  request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/user.png";
        Integer integer = userService.userRegister(username, password,email, type,header);
        return ResultUtils.success(integer);
    }


    /**
     * 邮箱发送验证码
     * @return
     */
    @PostMapping("/sendEamil")
    public Result<Integer> sendEamil(String username,String email){

        return ResultUtils.success(4536);
    }


    @PostMapping("/changeInfo")
    public Result<Integer> changeUserInfo(User user){
        Integer integer = userService.changeUserInfo(user);
        return ResultUtils.success(integer);


    }

    /**
     * 上传文件
     * @param file
     * @param request
     * @return
     * @throws FileNotFoundException
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws FileNotFoundException {
        if(file.isEmpty()){
            //返回选择文件提示
            return "请选择上传文件";
        }

        //构建文件上传所要保存的"文件夹路径"--这里是相对路径，保存到项目根路径的文件夹下
        String realPath = new String("src/main/resources/" + UPLOAD_PATH_PREFIX);

        //存放上传文件的文件夹
        File uploadFile = new File(realPath);
        if(!uploadFile.isDirectory()){
            //递归生成文件夹
            uploadFile.mkdirs();
        }
        //获取原始的名字  original:最初的，起始的  方法是得到原来的文件名在客户机的文件系统名称
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."),oldName.length());
        try {
            //构建真实的文件路径
            File newFile = new File(uploadFile.getAbsolutePath() + File.separator + newName);
            //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
            file.transferTo(newFile);
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" +  newName;
            tempImg = filePath;
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败!";

    }
}
