package com.throne.parser.ast;

import com.throne.parser.utils.PeekTokenIterator;

/**
 * 用以描述标量/值的类
 * com.throne.parser
 * Created by throne on 2020/6/29
 */
public class Scalar extends Factor {
    public Scalar(ASTNode parent, PeekTokenIterator iterator) {
        super(parent, iterator);
    }
}
