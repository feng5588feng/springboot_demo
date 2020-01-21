package com.example.springboot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @ClassName ManChannelAddr
 * @Description 管理通道地址
 * @Author tuoww
 * @DATE 2019/11/20
 * @Version V1.0
 **/
@Data
public class ManChannelAddr implements Serializable {

    private static final long serialVersionUID = -6549292139652303608L;

    @Field("engineRoomAddressId")
    @JSONField(name = "engineRoomAddressId")
    private Long engineRoomAddressId;//机房地址数据库id

    @JSONField(name = "url")
    @Pattern(regexp = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$",message = "url不正确")
    private String url;//URL地址,比如：https://2.2.3.4

    @JSONField(name = "port")
    private Integer port;//服务端口

    @JSONField(name = "operator")
    private String operator;//运行商

    @Min(value = 0,message = "优先级不能小于0")
    @Max(value = 999,message = "优先级不能大于999")
    @Field("priority")
    private Integer priority;//优先级：先匹配运营商，在匹配同一运营商下多个IP的不同优先级，取值越低优先级越高

    @Field("disable")
    private Integer disable;//是否启用 1启用 0禁用
}
