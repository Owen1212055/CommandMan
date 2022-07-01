package com.owen1212055.commandman.nms;

import com.mojang.brigadier.*;
import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.exceptions.*;
import com.owen1212055.commandman.api.node.argument.type.custom.*;
import io.papermc.paper.adventure.*;

public class CustomArgWrapper<T> implements ArgumentType<T> {

    private final CustomArgument<T> customArgument;

    public CustomArgWrapper(CustomArgument<T> customArgument) {
        this.customArgument = customArgument;
    }

    @Override
    public T parse(StringReader reader) throws CommandSyntaxException {
        try {
            switch (customArgument.getParseType()) {
                case GREEDY -> {
                    final String text = reader.getRemaining();
                    reader.setCursor(reader.getTotalLength());

                    return customArgument.parse(text);
                }
                case WORD -> {
                    return customArgument.parse(reader.readUnquotedString());
                }
                case STRING -> {
                    return customArgument.parse(reader.readString());
                }
            }
        } catch (ArgumentParsingException e) {
            throw new SimpleCommandExceptionType(PaperAdventure.asVanilla(e.getComponent())).createWithContext(reader);
        }

        return null;
    }


}
