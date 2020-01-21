package com.example.springboot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName MasterManChannelModule
 * @Description 管理通道配置，主从内容一样
 * @Author tuoww
 * @DATE 2019/11/20
 * @Version V1.0
 **/
@Data
public class ManChannelModule implements Serializable {

    private static final long serialVersionUID = 7389115998216894463L;

    @Field("ver")
    @JSONField(name = "ver")
    private Integer ver;

    @Field("sort_method")
    @JSONField(name = "sort_method")
    private Integer sortMethod;

    @Field("addr_cnt")
    @JSONField(name = "addr_cnt")
    private Integer addrCnt;

    @Field("addr")
    @JSONField(name = "addr")
    private List<ManChannelAddr> addr;
}
