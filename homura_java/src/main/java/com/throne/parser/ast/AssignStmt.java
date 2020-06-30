package com.throne.parser.ast;

/**
 * 用以描述赋值语句的类
 * com.throne.parser
 * Created by throne on 2020/6/29
 */
public class AssignStmt extends Statement {
    public AssignStmt(ASTNode parent) {
        super(parent, ASTNodeType.ASSIGN_STMT, "assign_stmt");
    }
}
