package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(value = "id",type = IdType.AUTO)
    protected Long id;

    @TableField(value = "create_time")
    protected Date createTime;

    @TableField(value = "update_time")
    protected  Date updateTime;


}

