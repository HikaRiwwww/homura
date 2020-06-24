package com.throne.lexer;

public enum TokenType {
    KEYWORD,    // 关键字
    VARIABLE,   // 变量
    OPERATOR,   // 操作符
    BRACKET,    // 括号

    /**
     * 以下为数据类型
     */
    STRING,     // 字符串
    FLOAT,      // 浮点数
    BOOLEAN,    // 布尔值
    INTEGER     // 整型
}
