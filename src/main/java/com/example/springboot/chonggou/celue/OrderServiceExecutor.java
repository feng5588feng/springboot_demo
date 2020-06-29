package com.example.springboot.chonggou.celue;

public class OrderServiceExecutor {
 
    private OrderService service;
 
    public OrderServiceExecutor(OrderService service) {
        this.service = service;
    }
 
    public void save(String orderNo) {
        this.service.saveOrder(orderNo);
    }
 
}