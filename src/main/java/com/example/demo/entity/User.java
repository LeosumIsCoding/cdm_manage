package com.example.demo.entity;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("users")
public class User {
    @TableId(type= IdType.INPUT)
    private String id;

    private String username;

    private String password;

    //    类型
    private Integer type;

    private String email;

//    头像
    private String header;

    //    是否删除
    @TableLogic(value = "0", delval = "1")
    private Integer isdeleted;
}
