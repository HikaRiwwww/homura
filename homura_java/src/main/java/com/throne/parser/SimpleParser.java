package com.throne.parser;

import com.throne.lexer.Token;
import com.throne.parser.ast.ASTNode;
import com.throne.parser.ast.ASTNodeType;
import com.throne.parser.ast.Expr;
import com.throne.parser.ast.Scalar;
import com.throne.parser.utils.ParseException;
import com.throne.parser.utils.PeekTokenIterator;

/**
 * com.throne.parser
 * Created by throne on 2020/6/30
 */
public class SimpleParser {
    public static ASTNode parse(PeekTokenIterator iterator) throws ParseException {
        Expr expr = new Expr(null);
        Scalar scalar = new Scalar(expr, iterator);
        // 退出递归条件
        if (!iterator.hasNext()){
            return scalar;
        }
        /*
         * 不符合退出条件则执行继续递归
         * 退出前，应该先完成节点的连接，即当前Expr应该有一个左子节点scalar，一个右子节点expr或scalar
         * 最后递归完成时返回一开始创建的父节点为空的那个expr，这个expr中有其子节点的信息，而子节点又有自己的子节点信息，
         * 从而形成了一个树形结构
         */

        Token nextMatch = iterator.nextMatch("+");
        expr.setLexeme(nextMatch);
        expr.setLabel(nextMatch.getValue());
        expr.setAstNodeType(ASTNodeType.BINARY_EXPR);
        expr.addChild(scalar);
        ASTNode rightNode = parse(iterator);
        expr.addChild(rightNode);
        return expr;
    }
}
