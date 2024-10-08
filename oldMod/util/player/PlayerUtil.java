package net.locipro.balleritemod.util.player;

import net.minecraft.world.entity.player.Player;

public abstract class PlayerUtil {
    public static String getPlayerName(Player player) {
        String name;
        name = player.getName().toString();
        name = name.replace("literal{", "");
        name = name.replace("}", "");
        return name;
    }
}
