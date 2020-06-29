package com.throne.parser;

/**
 * 用以描述变量声明语句的类
 * com.throne.parser
 * Created by throne on 2020/6/29
 */
public class DeclareStmt extends Statement {
    public DeclareStmt(ASTNode parent) {
        super(parent, ASTNodeType.DECLARE_STMT, "declare_stmt");
    }
}
