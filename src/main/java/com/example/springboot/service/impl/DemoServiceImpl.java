package com.example.springboot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot.dao.TestJsonMapper;
import com.example.springboot.entity.AgentThreadLocal;
import com.example.springboot.entity.TestJson;
import com.example.springboot.service.api.DemoService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0", timeout = 3000, retries = 0)
public class DemoServiceImpl implements DemoService {

    //private static ThreadLocal<Thread> tCache = new ThreadLocal<>();


    private static Thread th;


    @Autowired
    private TestJsonMapper testJsonMapper;

    @Override
    public String testService(String inP) {
        System.out.println("impl执行：" + inP);


        TestJson testJson = testJsonMapper.selectById(1);
        System.out.println(testJson.toString());

        TestJson testJson1 = new TestJson();
        JSONObject data1 = new JSONObject();
        data1.put("name", "aaa");
        data1.put("age", 15);
        data1.put("money", 1000);
        testJson1.setName("aaa");
        testJson1.setData(data1);
        testJson1.setId(null);
        testJsonMapper.insert(testJson1);









        return "11111111111";
    }

    public void testThread() {
        //System.out.println("AgentThreadLocal.LOCAL" + AgentThreadLocal.LOCAL);
        //停线程
        /*if (AgentThreadLocal.get() != null) {
            Thread thread = AgentThreadLocal.get();
            System.out.println("tCache获取到id:" + thread.getId());
            thread.interrupt();
            AgentThreadLocal.remove();
        }*/
        if(th != null && !th.isInterrupted()){
            th.interrupt();
            th.stop();
            th = null;

        }

        th = new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    Thread.sleep(1000);

                    System.out.println("运行线程----" + Thread.currentThread().getId());
                    System.out.println("i----" + i++);
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
        //新起线程

        th.start();
        System.out.println("当前执行线程id:" + th.getId());
        //添加到ThreadLocal
        //AgentThreadLocal.set(th);
        //System.out.println(AgentThreadLocal.get());
    }
}
