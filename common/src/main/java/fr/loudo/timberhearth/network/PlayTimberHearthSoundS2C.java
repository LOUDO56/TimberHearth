package fr.loudo.timberhearth.network;

import fr.loudo.timberhearth.TimberHearth;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record PlayTimberHearthSoundS2C(double seconds) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<PlayTimberHearthSoundS2C> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(TimberHearth.MOD_ID, "play_timber_hearth_sound"));

    public static final StreamCodec<ByteBuf, PlayTimberHearthSoundS2C> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.DOUBLE, PlayTimberHearthSoundS2C::seconds, PlayTimberHearthSoundS2C::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
