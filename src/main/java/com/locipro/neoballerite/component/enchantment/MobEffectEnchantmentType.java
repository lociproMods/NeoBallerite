package com.locipro.neoballerite.component.enchantment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;

/** Hey you big dumb fuck! You just spent the last hour working on this, succeeded, only to realize
 * that MINECRAFT ALREADY HAS A POST-ATTACK ADD-MOB-EFFECT EFFECT COMPONENT YOU IDIOT.
 * CHECK net.minecraft.world.item.enchantment.ApplyMobEffect**/
public record MobEffectEnchantmentType(MobEffect mobEffect, int duration, int amplifier, boolean particles_visible, boolean ambient) {
    public static final Codec<MobEffectEnchantmentType> CODEC =
            RecordCodecBuilder.create(instance ->
                    instance.group(
                            BuiltInRegistries.MOB_EFFECT.byNameCodec().fieldOf("effect").forGetter(MobEffectEnchantmentType::mobEffect),
                            Codec.INT.fieldOf("duration").forGetter(MobEffectEnchantmentType::duration),
                            Codec.INT.fieldOf("amplifier").forGetter(MobEffectEnchantmentType::duration),
                            Codec.BOOL.optionalFieldOf("particles_visible", true).forGetter(MobEffectEnchantmentType::particles_visible),
                            Codec.BOOL.optionalFieldOf("ambient", false).forGetter(MobEffectEnchantmentType::ambient)
                    ).apply(instance, MobEffectEnchantmentType::new));
}
