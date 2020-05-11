package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.io.Serializable;

/**
 * Description: 隧道详细信息
 *
 * @author zzw
 * Date: 2019/12/23 15:24
 */
@Data
public class TunnelDetail implements Serializable {

    private String id;

    /**
     * 设备名
     */
    private String dev;

    /**
     * 隧道状态 链接状态
     */
    private String state;

    /**
     * 隧道ping值，当隧道未建立时为空
     */
    @JsonAlias({"pingCnt","ping_cnt"})
    private String pingCnt;

    /**
     * 隧道丢包值，当隧道未建立时为空
     */
    @JsonAlias({ "pingLost", "ping_lost" })
    private String pingLost;


    /**
     * 隧道本地WAN IP地址，当隧道未建立时为空
     */
    @JsonAlias({"localWanIp","local_wan_ip"})
    private String localWanIp;

    /**
     * 隧道远端WAN IP地址，指示和哪个网关建立了隧道，当隧道未建立时为空
     */
    @JsonAlias({"remoteWanIp","remote_wan_ip"})
    private String remoteWanIp;

    /**
     * 隧道本地接口IP地址，当隧道未建立时为空
     */
    @JsonAlias({"localTunIp","local_tun_ip"})
    private String localTunIp;

    /**
     * 隧道远端接口IP地址，当隧道未建立时为空
     */
    @JsonAlias({"remoteTunIp","remote_tun_ip"})
    private String remoteTunIp;

    /**
     * 收包个数：增量值，建议TAS负责统计求和
     */
    @JsonAlias({"rxPackets","rx_packets"})
    private Long rxPackets;

    /**
     * 发包个数：增量值，建议TAS负责统计求和
     */
    @JsonAlias({"txPackets","tx_packets"})
    private Long txPackets;
}
