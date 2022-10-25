package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drug {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String code;
    private String name;
    private String classname;
    private String standardconcept;
    private String invalidreason;
    private String domain;
    private String vocabulary;
}
