package com.owen1212055.commandman.api.node.argument.type.custom.suggestor;

import net.kyori.adventure.text.*;
import org.jetbrains.annotations.*;

public record ArgumentSuggestion(String suggestion, @Nullable Component component) {

}
