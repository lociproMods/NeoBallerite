package net.locipro.balleritemod.world.gen;

import net.locipro.balleritemod.BalleriteMod;

//IT IS VITAL FOR PROPER WORLD GENERATION THAT YOU CALL THESE METHODS IN ORDER ACCORDING TO THEIR GENERATION STEP. REFER TO
/// MINECRAFT CLASS "GenerationStep" FOR THE PROPER ORDER.
public class ModWorldGeneration {

    public static void generateModWorldGen() {
        BalleriteMod.LOGGER.info("Registering world gen for " + BalleriteMod.MOD_ID);

        ModOreGeneration.generateOres();
        ModTreeGeneration.generateTrees();
        ModFlowerGeneration.generateFlowers();
    }
}
