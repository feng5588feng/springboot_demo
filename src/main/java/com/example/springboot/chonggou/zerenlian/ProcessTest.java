package com.example.springboot.chonggou.zerenlian;

import java.util.function.Consumer;

public class ProcessTest {

    public static void main(String[] args) {

        /*Processor p1 = new ProcessorImpl1(null);
        Processor p2 = new ProcessorImpl2(p1);
        p2.process("something happened");*/

        Consumer<String> p1 = param -> System.out.println("processor 1 is processing:" + param);
        Consumer<String> p2 = param -> System.out.println("processor 2 is processing:" + param);
        p2.andThen(p1).accept("something happened");

    }



}
