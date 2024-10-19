package com.locipro.neoballerite.datagen;

import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.component.enchantment.NeoEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.locipro.neoballerite.component.enchantment.NeoEnchantments.POISONED_TIP_ENCHANTMENT;


public class NeoEnchantmentTagProvider extends EnchantmentTagsProvider {


    public NeoEnchantmentTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, NeoBallerite.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(EnchantmentTags.NON_TREASURE)
                .add(POISONED_TIP_ENCHANTMENT);
        tag(EnchantmentTags.IN_ENCHANTING_TABLE)
                .add(POISONED_TIP_ENCHANTMENT);
        tag(EnchantmentTags.TRADES_JUNGLE_COMMON)
                .add(POISONED_TIP_ENCHANTMENT);
        tag(EnchantmentTags.TRADES_SWAMP_COMMON)
                .add(POISONED_TIP_ENCHANTMENT);
    }
}
