package com.throne.lexer;

import com.throne.common.PeekIterator;
import com.throne.common.SymbolHelper;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Lexer {


    /**
     * 对代码文本的字符流进行解析，拆解成一组token并返回
     *
     * @param source 代码文本字符流
     * @return token列表
     */
    public ArrayList<Token> analyse(Stream<Character> source) throws LexicalException {
        PeekIterator<Character> iterator = new PeekIterator<Character>(source);
        ArrayList<Token> tokens = new ArrayList<>();

        while (iterator.hasNext()) {
            Character next = iterator.next();
            if (next == ' ' || next == '\n') {
                continue;
            } else if (SymbolHelper.isNumber(next)) {
                iterator.putBack(next);
                tokens.add(Token.makeNumber(iterator));
            } else if (SymbolHelper.isBracket(next)) {
                tokens.add(new Token(TokenType.BRACKET, next + ""));
            } else if (SymbolHelper.isLetter(next)) {
                iterator.putBack(next);
                tokens.add(Token.makeVarOrKeyWordOrBool(iterator));
            } else if (next == '"' || next == '\'') {
                iterator.putBack(next);
                tokens.add(Token.makeString(iterator));
            } else if (SymbolHelper.isOperator(next)) {
                Character peek = iterator.peek();
                // 区分/作为注释开头和除号的情况
                if (next == '/' && peek == '/') {
                    // "//"形式的注释，直接iter至行尾即可
                    while (iterator.hasNext()) {
                        Character n = iterator.next();
                        if (n == '\n') {
                            break;
                        }
                    }
                } else if (next == '/' && peek == '*') {
                    // "/* */形式的注释，iter到闭合处，如果没找到则报错
                    iterator.next();
                    boolean closed = false;
                    while (iterator.hasNext()) {
                        Character n = iterator.next();
                        if (n == '*' && iterator.peek() == '/') {
                            iterator.next();
                            closed = true;
                            break;
                        }
                    }
                    if (!closed) {
                        throw new LexicalException("Comment Might Not Properly Closed.");
                    }
                } else {
                    // 运算符
                    // 要区分运算符和以符号开头表示数字的两种情况
                    // 在当前符号是+ - 或.的情况下：
                    // 如果上一个token如果为null或者不是数字和变量，视为正负号，否则，视为运算符
                    iterator.putBack(next);
                    if (next != '+' && next != '-' && next != '.') {
                        tokens.add(Token.makeOperator(iterator));
                    } else {
                        Token lastToken = tokens.get(tokens.size() - 1);
                        if (lastToken == null || (!lastToken.isVariable() && !lastToken.isNumber())) {
                            tokens.add(Token.makeNumber(iterator));
                        } else {
                            tokens.add(Token.makeOperator(iterator));
                        }
                    }
                }
            }
        }

        return tokens;
    }
}