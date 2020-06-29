package com.example.springboot.chonggou.guanchazhe;

public interface Subject {
    void registerObserver(Observer o);
    void notifyAllObserver(String orderNo);
}