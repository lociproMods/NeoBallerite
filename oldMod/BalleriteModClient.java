package net.locipro.balleritemod;

import net.fabricmc.api.ClientModInitializer;
import net.locipro.balleritemod.event.KeyInputHandler;
import net.locipro.balleritemod.network.BalleritePackets;
import net.locipro.balleritemod.util.ModRenderHelper;

public class BalleriteModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModRenderHelper.setAlphaLayers();
        ModRenderHelper.registerHudShaders();

        KeyInputHandler.register();
        BalleritePackets.registerS2CPackets();
    }
}
