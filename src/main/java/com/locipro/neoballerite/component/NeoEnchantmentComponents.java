package com.locipro.neoballerite.component;

import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.component.enchantment.MobEffectEnchantmentType;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NeoEnchantmentComponents {
    public static final DeferredRegister<DataComponentType<?>> ENCHANTMENT_COMPONENT_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, NeoBallerite.MODID);




    public static final DeferredHolder<DataComponentType<?>, DataComponentType<MobEffectEnchantmentType>> MOB_EFFECT_ENCHANTMENT =
            ENCHANTMENT_COMPONENT_TYPES.register("mob_effect_enchantment",
                    () -> DataComponentType.<MobEffectEnchantmentType>builder()
                            .persistent(MobEffectEnchantmentType.CODEC)
                            .build());
}
