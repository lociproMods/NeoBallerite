package net.locipro.balleritemod.util.data;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.locipro.balleritemod.BalleriteMod;
import net.locipro.balleritemod.item.ModItems;
import net.minecraft.advancements.critereon.EntityEquipmentPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public abstract class ModLootTableModifiers {
    private static final ResourceLocation GRASS_BLOCK_ID = new ResourceLocation("minecraft", "blocks/grass");
    private static final ResourceLocation IGLOO_STRUCTURE_CHEST_ID = new ResourceLocation("minecraft", "chests/igloo_chest");
    private static final ResourceLocation ABANDONED_MINESHAFT_CHEST_ID = new ResourceLocation("minecraft", "chests/abandoned_mineshaft");
    private static final ResourceLocation BURIED_TREASURE_CHEST_ID = new ResourceLocation("minecraft", "chests/buried_treasure");
    private static final ResourceLocation DESERT_PYRAMID_CHEST_ID = new ResourceLocation("minecraft", "chests/desert_pyramid");
    private static final ResourceLocation SPAWN_BONUS_CHEST_ID = new ResourceLocation("minecraft", "chests/spawn_bonus_chest");

    private static final ResourceLocation BASTION_BRIDGE_ID = new ResourceLocation("minecraft", "chests/bastion_bridge");
    private static final ResourceLocation BASTION_HOGLIN_STABLE_ID = new ResourceLocation("minecraft", "chests/bastion_hoglin_stable");
    private static final ResourceLocation BASTION_OTHER_ID = new ResourceLocation("minecraft", "chests/bastion_other");
    private static final ResourceLocation BASTION_TREASURE_ID = new ResourceLocation("minecraft", "chests/bastion_treasure");

    private static final ResourceLocation VILLAGE_ARMORER_ID = new ResourceLocation("minecraft", "chests/village/village_armorer");
    private static final ResourceLocation VILLAGE_PLAINS_HOUSE_ID = new ResourceLocation("minecraft", "chests/village/village_plains_house");
    private static final ResourceLocation VILLAGE_TOOLSMITH_ID = new ResourceLocation("minecraft", "chests/village/village_toolsmith");
    private static final ResourceLocation VILLAGE_WEAPONSMITH_ID = new ResourceLocation("minecraft", "chests/village/village_weaponsmith");

    private static final ResourceLocation ZOMBIE_ENTITY_ID = new ResourceLocation("minecraft", "entities/zombie");

    private static final ResourceLocation ANCIENT_CITY_ID = new ResourceLocation("minecraft", "chests/ancient_city");
    private static final ResourceLocation SIMPLE_DUNGEON_ID = new ResourceLocation("minecraft", "chests/simple_dungeon");
    private static final ResourceLocation STRONGHOLD_CORRIDOR_ID = new ResourceLocation("minecraft", "chests/stronghold_corridor");
    public static void modifyLootTables() {
        BalleriteMod.LOGGER.info("Modifying vanilla loot tables for mod: " + BalleriteMod.MOD_ID);

        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (GRASS_BLOCK_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(0.25f))
                            .add(LootItem.lootTableItem(ModItems.EGGPLANT_SEEDS))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
            if (GRASS_BLOCK_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.1f))
                        .add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 1f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
                if (ZOMBIE_ENTITY_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(0.75f))
                            .add(LootItem.lootTableItem(ModItems.EGGPLANT))
                            .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER,
                                    new EntityPredicate.Builder().equipment(EntityEquipmentPredicate.Builder.equipment()
                                            .mainhand(ItemPredicate.Builder.item().of(Items.STICK)).build()).build()))//ItemPredicate.Builder.create().create().items(Items.STICK).build()
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 1f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
                if (IGLOO_STRUCTURE_CHEST_ID.equals(id)) {
                    // Adds a Dowsing Rod into the Igloo Chest.
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(1f)) // Drops 100% of the time
                            .add(LootItem.lootTableItem(ModItems.BALL_DOWSER))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
                if (ABANDONED_MINESHAFT_CHEST_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(0.75F)) // Drops 75% of the time
                            .add(LootItem.lootTableItem(ModItems.COMPRESSED_BALLERITE))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 5.0f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
                if (BURIED_TREASURE_CHEST_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(0.5f)) // Drops 50% of the time
                            .add(LootItem.lootTableItem(ModItems.BALL_DOWSER))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 4.0f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
                if (SPAWN_BONUS_CHEST_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(1f)) // Drops 100% of the time
                            .add(LootItem.lootTableItem(ModItems.BALL_DOWSER))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
                if (DESERT_PYRAMID_CHEST_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(0.85f)) // Drops 85% of the time
                            .add(LootItem.lootTableItem(ModItems.COMPRESSED_BALLERITE))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 6.0f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
                if (VILLAGE_ARMORER_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(0.6f))
                            .add(LootItem.lootTableItem(ModItems.BALLERITE_HELMET))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 1f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }

                if (VILLAGE_PLAINS_HOUSE_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(0.8f)) // Drops 80% of the time
                            .add(LootItem.lootTableItem(ModItems.EGGPLANT_SEEDS))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0f, 16.0f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
                if (VILLAGE_TOOLSMITH_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(0.8f)) // Drops 80% of the time
                            .add(LootItem.lootTableItem(ModItems.BALLERITE_PICKAXE))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
                if (VILLAGE_WEAPONSMITH_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(0.8f)) // Drops 80% of the time
                            .add(LootItem.lootTableItem(ModItems.IRON_CLAYMORE))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }


                if (BASTION_BRIDGE_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(0.5f)) // Drops 50% of the time
                            .add(LootItem.lootTableItem(ModItems.PURPLE_CARROT))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
                if (BASTION_HOGLIN_STABLE_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(0.5f))
                            .add(LootItem.lootTableItem(ModItems.PURPLE_CARROT))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
                if (BASTION_OTHER_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(0.5f))
                            .add(LootItem.lootTableItem(ModItems.PURPLE_CARROT))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0F)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
                if (BASTION_TREASURE_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(0.5f))
                            .add(LootItem.lootTableItem(ModItems.PURPLE_CARROT))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
                if (ANCIENT_CITY_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(0.5f))
                            .add(LootItem.lootTableItem(ModItems.BALLERITE_HORSE_ARMOR))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 1f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
                if (DESERT_PYRAMID_CHEST_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(0.5f))
                            .add(LootItem.lootTableItem(ModItems.BALLERITE_HORSE_ARMOR))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 1f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
                if (SIMPLE_DUNGEON_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(0.5f))
                            .add(LootItem.lootTableItem(ModItems.BALLERITE_HORSE_ARMOR))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 1f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
                if (STRONGHOLD_CORRIDOR_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(0.75f))
                            .add(LootItem.lootTableItem(ModItems.BALLERITE_HORSE_ARMOR))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 1f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
        }));
    }
}
