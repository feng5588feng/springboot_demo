package com.example.springboot.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenData implements Serializable {

    private static final long serialVersionUID = 902329881718384732L;

    private String serial;

    private String areaCode;

    private String token;
}