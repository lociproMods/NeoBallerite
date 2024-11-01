package com.locipro.neoballerite.item.util;

public class FoodMath {
    public static float saturationModifierFromSaturationAndNutrition(float saturation, float nutrition) {
        return saturation / (2 * (float) nutrition);
    }
}
