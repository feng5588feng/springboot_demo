package com.example.springboot.service;

public interface FilterProcess<T> {
    boolean process(T t);
}