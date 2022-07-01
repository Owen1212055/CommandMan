package com.owen1212055.commandman.plugin.platform.bukkit.adapters.arguments.types.impl;

import com.owen1212055.commandman.api.node.argument.type.custom.*;
import com.owen1212055.commandman.api.node.argument.type.custom.suggestor.*;
import com.owen1212055.commandman.plugin.platform.bukkit.adapters.arguments.types.*;
import org.bukkit.command.*;

import java.util.*;

public class StringArgumentType implements BukkitArgumentType<String>, ArgumentSuggestor {

    private final StringType type;
    private final List<ArgumentSuggestion> examples;

    public StringArgumentType(StringType type) {
        this.type = type;
        this.examples = toSuggestions(type.examples);
    }

    @Override
    public String parse(StringReader reader) throws ArgumentParsingException {
        if (type == StringType.GREEDY_PHRASE) {
            final String text = reader.getRemaining();
            reader.setCursor(reader.getTotalLength());
            return text;
        } else if (type == StringType.SINGLE_WORD) {
            return reader.readUnquotedString();
        } else {
            return reader.readString();
        }
    }

    @Override
    public List<ArgumentSuggestion> listSuggestions(CommandSender sender) {
        return examples;
    }

    public enum StringType {
        SINGLE_WORD("word", "words_with_underscores"),
        QUOTABLE_PHRASE("\"quoted phrase\"", "word", "\"\""),
        GREEDY_PHRASE("word", "words with spaces", "\"and symbols\""),
        ;

        private final String[] examples;

        StringType(final String... examples) {
            this.examples = examples;
        }

    }
}
