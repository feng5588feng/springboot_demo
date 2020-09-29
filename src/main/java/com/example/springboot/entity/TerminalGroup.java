package com.example.springboot.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @Description 终端分组
 * @Author tuoww
 * @DATE 2019/10/30
 * @Version V1.0
 **/
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "terminal_group", autoResultMap = true)
public class TerminalGroup extends BaseEntity implements Cloneable {

    /**
     * 分组名
     */
    @NotNull(message = "分组名称不能为空")
    @TableField(value = "name")
    private String name;

    /**
     * 父分组id
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 层次编码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 终端软件表id
     */
    @TableField(value = "terminal_soft_id")
    private Long terminalSoftId;

    /**
     * 终端秘钥tokenkey
     */
    @TableField(value = "token_key")
    private String tokenKey;

    /**
     * 安全模块id
     */
    @TableField(value = "security_template_id")
    private Long securityTemplateId;

    @TableField(typeHandler = FastjsonTypeHandler.class, value = "template_data")
    private JSONObject templateData;

    /**
     * 是否是默认分组，1是 0否
     */
    @TableField(value = "def")
    private Integer def;

    /**
     * 是否已删除:0使用中 1已删除
     */
    @TableLogic
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @Override
    public Object clone() {
        com.example.springboot.entity.TerminalGroup info = null;
        try {

            info = (com.example.springboot.entity.TerminalGroup) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return info;
    }

}
