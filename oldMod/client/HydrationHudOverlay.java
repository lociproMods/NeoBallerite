package net.locipro.balleritemod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.locipro.balleritemod.BalleriteMod;
import net.locipro.balleritemod.hydration.HydrationManager;
import net.locipro.balleritemod.hydration.IHydratable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;


@Environment(EnvType.CLIENT)
public class HydrationHudOverlay implements HudRenderCallback {
    private static final ResourceLocation FILLED_HYDRATION = new ResourceLocation(BalleriteMod.MOD_ID,
            "textures/hud/hydration/filled_hydration.png");
    private static final ResourceLocation EMPTY_HYDRATION = new ResourceLocation(BalleriteMod.MOD_ID,
            "textures/hud/hydration/empty_hydration.png");
    @Override
    public void onHudRender(GuiGraphics drawContext, float tickDelta) {
        int x = 0;
        int y = 0;


        Minecraft client = Minecraft.getInstance();
        if (client.player != null && client.gameMode != null) {
            HydrationManager hydrationManager = ((IHydratable)client.player).getHydrationManager();

            if (hydrationManager.hasThirst()) {
                int width = client.getWindow().getGuiScaledWidth();
                int height = client.getWindow().getGuiScaledHeight();

                x = width / 2;
                y = height;
                if (client.gameMode.getPlayerMode().isSurvival()) {
                    RenderSystem.setShader(GameRenderer::getPositionColorShader);
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

                    /*int m = width / 2 - 91;
                    int n = width / 2 + 91;

                    drawContext.drawTexture(EMPTY_HYDRATION, m, y-54, 0, 0, 11, 11, 11, 11);
                    drawContext.drawTexture(EMPTY_HYDRATION, n, y-54, 0, 0, 11, 11, 11, 11);*/

                    RenderSystem.setShaderTexture(0, EMPTY_HYDRATION);
                    for(int i = 0; i < 10; i++) {
                        drawContext.blit(EMPTY_HYDRATION, x + (i * 8) + 8 ,y - 54,0,0,11,12,
                                11,12);
                    }

                    RenderSystem.setShaderTexture(0, FILLED_HYDRATION);
                    for (int j = 10; j > 0; j--) {
                        if (hydrationManager.getHydration() >= j) {
                            drawContext.blit(FILLED_HYDRATION, x + (10-j) * 8 + 8, y - 54, 0, 0, 11, 12,
                                    11, 12);
                        }
                    }


                   /* RenderSystem.setShaderTexture(0, FILLED_HYDRATION);
                    for(int i = 0; i < 10; i++) {
                        if(hydrationManager.getHydration() > i) {
                            drawContext.drawTexture(FILLED_HYDRATION, x + (i * 8) + 8,y - 54,0,0,11,12,
                                    11,12);
                        } else {
                            break;
                        }
                    }*/
                }
            }
        }
    }

}
