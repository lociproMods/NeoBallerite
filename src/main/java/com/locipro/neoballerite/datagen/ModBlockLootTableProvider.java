package com.locipro.neoballerite.datagen;

import com.locipro.neoballerite.block.ModBlocks;
import com.locipro.neoballerite.block.custom.*;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Set;
import static com.locipro.neoballerite.block.ModBlocks.*;
import static com.locipro.neoballerite.item.ModItems.*;


public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }
    protected static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    protected static final float[] HIGHER_LEAVES_SAPLING_CHANCES = new float[]{0.06F, 0.0725F, 0.093333336F, 0.12F};


    @Override
    protected void generate() {


        add(BALLERITE_ORE.get(),
                createVariableOreDrops(BALLERITE_ORE.get(), RAW_BALLERITE, UniformGenerator.between(1, 4)));
        add(LEAD_ORE.get(),
                createVariableOreDrops(LEAD_ORE.get(), RAW_LEAD, UniformGenerator.between(1, 2)));
        add(DEEPSLATE_LEAD_ORE.get(),
                createVariableOreDrops(DEEPSLATE_LEAD_ORE.get(), RAW_LEAD, UniformGenerator.between(1, 2)));
        // Like clay
        add(RAW_BALLERITE_BLOCK.get(),
                createSingleItemTableWithSilkTouch(RAW_BALLERITE_BLOCK.get(), RAW_BALLERITE, ConstantValue.exactly(4.0f)));
        add(COOKED_BALLERITE_BLOCK.get(),
                createSingleItemTableWithSilkTouch(COOKED_BALLERITE_BLOCK.get(), COOKED_BALLERITE, ConstantValue.exactly(4.0f)));
        dropSelf(BURNT_BALLERITE_BLOCK.get());
        dropSelf(CHARRED_BALLERITE_BLOCK.get());
        dropSelf(COMPRESSED_BALLERITE_BLOCK.get());
        dropSelf(LEAD_BLOCK.get());
        dropSelf(RAW_LEAD_BLOCK.get());


        dropSelf(WITHERED_SAPLING.get());
        dropSelf(WITHERED_LOG.get());
        dropSelf(STRIPPED_WITHERED_LOG.get());

        add(WITHERED_LEAVES.get(), createLeavesDrops(WITHERED_LEAVES.get(), WITHERED_SAPLING.get(), HIGHER_LEAVES_SAPLING_CHANCES));
        dropSelf(WITHERED_WOOD.get());
        dropSelf(STRIPPED_WITHERED_WOOD.get());

        dropSelf(WITHERED_PLANKS.get());
        add(WITHERED_SLAB.get(), createSlabItemTable(WITHERED_SLAB.get()));
        dropSelf(WITHERED_STAIRS.get());
        dropSelf(WITHERED_TRAPDOOR.get());
        dropSelf(WITHERED_FENCE.get());
        dropSelf(WITHERED_FENCE_GATE.get());
        dropSelf(WITHERED_BUTTON.get());
        dropSelf(WITHERED_PRESSURE_PLATE.get());
        add(WITHERED_DOOR.get(), createDoorTable(WITHERED_DOOR.get()));


        dropSelf(STAR_SAPLING.get());
        dropSelf(STAR_LOG.get());
        dropSelf(STRIPPED_STAR_LOG.get());

        add(STAR_LEAVES.get(), createLeavesDrops(STAR_LEAVES.get(), STAR_SAPLING.get(), HIGHER_LEAVES_SAPLING_CHANCES));
        dropSelf(STAR_WOOD.get());
        dropSelf(STRIPPED_STAR_WOOD.get());

        dropSelf(STAR_PLANKS.get());
        add(STAR_SLAB.get(), createSlabItemTable(STAR_SLAB.get()));
        dropSelf(STAR_STAIRS.get());
        dropSelf(STAR_TRAPDOOR.get());
        dropSelf(STAR_FENCE.get());
        dropSelf(STAR_FENCE_GATE.get());
        dropSelf(STAR_BUTTON.get());
        dropSelf(STAR_PRESSURE_PLATE.get());
        add(STAR_DOOR.get(), createDoorTable(STAR_DOOR.get()));


        addCustomBerryDrops(
                BLUEBERRY_BUSH, BLUEBERRIES, NeoBerryBushBlock.AGE, 3, 2
        );
        addCustomBerryDrops(
                BLACKBERRY_BUSH, BLACKBERRIES, NeoBerryBushBlock.AGE, 3, 2
        );

        addCustomBerryDrops(
                STRAWBERRY_BUSH, STRAWBERRY, UNRIPE_STRAWBERRY, STRAWBERRY_SEEDS, StrawBerryBushBlock.AGE, 5, 4
        );


        add(TOMATO_BUSH.get(), block ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(TOMATO))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(TOMATO_SEEDS))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))));


        addCropDrops(TOMATO_CROP, TOMATO.get(), TOMATO_SEEDS.get(), TomatoCropBlock.AGE, 7);
        addCropDrops(EGGPLANT_CROP, EGGPLANT.get(), EGGPLANT_SEEDS.get(), EggplantCropBlock.AGE, 7);
        addCropDrops(SWEET_POTATO_CROP, SWEET_POTATO.get(), SweetPotatoCropBlock.AGE, 3);
        add(SWEET_POTATO_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, SWEET_POTATO, ConstantValue.exactly(4.0F)));

        addTallCropDrops(CORN_CROP, CORN_COB.get(), CORN_KERNELS.get(),
                CornCropBlock.HALF, CornCropBlock.AGE, 7, 3);



    }

    protected LootTable.Builder createVariableOreDrops(Block block, ItemLike item, NumberProvider numberProvider) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(numberProvider))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    /** When no seed item is specified, uses the carrot loot table. **/
    protected void addCropDrops(DeferredBlock<?> crop, Item drop, Item seed, IntegerProperty ageProperty, int ageToDrop) {
        add(crop.get(),
                createCropDrops(crop.get(), drop, seed,
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(crop.get())
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ageProperty, ageToDrop))));
    }
    /** When no seed item is specified, uses the carrot loot table. **/
    protected void addCropDrops(DeferredBlock<?> crop, Item drop, IntegerProperty ageProperty, int ageToDrop) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        add(
                crop.get(),
                applyExplosionDecay(
                        crop,
                        LootTable.lootTable()
                                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(drop)))
                                .withPool(
                                        LootPool.lootPool()
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(crop.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ageProperty, ageToDrop)))
                                                .add(
                                                        LootItem.lootTableItem(drop)
                                                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(registrylookup.getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))
                                                )
                                )
                )
        );
    }
    /** Always drops seeds
     * The numbers are kinda hard coded for a 10 stage crop though... Hmm.**/
    protected void addTallCropDrops(DeferredBlock<?> crop, ItemLike drop, ItemLike seed, EnumProperty<DoubleBlockHalf> halfProperty, IntegerProperty ageProperty, int ageToStartDrops, int ageToStartSeeds) {
        if (ageToStartDrops <= 3) throw new IllegalArgumentException("If it's 3 then we have 0 in our number providers, cause we do age-3 check it bossman");
        LootItemBlockStatePropertyCondition.Builder half_condition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(crop.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(halfProperty, DoubleBlockHalf.LOWER));

        add(crop.get(), applyExplosionDecay(crop,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(AlternativesEntry.alternatives(
                                        ageProperty.getPossibleValues(),
                                        age -> {
                                            if (age >= ageToStartDrops) {
                                                return LootItem.lootTableItem(drop)
                                                        .when(half_condition)
                                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(crop.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ageProperty, age)))
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(age - 3, age + 1)));
                                            }
                                            return LootItem.lootTableItem(Items.AIR)
                                                    .when(half_condition)
                                                    .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(crop.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ageProperty, age)));
                                        }
                                ))
                        )
                        .withPool(LootPool.lootPool()
                                .add(AlternativesEntry.alternatives(
                                        ageProperty.getPossibleValues(),
                                        age -> {
                                            if (age >= ageToStartSeeds) {
                                                return LootItem.lootTableItem(seed)
                                                        .when(half_condition)
                                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(crop.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ageProperty, age)))
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(age - 3, age)));
                                            }
                                            return LootItem.lootTableItem(seed)
                                                    .when(half_condition)
                                                    .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(crop.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ageProperty, age)));
                                        }
                                )))));


        /*add(crop.get(),
                applyExplosionDecay(
                        crop,
                        LootTable.lootTable()
                                .withPool(LootPool.lootPool()
                                        .add(AlternativesEntry.alternatives(
                                                ageProperty.getPossibleValues(), // We will loop over the ages
                                                value -> {
                                                    if (value >= ageToDrop1 && value < ageToDrop2) {
                                                        return LootItem.lootTableItem(drop)
                                                                .when(half_condition)
                                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 5)));
                                                    }
                                                    else {
                                                        return LootItem.lootTableItem(drop)
                                                                .when(half_condition)
                                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 10)));
                                                    }
                                                }
                                        ))
                                        .add(AlternativesEntry.alternatives(
                                                ageProperty.getPossibleValues(),
                                                value -> {
                                                    if (value >= ageToDrop1 && value < ageToDrop2) {
                                                        return LootItem.lootTableItem(seed)
                                                                .when(half_condition)
                                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)));
                                                    }
                                                    else {
                                                        return LootItem.lootTableItem(seed)
                                                                .when(half_condition)
                                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 7)));
                                                    }
                                                }
                                        )))
                                .withPool(LootPool.lootPool()
                                        .add(LootItem.lootTableItem(seed))
                                        .when(half_condition)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)
                                        )))
                ));*/
        /*private LootTable.Builder createPitcherCropLoot() {
        return this.applyExplosionDecay(
            Blocks.PITCHER_CROP,
            LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .add(
                            AlternativesEntry.alternatives(
                                PitcherCropBlock.AGE.getPossibleValues(),
                                p_277248_ -> {
                                    LootItemBlockStatePropertyCondition.Builder lootitemblockstatepropertycondition$builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(
                                            Blocks.PITCHER_CROP
                                        )
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
                                    LootItemBlockStatePropertyCondition.Builder lootitemblockstatepropertycondition$builder1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(
                                            Blocks.PITCHER_CROP
                                        )
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PitcherCropBlock.AGE, p_277248_.intValue()));
                                    return p_277248_ == 4
                                        ? LootItem.lootTableItem(Items.PITCHER_PLANT)
                                            .when(lootitemblockstatepropertycondition$builder1)
                                            .when(lootitemblockstatepropertycondition$builder)
                                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                                        : LootItem.lootTableItem(Items.PITCHER_POD)
                                            .when(lootitemblockstatepropertycondition$builder1)
                                            .when(lootitemblockstatepropertycondition$builder)
                                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)));
                                }
                            )
                        )
                )
        );
    }*/
    }

    /** For when you have variants of your drops based on age **/
    protected void addCustomBerryDrops(DeferredBlock<?> berryBlock,
                                       DeferredItem<?> ripeBerryItem, DeferredItem<?> unripeBerryItem, DeferredItem<?> seedItem,
                                       Property<Integer> ageProperty, int ageRipe, int ageUnripe) {
        HolderGetter<Enchantment> enchantmentReg = registries.lookupOrThrow(Registries.ENCHANTMENT);

        add(
                berryBlock.get(),
                block -> applyExplosionDecay(
                        block,
                        LootTable.lootTable()
                                .withPool(
                                        LootPool.lootPool()
                                                .when(
                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(berryBlock.get())
                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ageProperty, ageUnripe))
                                                )
                                                .add(LootItem.lootTableItem(unripeBerryItem))
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                                                .apply(ApplyBonusCount.addUniformBonusCount(enchantmentReg.getOrThrow(Enchantments.FORTUNE)))
                                )
                                .withPool(
                                        LootPool.lootPool()
                                                .when(
                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(berryBlock.get())
                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ageProperty, ageRipe))
                                                )
                                                .add(LootItem.lootTableItem(ripeBerryItem))
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4)))
                                                .apply(ApplyBonusCount.addUniformBonusCount(enchantmentReg.getOrThrow(Enchantments.FORTUNE)))
                                )
                )
        );
    }

    protected void addCustomBerryDrops(DeferredBlock<?> berryBlock, DeferredItem<?> berryItem, Property<Integer> ageProperty, int maxAge4drop, int secondMaxAge4drop) {
        HolderGetter<Enchantment> enchantmentReg = registries.lookupOrThrow(Registries.ENCHANTMENT);
        add(
                berryBlock.get(),
                block -> applyExplosionDecay(
                        block,
                        LootTable.lootTable()
                                .withPool(
                                        LootPool.lootPool()
                                                .when(
                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(berryBlock.get())
                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ageProperty, maxAge4drop))
                                                )
                                                .add(LootItem.lootTableItem(berryItem))
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                                .apply(ApplyBonusCount.addUniformBonusCount(enchantmentReg.getOrThrow(Enchantments.FORTUNE)))
                                )
                                .withPool(
                                        LootPool.lootPool()
                                                .when(
                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(berryBlock.get())
                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ageProperty, secondMaxAge4drop))
                                                )
                                                .add(LootItem.lootTableItem(berryItem))
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                                .apply(ApplyBonusCount.addUniformBonusCount(enchantmentReg.getOrThrow(Enchantments.FORTUNE)))
                                )
                )
        );
    }

    // These are all the blocks that will need a loot table generated.
    // If you want a block to drop nothing, simply add ".noLootTable()" to its builder pattern when making the block
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
