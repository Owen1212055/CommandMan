package com.owen1212055.commandman.api.type;

import com.owen1212055.commandman.api.Command;
import org.bukkit.command.*;

public interface UniversalCommand extends Command<CommandSender> {

    @Override
    default Class<CommandSender> getTarget() {
        return CommandSender.class;
    }
}
