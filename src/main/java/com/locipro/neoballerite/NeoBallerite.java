package com.locipro.neoballerite;

import com.locipro.neoballerite.block.ModBlocks;
import com.locipro.neoballerite.component.NeoDataComponents;
import com.locipro.neoballerite.recipe.NeoRecipeSerializers;
import com.locipro.neoballerite.item.ModItems;
import com.locipro.neoballerite.item.armor.NeoArmorMaterials;
import com.locipro.neoballerite.menu.tabs.ModCreativeTabs;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// TODO -
//      CORN TEXTURES.
//      .
//      Add sugar components
//      Make torches turn off during rain
//      USE DATA COMPONENTS TO LINK BACK TO THE ORIGINAL FRUIT ONCE YOU IMPLEMENT JAMS.
//      RETEXTURE STAR_TRAPDOOR
//      RETEXTURE BALLERITE_INGOT
//      ADD TEXTURE TO BOTTOM OF ARMOR BOOTS. (It's already on ballerite but not lead or farmers)
//      RETEXTURE CROPS AND TOMATO BUSH.
//      Add global loot modifier to grass to make eggplant seeds obtainable.
@Mod(NeoBallerite.MODID)
public class NeoBallerite {
    public static final String MODID = "neoballerite";
    public static final Logger LOGGER = LogUtils.getLogger();


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public NeoBallerite(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);



        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        // Blocks before Items. See https://docs.neoforged.net/docs/items/#:~:text=If%20you%20keep%20your%20registered%20blocks%20in%20a%20separate%20class%2C%20you%20should%20classload%20your%20blocks%20class%20before%20your%20items%20class.
        //  ok uhm so, if we do blocks first mod berries break :/ fuck it we ball
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);

        NeoArmorMaterials.ARMOR_MATERIALS.register(modEventBus);
        NeoDataComponents.DATA_COMPONENT_TYPES.register(modEventBus);

        NeoRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);



        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (NeoBallerite) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        /*// Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));*/
    }

    // Add the example block item to the VANILLA building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        /*if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(EXAMPLE_BLOCK_ITEM);*/
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        /*LOGGER.info("HELLO from server starting");*/
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            /*// Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());*/
        }
    }
}
