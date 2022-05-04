package com.owen1212055.commandman.api.node;

import com.owen1212055.commandman.api.*;
import com.owen1212055.commandman.api.node.argument.*;
import com.owen1212055.commandman.api.node.argument.type.*;
import com.owen1212055.commandman.api.node.literal.*;
import com.owen1212055.commandman.api.node.command.*;

public interface NodeAdapter {

    LiteralNode literal(String literal);

    <T> ArgumentNode<T> of(String name, ArgumentType<T> type);

    CommandNode command(Command<?> command);

}
