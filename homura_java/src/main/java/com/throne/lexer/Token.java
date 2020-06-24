package com.throne.lexer;

public class Token {
    private TokenType _tokenType;
    private String _value;

    public Token(TokenType _tokenType, String _value) {
        this._tokenType = _tokenType;
        this._value = _value;
    }

    public TokenType get_tokenType() {
        return _tokenType;
    }

    public String get_value() {
        return _value;
    }

    @Override
    public String toString() {
        return "Token{" +
                "_tokenType=" + _tokenType +
                ", _value='" + _value + '\'' +
                '}';
    }

    /**
     * 判断当前token是否为变量
     *
     * @return 是变量为true，反之false
     */
    public boolean isVariable() {
        return this._tokenType == TokenType.VARIABLE;
    }

    /**
     * 判断当前对象是否为某一数据类型
     * @return 是为true， 反之false
     */
    public boolean isScalar() {
        return this._tokenType == TokenType.BOOLEAN ||
                this._tokenType == TokenType.FLOAT ||
                this._tokenType == TokenType.STRING ||
                this._tokenType == TokenType.INTEGER;
    }
}