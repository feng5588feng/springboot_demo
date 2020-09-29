package com.example.springboot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * TunnelConfig
 * 备用隧道接入地址对象
 *
 * @author tww
 * DATE 2020/6/8
 * @version V1.0
 **/
@Data
public class TunnelConfig implements Serializable {

    /**
     * 隧道是否启用
     */
    private Integer enabled;

    /**
     * 隧道优先级：只在mode为主备时起作用，值越小优先级越高
     */
    private Integer priority;

    /**
     * 设备名，必须是tunx，需取值数字0-3
     */
    private String dev;

    /**
     * 隧道接口mtu,隧道口mtu+openvpn隧道头部开销(42byte)<=wan口mtu，小于1500
     */
    private Integer mtu;

    /**
     * tcp/udp
     */
    private String proto;

    /**
     * 隧道服务器侧端口
     */
    private Integer port;

    /**
     * tcp端口
     */
    private Integer tport;

    /**
     * 加密方式
     */
    private String cipher;

    /**
     * 消息认证算法，如：SHA256/SHA384/SHA512/SM3，必须同步修改TSG1000配置
     */
    private String auth;

    /**
     * IP排序方式决定了终端访问特定机房的顺序：
     * 0：先按运行商，再按优先级排序，默认0
     * 1：先按优先级，再按运营商排序
     * 比如灾备机房场景，主机房的一组IP优先级要高于灾备机房的一组IP；
     * 比如双活场景，TAC根据TAS负载均衡，对不同TSG使用的同一组IP设置不同的优先级，根据双活机房针对同一运营山商否有多个IP，灵活选择排序方式
     */
    @Field("sort_method")
    @JsonProperty("sort_method")
    @JSONField(name = "sort_method")
    private Integer sortMethod;

    /**
     * 隧道server端地址个数
     */
    @Field("remote_cnt")
    @JsonProperty("remote_cnt")
    @JSONField(name = "remote_cnt")
    private Integer remoteCnt;

    @Field("remote")
    @JSONField(name = "remote")
    private List<TunnelRemote> remote;


}
