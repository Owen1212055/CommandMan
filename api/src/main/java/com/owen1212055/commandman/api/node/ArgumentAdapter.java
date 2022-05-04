package com.owen1212055.commandman.api.node;

import com.owen1212055.commandman.api.node.argument.type.*;

import java.util.*;

public interface ArgumentAdapter {

    ArgumentType<Boolean> bool();

    ArgumentType<Float> floatArg(float min, float max);

    ArgumentType<Double> doubleArg(double min, double max);

    ArgumentType<Long> longArg(long min, long max);

    ArgumentType<Integer> integer(int min, int max);

    ArgumentType<String> string();

    ArgumentType<String> word();

    ArgumentType<String> greedy();

    ArgumentType<UUID> uuid();
}
