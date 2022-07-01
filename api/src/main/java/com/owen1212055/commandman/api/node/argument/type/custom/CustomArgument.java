package com.owen1212055.commandman.api.node.argument.type.custom;

import com.owen1212055.commandman.api.node.argument.*;
import com.owen1212055.commandman.api.node.argument.type.*;
import com.owen1212055.commandman.api.node.argument.type.custom.suggestor.*;

public interface CustomArgument<T> extends ArgumentType<T>, ArgumentSuggestor {

    T parse(String string) throws ArgumentParsingException;

    default ParseType getParseType() {
        return ParseType.WORD;
    }

    default ArgumentType<?> getClientCompletion() {
        return switch (getParseType()) {
            case WORD -> Arguments.word();
            case GREEDY -> Arguments.greedy();
            case STRING -> Arguments.string();
        };
    }

}
