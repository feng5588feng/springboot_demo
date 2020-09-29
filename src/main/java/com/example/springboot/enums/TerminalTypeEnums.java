package com.example.springboot.enums;

import lombok.Getter;

/**
 * Description：隧道状态类型
 *
 * @author tww
 * Date：2020.2.13 5:38:00
 */
@Getter
public enum TerminalTypeEnums {
    TSG100(0, "tsg100"),
    TSG500(1, "tsg500"),
    TSG000(2, "tsg000");

    private final int value;
    private final String name;

    TerminalTypeEnums(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
