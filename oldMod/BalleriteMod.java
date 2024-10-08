package net.locipro.balleritemod;

import net.fabricmc.api.ModInitializer;
import net.locipro.balleritemod.block.ModBlocks;
import net.locipro.balleritemod.command.ModCommands;
import net.locipro.balleritemod.enchantment.ModEnchantments;
import net.locipro.balleritemod.item.ModItemGroup;
import net.locipro.balleritemod.item.ModItems;
import net.locipro.balleritemod.network.BalleritePackets;
import net.locipro.balleritemod.painting.ModPaintings;
import net.locipro.balleritemod.util.*;
import net.locipro.balleritemod.util.data.ModLootTableModifiers;
import net.locipro.balleritemod.util.data.ModTrades;
import net.locipro.balleritemod.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BalleriteMod implements ModInitializer {
	public static final String MOD_ID = "balleritemod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroup.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModPaintings.registerPaintings();

		ModEnchantments.registerModEnchantments();

		ModWorldGeneration.generateModWorldGen();

		ModFlammableBlockRegistry.registerFlammableBlocks();
		ModStrippables.registerModStrippables();
		ModFuelRegistry.registerModFuels();


		ModLootTableModifiers.modifyLootTables();

		ModTrades.registerCustomTrades();
		ModComposterChances.registerModComposterChances();


		BalleritePackets.registerC2SPackets();
		//ServerTickEvents.START_SERVER_TICK.register(new ServerTickHydrationSync());
		/*ServerPlayerEvents.AFTER_RESPAWN.register(new RespawnHydration());
		ClientPlayConnectionEvents.JOIN.register(new ClientJoinsServer());*/


		ModCommands.registerModCommands();

	}
}

// TODO OIL REFINING MOD??? LETS GO
// TODO ADD COCAINE PLANT! AND WEED PLANT
// TODO ADD ACHIEVEMNTS
// TODO REIMPLEMENT FLOUR, SPAGHETTI bla bla
// TODO MAKE CUSTOM CHESTS (STAR AND WITHERED)
// TODO CUSTOM ARMOR MODELS : THERE'S A TUTORIAL ON YT
// TODO MAKE TEA TREE / TEA LEAVES / TEA DRINK / TEASTICLES (TEA WITH BALLERITE)


