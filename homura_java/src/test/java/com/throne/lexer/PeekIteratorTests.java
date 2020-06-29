package com.throne.lexer;

import com.throne.common.PeekIterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * com.throne.lexer
 * Created by throne on 2020/6/24
 */
public class PeekIteratorTests {

    @Test
    public void testPeek(){
        String source = "abcdefg";
        PeekIterator<Character> peekIterator = new PeekIterator<>(source.chars().mapToObj(c -> (char) c));

        Assertions.assertEquals('a', peekIterator.next());
        Assertions.assertEquals('b', peekIterator.next());
        Assertions.assertEquals('c', peekIterator.next());
        Assertions.assertEquals('d', peekIterator.peek());
        Assertions.assertEquals(Boolean.TRUE, peekIterator.hasNext());
        Assertions.assertEquals('d', peekIterator.peek());
        Assertions.assertEquals('d', peekIterator.next());
        Assertions.assertEquals('e', peekIterator.next());
        Assertions.assertEquals('f', peekIterator.peek());
        Assertions.assertEquals('f', peekIterator.next());
        Assertions.assertEquals('g', peekIterator.peek());
        Assertions.assertEquals('g', peekIterator.next());
        Assertions.assertEquals(Boolean.FALSE, peekIterator.hasNext());
        Assertions.assertNull(peekIterator.peek());

    }

    @Test
    public void testPeekWithEndToken(){
        String source = "abcdefg";
        char endToken = '0';
        PeekIterator<Character> peekIterator = new PeekIterator<>(source.chars().mapToObj(c -> (char) c), endToken);

        Assertions.assertEquals('a', peekIterator.next());
        Assertions.assertEquals('b', peekIterator.next());
        Assertions.assertEquals('c', peekIterator.next());
        Assertions.assertEquals('d', peekIterator.peek());
        Assertions.assertEquals(Boolean.TRUE, peekIterator.hasNext());
        Assertions.assertEquals('d', peekIterator.peek());
        Assertions.assertEquals('d', peekIterator.next());
        Assertions.assertEquals('e', peekIterator.next());
        Assertions.assertEquals('f', peekIterator.peek());
        Assertions.assertEquals('f', peekIterator.next());
        Assertions.assertEquals('g', peekIterator.peek());
        Character n = peekIterator.next();
        peekIterator.putBack(n);
        Assertions.assertEquals('g', peekIterator.peek());
        Assertions.assertEquals('g', peekIterator.next());
        Assertions.assertEquals(Boolean.TRUE, peekIterator.hasNext());
        Assertions.assertEquals('0', peekIterator.peek());
        Assertions.assertEquals('0', peekIterator.next());
        Assertions.assertEquals(Boolean.FALSE, peekIterator.hasNext());
        Assertions.assertNull(peekIterator.peek());

    }



}
