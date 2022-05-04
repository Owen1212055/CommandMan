package com.owen1212055.commandman.api.node.argument.type.custom.suggestor;

import org.bukkit.command.*;

import java.util.*;

public interface ArgumentSuggestor {

    List<ArgumentSuggestion> listSuggestions(CommandSender sender);
}
