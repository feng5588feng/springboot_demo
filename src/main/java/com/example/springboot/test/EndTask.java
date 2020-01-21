package com.example.springboot.test;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName
 * @Description
 * @Author tuoww
 * @DATE 2019/12/10
 * @Version V1.0
 **/
public class EndTask implements Runnable{

    private CountDownLatch endTaskLatch;

    public EndTask(CountDownLatch latch) {

        this.endTaskLatch =latch;
    }

    public void run() {

        try {
            System.out.println("开始执行等待任务");
            endTaskLatch.await();
            System.out.println("开始执行最终任务");
            System.out.println(endTaskLatch.getCount());
        }catch(Exception e) {
            e.printStackTrace();

        }
    }
}