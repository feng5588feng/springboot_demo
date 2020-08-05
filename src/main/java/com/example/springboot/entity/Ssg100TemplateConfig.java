package com.example.springboot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName Ssg100TemplateConfig
 * @Description  Ssg100终端模板配置实体类，对应mongo中的数据
 * @Author tuoww
 * @DATE 2019/11/15
 * @Version V1.0
 **/
@Data
@Document(collection = "TEMPLATE")
public class Ssg100TemplateConfig implements Serializable {

    private static final long serialVersionUID = -2752333778277622666L;

    @Id
    @JSONField(name = "_id")
    private String id;

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

    @Field("mmc_load_time")
    @JSONField(name = "mmc_load_time")
    private Date mmcLoadTime;
}
