package com.example.springboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.TestJson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository(value = "testJsonMapper")
public interface TestJsonMapper extends BaseMapper<TestJson> {

}
