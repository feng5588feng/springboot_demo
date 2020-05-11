package com.example.springboot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.*;

/**
 * TerminalNetstat
 * 设备Netstat信息上报对象
 *
 * @author tuoww
 * DATE 2020/4/29 18:09
 * @version V1.0
 **/
@Data
@Document(collection = "TERMINAL_NETSTAT")
public class TerminalNetstat implements Serializable {

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

    private static final long serialVersionUID = -1717657480776835116L;

    @JsonProperty(value="client_id")
    @Indexed
    private String clientId;

    @JSONField(name = "netstat")
    private Object netstat;

    private Long utime;
}
