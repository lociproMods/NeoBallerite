package net.locipro.balleritemod.util;



public abstract class ModMathUtil {
    public static int[] generateArrayFromZeroToX(int x) {
        int[] output = new int[x+1];
        for (int i = 0; i < x+1; i++) {
            output[i] = i;
        }
        return output;
    }
    /*public static void main(String[] args)
    {
        String s = "blueberry";
        if (s.endsWith("y")) {
            System.out.println(s.substring(0, s.length()-1) + "ies");
        }
    }*/
}
