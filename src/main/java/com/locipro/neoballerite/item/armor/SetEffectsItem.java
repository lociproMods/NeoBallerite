package com.locipro.neoballerite.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Objects;


public class SetEffectsItem extends ArmorItem {
    private final Holder<MobEffect> mobEffect;
    private final int durationTicks;
    private final int amplifier;
    private boolean hasEffect;
    private boolean visibleParticles = true;
    private boolean ambient = true;
//    private int ticksPassed = 0;

    public SetEffectsItem(Holder<ArmorMaterial> material, Type type, Properties properties, Holder<MobEffect> mobEffect, int durationTicks, int amplifier) {
        super(material, type, properties);
        this.mobEffect = mobEffect;
        this.durationTicks = durationTicks;
        this.amplifier = amplifier;
    }
    public SetEffectsItem effectParticles(boolean visible) {
        this.visibleParticles = visible;
        return this;
    }
    public SetEffectsItem ambient(boolean isAmbient) {
        this.ambient = isAmbient;
        return this;
    }

    public MobEffectInstance getSetEffectInstance() {
        return new MobEffectInstance(mobEffect, durationTicks, amplifier, ambient, visibleParticles);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
//        ticksPassed++;
//        if (ticksPassed >= durationTicks) {
//            hasEffect = false;
//            ticksPassed = 0;
//        }

        if (entity instanceof ServerPlayer player) {
            if (canGetEffect(player, mobEffect, amplifier)) {
                byte count = 0;
                for (ItemStack armorItemStack : player.getArmorSlots()) {
                    if (armorItemStack.getItem() instanceof ArmorItem armorItem) {
                        if (armorItem.getMaterial() == material) {
                            count++;
                        };
                    }
                }
                if (count == 4) {
                    player.addEffect(getSetEffectInstance());
                }
            }
        }

        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }


    /** Checks to see if the player already has a certain effect, and if that effect is higher than a certain threshold.
     * If the player already has a stronger effect, return false. Else true. **/
    private static boolean canGetEffect(ServerPlayer player, Holder<MobEffect> mobEffect, int amplifier) {
        if (player.hasEffect(mobEffect)) {
            return Objects.requireNonNull(player.getEffect(mobEffect)).getAmplifier() < amplifier;
        }
        return true;
    }
}
