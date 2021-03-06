package com.owen1212055.commandman.nms.node.impl;

import com.owen1212055.commandman.api.node.literal.*;
import com.owen1212055.commandman.nms.node.SimpleNode;

public class LiteralNodeImpl extends SimpleNode implements LiteralNode {

    private final String literal;

    public LiteralNodeImpl(String literal) {
        this.literal = literal;
    }

    @Override
    public String getLiteral() {
        return this.literal;
    }
}
