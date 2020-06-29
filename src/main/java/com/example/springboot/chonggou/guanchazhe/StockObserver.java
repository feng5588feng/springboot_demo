package com.example.springboot.chonggou.guanchazhe;

public class StockObserver implements Observer {

    @Override
    public void notify(String orderNo) {
        System.out.println("订单 " + orderNo + " 已通知库房发货！");
    }
}