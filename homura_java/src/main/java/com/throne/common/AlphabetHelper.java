package com.throne.common;

import java.util.regex.Pattern;

public class AlphabetHelper {
    public static Pattern numberPattern = Pattern.compile("^[0-9]$");
    public static Pattern letterPattern = Pattern.compile("^[a-zA-Z]$");
    public static Pattern literalPattern = Pattern.compile("^[_0-9a-zA-Z]$");
    public static Pattern operatorPatter = Pattern.compile("^[+\\-*/<>=!&$|%^]$");

    public static boolean isNumber(char c) {
        return numberPattern.matcher(c + "").matches();
    }

    public static boolean isLetter(char c) {
        return letterPattern.matcher(c + "").matches();
    }

    public static boolean isOperator(char c) {
        return operatorPatter.matcher(c + "").matches();
    }
    public static boolean isLiteral(char c) {
        return literalPattern.matcher(c + "").matches();
    }
}