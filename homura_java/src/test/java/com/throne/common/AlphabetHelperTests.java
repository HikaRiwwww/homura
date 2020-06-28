package com.throne.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AlphabetHelperTests {
    @Test
    public void testIsNumber() {
        String[] split = "1234567890".split("");
        for (String s : split) {
            Assertions.assertEquals(Boolean.TRUE, AlphabetHelper.isNumber(s.charAt(0)));
        }
        String[] split2 = "alkjsehqkuhcnqwzpxo".split("");
        for (String s : split2) {
            Assertions.assertEquals(Boolean.FALSE, AlphabetHelper.isNumber(s.charAt(0)));
        }

        String[] split3 = "+*-/=&%!".split("");
        for (String s : split3) {
            Assertions.assertEquals(Boolean.FALSE, AlphabetHelper.isNumber(s.charAt(0)));
        }


    }

    @Test
    public void testIsLetter() {
        String[] split = "alkjsehqkuhcnqwzpxo".split("");
        for (String s : split) {
            Assertions.assertEquals(Boolean.TRUE, AlphabetHelper.isLetter(s.charAt(0)));
        }

        String[] split2 = "1234567890".split("");
        for (String s : split2) {
            Assertions.assertEquals(Boolean.FALSE, AlphabetHelper.isLetter(s.charAt(0)));
        }

        String[] split3 = "+*-/=&%!".split("");
        for (String s : split3) {
            Assertions.assertEquals(Boolean.FALSE, AlphabetHelper.isLetter(s.charAt(0)));
        }

    }

    @Test
    public void testIsOperator() {
        String[] split = "+*-/=&%!<>".split("");
        for (String s : split) {
            Assertions.assertEquals(Boolean.TRUE, AlphabetHelper.isOperator(s.charAt(0)));
        }

        String[] split2 = "alkjsehqkuhcnqwzpxo".split("");
        for (String s : split2) {
            Assertions.assertEquals(Boolean.FALSE, AlphabetHelper.isOperator(s.charAt(0)));
        }

        String[] split3 = "1234567890".split("");
        for (String s : split3) {
            Assertions.assertEquals(Boolean.FALSE, AlphabetHelper.isOperator(s.charAt(0)));
        }
    }
    @Test
    public void testIsLiteral() {
        String[] split = "+*-/=&%!<>".split("");
        for (String s : split) {
            Assertions.assertEquals(Boolean.FALSE, AlphabetHelper.isLiteral(s.charAt(0)));
        }

        String[] split2 = "alkjsehqkuhcnqwzpxo".split("");
        for (String s : split2) {
            Assertions.assertEquals(Boolean.TRUE, AlphabetHelper.isLiteral(s.charAt(0)));
        }

        String[] split3 = "1234567890".split("");
        for (String s : split3) {
            Assertions.assertEquals(Boolean.TRUE, AlphabetHelper.isLiteral(s.charAt(0)));
        }
    }
}