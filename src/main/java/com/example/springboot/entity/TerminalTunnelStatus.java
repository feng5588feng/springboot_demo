package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.*;
import java.util.List;

@Data
@Document("TERMINAL_TUNNEL_STATUS")
public class TerminalTunnelStatus implements Serializable {

    public Object deepClone() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            ByteArrayInputStream bais = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Object) ois.readObject();
        } catch (Exception e) {
            System.out.println("克隆出错" + e.getStackTrace());
            return null;
        }
    }

    private String id;
    @Indexed
    @JsonAlias({"clientId", "client_id"})
    private String clientId;
    /**
     * 客户端类型:
     * "appliance": 传统投注终端
     * "software": 软投注终端
     */
    @JsonAlias({"clientType", "client_type"})
    private String clientType;

    private Long utime;
    /**
     * 客户端型号
     */
    private String model;

    /**
     * 模式，含义同策略下发中隧道配置
     */
    private String mode;

    @JsonAlias({"cfgVer", "cfg_ver"})
    private String cfgVer;

    /**
     * 隧道状态
     */
    private Integer tunnelState;

    private List<TunnelDetail> tunnel;


    @JsonAlias({"wanStats", "wan_stat"})
    private List<WanStat> wanStats;
}
