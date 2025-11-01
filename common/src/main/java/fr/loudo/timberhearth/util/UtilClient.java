package fr.loudo.timberhearth.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LightLayer;

public class UtilClient {
    public static boolean inCave() {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || minecraft.level == null) return false;
        ClientLevel level = minecraft.level;
        BlockPos blockpos = minecraft.player.blockPosition();
        return level.getBrightness(LightLayer.SKY, blockpos) <= 3;
    }
}
