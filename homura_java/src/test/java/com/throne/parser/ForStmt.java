package com.throne.parser;

/**
 * 用以描述for循环语句的类
 * com.throne.parser
 * Created by throne on 2020/6/29
 */
public class ForStmt extends Statement {
    public ForStmt(ASTNode parent) {
        super(parent, ASTNodeType.FOR_STMT, "for_stmt");
    }
}
