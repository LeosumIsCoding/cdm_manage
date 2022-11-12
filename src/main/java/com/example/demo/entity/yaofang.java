package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class yaofang {
    int lid;
    String title;
    String cases;
    String made;
    String addReduce;
    String solution;
    String prescriptionMedicine;
}
