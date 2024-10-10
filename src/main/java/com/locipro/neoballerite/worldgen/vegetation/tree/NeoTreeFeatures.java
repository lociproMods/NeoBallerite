package com.locipro.neoballerite.worldgen.vegetation.tree;


import com.google.common.collect.ImmutableList;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MangrovePropaguleBlock;
import net.minecraft.world.level.block.SeaPickleBlock;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.BendingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.List;

import static com.locipro.neoballerite.block.ModBlocks.*;
import static com.locipro.neoballerite.worldgen.NeoConfiguredFeatures.createKey;

// You make the individual feature, i.e. a singular tree. Then you say how it's placed, NeoTreePlacements.
public class NeoTreeFeatures {

//    public static List<Block> MOD_SAPLINGS = List.of(
//            WITHERED_SAPLING.get()
//    );

    public static ResourceKey<ConfiguredFeature<?, ?>> WITHERED_TREE = createKey("withered_tree");
    public static ResourceKey<ConfiguredFeature<?, ?>> FANCY_WITHERED_TREE = createKey("fancy_withered_tree");

    public static ResourceKey<ConfiguredFeature<?, ?>> STAR_TREE = createKey("star_tree");
    public static ResourceKey<ConfiguredFeature<?, ?>> STRIPPED_STAR_TREE = createKey("stripped_star_tree");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>>context) {
        HolderGetter<Block> holderGetter = context.lookup(Registries.BLOCK);
//        BlockPredicate matchesBlock = BlockPredicate.matchesBlocks(MOD_SAPLINGS);

        FeatureUtils.register(context,
                WITHERED_TREE,
                Feature.TREE,
                createStraightBlobTree(
                        WITHERED_LOG.get(), WITHERED_LEAVES.get(), 8, 3, 1, 3
                ).build());

        FeatureUtils.register(context,
                FANCY_WITHERED_TREE,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(WITHERED_LOG.get()),
                        new FancyTrunkPlacer(12, 3, 1),
                        BlockStateProvider.simple(WITHERED_LEAVES.get()),
                        new FancyFoliagePlacer(UniformInt.of(4, 6), ConstantInt.of(0), 3),
                        new TwoLayersFeatureSize(2, 0 ,2)
                ).decorators(
                        ImmutableList.of(new AlterGroundDecorator(BlockStateProvider.simple(RAW_BALLERITE_BLOCK.get())))
                ).build());

        FeatureUtils.register(context,
                STAR_TREE,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(STAR_LOG.get()),
                        new ForkingTrunkPlacer(4, 4, 3),
                        BlockStateProvider.simple(STAR_LEAVES.get()),
                        new AcaciaFoliagePlacer(UniformInt.of(2, 3), ConstantInt.of(0)), // RADIUS, OFFSET
                        new TwoLayersFeatureSize(1, 0, 2)
                )
                        .ignoreVines()
                        .build()
                );

        FeatureUtils.register(context,
                STRIPPED_STAR_TREE,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(STRIPPED_STAR_LOG.get()),
                        new BendingTrunkPlacer(4, 2, 1, 2, UniformInt.of(2, 4)),
                        BlockStateProvider.simple(STAR_LEAVES.get()),
                        new RandomSpreadFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(3), 50),
                        new TwoLayersFeatureSize(1, 0, 2)
                )
                        .decorators(ImmutableList.of(new AttachedToLeavesDecorator(
                                0.24F,
                                1,
                                0,
                                BlockStateProvider.simple(Blocks.MOSS_CARPET),
                                4,
                                List.of(Direction.UP)
                        )))
                        .build()
                );
    }

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(
            Block logBlock, Block leavesBlock, int baseHeight, int heightRandA, int heightRandB, int radius
    ) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(logBlock),
                new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB),
                BlockStateProvider.simple(leavesBlock),
                new BlobFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1)
        );
    }
}
