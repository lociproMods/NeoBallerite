package com.locipro.neoballerite.datagen;

import com.locipro.neoballerite.NeoBallerite;
import com.locipro.neoballerite.block.ModBlocks;
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
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class NeoGLMProvider extends GlobalLootModifierProvider {
    public NeoGLMProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, NeoBallerite.MODID);
    }

    @Override
    protected void start() {



        add("eggplant_seeds_from_short_grass_modifier",
                addItemToBlock(Blocks.SHORT_GRASS, 1, 1, ModItems.EGGPLANT_SEEDS.get(), 0.33f, 0));
        add("eggplant_seeds_from_tall_grass_modifier",
                addItemToBlock(Blocks.TALL_GRASS, 1, 2, ModItems.EGGPLANT_SEEDS.get(), 0.33f, 0));


        add("corn_kernels_from_short_grass_modifier",
                addItemToBlock(Blocks.SHORT_GRASS, 1, 2, ModItems.CORN_KERNELS.get(), 0.1f, 0));
        add("corn_kernels_from_tall_grass_modifier",
                addItemToBlock(Blocks.TALL_GRASS, 1, 1, ModItems.CORN_KERNELS.get(), 0.1f, 0));



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

                }, 1, 1, ModItems.EGGPLANT.get(), 0));


        add("ballerite_horse_armor_from_pyramid", addItemToChest("desert_pyramid", 1, 1, ModItems.BALLERITE_HORSE_ARMOR.get(), 0.3f, 0));
        add("ballerite_horse_armor_from_mineshaft", addItemToChest("abandoned_mineshaft", 1, 1, ModItems.BALLERITE_HORSE_ARMOR.get(), 0.3f, 0));
        add("ballerite_horse_armor_from_dungeon", addItemToChest("simple_dungeon", 1, 1, ModItems.BALLERITE_HORSE_ARMOR.get(), 0.5f, 0));
        add("ballerite_horse_armor_from_jungle_temple", addItemToChest("jungle_temple", 1, 1, ModItems.BALLERITE_HORSE_ARMOR.get(), 0.7f, 0));

        add("lead_helmet_from_village_armorer", addItemToChest("village/village_armorer", 1, 1, ModItems.LEAD_HELMET.get(), 0.34f, 0));
        add("ballerite_helmet_from_village_armorer", addItemToChest("village/village_armorer", 1, 1, ModItems.BALLERITE_HELMET.get(), 0.125f, 0));

        add("ballerite_pickaxe_from_village_toolsmith", addItemToChest("village/village_toolsmith", 1, 1, ModItems.BALLERITE_PICKAXE.get(), 0.2f, 0));
        add("lead_axe_from_village_toolsmith", addItemToChest("village/village_toolsmith", 1, 1, ModItems.LEAD_AXE.get(), 0.35f, 0));


        add("iron_claymore_from_village_weaponsmith", addItemToChest("village/village_weaponsmith", 1, 1, ModItems.IRON_CLAYMORE.get(), 0.4f, 0));
        add("lead_sword_from_village_weaponsmith", addItemToChest("village/village_weaponsmith", 1, 1, ModItems.LEAD_SWORD.get(), 0.4f, 0));

        add("eggplant_seeds_from_village_plains_house", addItemToChest("village/village_plains_house", 1, 4, ModItems.EGGPLANT_SEEDS.get(), 0.50f, 3));
        add("tomato_seeds_from_village_plains_house", addItemToChest("village/village_plains_house", 1, 4, ModItems.TOMATO_SEEDS.get(), 0.45f, 3));
        add("withered_sapling_from_village_plains_house", addItemToChest("village/village_plains_house", 1, 2, ModBlocks.WITHERED_SAPLING.asItem(), 0.5f, 5));

        add("eggplant_seeds_from_village_savanna_house", addItemToChest("village/village_savanna_house", 1, 4, ModItems.EGGPLANT_SEEDS.get(), 0.50f, 3));
        add("tomato_seeds_from_village_savanna_house", addItemToChest("village/village_savanna_house", 1, 4, ModItems.TOMATO_SEEDS.get(), 0.45f, 3));
        add("corn_kernels_from_village_savanna_house", addItemToChest("village/village_savanna_house", 1, 4, ModItems.CORN_KERNELS.get(), 0.40f, 3));
        add("star_sapling_from_village_savanna_house", addItemToChest("village/village_savanna_house", 1, 2, ModBlocks.STAR_SAPLING.asItem(), 0.5f, 5));

        add("blueberries_from_village_taiga_house", addItemToChest("village/village_savanna_house", 1, 4, ModItems.BLUEBERRIES.get(), 0.40f, 3));
        add("blackberries_from_village_taiga_house", addItemToChest("village/village_savanna_house", 1, 4, ModItems.BLACKBERRIES.get(), 0.40f, 3));
        
        
        
        add("star_sapling_from_spawn_chest", addItemToChest("spawn_bonus_chest", 1, 2, ModBlocks.STAR_SAPLING.asItem(), 0.75f, 5));
        add("stone_claymore_from_spawn_chest", addItemToChest("spawn_bonus_chest", 1, 1, ModItems.STONE_CLAYMORE.get(), 0.4f, 0));


        add("diamond_carrot_from_mineshaft", addItemToChest("abandoned_mineshaft", 1, 1, ModItems.DIAMOND_CARROT.get(), 0.2f, 0));
        add("enchanted_diamond_carrot_from_mineshaft", addItemToChest("abandoned_mineshaft", 1, 1, ModItems.ENCHANTED_DIAMOND_CARROT.get(), 0.1f, 0));
        add("diamond_carrot_from_dungeon", addItemToChest("simple_dungeon", 1, 1, ModItems.DIAMOND_CARROT.get(), 0.3f, 1));
        // fix this
        //add("diamond_carrot_from_bastion")


    }

    private NeoItemLootModifier addItemToChest(String chestID, int count1, int count2, Item drop, float chance, int bonusRolls) {
        return addItemToID("chests/" + chestID, count1, count2, drop, chance, bonusRolls);
    }
    private NeoItemLootModifier addItemToID(String lootableID, int count1, int count2, Item drop, float chance, int bonusRolls) {
        return new NeoItemLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace(lootableID)).build(),
                LootItemRandomChanceCondition.randomChance(chance).build()
        }, count1, count2, drop, bonusRolls);
    }
    private NeoItemLootModifier addItemToBlock(Block block, int count1, int count2, Item drop, float chance, int bonusRolls) {
        return new NeoItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).build(),
                LootItemRandomChanceCondition.randomChance(chance).build()
        }, count1, count2, drop, bonusRolls);
    }
}