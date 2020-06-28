package com.throne.lexer;

import com.throne.common.PeekIterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TokenTests {

    @Test
    public void testMakeVarOrKeyWordOrBool() {
        PeekIterator<Character> iterator1 = new PeekIterator<>("if abc".chars().mapToObj(x -> (char) x));
        PeekIterator<Character> iterator2 = new PeekIterator<>("return value".chars().mapToObj(x -> (char) x));

        Token token1 = Token.makeVarOrKeyWordOrBool(iterator1);
        Token token2 = Token.makeVarOrKeyWordOrBool(iterator2);

        Assertions.assertEquals(TokenType.KEYWORD, token1.getTokenType());
        Assertions.assertEquals(TokenType.KEYWORD, token2.getTokenType());
        Assertions.assertEquals("if", token1.getValue());
        Assertions.assertEquals("return", token2.getValue());

        iterator1.next();
        iterator2.next();

        Token token3 = Token.makeVarOrKeyWordOrBool(iterator1);
        Token token4 = Token.makeVarOrKeyWordOrBool(iterator2);

        Assertions.assertEquals(TokenType.VARIABLE, token3.getTokenType());
        Assertions.assertEquals(TokenType.VARIABLE, token4.getTokenType());

        Assertions.assertEquals("abc", token3.getValue());
        Assertions.assertEquals("value", token4.getValue());

        PeekIterator<Character> iterator3 = new PeekIterator<>("false true".chars().mapToObj(x -> (char) x));

        Token token5 = Token.makeVarOrKeyWordOrBool(iterator3);
        Assertions.assertEquals(TokenType.BOOLEAN, token5.getTokenType());
        Assertions.assertEquals("false", token5.getValue());

        iterator3.next();

        Token token6 = Token.makeVarOrKeyWordOrBool(iterator3);
        Assertions.assertEquals(TokenType.BOOLEAN, token6.getTokenType());
        Assertions.assertEquals("true", token6.getValue());


    }

    @Test
    public void testMakeString() throws LexicalException {
        PeekIterator<Character> iterator1 = new PeekIterator<>("\"hello!\"".chars().mapToObj(x -> (char) x));
        PeekIterator<Character> iterator2 = new PeekIterator<>("'good morning'".chars().mapToObj(x -> (char) x));
        PeekIterator<Character> iterator3 = new PeekIterator<>("'let us go".chars().mapToObj(x -> (char) x));
        PeekIterator<Character> iterator4 = new PeekIterator<>("let us go'".chars().mapToObj(x -> (char) x));

        Token token1 = Token.makeString(iterator1);
        Token token2 = Token.makeString(iterator2);

        try {
            Token token3 = Token.makeString(iterator3);
        } catch (LexicalException e) {
            Assertions.assertEquals("A String Might Not End With \" or '.", e.getMessage());
        }
        try {
            Token token4 = Token.makeString(iterator4);
        } catch (LexicalException e) {
            Assertions.assertEquals("A String Must Start With \" or '.", e.getMessage());
        }

        Assertions.assertEquals(TokenType.STRING, token1.getTokenType());
        Assertions.assertEquals(TokenType.STRING, token2.getTokenType());

        Assertions.assertEquals("\"hello!\"", token1.getValue());
        Assertions.assertEquals("'good morning'", token2.getValue());

    }

    @Test
    public void testMakeOperator() throws LexicalException {
        String[] operators = {"+", "-", "*", "/", "=", "++", "--", "+=", "-=", "=="};
        String[] samples = {"+ 1003", "- 500", "*3721", "/981", "= abc", "++", "--", "+=1", "-=20", "==false"};
        List<String> opList = Arrays.asList(operators);
        List<String> sampleList = Arrays.asList(samples);
        for (int i = 0; i < opList.size(); i++) {
            PeekIterator<Character> iterator = new PeekIterator<>(sampleList.get(i).chars().mapToObj(x -> (char) x));
            Token token = Token.makeOperator(iterator);
            Assertions.assertEquals(TokenType.OPERATOR, token.getTokenType());
            Assertions.assertEquals(opList.get(i), token.getValue());
        }
    }

    @Test
    public void testMakeNumber() throws LexicalException {
        String[] numbers = {
                "123",
                "00004",
                "-15",
                "+87",
                "10.09",
                "-43.11",
                "+9.87",
                "0.001",
                ".",
                "98 + 3",
                "3.14= 3.11+0.03",
        };

        TokenType[] types = {
                TokenType.INTEGER,
                TokenType.INTEGER,
                TokenType.INTEGER,
                TokenType.INTEGER,
                TokenType.FLOAT,
                TokenType.FLOAT,
                TokenType.FLOAT,
                TokenType.FLOAT,
                TokenType.FLOAT,
                TokenType.INTEGER,
                TokenType.FLOAT,
        };
        String [] values = {
                "123",
                "00004",
                "-15",
                "87",
                "10.09",
                "-43.11",
                "9.87",
                "0.001",
                "0.00",
                "98",
                "3.14"
        };
        List<String> numList = Arrays.asList(numbers);
        List<TokenType> typeList = Arrays.asList(types);
        List<String> valueList = Arrays.asList(values);
        for (int i = 0; i < numList.size(); i++) {
            PeekIterator<Character> iterator = new PeekIterator<>(numList.get(i).chars().mapToObj(x -> (char) x));
            Token token = Token.makeNumber(iterator);
            Assertions.assertEquals(typeList.get(i),token.getTokenType());
            Assertions.assertEquals(valueList.get(i),token.getValue());
        }
    }
}