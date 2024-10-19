package com.locipro.neoballerite.datagen;


import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.component.NeoEnchantmentComponents;
import com.locipro.neoballerite.component.enchantment.MobEffectEnchantmentType;
import com.locipro.neoballerite.component.enchantment.NeoEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.providers.EnchantmentProvider;

import java.util.List;
import java.util.Optional;

public class NeoEnchantmentProvider {
    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Item> items = context.lookup(Registries.ITEM);
        HolderGetter<Enchantment> enchantments = context.lookup(Registries.ENCHANTMENT);

        context.register(
                NeoEnchantments.POISONED_TIP_ENCHANTMENT,
                new Enchantment( //https://docs.neoforged.net/docs/resources/server/enchantments/
                        Component.translatable("neoballerite.enchantment.poisoned_tip_enchantment"),
                        new Enchantment.EnchantmentDefinition(
                                items.getOrThrow(ItemTags.SHARP_WEAPON_ENCHANTABLE),
                                Optional.of(items.getOrThrow(ItemTags.SWORD_ENCHANTABLE)),
                                6,
                                3,
                                new Enchantment.Cost(10, 20),
                                new Enchantment.Cost(60, 20),
                                3,
                                List.of(EquipmentSlotGroup.HAND)
                        ),
                        HolderSet.direct(enchantments.getOrThrow(Enchantments.FIRE_ASPECT)),
                        DataComponentMap.builder()
                                .set(NeoEnchantmentComponents.MOB_EFFECT_ENCHANTMENT, new MobEffectEnchantmentType(
                                        MobEffects.POISON.value(),
                                        60,
                                        1, // Won't make an effect II , Will make it I.
                                        true,
                                        false
                                ))
                                .build()
                )
        );

        // Most definitely I could just make an enchantment and .withEffect like vanilla
        /*register(
            context,
            FIRE_ASPECT,
            Enchantment.enchantment(
                    Enchantment.definition(
                        holdergetter2.getOrThrow(ItemTags.FIRE_ASPECT_ENCHANTABLE),
                        holdergetter2.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                        2,
                        2,
                        Enchantment.dynamicCost(10, 20),
                        Enchantment.dynamicCost(60, 20),
                        4,
                        EquipmentSlotGroup.MAINHAND
                    )
                )
                .withEffect(
                    EnchantmentEffectComponents.POST_ATTACK,
                    EnchantmentTarget.ATTACKER,
                    EnchantmentTarget.VICTIM,
                    new Ignite(LevelBasedValue.perLevel(4.0F)),
                    DamageSourceCondition.hasDamageSource(DamageSourcePredicate.Builder.damageType().isDirect(true))
                )
        );*/
    }
}
