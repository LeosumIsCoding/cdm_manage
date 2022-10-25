package com.example.demo.controller;



import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.demo.config.Result;
import com.example.demo.entity.Drug;
import com.example.demo.mapper.DrugMapper;
import com.example.demo.service.DrugService;
import com.example.demo.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;


@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/drug")
public class DrugController {

    @Autowired
    private DrugService drugService;

    @Autowired
    private DrugMapper drugMapper;

    @PostMapping
    public List<Drug> getDrugs(String tableName,Integer page,Integer pageSize){
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("pageSize",pageSize);
        map.put("tableName",tableName);
        List<Drug> pageByName = drugMapper.getPageByName(map);
        return pageByName;
    }


    @PostMapping("/total")
    public Result<Integer> getTotalCount(String tableName){
        HashMap<String, Object> map = new HashMap<>();  //需要map形式传参
        map.put("tableName",tableName);
        Integer pageCount = drugMapper.getPageCount(map);
        return ResultUtils.success(pageCount);
    }

    @GetMapping("/export/{tableName}")
    public void exportInfo(HttpServletResponse response,@PathVariable("tableName") String tableName) throws Exception{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(tableName+"表");
        String fileName = tableName+ ".xls";
        // 新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = { "ID", "Code", "Name", "ClassName","StandardConcept","Invalidreason","Domain","Vocablulary"};
        HSSFRow row = sheet.createRow(0);
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

//        从数据库中查询所有的
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("tableName",tableName);
        List<Drug> list = drugMapper.getListByDrugName(hashMap);
//        System.out.println(list);

        //在表中存放查询到的数据放入对应的列
        for (Drug item : list) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(item.getId());
            row1.createCell(1).setCellValue(item.getCode());
            row1.createCell(2).setCellValue(item.getName());
            row1.createCell(3).setCellValue(item.getClassname());
            row1.createCell(4).setCellValue(item.getStandardconcept());
            row1.createCell(5).setCellValue(item.getInvalidreason());
            row1.createCell(6).setCellValue(item.getDomain());
            row1.createCell(7).setCellValue(item.getVocabulary());
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        OutputStream outputStream = response.getOutputStream();
        workbook.write(response.getOutputStream());
        outputStream.flush();
        outputStream.close();

    }


}
