package com.throne.parser.utils;

import com.throne.common.PeekIterator;
import com.throne.lexer.Token;
import com.throne.lexer.TokenType;

import java.util.stream.Stream;

/**
 * com.throne.parser.utils
 * Created by throne on 2020/6/30
 */
public class PeekTokenIterator extends PeekIterator<Token> {
    public PeekTokenIterator(Stream<Token> stream) {
        super(stream);
    }

    public Token nextMatch(String value) throws ParseException {
        Token token = this.next();
        if (!token.getValue().equals(value)) {
            throw new ParseException(token);
        }
        return token;
    }

    public Token nextMatch(TokenType tokenType) throws ParseException {
        Token token = this.next();
        if (token.getTokenType() != tokenType) {
            throw new ParseException(token);
        }
        return token;
    }
}
