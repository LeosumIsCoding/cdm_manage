package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Drug;
import com.example.demo.mapper.DrugMapper;
import com.example.demo.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class DrugServiceImpl extends ServiceImpl<DrugMapper, Drug> implements DrugService {

    @Autowired
    private DrugMapper drugMapper;

    @Override
    public IPage<Drug> selectDrugPage(Page page){
        IPage<Drug> drugIPage = drugMapper.selectPage(page,null);
        return drugIPage;
    }
}
