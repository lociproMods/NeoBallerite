package com.locipro.neoballerite.item.armor;

import com.locipro.neoballerite.Config;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;


public class SetEffectsItem extends ArmorItem {
    private final Holder<MobEffect> mobEffect;
    private final ArmorMaterial material;
    private final int durationTicks;
    private final int amplifier;
    private boolean visibleParticles = false;
    private boolean ambient = false;
//    private int ticksPassed = 0;

    private static final Map<ArmorMaterial, Supplier<Boolean>> materialToConfigValue = Map.of(
            NeoArmorMaterials.LEAD, () -> Config.lead_armor_set_effects,
            NeoArmorMaterials.BALLERITE, () -> Config.ballerite_armor_set_effects
    );

    public SetEffectsItem(ArmorMaterial material, ArmorType type, Properties properties, Holder<MobEffect> mobEffect, int durationTicks, int amplifier) {
        super(material, type, properties);
        this.material = material;
        this.mobEffect = mobEffect;
        this.durationTicks = durationTicks;
        this.amplifier = amplifier;
    }
    public SetEffectsItem effectParticles() {
        this.visibleParticles = true;
        return this;
    }
    public SetEffectsItem ambient() {
        this.ambient = true;
        return this;
    }

    public MobEffectInstance getSetEffectInstance() {
        return new MobEffectInstance(mobEffect, durationTicks, amplifier, ambient, visibleParticles);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
        if (materialToConfigValue.containsKey(material) && materialToConfigValue.get(material).get()) {
            if (entity instanceof ServerPlayer player) {
                if (canGetEffect(player, mobEffect, amplifier)) {
                    byte count = 0;
                    for (ItemStack armorItemStack : player.getArmorSlots()) {
                        if (armorItemStack.getItem() instanceof ArmorItem armorItem) {
                            if (Objects.requireNonNull(armorItemStack.getComponents().get(DataComponents.EQUIPPABLE)).assetId().isPresent()) {
                                if (armorItemStack.getComponents().get(DataComponents.EQUIPPABLE).assetId().get().equals(material.assetId())) count++;
                            }
                        }
                    }

                    if (count >= 4) {
                        player.addEffect(getSetEffectInstance());
                    }
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
