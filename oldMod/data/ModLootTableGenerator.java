package net.locipro.balleritemod.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.locipro.balleritemod.block.ModBlocks;
import net.locipro.balleritemod.block.custom.*;
import net.locipro.balleritemod.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;



public class ModLootTableGenerator extends FabricBlockLootTableProvider {

    //REFER TO "VanillaBlockLootTableGenerator" FOR VANILLA RECIPES
    public ModLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }
    private static final float[] defaultSaplingDropChances = {0.02f, 0.022222223f, 0.025f, 0.033333335f, 0.1f}; //Just use SAPLING_DROP_CHANCE

    /**
     * <b>Generally, you use the addDrop method</b>. <br>
     * <i>For blocks that drop themselves, with no extra work needed, just use addDrop(block)</i>.<br>
     * For blocks that drop other blocks or items, use addDrop(block, drop).<br>
     * For crops, use addDrop(crop, CropLootTableGenerator()).
     * <br>
     * <b>"How am I supposed to know which method to use, and how to use it!"</b> <br>
     * Well, <i>"VanillaBlockLootTableGenerator"</i> has all the vanilla loottables, generated using fabric's api!
     */
    @Override
    public void generate() {
        add(ModBlocks.RAW_BALLERITE_BLOCK, block -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(ModItems.RAW_BALLERITE).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 7))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 1)).apply(LimitCount.limitCount(IntRange.range(5, 9))))));
        add(ModBlocks.BALLERITE_BLOCK, block -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(ModItems.BALLERITE).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 7))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 1)).apply(LimitCount.limitCount(IntRange.range(5, 9))))));
        dropSelf(ModBlocks.BURNT_BALLERITE_BLOCK);
        dropSelf(ModBlocks.CHARRED_BALLERITE_BLOCK);
        dropSelf(ModBlocks.COMPRESSED_BALLERITE_BLOCK);

        add(ModBlocks.BALLERITE_ORE, block -> addOreDropWithCount(block, ModItems.RAW_BALLERITE, 2, 4, 8));

        dropSelf(ModBlocks.WITHERED_SAPLING);
        dropSelf(ModBlocks.WITHERED_LOG);
        dropSelf(ModBlocks.WITHERED_WOOD);
        dropSelf(ModBlocks.STRIPPED_WITHERED_LOG);
        dropSelf(ModBlocks.STRIPPED_WITHERED_WOOD);
        dropSelf(ModBlocks.WITHERED_PLANKS);
        dropSelf(ModBlocks.WITHERED_BUTTON);
        dropSelf(ModBlocks.WITHERED_PRESSURE_PLATE);
        dropSelf(ModBlocks.WITHERED_FENCE);
        dropSelf(ModBlocks.WITHERED_FENCE_GATE);


        dropSelf(ModBlocks.WITHERED_TRAPDOOR);
        dropSelf(ModBlocks.WITHERED_STAIRS);
        add(ModBlocks.WITHERED_SLAB, this::createSlabItemTable);
        add(ModBlocks.WITHERED_DOOR, this::createDoorTable);


        dropSelf(ModBlocks.STAR_SAPLING);
        dropSelf(ModBlocks.STAR_LOG);
        dropSelf(ModBlocks.STAR_WOOD);
        dropSelf(ModBlocks.STRIPPED_STAR_LOG);
        dropSelf(ModBlocks.STRIPPED_STAR_WOOD);
        dropSelf(ModBlocks.STAR_PLANKS);
        dropSelf(ModBlocks.STAR_BUTTON);
        dropSelf(ModBlocks.STAR_PRESSURE_PLATE);
        dropSelf(ModBlocks.STAR_FENCE);
        dropSelf(ModBlocks.STAR_FENCE_GATE);


        dropSelf(ModBlocks.STAR_TRAPDOOR);
        dropSelf(ModBlocks.STAR_STAIRS);
        add(ModBlocks.STAR_SLAB, this::createSlabItemTable);
        add(ModBlocks.STAR_DOOR, this::createDoorTable);

        add(ModBlocks.COBBLESTONE_DOOR, this::createDoorTable);
        add(ModBlocks.COPPER_DOOR, this::createDoorTable);

        dropSelf(ModBlocks.COPPER_TRAPDOOR);
        dropSelf(ModBlocks.COBBLESTONE_TRAPDOOR);

        dropSelf(ModBlocks.COPPER_BUTTON);
        dropSelf(ModBlocks.COPPER_PRESSURE_PLATE);

        //This made me tear my hair out. this works though.
        //For drop chance you can use any array. "SAPLING_DROP_CHANCE" is minecraft's default.
        //This is the same as : addDrop(ModBlocks.WITHERED_LEAVES, leavesDrops(ModBlocks.WITHERED_LEAVES, ModBlocks.WITHERED_SAPLING, SAPLING_DROP_CHANCE));
        add(ModBlocks.WITHERED_LEAVES, block -> createLeavesDrops(block, ModBlocks.WITHERED_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));
        add(ModBlocks.STAR_LEAVES, block -> createLeavesDrops(block, ModBlocks.STAR_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));

        dropSelf(ModBlocks.LEAD_ORE);
        dropSelf(ModBlocks.DEEPSLATE_LEAD_ORE);

        add(ModBlocks.SWEET_POTATO_BLOCK, block -> {
            return applyExplosionDecay(block, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.SWEET_POTATO).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286f, 6)))));
        });

        add(ModBlocks.BLUE_BERRY_BUSH, block -> {
            return applyExplosionDecay(block, LootTable.lootTable()
                    .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.BLUE_BERRIES).when(blockPropertyLootCondition(block, ModBerryBushBlock.AGE, 3)).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 1)).apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 3f)))))
                    .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.BLUE_BERRIES).when(blockPropertyLootCondition(block, ModBerryBushBlock.AGE, 2)).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 1)).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 2f))))));
        });
        add(ModBlocks.BLACK_BERRY_BUSH, block -> {
            return applyExplosionDecay(block, LootTable.lootTable()
                    .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.BLACK_BERRIES).when(blockPropertyLootCondition(block, ModBerryBushBlock.AGE, 3)).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 1)).apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 3f)))))
                    .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.BLACK_BERRIES).when(blockPropertyLootCondition(block, ModBerryBushBlock.AGE, 2)).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 1)).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 2f))))));
        });

        add(ModBlocks.STRAWBERRY_BUSH, block -> {
            return applyExplosionDecay(block, LootTable.lootTable()
                    .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.STRAWBERRY_SEEDS)))
                    .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.STRAWBERRY).when(blockPropertyLootCondition(block, StrawberryBushBlock.AGE, 5)).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 1)).apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 3f)))))
                    .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.UNRIPE_STRAWBERRY).when(blockPropertyLootCondition(block, StrawberryBushBlock.AGE, 4)).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 1)).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 2f))))));
        });

        //Old code.
        //  This gives 1 eggplant and some random seeds per harvest.
        //  This is the same as : addDrop(ModBlocks.EGGPLANT_CROP, cropDrops(ModBlocks.EGGPLANT_CROP, ModItems.EGGPLANT, ModItems.EGGPLANT_SEEDS, eggplantCondition));
        //  addDrop(ModBlocks.EGGPLANT_CROP, (Block block) -> cropDrops(block, ModItems.EGGPLANT, ModItems.EGGPLANT_SEEDS, eggplantCondition));



        LootItemBlockStatePropertyCondition.Builder cornCondition = blockPropertyLootCondition(ModBlocks.CORN_CROP, CornCropBlock.AGE, 9);
        add(ModBlocks.CORN_CROP, block -> CropLootTableGenerator(block, ModItems.CORN_COB, cornCondition, 4, 8, false, 3));

        LootItemBlockStatePropertyCondition.Builder eggplantCondition = blockPropertyLootCondition(ModBlocks.EGGPLANT_CROP, EggplantCropBlock.AGE, 7);
        add(ModBlocks.EGGPLANT_CROP, block -> CropLootTableGenerator(block, ModItems.EGGPLANT, ModItems.EGGPLANT_SEEDS, eggplantCondition, 1, 3, true, 3));

        LootItemBlockStatePropertyCondition.Builder tomatoCondition = blockPropertyLootCondition(ModBlocks.TOMATO_CROP, TomatoCropBlock.AGE, 7);
        add(ModBlocks.TOMATO_CROP, block -> CropLootTableGenerator(block, ModItems.TOMATO, ModItems.TOMATO_SEEDS, tomatoCondition, 1, 5, true, 3));

        LootItemBlockStatePropertyCondition.Builder sweetPotatoCondition = blockPropertyLootCondition(ModBlocks.SWEET_POTATO_CROP, SweetPotatoCropBlock.AGE, 3);
        add(ModBlocks.SWEET_POTATO_CROP, block -> CropLootTableGenerator(block, ModItems.SWEET_POTATO, sweetPotatoCondition, 1, 4, true, 3));


        add(ModBlocks.TOMATO_FLOWER, block -> {
            return applyExplosionDecay(block, LootTable.lootTable()
                    .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.TOMATO).otherwise(LootItem.lootTableItem(ModItems.TOMATO_SEEDS))))
                    .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS)).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286f, 3))));
        });

    }

    /**
     * @param block The block
     * @param property The block's property
     * @param value The property's value.
     * @return Returns LootCondition.Builder that can be used in LootPool.builder(). builder pattern.
     */
    private LootItemBlockStatePropertyCondition.Builder blockPropertyLootCondition(Block block, Property<Integer> property, int value) {
        return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(property, value));
    }

    /**
     * @param condition Usually the .AGE state of the block. Use the provided "blockPropertyLootCondition" to make conditions then use them here.
     * @param affectedByFortune If the dropped products increase when using fortune-enchanted tools
     * @param fortuneExtra How much extra product can drop from fortune
     * @return Returns the LootTable that can be used in "addDrop(Block, LootTable)" method.
     */
    private LootTable.Builder CropLootTableGenerator(Block crop, Item product, Item seeds, LootItemCondition.Builder condition, float productMin, float productMax, boolean affectedByFortune, int fortuneExtra) {
        if (!affectedByFortune)
            return applyExplosionDecay(crop, LootTable.lootTable()
                    .withPool(LootPool.lootPool().add(LootItem.lootTableItem(seeds)))
                    .withPool(LootPool.lootPool().add(LootItem.lootTableItem(product).when(condition).apply(SetItemCountFunction.setCount(UniformGenerator.between(productMin, productMax))))));
        return applyExplosionDecay(crop, LootTable.lootTable()
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(seeds)))
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(product).when(condition).apply(SetItemCountFunction.setCount(UniformGenerator.between(productMin, productMax))).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286f, fortuneExtra)))));
    }
    /**
     * @param condition Usually the .AGE state of the block. Use the provided "blockPropertyLootCondition" to make conditions then use them here.
     * @param affectedByFortune If the dropped products increase when using fortune-enchanted tools
     * @param fortuneExtra How much extra product can drop from fortune
     * @return Returns the LootTable that can be used in "addDrop(Block, LootTable)" method.
     */
    private LootTable.Builder CropLootTableGenerator(Block crop, Item product, LootItemCondition.Builder condition, float productMin, float productMax, boolean affectedByFortune, int fortuneExtra) {
        if (!affectedByFortune)
            return applyExplosionDecay(crop, LootTable.lootTable()
                    .withPool(LootPool.lootPool().add(LootItem.lootTableItem(product)))
                    .withPool(LootPool.lootPool().when(condition).add(LootItem.lootTableItem(product).apply(SetItemCountFunction.setCount(UniformGenerator.between(productMin , productMax))))));
        return applyExplosionDecay(crop, LootTable.lootTable()
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(product)))
                .withPool(LootPool.lootPool().when(condition).add(LootItem.lootTableItem(product).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286f, fortuneExtra)))));
    }

    private LootTable.Builder addOreDropWithCount(Block dropWithSilkTouch, Item drop, int minCount, int maxCount, int maxFortuneCount) {
        return BlockLootSubProvider.createSilkTouchDispatchTable(dropWithSilkTouch, applyExplosionDecay(dropWithSilkTouch, LootItem.lootTableItem(drop).apply(SetItemCountFunction.setCount(UniformGenerator.between(minCount, maxCount))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)).apply(LimitCount.limitCount(IntRange.range(minCount, maxFortuneCount)))));
    }
}