package com.throne.parser;

/**
 * 用以描述参与各自计算的因子的类
 * com.throne.parser
 * Created by throne on 2020/6/29
 */
public abstract class Factor extends ASTNode {
    public Factor(ASTNode parent, ASTNodeType astNodeType, String label) {
        super(parent, astNodeType, label);
    }
}
