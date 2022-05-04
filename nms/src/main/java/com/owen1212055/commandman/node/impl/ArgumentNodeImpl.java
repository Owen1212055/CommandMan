package com.owen1212055.commandman.node.impl;

import com.owen1212055.commandman.api.node.argument.*;
import com.owen1212055.commandman.api.node.argument.type.*;
import com.owen1212055.commandman.node.*;

public class ArgumentNodeImpl<T> extends SimpleNode implements ArgumentNode<T> {

    private final String name;
    private final ArgumentType<T> type;

    public ArgumentNodeImpl(String name, ArgumentType<T> type) {
        this.name = name;
        this.type = type;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ArgumentType<T> getType() {
        return this.type;
    }

}
