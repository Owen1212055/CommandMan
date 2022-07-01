package com.owen1212055.commandman.api.node;

import com.owen1212055.commandman.api.Command;
import com.owen1212055.commandman.api.CommandPlatforms;
import com.owen1212055.commandman.api.node.argument.ArgumentNode;
import com.owen1212055.commandman.api.node.argument.Arguments;
import com.owen1212055.commandman.api.node.argument.type.ArgumentType;
import com.owen1212055.commandman.api.node.command.CommandNode;
import com.owen1212055.commandman.api.node.literal.LiteralNode;

public class Nodes {

    private static final NodeAdapter adapter = CommandPlatforms.getPlatform().getNodeAdapter();

    public static <T> ArgumentNode<T> of(String name, ArgumentType<T> type) {
        return adapter.of(name, type);
    }

    public static ArgumentNode<String> string(String name) {
        return of(name, Arguments.string());
    }

    public static ArgumentNode<String> word(String name) {
        return of(name, Arguments.word());
    }

    public static ArgumentNode<String> greedy(String name) {
        return of(name, Arguments.greedy());
    }

    public static LiteralNode literal(String literal) {
        return adapter.literal(literal);
    }

    public static CommandNode command(Command<?> command) {
        return adapter.command(command);
    }

    public static ArgumentNode<Long> longArg(String name) {
        return longArg(name, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static ArgumentNode<Long> longArg(String name, long min, long max) {
        return of(name, Arguments.longArg(min, max));
    }

    public static ArgumentNode<Double> doubleArg(String name) {
        return doubleArg(name, Double.MIN_VALUE, Double.MAX_VALUE);
    }

    public static ArgumentNode<Double> doubleArg(String name, double min, double max) {
        return of(name, Arguments.doubleArg(min, max));
    }

    public static ArgumentNode<Float> floatArg(String name) {
        return floatArg(name, Float.MIN_VALUE, Float.MAX_VALUE);
    }

    public static ArgumentNode<Float> floatArg(String name, float min, float max) {
        return of(name, Arguments.floatArg(min, max));
    }

    public static ArgumentNode<Boolean> bool(String name) {
        return of(name, Arguments.bool());
    }

    public static ArgumentNode<Integer> integer(String amount) {
        return integer(amount, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static ArgumentNode<Integer> integer(String name, int min, int max) {
        return of(name, Arguments.integer(min, max));
    }
}
