package com.example.springboot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
public class Ssg100ModuleVersion implements Serializable {

    private static final long serialVersionUID = 4238101588533733674L;

    public Ssg100ModuleVersion() {
        srvTunnel = 1;
        upg = 1;
        agent = 1;
        masterManChannel = 1;
        slaveManChannel = 1;
        stat = 1;
    }

    @Field("srv_tunnel")
    @JSONField(name = "srv_tunnel")
    private Integer srvTunnel;

    @Field("upg")
    @JSONField(name = "upg")
    private Integer upg;

    @Field("agent")
    @JSONField(name = "agent")
    private Integer agent;

    @Field("master_man_channel")
    @JSONField(name = "master_man_channel")
    private Integer masterManChannel;

    @Field("slave_man_channel")
    @JSONField(name = "slave_man_channel")
    private Integer slaveManChannel;

    @Field("stat")
    @JSONField(name = "stat")
    private Integer stat;

}
