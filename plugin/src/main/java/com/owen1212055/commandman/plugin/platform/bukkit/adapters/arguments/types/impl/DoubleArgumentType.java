package com.owen1212055.commandman.plugin.platform.bukkit.adapters.arguments.types.impl;

import com.owen1212055.commandman.api.node.argument.type.custom.*;
import com.owen1212055.commandman.api.node.argument.type.custom.suggestor.*;
import com.owen1212055.commandman.plugin.platform.bukkit.adapters.arguments.types.*;
import net.kyori.adventure.text.*;
import org.bukkit.command.*;

import java.util.*;

public class DoubleArgumentType implements BukkitArgumentType<Double> {

    private final List<ArgumentSuggestion> EXAMPLES = toSuggestions("0", "1.2", ".5", "-1", "-.5", "-1234.56");

    private final double min;
    private final double max;

    public DoubleArgumentType(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Double parse(StringReader reader) throws ArgumentParsingException {
        final int start = reader.getCursor();
        final double result = reader.readDouble();
        if (result < min) {
            reader.setCursor(start);
            throw new ArgumentParsingException(Component.translatable("argument.double.low", Component.text(min), Component.text(result)));
        }
        if (result > max) {
            reader.setCursor(start);
            throw new ArgumentParsingException(Component.translatable("argument.double.high", Component.text(max), Component.text(result)));
        }
        return result;
    }

    @Override
    public List<ArgumentSuggestion> listSuggestions(CommandSender sender) {
        return EXAMPLES;
    }
}
