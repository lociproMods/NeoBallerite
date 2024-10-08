package net.locipro.balleritemod.util;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.locipro.balleritemod.block.ModBlocks;
import net.locipro.balleritemod.client.HydrationHudOverlay;
import net.minecraft.client.renderer.RenderType;

public abstract class ModRenderHelper {
    public static void setAlphaLayers() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WITHERED_SAPLING, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WITHERED_LEAVES, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STAR_SAPLING, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STAR_LEAVES, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EGGPLANT_CROP, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CORN_CROP, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TOMATO_CROP, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TOMATO_FLOWER, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLUE_BERRY_BUSH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLACK_BERRY_BUSH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SWEET_POTATO_CROP, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STRAWBERRY_BUSH, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WITHERED_DOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STAR_DOOR, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WITHERED_TRAPDOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STAR_TRAPDOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COBBLESTONE_TRAPDOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_TRAPDOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COBBLESTONE_DOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_DOOR, RenderType.cutout());

    }

    public static void registerHudShaders() {
        HudRenderCallback.EVENT.register(new HydrationHudOverlay());
    }
}
