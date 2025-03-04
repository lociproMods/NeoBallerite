package com.locipro.neoballerite.datagen;

import com.locipro.neoballerite.item.NeoSandwiches;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import static com.locipro.neoballerite.NeoBallerite.MODID;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MODID)
public class DataGenHandler {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        // Data generators may require some of these as constructor parameters.
        // See below for more details on each of these.
        DataGenerator generator = event.getGenerator();

        //PackOutput output = generator.getPackOutput();
        //CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        NeoSandwiches.init();

        //event.createProvider(recipeprovider::new)

        event.createProvider((output, lookupProvider) -> new LootTableProvider(
                output,
                Set.of(),
                List.of(new LootTableProvider.SubProviderEntry(
                        ModBlockLootTableProvider::new,
                        LootContextParamSets.BLOCK
                )),
                lookupProvider
        ));

        event.createProvider(ModRecipeProvider.Runner::new);

        /*

        generator.addProvider(
                event.includeServer(),
                new ModRecipeProvider(output, lookupProvider)
        );



        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(output, lookupProvider, existingFileHelper);
        generator.addProvider(
                event.includeServer(),
                blockTagsProvider
        );
        generator.addProvider(
                event.includeServer(),
                new ModItemTagProvider(output, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper)
        );

        generator.addProvider(
                event.includeServer(),
                new ModDataMapProvider(output, lookupProvider)
        );


        generator.addProvider(
                event.includeClient(),
                new ModBlockstateModelProvider(output, event.getExistingFileHelper())
        );
        generator.addProvider(
                event.includeClient(),
                new ModItemModelProvider(output, event.getExistingFileHelper())
        );



        // World gen and enchantments
        generator.addProvider(
                event.includeServer(),
                new NeoDatapackBuiltinProvider(output, lookupProvider)
        );

        // Since the enchantments are MY DATA under MY namespace, It needs access to them so we pass our datapack provider.
        generator.addProvider(
                event.includeServer(),
                new NeoEnchantmentTagProvider(output,
                        new NeoDatapackBuiltinProvider(output, lookupProvider).getRegistryProvider(),
                        existingFileHelper)
        );

        generator.addProvider(
                event.includeServer(),
                new NeoGLMProvider(output, lookupProvider));*/



    }
}
