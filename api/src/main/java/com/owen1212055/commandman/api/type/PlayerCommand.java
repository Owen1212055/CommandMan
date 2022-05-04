package com.owen1212055.commandman.api.type;

import com.owen1212055.commandman.api.*;
import org.bukkit.entity.*;

public interface PlayerCommand extends Command<Player> {

    @Override
    default Class<Player> getTarget() {
        return Player.class;
    }
}
