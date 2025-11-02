package fr.loudo.timberhearth.mixin;

import fr.loudo.timberhearth.audio.ChannelExtension;
import fr.loudo.timberhearth.audio.SoundExtension;
import java.util.Map;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.ChannelAccess;
import net.minecraft.client.sounds.SoundEngine;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SoundEngine.class)
public abstract class SoundEngineMixin implements SoundExtension {
    @Shadow
    @Final
    private Map<SoundInstance, ChannelAccess.ChannelHandle> instanceToChannel;

    @Shadow
    public abstract SoundEngine.PlayResult play(SoundInstance p_sound);

    @Override
    public void timberHearth$playAt(SoundInstance soundInstance, double seconds) {
        play(soundInstance);
        ChannelAccess.ChannelHandle channelaccess$channelhandle = this.instanceToChannel.get(soundInstance);
        if (channelaccess$channelhandle != null) {
            channelaccess$channelhandle.execute(
                    channel -> ((ChannelExtension) channel).timberHearth$playAt((float) seconds));
        }
    }
}
