package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.Drug;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DrugMapper extends BaseMapper<Drug> {

    @Select("select * from ${tableName} limit #{pageSize} offset #{page}")
    List<Drug> getPageByName(Map<String, Object> map);

    @Select("select count(*) from ${tableName}")
    Integer getPageCount(Map<String, Object> map);

    @Select("select * from ${tableName}")
    List<Drug> getListByDrugName(Map<String, Object> map);
}
