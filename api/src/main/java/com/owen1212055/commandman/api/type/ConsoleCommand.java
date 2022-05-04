package com.owen1212055.commandman.api.type;


import com.owen1212055.commandman.api.Command;
import org.bukkit.command.*;

public interface ConsoleCommand extends Command<ConsoleCommandSender> {

    @Override
    default Class<ConsoleCommandSender> getTarget() {
        return ConsoleCommandSender.class;
    }
}
