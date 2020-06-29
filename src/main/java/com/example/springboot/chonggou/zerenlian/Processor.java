package com.example.springboot.chonggou.zerenlian;

public interface Processor {
 
    Processor getNextProcessor();
 
    void process(String param);
}