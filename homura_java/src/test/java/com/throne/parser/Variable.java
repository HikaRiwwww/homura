package com.throne.parser;

/**
 * 用以描述变量的类
 * com.throne.parser
 * Created by throne on 2020/6/29
 */
public class Variable extends Factor {
    public Variable(ASTNode parent) {
        super(parent, ASTNodeType.VARIABLE, "");
    }
}
