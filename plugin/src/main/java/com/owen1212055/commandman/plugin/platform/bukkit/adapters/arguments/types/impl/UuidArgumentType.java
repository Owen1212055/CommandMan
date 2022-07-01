package com.owen1212055.commandman.plugin.platform.bukkit.adapters.arguments.types.impl;

import com.owen1212055.commandman.api.node.argument.type.custom.*;
import com.owen1212055.commandman.api.node.argument.type.custom.suggestor.*;
import com.owen1212055.commandman.plugin.platform.bukkit.adapters.arguments.types.*;
import net.kyori.adventure.text.*;
import org.bukkit.command.*;

import java.util.*;
import java.util.regex.*;

public class UuidArgumentType implements BukkitArgumentType<UUID> {

    private static final Pattern ALLOWED_CHARACTERS = Pattern.compile("^([-A-Fa-f0-9]+)");
    private final List<ArgumentSuggestion> EXAMPLES = toSuggestions("dd12be42-52a9-4a91-a8a1-11c01849e498");

    @Override
    public UUID parse(StringReader reader) throws ArgumentParsingException {
        String string = reader.getRemaining();
        Matcher matcher = ALLOWED_CHARACTERS.matcher(string);

        if (matcher.find()) {
            String string2 = matcher.group(1);

            try {
                UUID uUID = UUID.fromString(string2);
                reader.setCursor(reader.getCursor() + string2.length());
                return uUID;
            } catch (IllegalArgumentException ignored) {
            }
        }

        throw new ArgumentParsingException(Component.translatable("argument.uuid.invalid"));
    }

    @Override
    public List<ArgumentSuggestion> listSuggestions(CommandSender sender) {
        return EXAMPLES;
    }
}
