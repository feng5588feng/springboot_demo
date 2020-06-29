package com.example.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 抽象实体父类
 *
 * @author PENG.W
 * @version 1.0, 2019-09-19 18:09:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {

    /** 主键 */
    protected Long id;

    protected Date createTime;

    protected  Date updateTime;


}
