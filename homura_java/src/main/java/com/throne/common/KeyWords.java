package com.throne.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KeyWords {
    private final  static String[]  keyWordsList = {
            "if",
            "else",
            "while",
            "for",
            "var",
            "return",
            "func"
    };

    private final static Set<String> keyWordSet = new HashSet<>(Arrays.asList(keyWordsList));

    public static boolean isKeyWord(String word){
        return keyWordSet.contains(word);
    }
}