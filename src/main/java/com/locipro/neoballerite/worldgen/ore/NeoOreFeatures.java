package com.locipro.neoballerite.worldgen.ore;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import static com.locipro.neoballerite.block.ModBlocks.*;

import java.util.List;

import static com.locipro.neoballerite.worldgen.NeoConfiguredFeatures.createKey;

public class NeoOreFeatures {
    //public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registries.CONFIGURED_FEATURE, MODID);

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_BALLERITE = createKey("ore_ballerite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_LEAD = createKey("ore_lead");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_LEAD_BURIED = createKey("ore_lead_buried");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stone_replace = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslate_replace = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> ballerite_target = List.of(
                OreConfiguration.target(stone_replace, BALLERITE_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> lead_target = List.of(
                OreConfiguration.target(stone_replace, LEAD_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslate_replace, DEEPSLATE_LEAD_ORE.get().defaultBlockState())
        );

//        CONFIGURED_FEATURES.register("ore_ballerite", )

        FeatureUtils.register(context, ORE_BALLERITE, Feature.ORE, new OreConfiguration(ballerite_target, 12, 0.2f));
        FeatureUtils.register(context, ORE_LEAD, Feature.ORE, new OreConfiguration(lead_target, 9, 0.8f));
        FeatureUtils.register(context, ORE_LEAD_BURIED, Feature.ORE, new OreConfiguration(lead_target, 16, 0.1f));
    }



}
