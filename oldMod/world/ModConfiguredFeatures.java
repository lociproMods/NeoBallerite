package net.locipro.balleritemod.world;

import net.locipro.balleritemod.BalleriteMod;
import net.locipro.balleritemod.block.ModBlocks;
import net.locipro.balleritemod.block.custom.ModBerryBushBlock;
import net.locipro.balleritemod.block.custom.StrawberryBushBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import java.util.List;

/**
 * <pre>
 *  From what I understand, World gen in 1.19.4 works like this : Funky business in Json files, then the code is like this:
 *      Configured features : are the feature themselves, for example, the tree, how it looks, the leaves, etc.
 *          How to register them: register(context, *f registry key*, Feature.whateverFeatureYouWant, then goes the feature config builder).
 *
 *      Placed features : are how the feature spawns in the world. Aka their placement, their count, etc.
 *
 *  bootstrap method gets called in BalleriteModDataGenerator, and it generates all the json files necessary for world gen.
 *
 *
 *      Refer to ConfiguredFeatures's bootstrap method for the different types of vanilla features
 *      Refer to PlacedFeatures's bootstrap method for the different types of vanilla features
 *
 *  After you make the configured features and placed features, go to world.gen.ModXGeneration and follow the code there
 *  </pre>
 *  <b>FOR VANILLA CONFIGURED FEATURES, RETURN TO ConfiguredFeatures BOOTSTRAP METHOD.</b>
 *
 */
public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> WITHERED_TREE_KEY = registerKey("withered_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STAR_TREE_KEY = registerKey("star_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BALLERITE_ORE_KEY = registerKey("ballerite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEAD_ORE_KEY = registerKey("lead_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BURIED_LEAD_ORE_KEY = registerKey("buried_lead_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> TOMATO_FLOWER_KEY = registerKey("tomato_flower");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SWEET_POTATO_PATCH_KEY = registerKey("sweet_potato_patch");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_BERRY_PATCH_KEY = registerKey("blue_berry_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_BERRY_PATCH_KEY = registerKey("black_berry_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STRAWBERRY_PATCH_KEY = registerKey("strawberry_patch");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldBalleriteOres =
                List.of(OreConfiguration.target(stoneReplaceables, ModBlocks.BALLERITE_ORE.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldLeadOres =
                List.of(OreConfiguration.target(stoneReplaceables, ModBlocks.LEAD_ORE.defaultBlockState()),
                        OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_LEAD_ORE.defaultBlockState()));


        register(context, WITHERED_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.WITHERED_LOG),
                new StraightTrunkPlacer(4, 2, 3),
                BlockStateProvider.simple(ModBlocks.WITHERED_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(2, 0, 3)).build());

        register(context, STAR_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.STAR_LOG),
                new ForkingTrunkPlacer(5, 2, 3),
                BlockStateProvider.simple(ModBlocks.STAR_LEAVES),
                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 2)).ignoreVines().build());



        register(context, BALLERITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldBalleriteOres, 6));
        register(context, LEAD_ORE_KEY, Feature.ORE, new OreConfiguration(overworldLeadOres, 7));
        register(context, BURIED_LEAD_ORE_KEY, Feature.ORE, new OreConfiguration(overworldLeadOres, 14));


        //I, J, K FOR "RandomPatchFeatureConfig" ARE AS FOLLOWING : tries, xz_spread, y_spread.
        register(context, TOMATO_FLOWER_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(48, 7, 3, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.TOMATO_FLOWER)),
                        BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.noFluid(),
                                BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.DIRT)))));

        register(context, SWEET_POTATO_PATCH_KEY, Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(64, 5, 3, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SWEET_POTATO_BLOCK)),
                        BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.noFluid(),
                                BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.RED_SAND)))));

        register(context, BLUE_BERRY_PATCH_KEY, Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(48, 8, 2, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLUE_BERRY_BUSH.defaultBlockState().setValue(ModBerryBushBlock.AGE, 3))),
                        BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.noFluid(),
                                BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.GRASS_BLOCK, Blocks.DIRT)))));
        register(context, BLACK_BERRY_PATCH_KEY, Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(48, 8, 2, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLACK_BERRY_BUSH.defaultBlockState().setValue(ModBerryBushBlock.AGE, 3))),
                        BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.noFluid(),
                                BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.GRASS_BLOCK, Blocks.DIRT)))));
        register(context, STRAWBERRY_PATCH_KEY, Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(48, 6, 3, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.STRAWBERRY_BUSH.defaultBlockState().setValue(StrawberryBushBlock.AGE, 5))),
                        BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.noFluid(),
                                BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.GRASS_BLOCK, Blocks.DIRT)))));
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(BalleriteMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                   ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
