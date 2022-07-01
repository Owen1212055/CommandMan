package com.owen1212055.commandman.plugin.platform.bukkit.adapters.arguments;

import com.owen1212055.commandman.api.node.*;
import com.owen1212055.commandman.api.node.argument.type.*;
import com.owen1212055.commandman.plugin.platform.bukkit.adapters.arguments.types.impl.*;

import java.util.*;

public class BukkitArgumentAdapter implements ArgumentAdapter {

    private static final BooleanArgumentType BOOLEAN_ARGUMENT_TYPE = new BooleanArgumentType();
    @Override
    public ArgumentType<Boolean> bool() {
        return BOOLEAN_ARGUMENT_TYPE;
    }

    @Override
    public ArgumentType<Float> floatArg(float min, float max) {
        return new FloatArgumentType(min, max);
    }

    @Override
    public ArgumentType<Double> doubleArg(double min, double max) {
        return new DoubleArgumentType(min, max);
    }

    @Override
    public ArgumentType<Long> longArg(long min, long max) {
        return new LongArgumentType(min, max);
    }

    @Override
    public ArgumentType<Integer> integer(int min, int max) {
        return new IntegerArgumentType(min, max);
    }

    private static final StringArgumentType STRING_ARGUMENT_TYPE = new StringArgumentType(StringArgumentType.StringType.QUOTABLE_PHRASE);
    @Override
    public ArgumentType<String> string() {
        return STRING_ARGUMENT_TYPE;
    }

    private static final StringArgumentType WORD_ARGUMENT_TYE = new StringArgumentType(StringArgumentType.StringType.SINGLE_WORD);
    @Override
    public ArgumentType<String> word() {
        return WORD_ARGUMENT_TYE;
    }

    private static final StringArgumentType GREEDY_ARGUMENT_TYPE = new StringArgumentType(StringArgumentType.StringType.GREEDY_PHRASE);
    @Override
    public ArgumentType<String> greedy() {
        return GREEDY_ARGUMENT_TYPE;
    }

    private static final UuidArgumentType UUID_ARGUMENT_TYPE = new UuidArgumentType();
    @Override
    public ArgumentType<UUID> uuid() {
        return UUID_ARGUMENT_TYPE;
    }
}
