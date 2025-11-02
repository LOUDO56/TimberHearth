package fr.loudo.timberhearth.mixin;

import com.mojang.blaze3d.audio.Channel;
import fr.loudo.timberhearth.audio.ChannelExtension;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Channel.class)
public abstract class ChannelMixin implements ChannelExtension {
    @Shadow
    @Final
    private int source;

    @Override
    public void timberHearth$playAt(float seconds) {
        AL10.alSourcef(this.source, AL11.AL_SEC_OFFSET, seconds);
    }
}
