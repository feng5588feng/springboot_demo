package com.example.springboot.test;

import com.example.springboot.entity.RegisterInfo;

import java.util.HashMap;
import java.util.Map;

public class TTest {

    public static void main(String[] args) {


        try {
            System.out.println(222);
            return;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(1111);
        }
        /*Map<String, Object> ret = new HashMap<>();

        RegisterInfo info = new RegisterInfo();
        info.setCert("1111");
        info.setCertId("1212132");
        try {
            ret.put("old", info.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        info.setCertId("ssssssssss");
        ret.put("new", info);
        System.out.println(ret.toString());*/


    }
}
