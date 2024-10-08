package net.locipro.balleritemod.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.locipro.balleritemod.item.StatusEffectSwordItem;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Tier;

public class LeadSword extends StatusEffectSwordItem {
    public LeadSword(Tier toolMaterial, int attackDamage, float attackSpeed) {
        super(toolMaterial, attackDamage, attackSpeed, new FabricItemSettings(), MobEffects.POISON, 60);
    }
}
