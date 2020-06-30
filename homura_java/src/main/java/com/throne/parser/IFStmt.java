package com.throne.parser;

/**
 * 用以描述if语句的类
 * com.throne.parser
 * Created by throne on 2020/6/29
 */
public class IFStmt extends Statement {
    public IFStmt(ASTNode parent) {
        super(parent, ASTNodeType.IF_STMT, "if_stmt");
    }
}
