package com.example.springboot.service.impl;

import com.example.springboot.service.api.DemoService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.0.0", timeout = 3000, retries = 0)
public class DemoServiceImpl implements DemoService {

    @Override
    public String testService(String inP) {
        System.out.println("impl执行：" + inP);
        return "11111111111";
    }
}
