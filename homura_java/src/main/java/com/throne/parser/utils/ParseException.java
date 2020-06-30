package com.throne.parser.utils;

import com.throne.lexer.Token;

/**
 * com.throne.parser.utils
 * Created by throne on 2020/6/30
 */
public class ParseException extends Exception {
    private String _msg;

    public ParseException(String msg) {
        this._msg = msg;
    }

    public ParseException(Token token) {
        this._msg = "Syntax Error: Unexpected token " + token.getValue();
    }

}
