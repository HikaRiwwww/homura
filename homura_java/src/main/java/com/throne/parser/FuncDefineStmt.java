package com.throne.parser;

/**
 * 用以描述函数声明语句的类
 * com.throne.parser
 * Created by throne on 2020/6/29
 */
public class FuncDefineStmt extends Statement {
    public FuncDefineStmt(ASTNode parent) {
        super(parent, ASTNodeType.FUNCTION_DEFINE_STMT, "func_define_stmt");
    }
}
