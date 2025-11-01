package fr.loudo.timberhearth.util;

import fr.loudo.timberhearth.mixin.accessor.SoundEngineAccessor;
import fr.loudo.timberhearth.mixin.accessor.SoundManagerAccessor;
import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LightLayer;

public class UtilClient {

    public static SoundInstance getSoundInstance(ResourceLocation resourceLocation) {
        AtomicReference<SoundInstance> instance = new AtomicReference<>();
        SoundManager soundManager = Minecraft.getInstance().getSoundManager();
        SoundEngine soundEngine = ((SoundManagerAccessor) soundManager).getSoundEngine();
        ((SoundEngineAccessor) soundEngine).getInstanceToChannel().forEach((soundInstance, channelHandle) -> {
            if (soundInstance.getLocation().equals(resourceLocation)) {
                instance.set(soundInstance);
            }
        });
        return instance.get();
    }

    public static boolean inCave() {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || minecraft.level == null) return false;
        ClientLevel level = minecraft.level;
        BlockPos blockpos = minecraft.player.blockPosition();
        return level.getBrightness(LightLayer.SKY, blockpos) <= 3;
    }
}
