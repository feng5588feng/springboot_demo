package com.example.springboot.chonggou.mobanfangfa;

public class PushScoreTemplate extends AbstractPushTemplate {
 
    @Override
    protected void execute(int customerId, String shopName) {
        System.out.println("会员:" + customerId + ",你好，" + shopName + "送您10个积分");
    }
}