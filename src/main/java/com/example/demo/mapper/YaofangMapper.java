package com.example.demo.mapper;

import com.example.demo.entity.yaofang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface YaofangMapper {

    @Select("select lid,title,cases,made,add_reduce,solution,prescription_medicine from lzyf")
    public ArrayList<yaofang> getAllYaofang();
}
