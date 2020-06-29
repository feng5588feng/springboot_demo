package com.example.springboot.service.impl;

import com.example.springboot.entity.AgentThreadLocal;
import com.example.springboot.service.api.DemoService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.0.0", timeout = 3000, retries = 0)
public class DemoServiceImpl implements DemoService {

    //private static ThreadLocal<Thread> tCache = new ThreadLocal<>();

    @Override
    public String testService(String inP) {
        System.out.println("impl执行：" + inP);
        return "11111111111";
    }

    public void testThread() {
        System.out.println("AgentThreadLocal.LOCAL" + AgentThreadLocal.LOCAL);
        //停线程
        if (AgentThreadLocal.get() != null) {
            Thread thread = AgentThreadLocal.get();
            System.out.println("tCache获取到id:" + thread.getId());
            thread.interrupt();
            AgentThreadLocal.remove();
        }
        //新起线程
        Thread th = new Thread(() -> {
            while (true) {
                try {
                    //Thread.sleep(1000);

                    System.out.println("运行线程----" + Thread.currentThread().getId());
                    if (Thread.currentThread().isInterrupted()) {
                        //处理终端逻辑
                        System.out.println("中断线程---" + Thread.currentThread().getId());
                        break;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    AgentThreadLocal.remove();
                }

            }
        });
        th.start();
        System.out.println("当前执行线程id:" + th.getId());
        //添加到ThreadLocal
        AgentThreadLocal.set(th);
        System.out.println(AgentThreadLocal.get());
    }
}
