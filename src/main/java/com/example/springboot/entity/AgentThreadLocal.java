package com.example.springboot.entity;

public class AgentThreadLocal {

    private AgentThreadLocal(){
    }
    public static final ThreadLocal<Thread> LOCAL = new ThreadLocal<>();

    public static void set(Thread thread){
        LOCAL.set(thread);
    }

    public static Thread get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }
}