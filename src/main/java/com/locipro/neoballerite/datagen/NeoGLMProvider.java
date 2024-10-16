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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class NeoGLMProvider extends GlobalLootModifierProvider {
    public NeoGLMProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, NeoBallerite.MODID);
    }

    @Override
    protected void start() {



        add("eggplant_from_short_grass_modifier",
                addItemToBlock(Blocks.SHORT_GRASS, 1, 1, ModItems.EGGPLANT_SEEDS.get(), 0.33f));
        add("eggplant_from_tall_grass_modifier",
                addItemToBlock(Blocks.TALL_GRASS, 1, 2, ModItems.EGGPLANT_SEEDS.get(), 0.33f));


        add("corn_from_short_grass_modifier",
                addItemToBlock(Blocks.SHORT_GRASS, 1, 2, ModItems.CORN_KERNELS.get(), 0.1f));
        add("corn_from_tall_grass_modifier",
                addItemToBlock(Blocks.TALL_GRASS, 1, 1, ModItems.CORN_KERNELS.get(), 0.1f));



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


        add("ballerite_horse_armor_from_pyramid", addItemToChest("desert_pyramid", 1, 1, ModItems.BALLERITE_HORSE_ARMOR.get(), 0.3f) );
        add("ballerite_horse_armor_from_mineshaft", addItemToChest("abandoned_mineshaft", 1, 1, ModItems.BALLERITE_HORSE_ARMOR.get(), 0.3f));
        add("ballerite_horse_armor_from_dungeon", addItemToChest("simple_dungeon", 1, 1, ModItems.BALLERITE_HORSE_ARMOR.get(), 0.5f));
        add("ballerite_horse_armor_from_jungle_temple", addItemToChest("jungle_temple", 1, 1, ModItems.BALLERITE_HORSE_ARMOR.get(), 0.7f));
    }

    private NeoItemLootModifier addItemToChest(String chestID, int count1, int count2, Item drop, float chance) {
        return addItemToID("chests/" + chestID, count1, count2, drop, chance);
    }
    private NeoItemLootModifier addItemToID(String lootableID, int count1, int count2, Item drop, float chance) {
        return new NeoItemLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace(lootableID)).build(),
                LootItemRandomChanceCondition.randomChance(chance).build()
        }, count1, count2, drop);
    }
    private NeoItemLootModifier addItemToBlock(Block block, int count1, int count2, Item drop, float chance) {
        return new NeoItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).build(),
                LootItemRandomChanceCondition.randomChance(chance).build()
        }, count1, count2, drop);
    }
}
