package com.example.springboot.chonggou.guanchazhe;

public class OrderObserver implements Observer {

    @Override
    public void notify(String orderNo) {
        System.out.println("订单 " + orderNo + " 状态更新为【已支付】");
    }
}