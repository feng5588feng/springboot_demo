package com.example.springboot.config.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplateConfig
 * http和https连接加载类
 *
 * @author tuoww
 * DATE 2020/4/28 15:44
 * @version V1.0
 **/
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate getHttpGetClient(){
        SslClientHttpRequestFactory clientFactory = new SslClientHttpRequestFactory();
        clientFactory.setConnectTimeout(5000); //建立连接的超时时间  5秒
        clientFactory.setReadTimeout(5000); // 传递数据的超时时间（在网络抖动的情况下，这个参数很有用）
        return new RestTemplate(clientFactory);
    }

}
