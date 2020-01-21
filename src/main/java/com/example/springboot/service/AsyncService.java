package com.example.springboot.service;


import com.example.springboot.service.api.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service("asyncService")
public class AsyncService {

    @Autowired
    DemoService demoService;

    @Async
    public void executorAsyncTask(String name){
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        log.info("执行异步：{}" ,name);
        demoService.testService(name);
    }

}