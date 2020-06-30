package com.throne.parser;

/**
 * 为语法树的各个节点提供相关信息的枚举类型
 * 用来表明某一个节点代表着语法中的哪一类
 * com.throne.parser
 * Created by throne on 2020/6/29
 */
public enum ASTNodeType {
    // 代码块
    BLOCK,
    // 二元表达式，如1+1
    BINARY_EXPR,
    // 一元表达式 如i++
    UNARY_EXPR,
    // 变量
    VARIABLE,
    // 标量，值 3.14 true
    SCALAR,
    // if语句
    IF_STMT,
    // while语句
    WHILE_STMT,
    // for语句
    FOR_STMT,
    // 赋值语句
    ASSIGN_STMT,
    // 函数声明语句
    FUNCTION_DEFINE_STMT,
    // 声明语句
    DECLARE_STMT,
}
