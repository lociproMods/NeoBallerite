package com.locipro.neoballerite.datagen;

import com.locipro.neoballerite.NeoBallerite;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.locipro.neoballerite.component.enchantment.NeoEnchantments.POISONED_TIP_ENCHANTMENT;
import static com.locipro.neoballerite.component.enchantment.NeoEnchantments.THORS_CURSE_ENCHANTMENT;


public class NeoEnchantmentTagProvider extends EnchantmentTagsProvider {


    public NeoEnchantmentTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, NeoBallerite.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(EnchantmentTags.NON_TREASURE)
                .add(POISONED_TIP_ENCHANTMENT)
                .add(THORS_CURSE_ENCHANTMENT);

        tag(EnchantmentTags.TRADES_JUNGLE_COMMON)
                .add(POISONED_TIP_ENCHANTMENT);
        tag(EnchantmentTags.TRADES_SWAMP_COMMON)
                .add(POISONED_TIP_ENCHANTMENT);

        tag(EnchantmentTags.TRADES_SAVANNA_SPECIAL)
                .add(THORS_CURSE_ENCHANTMENT);
        tag(EnchantmentTags.DOUBLE_TRADE_PRICE)
                .add(THORS_CURSE_ENCHANTMENT);

        tag(EnchantmentTags.ON_TRADED_EQUIPMENT)
                .add(THORS_CURSE_ENCHANTMENT)
                .add(POISONED_TIP_ENCHANTMENT);
    }
}
