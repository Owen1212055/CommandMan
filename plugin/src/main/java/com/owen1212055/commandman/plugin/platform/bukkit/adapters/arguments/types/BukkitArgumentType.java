package com.owen1212055.commandman.plugin.platform.bukkit.adapters.arguments.types;

import com.owen1212055.commandman.api.node.argument.type.*;
import com.owen1212055.commandman.api.node.argument.type.custom.*;
import com.owen1212055.commandman.api.node.argument.type.custom.suggestor.*;

public interface BukkitArgumentType<T> extends ArgumentType<T>, ArgumentSuggestor {

    T parse(StringReader reader) throws ArgumentParsingException;
}
