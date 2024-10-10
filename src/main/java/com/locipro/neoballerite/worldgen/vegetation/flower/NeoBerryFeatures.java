package com.locipro.neoballerite.worldgen.vegetation.flower;


import com.locipro.neoballerite.block.ModBlocks;
import com.locipro.neoballerite.block.custom.NeoBerryBushBlock;
import com.locipro.neoballerite.block.custom.StrawBerryBushBlock;
import com.locipro.neoballerite.worldgen.NeoConfiguredFeatures;
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
import net.minecraft.world.level.levelgen.feature.configurations.RandomBooleanFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.List;

import static com.locipro.neoballerite.worldgen.NeoConfiguredFeatures.createKey;

public class NeoBerryFeatures {

    public static ResourceKey<ConfiguredFeature<?, ?>> BLUEBERRY_BUSH = createKey("blueberry_bush");
    public static ResourceKey<ConfiguredFeature<?, ?>> BLACKBERRY_BUSH = createKey("blackberry_bush");
    public static ResourceKey<ConfiguredFeature<?, ?>> STRAWBERRY_BUSH = createKey("strawberry_bush");


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
                                        BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(),
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
                                        BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(),
                                                Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.PODZOL, Blocks.COARSE_DIRT)
                                ))));

        FeatureUtils.register(bootstrapContext,
                STRAWBERRY_BUSH,
                Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(12, 4, 4,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.STRAWBERRY_BUSH.get()
                                        .defaultBlockState().setValue(StrawBerryBushBlock.AGE, 5))),
                                BlockPredicate.allOf(
                                        BlockPredicate.replaceable(), BlockPredicate.noFluid(),
                                        BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(),
                                                Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.PODZOL, Blocks.COARSE_DIRT)
                                ))));
    }
}
