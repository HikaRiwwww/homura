package com.throne.lexer;

import com.throne.common.AlphabetHelper;
import com.throne.common.KeyWords;
import com.throne.common.PeekIterator;

public class Token {
    private TokenType _tokenType;
    private String _value;

    public Token(TokenType _tokenType, String _value) {
        this._tokenType = _tokenType;
        this._value = _value;
    }

    /**
     * 从字符流中读取出变量名，关键字或布尔值
     * 循环判断下一个字符是否属于文本，如果是则加入字符的拼接，直到下一个字符不是文本为止
     * 根据文本判断是关键字，还是布尔值，除此之外视为变量名
     *
     * @param peekIterator 字符流
     * @return 变量名，关键字或布尔值的Token实例
     */
    public static Token makeVarOrKeyWordOrBool(PeekIterator<Character> peekIterator) {
        StringBuilder stringBuilder = new StringBuilder();
        while (peekIterator.hasNext()) {
            Character peek = peekIterator.peek();
            if (AlphabetHelper.isLiteral(peek)) {
                stringBuilder.append(peek);
                peekIterator.next();
            } else {
                break;
            }
        }
        String word = stringBuilder.toString();
        if (KeyWords.isKeyWord(word)) {
            return new Token(TokenType.KEYWORD, word);
        } else if (word.equals("false") || word.equals("true")) {
            return new Token(TokenType.BOOLEAN, word);
        } else {
            return new Token(TokenType.VARIABLE, word);
        }

    }

    /**
     * 从字符流中读取出字符串
     *
     * @param peekIterator 字符流
     * @return 字符串的Token实例
     * @throws LexicalException 语法错误
     */
    public static Token makeString(PeekIterator<Character> peekIterator) throws LexicalException {
        StringBuilder s = new StringBuilder();
        int status = 0;
        while (peekIterator.hasNext()) {
            Character c = peekIterator.next();
            switch (status) {
                case 0:
                    if (c == '\''){
                        status = 1;
                    }else if (c == '"'){
                        status = 2;
                    }else {
                        throw new LexicalException("A String Must Start With \" or '.");
                    }
                    s.append(c);
                    break;

                case 1:
                    if (c == '\''){
                        return new Token(TokenType.STRING, s.append(c).toString());
                    }else {
                        s.append(c);
                    }
                    break;
                case 2:
                    if (c == '"'){
                        return new Token(TokenType.STRING, s.append(c).toString());
                    }else {
                        s.append(c);
                    }
                    break;

            }
        }
        throw new LexicalException("A String Might Not End With \" or '.");
    }

    /**
     * 从字符流中读取出操作符
     * todo : 2020/6/28 先写出'+-*\/='几个case
     * @param peekIterator 字符流
     * @return 操作符的Token实例
     * @throws LexicalException 语法错误
     */
    public static Token makeOperator(PeekIterator<Character> peekIterator) throws LexicalException{
        StringBuilder s = new StringBuilder();
        int status = 0;
        while (peekIterator.hasNext()){
            Character peek = peekIterator.peek();
            switch (status){
                case 0:
                    if (peek == '+'){
                        status = 1;
                        break;
                    }
                    else if (peek == '-'){
                        status = 2;
                        break;
                    }
                    else if (peek == '*'){
                        status = 3;
                        break;
                    }
                    else if (peek == '/'){
                        status = 4;
                        break;
                    }
                    else if (peek == '='){
                        status = 5;
                        break;
                    }
                    peekIterator.next();
                case 1: // '+'
                    if (AlphabetHelper.isOperator(peek)){
                        if (peek == '+' || peek == '='){
                            return new Token(TokenType.OPERATOR, s.append(peek).toString());
                        }else {
                            throw new LexicalException("Can not resolve operator \" " + s.append(peek).toString() + "\".");
                        }
                    }return new Token(TokenType.OPERATOR, s.toString());

                case 2: // '-'
                    if (AlphabetHelper.isOperator(peek)){
                        if (peek == '-' || peek == '='){
                            return new Token(TokenType.OPERATOR, s.append(peek).toString());
                        }else {
                            throw new LexicalException("Can not resolve operator \" " + s.append(peek).toString() + "\".");
                        }
                    }return new Token(TokenType.OPERATOR, s.toString());

                case 3: // '*'

                case 4: // '/'

                case 5: // '='
                    if (AlphabetHelper.isOperator(peek)){
                        if (peek == '='){
                            return new Token(TokenType.OPERATOR, s.append(peek).toString());
                        }else {
                            throw new LexicalException("Can not resolve operator \" " + s.append(peek).toString() + "\".");
                        }
                    }return new Token(TokenType.OPERATOR, s.toString());
            }
            s.append(peek);
            peekIterator.next();
        }
        throw new LexicalException("Incorrect Usage Of Operator: " + s.toString() + " Should Be Followed With A Specific Variable or Data Type");
    }

    public static Token makeNumber(PeekIterator<Character> peekIterator) throws LexicalException{
        StringBuilder s = new StringBuilder();
        int status = 0;
        while (peekIterator.hasNext()){
            Character peek = peekIterator.peek();
            switch (status){
                case 0:
                    if (peek == '+' || peek =='-'){
                        status = 1;

                    } else if (AlphabetHelper.isNumber(peek)) {
                        status = 2;
                    } else if (peek == '.'){
                        status = 4;
                    }
                    else {
                        throw new LexicalException("Can Not resolve A Number Starts With \"" + peek + "\"");
                    }
                    break;
                case 1: // 以+或-开头
                    if (AlphabetHelper.isNumber(peek)){
                    }else if (peek == '.'){
                        status = 3;
                    }else {
                        return new Token(TokenType.INTEGER, s.toString());
                    }
                    break;
                case 2: // 以0-9开头
                    if (AlphabetHelper.isNumber(peek)){
                        status = 2;
                    }else if (peek == '.'){
                        status = 3;
                    }else {
                        return new Token(TokenType.INTEGER, s.toString());
                    }
                    break;
                case 3: // float
                    if (AlphabetHelper.isNumber(peek)){
                        status = 3;
                    }else {
                        return new Token(TokenType.FLOAT, s.toString().replace("+", ""));
                    }
                    break;
            }
            s.append(peek);
            peekIterator.next();
        }
        String rawNum = s.toString();
        String num = rawNum.replace("+", "");
        if ("".equals(rawNum)){
            throw new LexicalException("Can Not Resolve " + rawNum + " As A number");
        }
        if (num.contains(".")){
            if (".".equals(num)){
                // 将仅有一个小数点的情况视为0.00处理
                return new Token(TokenType.FLOAT, "0.00");
            }
            return new Token(TokenType.FLOAT, num);
        }else {
            return new Token(TokenType.INTEGER, num);
        }
    }

    public TokenType getTokenType() {
        return _tokenType;
    }

    public String getValue() {
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
     *
     * @return 是为true， 反之false
     */
    public boolean isScalar() {
        return this._tokenType == TokenType.BOOLEAN ||
                this._tokenType == TokenType.FLOAT ||
                this._tokenType == TokenType.STRING ||
                this._tokenType == TokenType.INTEGER;
    }
}