package net.locipro.balleritemod.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.locipro.balleritemod.util.data.IEntityDataSaver;
import net.locipro.balleritemod.util.data.HydrationData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;


public class PlayerTickHandler implements ServerTickEvents.StartTick{
    //
    // TODO it feels like there's an issue waiting to happen here - if not already happend
    int thirstTickTimer = 24000/10;
    @Override
    public void onStartTick(MinecraftServer server) {

        for (ServerPlayer playerEntity : server.getPlayerList().getPlayers()) {
            IEntityDataSaver player = ((IEntityDataSaver) playerEntity);
            int time = (player.getPersistentData().getInt("thirst_timer"));
            if (time >= thirstTickTimer/10) {
                time = 0;
                player.getPersistentData().putInt("thirst_timer", 0);
                HydrationData.removeHydration(player, 1);
                HydrationData.syncThirstTimer(time, playerEntity);
            }else {
                player.getPersistentData().putInt("thirst_timer", ++time);
            }
        }
    }
}
