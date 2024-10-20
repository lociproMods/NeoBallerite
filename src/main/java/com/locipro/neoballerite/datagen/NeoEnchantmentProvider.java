package com.locipro.neoballerite.datagen;


import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.component.NeoEnchantmentComponents;
import com.locipro.neoballerite.component.enchantment.MobEffectEnchantmentType;
import com.locipro.neoballerite.component.enchantment.NeoEnchantments;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.effects.AllOf;
import net.minecraft.world.item.enchantment.effects.PlaySoundEffect;
import net.minecraft.world.item.enchantment.effects.SummonEntityEffect;
import net.minecraft.world.item.enchantment.providers.EnchantmentProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.*;

import java.util.List;
import java.util.Optional;

public class NeoEnchantmentProvider {
    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Item> items = context.lookup(Registries.ITEM);
        HolderGetter<Enchantment> enchantments = context.lookup(Registries.ENCHANTMENT);


        // Maybe I should refactor this to use the default minecraft mob effect enchantment... B-But my ego!
        context.register(
                NeoEnchantments.POISONED_TIP_ENCHANTMENT,
                new Enchantment( //https://docs.neoforged.net/docs/resources/server/enchantments/
                        Component.translatable(NeoBallerite.MODID + ".enchantment.poisoned_tip_enchantment"),
                        new Enchantment.EnchantmentDefinition(
                                items.getOrThrow(ItemTags.SHARP_WEAPON_ENCHANTABLE),
                                Optional.of(items.getOrThrow(ItemTags.SWORD_ENCHANTABLE)),
                                6,
                                3,
                                new Enchantment.Cost(10, 20),
                                new Enchantment.Cost(60, 20),
                                4,
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
        //Trident
        /*.withEffect(
                    EnchantmentEffectComponents.POST_ATTACK,
                    EnchantmentTarget.ATTACKER,
                    EnchantmentTarget.VICTIM,
                    AllOf.entityEffects(
                        new SummonEntityEffect(HolderSet.direct(EntityType.LIGHTNING_BOLT.builtInRegistryHolder()), false),
                        new PlaySoundEffect(SoundEvents.TRIDENT_THUNDER, ConstantFloat.of(5.0F), ConstantFloat.of(1.0F))
                    ),
                    AllOfCondition.allOf(
                        WeatherCheck.weather().setThundering(true),
                        LootItemEntityPropertyCondition.hasProperties(
                            LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().located(LocationPredicate.Builder.location().setCanSeeSky(true))
                        ),
                        LootItemEntityPropertyCondition.hasProperties(
                            LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.entity().of(EntityType.TRIDENT)
                        )
                    )
                )*/

        context.register(
                NeoEnchantments.THORS_CURSE_ENCHANTMENT,
                Enchantment.enchantment(
                                Enchantment.definition(
                                        items.getOrThrow(ItemTags.SHARP_WEAPON_ENCHANTABLE),
                                        items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                                        16,
                                        1,
                                        Enchantment.dynamicCost(24, 24),
                                        Enchantment.dynamicCost(64, 24),
                                        8,
                                        EquipmentSlotGroup.MAINHAND
                                )
                        )
                        .withEffect(
                                EnchantmentEffectComponents.POST_ATTACK,
                                EnchantmentTarget.ATTACKER,
                                EnchantmentTarget.VICTIM,
                                AllOf.entityEffects(
                                        new SummonEntityEffect(HolderSet.direct(entityHolder(EntityType.LIGHTNING_BOLT)), false)),
                                AllOfCondition.allOf(
                                        LootItemEntityPropertyCondition.hasProperties(
                                                LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().located(LocationPredicate.Builder.location().setCanSeeSky(true))
                                        )
                                )
                        )
                        .build(ResourceLocation.fromNamespaceAndPath(NeoBallerite.MODID, "thors_curse_enchantment"))
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


    private static Holder<EntityType<?>> entityHolder(EntityType<?> type) {
        return BuiltInRegistries.ENTITY_TYPE.wrapAsHolder(type);
    }
}
