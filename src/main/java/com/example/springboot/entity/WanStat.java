package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.io.Serializable;

/**
 * Description: wan口收发统计
 *
 * @author zzw
 * Date: 2019/12/23 15:24
 */
@Data
public class WanStat implements Serializable {

    private static final long serialVersionUID = 2289688018653933174L;

    /**
     * 接口名称
     */
    @JsonAlias({"devName","dev_name"})
    private String devName;

    /**
     * 发包个数当隧道未建立时为空
     */
    @JsonAlias({"txPackets","tx_packets"})
    private Long txPackets;

    /**
     * 收包个数当隧道未建立时为空
     */
    @JsonAlias({"rxPackets","rx_packets"})
    private Long rxPackets;


}
