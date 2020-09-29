package com.example.springboot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * SrvTunnelModule
 * 业务隧道配置模块对象
 *
 * @author tww
 * DATE 2020/6/4
 * @version V1.0
 **/
@Data
public class SrvTunnelModule implements Serializable {

    @Field("ver")
    @JSONField(name = "ver")
    private Integer ver;

    @Field("nat")
    @JSONField(name = "nat")
    private Integer nat;

    @Field("mode")
    @JSONField(name = "mode")
    private Integer mode;

    @Field("ca_crt")
    @JsonProperty("ca_crt")
    @JSONField(name = "ca_crt")
    private String caCrt;

    @Field("tunnel_cnt")
    @JsonProperty("tunnel_cnt")
    @JSONField(name = "tunnel_cnt")
    private Integer tunnelCnt;

    @Field("tunnel")
    @JSONField(name = "tunnel")
    private List<TunnelConfig> tunnel;
}
