package com.throne.parser.ast;

import com.throne.parser.utils.PeekTokenIterator;

/**
 * 用以描述变量的类
 * com.throne.parser
 * Created by throne on 2020/6/29
 */
public class Variable extends Factor {
    public Variable(ASTNode parent, PeekTokenIterator iterator) {
        super(parent, iterator);
    }
}
