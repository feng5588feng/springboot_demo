package com.example.springboot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Map;

@Data
@Document(collection = "TSG000")
public class Ssg100Config implements Serializable {

    private static final long serialVersionUID = -4682210750808593947L;

    @org.springframework.data.annotation.Id
    @JSONField(serialize=false)
    private String Id;

    @Field("serial")
    @JSONField(name = "serial")
    private String serial;

    @Field("ver")
    @JSONField(name = "ver")
    private Integer ver;

    @Field("srv_tunnel")
    @JSONField(name = "srv_tunnel")
    private Map<String,Object> srvTunnel;

    @Field("upg")
    @JSONField(name = "upg")
    private Map<String,Object> upg;

    @Field("agent")
    @JSONField(name = "agent")
    private Map<String,Object> agent;

    @Field("stat")
    @JSONField(name = "stat")
    private Map<String,Object> stat;

    @Field("master_man_channel")
    @JSONField(name = "master_man_channel")
    private ManChannelModule masterManChannel;

    @Field("ssg100_module_version")
    @JSONField(name = "ssg100_module_version")
    private Ssg100ModuleVersion ssg100ModuleVersion;
}
