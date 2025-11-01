package fr.loudo.timberhearth.network;

import fr.loudo.timberhearth.TimberHearth;
import fr.loudo.timberhearth.client.TimberHearthSoundControl;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record TimberHearthFadeSoundS2C(boolean flag, TimberHearthSoundControl.SoundType soundType)
        implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<TimberHearthFadeSoundS2C> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(TimberHearth.MOD_ID, "timber_heath_fade_sound"));

    public static final StreamCodec<ByteBuf, TimberHearthFadeSoundS2C> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            TimberHearthFadeSoundS2C::flag,
            ByteBufCodecs.STRING_UTF8,
            packet -> packet.soundType().name(),
            (flag, soundTypeName) ->
                    new TimberHearthFadeSoundS2C(flag, TimberHearthSoundControl.SoundType.valueOf(soundTypeName)));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
