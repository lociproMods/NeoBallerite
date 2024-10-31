package com.locipro.neoballerite.item.util;

import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.component.NeoDataComponents;
import com.locipro.neoballerite.item.ModItems;
import com.locipro.neoballerite.item.NeoSandwiches;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;


@Mod(value = NeoBallerite.MODID, dist = Dist.CLIENT)
public class SandwichProperties {
    public static void addSandwichProperties() {
        // For the RENDERER. Check package of ItemProperties
        ItemProperties.register(ModItems.SANDWICH.get(),
                ResourceLocation.fromNamespaceAndPath(NeoBallerite.MODID, "bread"),
                (itemStack, clientLevel, livingEntity, i) -> {
                    if (itemStack.has(NeoDataComponents.SANDWICH_BREAD)) {
                        return NeoSandwiches.BREAD_MAP.get(itemStack.get(NeoDataComponents.SANDWICH_BREAD));
                    }
                    return 0f;
                });
        ItemProperties.register(ModItems.SANDWICH.get(),
                ResourceLocation.fromNamespaceAndPath(NeoBallerite.MODID, "meat"),
                (itemStack, clientLevel, livingEntity, i) -> {
                    if (itemStack.has(NeoDataComponents.SANDWICH_MEAT)) {
                        return NeoSandwiches.MEAT_MAP.get(itemStack.get(NeoDataComponents.SANDWICH_MEAT));
                    }
                    return 0f;
                });
        ItemProperties.register(ModItems.SANDWICH.get(),
                ResourceLocation.fromNamespaceAndPath(NeoBallerite.MODID, "cheese"),
                (itemStack, clientLevel, livingEntity, i) -> {
                    if (itemStack.has(NeoDataComponents.SANDWICH_CHEESE)) {
                        return NeoSandwiches.CHEESE_MAP.get(itemStack.get(NeoDataComponents.SANDWICH_CHEESE));
                    }
                    return 0f;
                });
    }
}
