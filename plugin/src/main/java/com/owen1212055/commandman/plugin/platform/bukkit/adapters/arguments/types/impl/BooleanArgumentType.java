package com.owen1212055.commandman.plugin.platform.bukkit.adapters.arguments.types.impl;

import com.owen1212055.commandman.api.node.argument.type.custom.*;
import com.owen1212055.commandman.api.node.argument.type.custom.suggestor.*;
import com.owen1212055.commandman.plugin.platform.bukkit.adapters.arguments.types.*;
import org.bukkit.command.*;

import java.util.*;

public class BooleanArgumentType implements BukkitArgumentType<Boolean> {

    private final List<ArgumentSuggestion> examples = toSuggestions("true", "false");

    @Override
    public Boolean parse(StringReader reader) throws ArgumentParsingException {
        return reader.readBoolean();
    }

    @Override
    public List<ArgumentSuggestion> listSuggestions(CommandSender sender) {
        return examples;
    }
}
