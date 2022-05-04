package com.owen1212055.commandman.api.node.argument;

import com.owen1212055.commandman.api.node.*;
import com.owen1212055.commandman.api.node.argument.type.*;

public interface ArgumentNode<T> extends Node {

    String getName();

    ArgumentType<T> getType();

}
