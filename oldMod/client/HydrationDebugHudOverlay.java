package net.locipro.balleritemod.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.GuiGraphics;

@Environment(EnvType.CLIENT)
public class HydrationDebugHudOverlay implements HudRenderCallback {
    //TODO figure this shit out.
    @Override
    public void onHudRender(GuiGraphics drawContext, float tickDelta) {

    }
}
