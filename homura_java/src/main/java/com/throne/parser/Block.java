package com.throne.parser;

/**
 * 用以描述代码块的类
 * com.throne.parser
 * Created by throne on 2020/6/29
 */
public class Block extends Statement {
    public Block(ASTNode parent) {
        super(parent, ASTNodeType.BLOCK, "block");
    }
}
