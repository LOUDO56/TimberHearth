package fr.loudo.timberhearth.mixin;

import fr.loudo.timberhearth.audio.VolumeAudio;
import java.util.Map;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.ChannelAccess;
import net.minecraft.client.sounds.SoundEngine;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SoundEngine.class)
public abstract class SoundEngineMixin implements VolumeAudio {
    @Shadow
    private boolean loaded;

    @Shadow
    @Final
    private Map<SoundInstance, ChannelAccess.ChannelHandle> instanceToChannel;

    @Shadow
    protected abstract float calculateVolume(SoundInstance sound);

    // Code owned by Mojang.
    @Override
    public void timberHearth$setVolume(SoundInstance soundInstance, float volume) {
        if (this.loaded) {
            ChannelAccess.ChannelHandle channelHandle = this.instanceToChannel.get(soundInstance);
            if (channelHandle != null) {
                channelHandle.execute((channel) -> channel.setVolume(volume * this.calculateVolume(soundInstance)));
            }
        }
    }
}
