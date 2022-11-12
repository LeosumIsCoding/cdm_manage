package com.example.demo.controller;


import com.example.demo.entity.Made;
import com.example.demo.entity.Medicine;
import com.example.demo.entity.TYaoFang;
import com.example.demo.entity.yaofang;
import com.example.demo.mapper.YaofangMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/yaofang")@RestController
@CrossOrigin
@Slf4j
public class YaofangController {


    @Autowired
    public YaofangMapper yaofangMapper;

    @GetMapping("/get/allYaofang")
    @ResponseBody
    public ArrayList<TYaoFang> getAllYaofang(){

        ArrayList<yaofang> yaofangs = this.yaofangMapper.getAllYaofang();
        ArrayList<TYaoFang> tYaoFangs = new ArrayList<TYaoFang>();

        yaofangs.forEach(yaofang -> {
            TYaoFang tYaoFang = new TYaoFang();
            tYaoFang.setLid(yaofang.getLid());
            tYaoFang.setTitle(yaofang.getTitle().replace("\\n",""));

            tYaoFang.setCases(yaofang.getCases());
            tYaoFang.setMades(this.handleMade(yaofang.getMade()));
            tYaoFang.setAddReduce(this.handleAddReduce(yaofang.getAddReduce()));
            tYaoFang.setSolutions(this.handleSolutions(yaofang.getSolution()));
            tYaoFang.setPrescriptionMedicine(this.handlePrescriptionMedicine(yaofang.getPrescriptionMedicine()));
            tYaoFangs.add(tYaoFang);
        });

        return tYaoFangs;

    }

    public ArrayList<Medicine> handlePrescriptionMedicine(String prescriptionMedicine) {
        ArrayList<Medicine> medicines = new ArrayList<Medicine>();
        String[] fangyao = prescriptionMedicine.split("\\n");
        for(int i = 0; i< fangyao.length; i++){
            try{
                ArrayList<Made> mades = new ArrayList<Made>();
                String fangyaoo = fangyao[i];
                fangyaoo = fangyaoo.replace("【方药】","");
                fangyaoo = fangyaoo.replace("克","");
                fangyaoo = fangyaoo.replace("\\","");
                fangyaoo = fangyaoo.replace(",", "，");
                fangyaoo = fangyaoo.replace("。", "");

                String[] fangyaos = fangyaoo.split("，");


                for(int j = 0; j<fangyaos.length; j++){
                    String com = fangyaos[j];
                    String regEx="[^0-9]";
                    Pattern p = Pattern.compile(regEx);
                    Matcher m = p.matcher(com);
                    int value = Integer.parseInt(m.replaceAll("").trim());
                    String name = com.replaceAll("[0-9]","");
                    Made made1 = new Made();
                    made1.setName(name);
                    made1.setValue(value);
                    mades.add(made1);
                }
                Medicine medicine = new Medicine();
                medicine.setMades(mades);
                medicines.add(medicine);
            }catch (Exception e){
                continue;
            }
        }
        System.out.println(medicines);

        return medicines;
    }

    public ArrayList<String> handleSolutions(String solution) {
        solution = solution.replace("【方解】", "");
        solution = solution.replace("\\n", "");
        solution = solution.replace(";", "；");
        ArrayList<String> solutionss= new ArrayList<String>();
        String[] solutions = solution.split("；");
        for(int i = 0; i<solutions.length; i++){
            solutionss.add(solutions[i]);
        }
        return solutionss;
    }

    public ArrayList<Made> handleMade(String made) {
        made = made.replace("【组成】", "");
        made = made.replace("\\n", "");
        made = made.replace(",", "，");
        made = made.replace("。","");
        made = made.replace("克", "");
        String[] mades = made.split("，");
        ArrayList<Made> mades1 = new ArrayList<Made>();
        for(int i = 0; i<mades.length;i++){
            try{
                //            System.out.println(mades[i]);
                String com = mades[i];
                String regEx="[^0-9]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(com);
                int value = Integer.parseInt(m.replaceAll("").trim());
                String name = com.replaceAll("[0-9]","");
//            System.out.println(name +":"+value);
                Made made1 = new Made();
                made1.setName(name.replace("\\n",""));
                made1.setValue(value);
                mades1.add(made1);
            }catch (Exception e){
                continue;
            }
        }
        System.out.println(mades1);
        return mades1;
    }

    public ArrayList<String> handleAddReduce(String addReduce) {
        addReduce = addReduce.replace("【加减】", "");
        addReduce = addReduce.replace("\\n", "");
        addReduce = addReduce.replace(",", "，");
        addReduce = addReduce.replace(".", "");
        String[] addreduces = addReduce.split("；");

        ArrayList<String> addreducess = new ArrayList<String>();
        for(int i = 0; i<addreduces.length; i++){
            System.out.println(addreduces[i]);
            addreducess.add(addreduces[i].replace("\\n",""));
        }
        return addreducess;
    }
}
