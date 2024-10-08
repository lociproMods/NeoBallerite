package net.locipro.balleritemod.hydration;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.locipro.balleritemod.network.BalleritePackets;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public abstract class HydrationHelper {
    public static void syncHydration(ServerPlayer player) {
        HydrationManager hydrationManager = ((IHydratable)player).getHydrationManager();
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(hydrationManager.getHydration());
        ServerPlayNetworking.send(player, BalleritePackets.THIRST_SYNC_ID, buffer);
    }
    public static void syncHydrationBoolean(ServerPlayer player) {
        HydrationManager hydrationManager = ((IHydratable)player).getHydrationManager();
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeBoolean(hydrationManager.hasThirst());
        ServerPlayNetworking.send(player, BalleritePackets.THIRST_BOOLEAN_ID, buffer);
    }
    public static void syncHydrationThirstTickTimer(ServerPlayer player) {
        HydrationManager hydrationManager = ((IHydratable)player).getHydrationManager();
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(hydrationManager.getHydration());
        ServerPlayNetworking.send(player, BalleritePackets.THIRST_TICK_SYNC_ID, buffer);
    }
    public static void syncHydrationThirstTickMultiplier(ServerPlayer player) {
        HydrationManager hydrationManager = ((IHydratable)player).getHydrationManager();
        FriendlyByteBuf buf = PacketByteBufs.create();
        buf.writeInt(hydrationManager.getThirstTickMultiplier());
        ServerPlayNetworking.send(player, BalleritePackets.THIRST_TICK_MULTIPLIER_SYNC_ID, buf);
    }
    public static void syncDebugHudBoolean(ServerPlayer player) {
        HydrationManager hydrationManager = ((IHydratable)player).getHydrationManager();
        FriendlyByteBuf buf = PacketByteBufs.create();
        buf.writeBoolean(hydrationManager.getDebugHudBool());
        ServerPlayNetworking.send(player, BalleritePackets.HYDRATION_DEBUG_HUD_BOOL_SYNC_ID, buf);
    }
    public static void syncAllHydration(ServerPlayer player) {
        HydrationManager hydrationManager = ((IHydratable)player).getHydrationManager();

        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(hydrationManager.getHydration());
        ServerPlayNetworking.send(player, BalleritePackets.THIRST_SYNC_ID, buffer);

        FriendlyByteBuf buffer1 = PacketByteBufs.create();
        buffer1.writeInt(hydrationManager.getThirstTickTimer());
        ServerPlayNetworking.send(player, BalleritePackets.THIRST_TICK_SYNC_ID, buffer1);

        FriendlyByteBuf buffer2 = PacketByteBufs.create();
        buffer2.writeBoolean(hydrationManager.hasThirst());
        ServerPlayNetworking.send(player, BalleritePackets.THIRST_BOOLEAN_ID, buffer2);

        FriendlyByteBuf buffer3 = PacketByteBufs.create();
        buffer3.writeInt(hydrationManager.getThirstTickMultiplier());
        ServerPlayNetworking.send(player, BalleritePackets.THIRST_TICK_MULTIPLIER_SYNC_ID, buffer3);

        FriendlyByteBuf buffer4 = PacketByteBufs.create();
        buffer4.writeBoolean(hydrationManager.getDebugHudBool());
        ServerPlayNetworking.send(player, BalleritePackets.HYDRATION_DEBUG_HUD_BOOL_SYNC_ID, buffer4);
    }
    public static void copyFrom(ServerPlayer oldPlayer, ServerPlayer player) {
        HydrationManager oldHydrationManager = ((IHydratable)oldPlayer).getHydrationManager();
        HydrationManager newHydrationManager = ((IHydratable)player).getHydrationManager();
        newHydrationManager.setThirstBool(oldHydrationManager.hasThirst());
        newHydrationManager.setHydration(oldHydrationManager.getHydration());
        newHydrationManager.setThirstTickTimer(oldHydrationManager.getThirstTickTimer());
    }
}
