package com.throne.parser.ast;

/**
 * 用以描述声明语句的抽象类
 * com.throne.parser
 * Created by throne on 2020/6/29
 */
public abstract class Statement extends ASTNode {
    public Statement(ASTNode parent, ASTNodeType astNodeType, String label) {
        super(parent, astNodeType, label);
    }
}
