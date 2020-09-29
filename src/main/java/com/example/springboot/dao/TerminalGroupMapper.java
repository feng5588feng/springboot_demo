package com.example.springboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.TerminalGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName TerminalGroupMapper
 * Description 终端分组Mapper
 *
 * @author tww
 * DATE 2019/11/07
 * @version V1.0
 **/
@Mapper
@Repository(value = "terminalGroupMapper")
public interface TerminalGroupMapper extends BaseMapper<TerminalGroup> {

    @Select({"<script>",
            "SELECT count(1) FROM terminal_group where is_deleted = 0 ",
            "<when test='terminalType != null and terminalType == 0'>",
            "and json_extract(template_data,'$.tsg100') = #{templateId} ",
            "</when>",
            "<when test='terminalType != null and terminalType == 1'>",
            "and json_extract(template_data,'$.tsg500') = #{templateId} ",
            "</when>",
            "<when test='terminalType != null and terminalType == 2'>",
            "and json_extract(template_data,'$.tsg000') = #{templateId} ",
            "</when>",
            "</script>"})
    int countGroupNumByTemplateId(Long templateId, Integer terminalType);

}
