package com.locipro.neoballerite.worldgen.vegetation.flower;


import com.locipro.neoballerite.block.ModBlocks;
import com.locipro.neoballerite.block.custom.NeoBerryBushBlock;
import com.locipro.neoballerite.block.custom.StrawBerryBushBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import static com.locipro.neoballerite.worldgen.NeoConfiguredFeatures.createKey;

public class NeoBerryFeatures {

    // These features are PATCH features
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUEBERRY_BUSH = createKey("blueberry_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACKBERRY_BUSH = createKey("blackberry_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STRAWBERRY_BUSH = createKey("strawberry_bush");


    public static final ResourceKey<ConfiguredFeature<?, ?>> TOMATO_BUSH = createKey("tomato_bush");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SWEET_POTATO_PATCH = createKey("sweet_potato_patch");




    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> bootstrapContext) {
        HolderGetter<Block> blockRegistry = bootstrapContext.lookup(Registries.BLOCK);

        FeatureUtils.register(bootstrapContext,
                BLUEBERRY_BUSH,
                Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(24, 8, 2,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLUEBERRY_BUSH.get()
                                        .defaultBlockState().setValue(NeoBerryBushBlock.AGE, 3))),
                                BlockPredicate.allOf(
                                        BlockPredicate.replaceable(), BlockPredicate.noFluid(),
                                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(),
                                                Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.PODZOL, Blocks.COARSE_DIRT)
                                ))));
        FeatureUtils.register(bootstrapContext,
                BLACKBERRY_BUSH,
                Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(24, 8, 2,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLACKBERRY_BUSH.get()
                                        .defaultBlockState().setValue(NeoBerryBushBlock.AGE, 3))),
                                BlockPredicate.allOf(
                                        BlockPredicate.replaceable(), BlockPredicate.noFluid(),
                                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(),
                                                Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.PODZOL, Blocks.COARSE_DIRT)
                                ))));
/*//I, J, K FOR "RandomPatchFeatureConfig" ARE AS FOLLOWING : tries, xz_spread, y_spread.
        register(context, TOMATO_FLOWER_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(48, 7, 3, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.TOMATO_FLOWER)),
                        BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.noFluid(),
                                BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.DIRT)))));
*/
        FeatureUtils.register(bootstrapContext,
                STRAWBERRY_BUSH,
                Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(12, 4, 4,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.STRAWBERRY_BUSH.get()
                                        .defaultBlockState().setValue(StrawBerryBushBlock.AGE, 5))),
                                BlockPredicate.allOf(
                                        BlockPredicate.replaceable(), BlockPredicate.noFluid(),
                                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(),
                                                Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.PODZOL, Blocks.COARSE_DIRT)
                                ))));

        FeatureUtils.register(bootstrapContext,
                TOMATO_BUSH,
                Feature.FLOWER,
                new RandomPatchConfiguration(36, 6, 3,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.TOMATO_BUSH.get()
                                        .defaultBlockState())),
                                BlockPredicate.allOf(
                                        BlockPredicate.noFluid(), BlockPredicate.replaceable(),
                                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(),
                                                Blocks.GRASS_BLOCK, Blocks.DIRT)
                                ))));

        FeatureUtils.register(bootstrapContext,
                SWEET_POTATO_PATCH,
                Feature.FLOWER,
                new RandomPatchConfiguration(64, 5, 3,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SWEET_POTATO_BLOCK.get()
                                        .defaultBlockState())),
                                BlockPredicate.allOf(
                                        BlockPredicate.noFluid(), BlockPredicate.replaceable(),
                                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(),
                                                Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.RED_SAND)
                                ))));
    }
}
