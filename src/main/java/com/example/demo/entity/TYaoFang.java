package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TYaoFang {


    int lid;

    String title;

    String cases;

    ArrayList<Made> mades;

    ArrayList<String> addReduce;

    ArrayList<String> solutions;

    ArrayList<Medicine> prescriptionMedicine;

}
