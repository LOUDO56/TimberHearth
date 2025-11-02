package fr.loudo.timberhearth.mixin;

import fr.loudo.timberhearth.audio.SoundExtension;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.client.sounds.SoundManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SoundManager.class)
public class SoundManagerMixin implements SoundExtension {
    @Shadow
    @Final
    private SoundEngine soundEngine;

    @Override
    public void timberHearth$playAt(SoundInstance soundInstance, double seconds) {
        ((SoundExtension) this.soundEngine).timberHearth$playAt(soundInstance, seconds);
    }
}
