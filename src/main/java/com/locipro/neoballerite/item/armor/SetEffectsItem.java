package com.locipro.neoballerite.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

// TODO do this stuff.
public class SetEffectsItem extends ArmorItem {
    private Holder<MobEffect> mobEffect;
    private int durationTicks;
    private int amplifier;

    public SetEffectsItem(Holder<ArmorMaterial> material, Type type, Properties properties, MobEffect mobEffect, int durationTicks, int amplifier) {
        super(material, type, properties);
        this.mobEffect = Holder.direct(mobEffect);
        this.durationTicks = durationTicks;
        this.amplifier = amplifier;
    }
    public MobEffectInstance getSetEffectInstance() {
        return new MobEffectInstance(mobEffect, durationTicks, amplifier);
    }
}
