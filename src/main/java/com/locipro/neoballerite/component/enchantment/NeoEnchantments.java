package com.locipro.neoballerite.component.enchantment;

import com.locipro.neoballerite.NeoBallerite;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

public class NeoEnchantments {
    public static final ResourceKey<Enchantment> POISONED_TIP_ENCHANTMENT = createKey("poisoned_tip_enchantment");
    public static final ResourceKey<Enchantment> THORS_CURSE_ENCHANTMENT = createKey("thors_curse_enchantment");

    private static ResourceKey<Enchantment> createKey(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(NeoBallerite.MODID, name));
    }
}
