package com.throne.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SymbolHelperTests {
    @Test
    public void testIsNumber() {
        String[] split = "1234567890".split("");
        for (String s : split) {
            Assertions.assertEquals(Boolean.TRUE, SymbolHelper.isNumber(s.charAt(0)));
        }
        String[] split2 = "alkjsehqkuhcnqwzpxo".split("");
        for (String s : split2) {
            Assertions.assertEquals(Boolean.FALSE, SymbolHelper.isNumber(s.charAt(0)));
        }

        String[] split3 = "+*-/=&%!".split("");
        for (String s : split3) {
            Assertions.assertEquals(Boolean.FALSE, SymbolHelper.isNumber(s.charAt(0)));
        }


    }

    @Test
    public void testIsLetter() {
        String[] split = "alkjsehqkuhcnqwzpxo".split("");
        for (String s : split) {
            Assertions.assertEquals(Boolean.TRUE, SymbolHelper.isLetter(s.charAt(0)));
        }

        String[] split2 = "1234567890".split("");
        for (String s : split2) {
            Assertions.assertEquals(Boolean.FALSE, SymbolHelper.isLetter(s.charAt(0)));
        }

        String[] split3 = "+*-/=&%!".split("");
        for (String s : split3) {
            Assertions.assertEquals(Boolean.FALSE, SymbolHelper.isLetter(s.charAt(0)));
        }

    }

    @Test
    public void testIsOperator() {
        String[] split = "+*-/=&%!<>".split("");
        for (String s : split) {
            Assertions.assertEquals(Boolean.TRUE, SymbolHelper.isOperator(s.charAt(0)));
        }

        String[] split2 = "alkjsehqkuhcnqwzpxo".split("");
        for (String s : split2) {
            Assertions.assertEquals(Boolean.FALSE, SymbolHelper.isOperator(s.charAt(0)));
        }

        String[] split3 = "1234567890".split("");
        for (String s : split3) {
            Assertions.assertEquals(Boolean.FALSE, SymbolHelper.isOperator(s.charAt(0)));
        }
    }
    @Test
    public void testIsLiteral() {
        String[] split = "+*-/=&%!<>".split("");
        for (String s : split) {
            Assertions.assertEquals(Boolean.FALSE, SymbolHelper.isLiteral(s.charAt(0)));
        }

        String[] split2 = "alkjsehqkuhcnqwzpxo".split("");
        for (String s : split2) {
            Assertions.assertEquals(Boolean.TRUE, SymbolHelper.isLiteral(s.charAt(0)));
        }

        String[] split3 = "1234567890".split("");
        for (String s : split3) {
            Assertions.assertEquals(Boolean.TRUE, SymbolHelper.isLiteral(s.charAt(0)));
        }
    }
}