package com.throne.parser;

import com.throne.common.PeekIterator;
import com.throne.lexer.Lexer;
import com.throne.lexer.LexicalException;
import com.throne.lexer.Token;
import com.throne.lexer.TokenType;
import com.throne.parser.ast.ASTNode;
import com.throne.parser.ast.ASTNodeType;
import com.throne.parser.utils.ParseException;
import com.throne.parser.utils.PeekTokenIterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * com.throne.parser
 * Created by throne on 2020/6/30
 */
public class SimpleParserTests {
    @Test
    public void testParser() throws LexicalException, ParseException {
        Stream<Character> stream = "1+2 +3+ 4 + 5".chars().mapToObj(c -> (char) c);
        Lexer lexer = new Lexer();
        PeekTokenIterator iterator = new PeekTokenIterator(lexer.analyse(stream).stream());
        ASTNode expr = SimpleParser.parse(iterator);

        Assertions.assertEquals(2, expr.getChildren().size());
        Assertions.assertEquals(ASTNodeType.SCALAR, expr.getChild(0).getAstNodeType());
        Assertions.assertEquals(ASTNodeType.BINARY_EXPR ,expr.getChild(1).getAstNodeType());

        // 第一个+号右侧表达式
        ASTNode child0 = expr.getChildren().get(0);
        ASTNode child1 = expr.getChildren().get(1);
        Assertions.assertFalse(child0.hasChildren());
        Assertions.assertEquals(2, child1.getChildren().size());
        Assertions.assertEquals(ASTNodeType.BINARY_EXPR ,child1.getAstNodeType());

        // 第二个+号右侧表达式
        ASTNode child2 = child1.getChild(0);
        ASTNode child3 = child1.getChild(1);
        Assertions.assertFalse(child2.hasChildren());
        Assertions.assertEquals(2, child3.getChildren().size());
        Assertions.assertEquals(ASTNodeType.BINARY_EXPR ,child3.getAstNodeType());

        // 第三个+号右侧表达式
        ASTNode child4 = child3.getChild(0);
        ASTNode child5 = child3.getChild(1);
        Assertions.assertFalse(child4.hasChildren());
        Assertions.assertEquals(2, child5.getChildren().size());
        Assertions.assertEquals(ASTNodeType.BINARY_EXPR ,child5.getAstNodeType());


        // 第四个+号右侧表达式
        ASTNode child6 = child5.getChild(0);
        ASTNode child7 = child5.getChild(1);
        Assertions.assertFalse(child6.hasChildren());
        Assertions.assertFalse(child7.hasChildren());
        Assertions.assertEquals(0, child7.getChildren().size());
        Assertions.assertEquals(ASTNodeType.SCALAR ,child7.getAstNodeType());




    }
}
