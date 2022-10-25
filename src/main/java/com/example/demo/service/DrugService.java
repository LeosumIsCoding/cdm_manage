package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Drug;
import org.springframework.stereotype.Service;


public interface DrugService extends IService<Drug> {

    IPage<Drug> selectDrugPage(Page page);
}
