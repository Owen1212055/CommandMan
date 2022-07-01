package com.owen1212055.commandman.nms;

import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.builder.*;
import com.mojang.brigadier.tree.*;
import net.minecraft.commands.*;

/*
Ensures that the client is sent string argument types
 */
public class ForceStringArgumentNode<T> extends ArgumentCommandNode<CommandSourceStack, T> {

    private final ArgumentType<?> vanillaType;

    public ForceStringArgumentNode(ArgumentType<?> vanillaType, RequiredArgumentBuilder<CommandSourceStack, ?> builder) {
        super(builder.getName(),
                (ArgumentType<T>) builder.getType(),
                builder.getCommand(),
                builder.getRequirement(),
                builder.getRedirect(),
                builder.getRedirectModifier(),
                builder.isFork(),
                builder.getSuggestionsProvider());

        this.vanillaType = vanillaType;
    }

    @SuppressWarnings("unchecked")
    @Override
    public RequiredArgumentBuilder<CommandSourceStack, T> createBuilder() {
        RequiredArgumentBuilder<CommandSourceStack, ?> builder = RequiredArgumentBuilder.argument(getName(), vanillaType);
        builder.requires(getRequirement());
        builder.forward(getRedirect(), getRedirectModifier(), isFork());
        builder.suggests(getCustomSuggestions());
        if (getCommand() != null) {
            builder.executes(getCommand());
        }

        return (RequiredArgumentBuilder<CommandSourceStack, T>) builder;
    }

}
