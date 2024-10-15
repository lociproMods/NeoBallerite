package com.locipro.neoballerite.datagen;

import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.datagen.lootmodifier.NeoItemLootModifier;
import com.locipro.neoballerite.item.ModItems;
import net.minecraft.advancements.critereon.EntityEquipmentPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class NeoGLMProvider extends GlobalLootModifierProvider {
    public NeoGLMProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, NeoBallerite.MODID);
    }

    @Override
    protected void start() {
        // TODO reverte to 0.33f after testing
        add("eggplant_from_grass_modifier",
                new NeoItemLootModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.33f).build()
                }, 1, 2, ModItems.EGGPLANT_SEEDS.get()));

        add("eggplant_from_tall_grass_modifier",
                new NeoItemLootModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.25f).build()
                }, 1, 1, ModItems.EGGPLANT_SEEDS.get()));


        add("corn_from_grass_modifier",
                new NeoItemLootModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.1f).build()
                }, 1, 2, ModItems.CORN_KERNELS.get()));

        add("corn_from_tall_grass_modifier",
                new NeoItemLootModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.1f).build()
                }, 1, 1, ModItems.CORN_KERNELS.get()));



        add("eggplant_from_zombie",
                new NeoItemLootModifier(new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/zombie")).build(),
                        LootItemKilledByPlayerCondition.killedByPlayer().build(),
                        LootItemRandomChanceCondition.randomChance(0.75f).build(),
                        LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.ATTACKER,
                                new EntityPredicate.Builder().equipment(EntityEquipmentPredicate.Builder.equipment().mainhand(
                                        ItemPredicate.Builder.item().of(Items.STICK)
                                ).build()).build()).build()

                }, 1, 1, ModItems.EGGPLANT.get()));
    }
}
