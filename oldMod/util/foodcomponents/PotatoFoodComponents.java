package net.locipro.balleritemod.util.foodcomponents;
import net.minecraft.world.food.FoodProperties;
public abstract class PotatoFoodComponents {
    public static final FoodProperties SWEET_POTATO = new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build();
    public static final FoodProperties BAKED_SWEET_POTATO = new FoodProperties.Builder().nutrition(6).saturationMod(0.5f).build();

}
