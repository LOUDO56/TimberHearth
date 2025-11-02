package fr.loudo.timberhearth.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class UtilCommon {

    public static double getOSTSecondsFromDayTime(long time) {
        return (time % 24000) * (204.0 / 12000.0);
    }

    public static boolean isDayTime(long time) {
        long dayTime = time % 24000;
        return (dayTime >= 0.0 && dayTime <= 12000);
    }

    public static boolean inCave(Level level, BlockPos blockPos) {
        return level.getBrightness(LightLayer.SKY, blockPos) <= 3;
    }
}
