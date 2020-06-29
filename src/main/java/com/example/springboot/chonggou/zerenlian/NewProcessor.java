package com.example.springboot.chonggou.zerenlian;

import java.util.function.Consumer;

@FunctionalInterface
public interface NewProcessor {

    Consumer<String> process(String param);
}