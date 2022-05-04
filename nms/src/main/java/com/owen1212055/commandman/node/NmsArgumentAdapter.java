package com.owen1212055.commandman.node;

import com.mojang.brigadier.arguments.*;
import com.owen1212055.commandman.*;
import com.owen1212055.commandman.api.node.*;
import com.owen1212055.commandman.api.node.argument.type.ArgumentType;
import net.minecraft.commands.arguments.*;

import java.util.*;

public class NmsArgumentAdapter implements ArgumentAdapter {

    @Override
    public ArgumentType<String> string() {
        return of(StringArgumentType.string());
    }

    @Override
    public ArgumentType<String> word() {
        return of(StringArgumentType.word());
    }

    @Override
    public ArgumentType<String> greedy() {
        return of(StringArgumentType.greedyString());
    }

    @Override
    public ArgumentType<UUID> uuid() {
        return of(UuidArgument.uuid());
    }

    @Override
    public ArgumentType<Boolean> bool() {
        return of(BoolArgumentType.bool());
    }

    @Override
    public ArgumentType<Float> floatArg(float min, float max) {
        return of(FloatArgumentType.floatArg(min, max));
    }

    @Override
    public ArgumentType<Double> doubleArg(double min, double max) {
        return of(DoubleArgumentType.doubleArg(min, max));
    }

    @Override
    public ArgumentType<Long> longArg(long min, long max) {
        return of(LongArgumentType.longArg(min, max));
    }

    @Override
    public ArgumentType<Integer> integer(int min, int max) {
        return of(IntegerArgumentType.integer(min, max));
    }

    private static <T> ArgumentType<T> of(com.mojang.brigadier.arguments.ArgumentType<T> type) {
        return new BrigArgWrapper<>(type);
    }
}
