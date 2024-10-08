package net.locipro.balleritemod.network.packets;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.locipro.balleritemod.hydration.IHydratable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;

public class HydrationBooleanSyncS2CPacket {
    public static void receive(Minecraft client, ClientPacketListener handler,
                               FriendlyByteBuf buf, PacketSender responseSender) {
        if (client.player != null) {
            ((IHydratable)client.player).getHydrationManager().setThirstBool(buf.readBoolean());
        }
    }
}
