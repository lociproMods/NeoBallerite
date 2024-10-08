package net.locipro.balleritemod.util.data;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.locipro.balleritemod.network.BalleritePackets;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class HydrationData {
    public static int addHydration(IEntityDataSaver player, int amount) {
        CompoundTag nbt = player.getPersistentData();
        int hydration = nbt.getInt("hydration");

        if (hydration + amount >= 10) {
            hydration = 10;
        }else {
            hydration += amount;
        }

        nbt.putInt("hydration", hydration);

        syncThirst(hydration, (ServerPlayer) player);

        return hydration;
    }

    public static int removeHydration(IEntityDataSaver player, int amount) {
        CompoundTag nbt = player.getPersistentData();
        int hydration = nbt.getInt("hydration");


        if(hydration - amount < 0) {
            hydration = 0;
        } else {
            hydration -= amount;
        }



        nbt.putInt("hydration", hydration);

        syncThirst(hydration, (ServerPlayer) player);

        return hydration;
    }

    public static int getHydration(IEntityDataSaver player) {
        CompoundTag nbt = player.getPersistentData();
        return nbt.getInt("hydration");
    }

    public static void syncThirst(int thirst, ServerPlayer player) {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(thirst);
        ServerPlayNetworking.send(player, BalleritePackets.THIRST_SYNC_ID, buffer);
    }
    public static void syncThirstTimer(int time, ServerPlayer player) {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(time);
        ServerPlayNetworking.send(player, BalleritePackets.THIRST_TICK_SYNC_ID, buffer);
    }

}
