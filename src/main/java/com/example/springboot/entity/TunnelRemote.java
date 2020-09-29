package com.example.springboot.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * TunnelRemote
 * 备用隧道接入地址对象中的remote子对象
 *
 * @author tww
 * DATE 2020/6/4
 * @version V1.0
 **/
@Data
public class TunnelRemote implements Serializable {

    /**
     * 隧道server端地址或域名：
     */
    private String addr;

    /**
     * 运营商
     */
    private String operator;

    /**
     * 优先级
     */
    private Integer priority;


}
