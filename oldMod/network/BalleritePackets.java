package net.locipro.balleritemod.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.locipro.balleritemod.BalleriteMod;
import net.locipro.balleritemod.network.packets.*;
import net.minecraft.resources.ResourceLocation;

public class BalleritePackets {
    public static final ResourceLocation DRINKING_ID = new ResourceLocation(BalleriteMod.MOD_ID, "drinking");
    public static final ResourceLocation THIRST_BOOLEAN_ID = new ResourceLocation(BalleriteMod.MOD_ID, "thirst_boolean_sync");
    public static final ResourceLocation CLIENT_JOINED_ID = new ResourceLocation(BalleriteMod.MOD_ID, "client_joined");
    public static final ResourceLocation THIRST_SYNC_ID = new ResourceLocation(BalleriteMod.MOD_ID, "thirst_sync");
    public static final ResourceLocation THIRST_TICK_SYNC_ID = new ResourceLocation(BalleriteMod.MOD_ID, "thirst_tick_sync");
    public static final ResourceLocation THIRST_TICK_MULTIPLIER_SYNC_ID = new ResourceLocation(BalleriteMod.MOD_ID, "thirst_tick_multiplier_sync");
    public static final ResourceLocation HYDRATION_DEBUG_HUD_BOOL_SYNC_ID = new ResourceLocation(BalleriteMod.MOD_ID, "debug_hud_bool_sync");


    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(DRINKING_ID, DrinkC2SPacket::receive);
        //ServerPlayNetworking.registerGlobalReceiver(CLIENT_JOINED_ID, ClientJoinedC2SPacket::receive);
    }
    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(THIRST_SYNC_ID, HydrationSyncS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(THIRST_TICK_SYNC_ID, HydrationTimerSyncS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(THIRST_BOOLEAN_ID, HydrationBooleanSyncS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(THIRST_TICK_MULTIPLIER_SYNC_ID, HydrationThirstMultiplierSyncS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(HYDRATION_DEBUG_HUD_BOOL_SYNC_ID, HydrationDebugHudBoolSyncS2CPacket::receive);
    }
}
