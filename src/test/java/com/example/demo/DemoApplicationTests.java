package com.example.demo;

import com.example.demo.controller.YaofangController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }
@Autowired
    private YaofangController yaofangController;

    @Test
    void TestYaofangController(){
//        System.out.println(yaofangController.handleAddReduce("【加减】心烦易怒、心悸头晕者，加百合15克、当归10克； 纳差呕吐甚，加砂仁10克；小腹微痛伴腰酸腰痛者，加炒杜仲 15克、炒艾叶10克。\\n").get(0));

//        System.out.println(yaofangController.handleMade("【组成】炒白术10克，白茯苓10克，陈皮10克，白芍10 克，黄苓10克，黄连10克（捣碎），苏梗10克，制半夏10克， 炒菟丝子15克（捣碎），桑寄生15克，川续断15克，阿胶10 克（洋化兑服）。\\n"));
//        System.out.println(yaofangController.handleSolutions("【方解】方中白术健脾渗湿；苏梗、陈皮、白芍和胃抑肝理 气；黄连、黄苓苦寒清热降胃；半夏豁痰降逆止呕；菟丝、川 断、寄生、阿胶安胎、养胎、补肾系胎。诸药共奏抑肝降逆、止 呕安胎之功也。\\n").get(0));
        yaofangController.handlePrescriptionMedicine("【方药】炒白术10克，白茯苓10克，陈皮10克，制半夏 10克，苏梗10克，黄连10克（捣碎）,黄苓10克，梔子10克 （捣碎），当归10克，党参10克，炒菟丝子15克（捣碎），川续 断15克，桑寄生15克。\\n【方药】当归10克，制半夏10克，陈皮10克，党参10克， 砂仁10克（捣碎），黄苓10克，黄连10克（捣碎），苏梗10 克，炒菟丝子15克（捣碎），川续断15克，桑寄生15克，阿胶 10克（律化兑服）。\\n【方药】炒白芍15克，枳壳10克，炒白术10克，砂仁6克 （捣碎），黄连10克（捣碎），苏梗10克，当归10克，制半夏 10克，桑寄生15克，川续断15克，炒菟丝子15克（捣碎）， 阿胶10克（洋化兑服）。\\n【方药】炒白芍15克，白茯苓10克，制半夏10克，砂仁 10克（捣碎），炒白术10克，当归10克，党参10克，黄连10 克（捣碎），苏梗10克，升麻6克，炒菟丝子15克（捣碎），川 续断15克，桑寄生15克，阿胶10克（烽化兑服）。\\n【方药】炒白术10克，白茯苓10克，制半夏10克，砂仁 10 （捣碎），陈皮10克，苏叶10克，黄连10克（捣碎），柴胡 10克，桑寄生15克，川续断15克，炒菟丝子15克（捣碎）。\\n");
    }

}
