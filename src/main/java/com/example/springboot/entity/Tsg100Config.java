package com.example.springboot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.springboot.entity.ManChannelModule;
import com.example.springboot.entity.SrvTunnelModule;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName Tsg100Config
 * @Description  TSG100配置实体类，对应mongo中的数据
 * @Author tuoww
 * @DATE 2019/11/15
 * @Version V1.0
 **/
@Data
@Document(collection = "TSG100")
public class Tsg100Config implements Serializable {

    private static final long serialVersionUID = -1250190247926075244L;

    @Id
    @JSONField(serialize=false)
    private String Id;

    @Field("serial")
    @JSONField(name = "serial")
    private String serial;

    @Field("groupId")
    @JSONField(name = "groupId")
    private String groupId;

    @Field("templateId")
    @JSONField(name = "templateId")
    private String templateId;

    @Field("ver")
    @JSONField(name = "ver")
    private Integer ver;

    @Field("wireless")
    @JSONField(name = "wireless")
    private Map<String,Object> wireless;

    @Field("lan")
    @JSONField(name = "lan")
    private Map<String,Object> lan;

    @Field("wifidog")
    @JSONField(name = "wifidog")
    private Map<String,Object> wifidog;

    @Field("qos")
    @JSONField(name = "qos")
    private Map<String,Object> qos;

    @Field("srv_tunnel")
    @JSONField(name = "srv_tunnel")
    private SrvTunnelModule srvTunnel;

    @Field("pppoe")
    @JSONField(name = "pppoe")
    private Map<String,Object> pppoe;

    @Field("cipher_srv")
    @JSONField(name = "cipher_srv")
    private Map<String,Object> cipherSrv;

    @Field("nac")
    @JSONField(name = "nac")
    private Map<String,Object> nac;

    @Field("wan")
    @JSONField(name = "wan")
    private Map<String,Object> wan;

    @Field("tmc")
    @JSONField(name = "tmc")
    private Map<String,Object> tmc;

    @Field("upg")
    @JSONField(name = "upg")
    private Map<String,Object> upg;

    @Field("agent")
    @JSONField(name = "agent")
    private Map<String,Object> agent;

    @Field("slog")
    @JSONField(name = "slog")
    private Map<String,Object> slog;

    @Field("stat")
    @JSONField(name = "stat")
    private Map<String,Object> stat;

    @Field("master_man_channel")
    @JSONField(name = "master_man_channel")
    private ManChannelModule masterManChannel;

    @Field("mmc_load_time")
    @JSONField(name = "mmc_load_time")
    private Date mmcLoadTime;

}
