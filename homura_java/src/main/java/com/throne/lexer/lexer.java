package com.throne.lexer;

import com.throne.common.AlphabetHelper;
import com.throne.common.PeekIterator;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class lexer {
    private static final Character[] _BRACKETS = {'{', '}', '[', ']', '(', ')'};
    private static final List<Character> BRACKETS = Arrays.asList(_BRACKETS);


    /**
     * 对代码文本的字符流进行解析，拆解成一组token并返回
     *
     * @param source 代码文本字符流
     * @return token列表
     */
    public ArrayList<Token> analyse(Stream<Character> source) throws LexicalException {
        PeekIterator<Character> iterator = new PeekIterator<Character>(source, '0');
        ArrayList<Token> tokens = new ArrayList<>();

        while (iterator.hasNext()) {
            Character peek = iterator.peek();
            if (peek == ' ' || peek == '\n') {
                continue;
            } else if (AlphabetHelper.isNumber(peek)) {
                tokens.add(Token.makeNumber(iterator));
            } else if (BRACKETS.contains(peek)) {
                tokens.add(new Token(TokenType.BRACKET, peek + ""));
            } else if (AlphabetHelper.isLetter(peek)) {
                tokens.add(Token.makeVarOrKeyWordOrBool(iterator));
            } else if (peek == '"' || peek == '\'') {
                tokens.add(Token.makeString(iterator));
            } else if (AlphabetHelper.isOperator(peek)) {
                // todo: 要区分运算符和以符号开头表示数字的两种情况
                continue;
            }
        }

        return tokens;
    }
}