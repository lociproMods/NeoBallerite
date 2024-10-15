package com.locipro.neoballerite.datagen.lootmodifier;

import com.locipro.neoballerite.NeoBallerite;
import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class GLMSerializers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, NeoBallerite.MODID);

    public static final Supplier<MapCodec<NeoItemLootModifier>> NEO_ITEM_LOOT_MODIFIER =
            GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("neo_item_loot_modifier", () -> NeoItemLootModifier.CODEC);
}
