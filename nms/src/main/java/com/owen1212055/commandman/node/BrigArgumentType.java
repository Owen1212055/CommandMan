package com.owen1212055.commandman.node;

import com.owen1212055.commandman.api.node.argument.type.*;

public interface BrigArgumentType<T> extends ArgumentType<T> {

    com.mojang.brigadier.arguments.ArgumentType<T> getType();
}
