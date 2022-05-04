package com.owen1212055.commandman.api.node.argument;

import com.owen1212055.commandman.api.node.*;
import com.owen1212055.commandman.api.node.argument.type.*;

import java.util.*;

public class Arguments {

    private static ArgumentAdapter adapter;

    public static void setAdapter(ArgumentAdapter nodeAdapter) {
        if (adapter != null) {
            throw new IllegalStateException("Can't reset singleton!");
        }

        adapter = nodeAdapter;
    }

    public static ArgumentType<String> string() {
        return adapter.string();
    }

    public static ArgumentType<String> word() {
        return adapter.word();
    }

    public static ArgumentType<String> greedy() {
        return adapter.greedy();
    }

    public static ArgumentType<Integer> integer(int min, int max) {
        return adapter.integer(min, max);
    }

    public static ArgumentType<Long> longArg(long min, long max) {
        return adapter.longArg(min, max);
    }

    public static ArgumentType<Float> floatArg(float min, float max) {
        return adapter.floatArg(min, max);
    }

    public static ArgumentType<Double> doubleArg(double min, double max) {
        return adapter.doubleArg(min, max);
    }

    public static ArgumentType<Boolean> bool() {
        return adapter.bool();
    }

    public static ArgumentType<UUID> uuid() {
        return adapter.uuid();
    }
}
