package com.example.springboot.chonggou.guanchazhe;

import java.util.ArrayList;
import java.util.List;

public interface NewSubject {

    List<Observer> list = new ArrayList<>();

    default void registerObserver(Observer o) {
        list.add(o);
    }

    default void nofityAllObserver(String orderNo) {
        list.forEach(c -> c.notify(orderNo));
    }
}