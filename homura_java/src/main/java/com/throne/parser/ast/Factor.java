package com.throne.parser.ast;

import com.throne.lexer.Token;
import com.throne.lexer.TokenType;
import com.throne.parser.utils.PeekTokenIterator;

/**
 * 用以描述参与各自计算的因子的类
 * com.throne.parser
 * Created by throne on 2020/6/29
 */
public abstract class Factor extends ASTNode {
    public Factor(ASTNode parent, PeekTokenIterator iterator) {
        super(parent);

        Token token = iterator.next();
        TokenType tokenType = token.getTokenType();

        if (tokenType == TokenType.VARIABLE) {
            this.astNodeType = ASTNodeType.VARIABLE;
        } else {
            this.astNodeType = ASTNodeType.SCALAR;
        }
        this.label = token.getValue();
        this.lexeme = token;
    }
}
