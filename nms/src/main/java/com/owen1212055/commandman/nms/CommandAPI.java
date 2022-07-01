package com.owen1212055.commandman.nms;

import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.builder.*;
import com.mojang.brigadier.context.*;
import com.mojang.brigadier.tree.*;
import com.owen1212055.commandman.api.Command;
import com.owen1212055.commandman.api.*;
import com.owen1212055.commandman.api.node.*;
import com.owen1212055.commandman.api.node.argument.*;
import com.owen1212055.commandman.api.node.argument.type.custom.*;
import com.owen1212055.commandman.api.node.argument.type.custom.suggestor.*;
import com.owen1212055.commandman.api.node.command.CommandNode;
import com.owen1212055.commandman.api.node.literal.*;
import com.owen1212055.commandman.api.permission.*;
import com.owen1212055.commandman.nms.node.*;
import io.papermc.paper.adventure.*;
import net.minecraft.commands.*;
import net.minecraft.server.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.craftbukkit.v1_19_R1.command.VanillaCommandWrapper;
import org.bukkit.craftbukkit.v1_19_R1.scheduler.MinecraftInternalPlugin;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.scheduler.*;
import org.jetbrains.annotations.*;

import java.util.concurrent.*;

// Try to move more things to methods, as there is lots of dupe code.
public class CommandAPI {

    public static void register(Plugin plugin, com.owen1212055.commandman.api.Command<?> cmd) {
        Commands dispatcher = MinecraftServer.getServer().vanillaCommandDispatcher;

        com.mojang.brigadier.tree.CommandNode<CommandSourceStack> node = convertNode(Nodes.command(cmd), cmd, null, plugin);

        register(plugin, dispatcher, node);
    }


    public static com.mojang.brigadier.tree.CommandNode<CommandSourceStack> convertNode(Node node, com.owen1212055.commandman.api.Command<?> cmd,
                                                                                        @Nullable ArgumentBuilder<CommandSourceStack, ?> parent,
                                                                                        Plugin plugin) {
        ArgumentBuilder<CommandSourceStack, ?> builder;

        com.owen1212055.commandman.api.Command<?> targetCommand;
        if (node instanceof LiteralNode literalNode) {

            builder = LiteralArgumentBuilder.literal(literalNode.getLiteral());
            targetCommand = cmd;
        } else if (node instanceof ArgumentNode<?> argumentNode) {
            com.owen1212055.commandman.api.node.argument.type.ArgumentType<?> api = argumentNode.getType();
            ArgumentType<?> brigArg = toNms(api);

            RequiredArgumentBuilder<CommandSourceStack, ?> requiredArgumentBuilder = RequiredArgumentBuilder.argument(argumentNode.getName(), brigArg);

            if (api instanceof ArgumentSuggestor argumentSuggestor) {
                requiredArgumentBuilder.suggests((context, builder1) -> CompletableFuture.supplyAsync(() -> {
                    for (ArgumentSuggestion suggestion : argumentSuggestor.listSuggestions(context.getSource().getBukkitSender())) {
                        builder1.suggest(suggestion.suggestion(), PaperAdventure.asVanilla(suggestion.component()));
                    }

                    return builder1.build();
                }));
            }

            targetCommand = cmd;
            builder = requiredArgumentBuilder;
        } else if (node instanceof CommandNode commandNode) {
            targetCommand = commandNode.getCommand();

            builder = LiteralArgumentBuilder.literal(commandNode.getCommand().getName());
        } else {
            throw new UnsupportedOperationException("Couldn't figure out how to parse node: " + node);
        }

        if (node.getPermission() != null) {
            builder.requires((permission) -> hasPermission(node.getPermission(), permission));
        }

        if (node.isOptional() && parent != null) {
            parent.executes(ctx -> execute(ctx, (Command<CommandSender>) targetCommand));
        }

        if (node.getChildren().size() > 0) {
            for (Node children : node.getChildren()) {
                builder.then(convertNode(children, targetCommand, builder, plugin));
            }
        } else {
            builder.executes(ctx -> execute(ctx, (Command<CommandSender>) targetCommand));
        }

        if (node instanceof ArgumentNode<?> argNode && argNode.getType() instanceof CustomArgument<?> customArgument) {
            RequiredArgumentBuilder<CommandSourceStack, ?> requiredArgumentBuilder = (RequiredArgumentBuilder<CommandSourceStack, ?>) builder;

            ForceStringArgumentNode<CommandSourceStack> customArgHack = new ForceStringArgumentNode<>(toNms(customArgument.getClientCompletion()), requiredArgumentBuilder);

            for (com.mojang.brigadier.tree.CommandNode<CommandSourceStack> builderArgument : builder.getArguments()) {
                customArgHack.addChild(builderArgument);
            }

            return customArgHack;
        }

        com.mojang.brigadier.tree.CommandNode<CommandSourceStack> compiledNode = builder.build();

        if (node instanceof CommandNode commandNode) {
            for (String alias : commandNode.getCommand().getAliases()) {
                if (parent == null) { // Parent null? ROot!
                    register(plugin, getDispatcher(), makeAlias(alias, compiledNode).build());
                } else {
                    parent.then(makeAlias(alias, compiledNode));
                }
            }
        }

        return compiledNode;
    }

    public static ArgumentType<?> toNms(com.owen1212055.commandman.api.node.argument.type.ArgumentType<?> apiType) {
        if (apiType instanceof BrigArgumentType<?> brigArgumentType) {
            return brigArgumentType.getType();
        } else if (apiType instanceof CustomArgument<?> customArgument) {
            return new CustomArgWrapper<>(customArgument);
        } else {
            throw new UnsupportedOperationException("Couldn't figure out how to parse argument type: " + apiType);
        }
    }


    // See: https://github.com/Mojang/brigadier/issues/46
    protected static LiteralArgumentBuilder<CommandSourceStack> makeAlias(String alias, com.mojang.brigadier.tree.CommandNode<CommandSourceStack> commandNode) {
        LiteralArgumentBuilder<CommandSourceStack> argumentBuilder = Commands.literal(alias)
                .requires(commandNode.getRequirement())
                .forward(commandNode.getRedirect(), commandNode.getRedirectModifier(), commandNode.isFork())
                .executes(commandNode.getCommand());

        commandNode.getChildren().forEach(argumentBuilder::then);

        return argumentBuilder;
    }

    @SuppressWarnings("deprecation")
    private static Commands getDispatcher() {
        return MinecraftServer.getServer().vanillaCommandDispatcher;
    }

    private static void register(Plugin plugin, Commands commands, com.mojang.brigadier.tree.CommandNode<CommandSourceStack> node) {
        CommandMap map = Bukkit.getCommandMap();
        VanillaCommandWrapper wrapper = new VanillaCommandWrapper(commands, node);
        wrapper.setPermission(null);

        map.register(plugin.getName(), wrapper);

        // Delay register to prevent getting bundled with mc commands
        new BukkitRunnable() {
            @Override
            public void run() {
                commands.getDispatcher().getRoot().addChild(node);
            }
        }.runTask(new MinecraftInternalPlugin());

    }

    static boolean hasPermission(PermissionModule module, CommandSourceStack source) {
        CommandSender sender = source.getBukkitSender();
        if (sender instanceof Player player) {
            return module.hasPermission(player).hasPermission();
        } else {
            return true;
        }
    }

    private static int execute(CommandContext<CommandSourceStack> ctx, Command<CommandSender> command) {
        try {
            CommandSourceStack source = ctx.getSource();

            if (command.getTarget().isAssignableFrom(source.getBukkitSender().getClass())) {
                if (!hasPermission(command.getPermissionModule(), source)) {
                    source.getBukkitSender().sendMessage("No permission!");
                    return 0;
                }

                ArgumentSet argumentSet = new ArgumentSet();

                for (ParsedCommandNode<CommandSourceStack> parsedNode : ctx.getNodes()) {
                    com.mojang.brigadier.tree.CommandNode<CommandSourceStack> node = parsedNode.getNode();

                    if (node instanceof ArgumentCommandNode) {
                        argumentSet.set(node.getName(), ctx.getArgument(node.getName(), Object.class));
                    }

                    if (node instanceof LiteralCommandNode) {
                        argumentSet.set(node.getName(), true);
                    }
                }

                command.execute(source.getBukkitSender(), argumentSet);
                return 1;
            } else {
                source.getBukkitSender().sendMessage("Unexpected sender!");
                return 0;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return 0;
    }
}
