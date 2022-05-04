package com.owen1212055.commandman.node;

import com.owen1212055.commandman.api.*;
import com.owen1212055.commandman.api.node.*;
import com.owen1212055.commandman.api.node.argument.*;
import com.owen1212055.commandman.api.node.argument.type.*;
import com.owen1212055.commandman.api.node.command.*;
import com.owen1212055.commandman.api.node.literal.*;
import com.owen1212055.commandman.node.impl.*;

public class NmsNodeAdapter implements NodeAdapter {

    @Override
    public LiteralNode literal(String literal) {
        return new LiteralNodeImpl(literal);
    }

    @Override
    public <T> ArgumentNode<T> of(String name, ArgumentType<T> type) {
        return new ArgumentNodeImpl<>(name, type);
    }

    @Override
    public CommandNode command(Command<?> command) {
        return new CommandNodeImpl(command);
    }

}
