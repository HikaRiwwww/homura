package com.throne.parser;

/**
 * 用以描述标量/值的类
 * com.throne.parser
 * Created by throne on 2020/6/29
 */
public class Scalar extends Factor {
    public Scalar(ASTNode parent) {
        super(parent, ASTNodeType.SCALAR, "");
    }
}
