package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * ClassName
 * Description
 *
 * @author tuoww
 * DATE 2020/01/21
 * @version V1.0
 **/
@RestController
@RequestMapping("thread")
public class ThreadController {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    /**
     *
     * @return
     */
    @GetMapping("test")
    public String threadTest() {
        taskExecutor.execute(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("***************************");
                System.out.println(Thread.currentThread().getName());
                System.out.println("***************************");
            }
        });
        System.out.println("***********threadTest***********");
        return "success";
    }

    /**
     * @Date:     2018/10/22
     * @describe: 有参返回方法
     * @param null :
     * @return : null
     * @throws:
     */
    @GetMapping("hasReturn")
    public String hasReturn() throws ExecutionException, InterruptedException {
        Future<String> future = taskExecutor.submit(new Callable<String>() {
            public String call() throws Exception {
                Thread.sleep(3000);
                System.out.println("***************************");
                System.out.println(Thread.currentThread().getName());
                System.out.println("***************************");
                return "执行成功";
            }
        });
        System.out.println("***********hasReturn***********");
        String returnStr = future.get();
        System.out.println("***********end***********");
        return "success";
    }

}

