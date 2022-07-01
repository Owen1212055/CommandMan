package com.owen1212055.commandman.plugin.platform.bukkit.adapters.arguments.types.impl;

import com.owen1212055.commandman.api.node.argument.type.custom.*;
import com.owen1212055.commandman.api.node.argument.type.custom.suggestor.*;
import com.owen1212055.commandman.plugin.platform.bukkit.adapters.arguments.types.*;
import net.kyori.adventure.text.*;
import org.bukkit.command.*;

import java.util.*;

public class LongArgumentType implements BukkitArgumentType<Long> {

    private final List<ArgumentSuggestion> EXAMPLES = toSuggestions("0", "123", "-123");

    private final long min;
    private final long max;

    public LongArgumentType(long min, long max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Long parse(StringReader reader) throws ArgumentParsingException {
        final int start = reader.getCursor();
        final long result = reader.readLong();
        if (result < min) {
            reader.setCursor(start);
            throw new ArgumentParsingException(Component.translatable("argument.long.low", Component.text(min), Component.text(result)));
        }
        if (result > max) {
            reader.setCursor(start);
            throw new ArgumentParsingException(Component.translatable("argument.long.high", Component.text(max), Component.text(result)));
        }
        return result;
    }

    @Override
    public List<ArgumentSuggestion> listSuggestions(CommandSender sender) {
        return EXAMPLES;
    }
}
