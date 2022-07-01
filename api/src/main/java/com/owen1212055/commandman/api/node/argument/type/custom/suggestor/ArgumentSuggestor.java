package com.owen1212055.commandman.api.node.argument.type.custom.suggestor;

import org.bukkit.command.*;

import java.util.*;

public interface ArgumentSuggestor {

    List<ArgumentSuggestion> listSuggestions(CommandSender sender);

    default List<ArgumentSuggestion> toSuggestions(String... suggestions) {
        List<ArgumentSuggestion> suggestionList = new ArrayList<>(suggestions.length);
        for (String suggestion : suggestions) {
            suggestionList.add(new ArgumentSuggestion(suggestion, null));
        }

        return suggestionList;
    }
}
