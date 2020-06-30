package com.throne.parser;

import com.throne.lexer.Token;

import java.util.ArrayList;

/**
 * com.throne.parser
 * Created by throne on 2020/6/29
 */
public abstract class ASTNode {
    // 树形结构中的子节点和父节点, 一个节点至多只有一个父节点，可以有多个子节点
    protected ArrayList<ASTNode> children = new ArrayList<>();
    protected ASTNode _parent;

    // 节点的关键信息
    protected ASTNodeType astNodeType;
    protected Token lexeme; // 词法单元
    protected String label; // 备注标签

    public ASTNode(ASTNode parent) {
        this._parent = parent;
    }

    public ASTNode(ASTNode parent, ASTNodeType astNodeType, String label) {
        this._parent = parent;
        this.astNodeType = astNodeType;
        this.label = label;
    }

    /**
     * 随机访问子节点中的一个
     *
     * @param index 下标
     * @return 下标对应的子节点
     */
    public ASTNode getChild(int index) {
        return this.children.get(index);
    }

    /**
     * 增加子节点
     *
     * @param node 子节点
     */
    public void addChild(ASTNode node) {
        children.add(node);
    }

    public ArrayList<ASTNode> getChildren() {
        return children;
    }

    public Token getLexeme() {
        return lexeme;
    }


}
