package com.example.springboot.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;

@Data
@TableName(autoResultMap = true, value = "test_json")
public class TestJson extends BaseEntity {

    /** 分组名 */
    @TableField(value = "name")
    private String name;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private JSONObject data;

}
