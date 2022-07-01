package com.owen1212055.commandman.nms;

import com.mojang.brigadier.arguments.*;
import com.owen1212055.commandman.nms.node.BrigArgumentType;

public class BrigArgWrapper<T> implements BrigArgumentType<T> {

    private final com.mojang.brigadier.arguments.ArgumentType<T> brigArg;

    public BrigArgWrapper(com.mojang.brigadier.arguments.ArgumentType<T> brigArg) {
        this.brigArg = brigArg;
    }

    @Override
    public ArgumentType<T> getType() {
        return brigArg;
    }
}
