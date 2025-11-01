package fr.loudo.timberhearth.network;

import fr.loudo.timberhearth.TimberHearth;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public class StopTimberHearthSoundS2C implements CustomPacketPayload {

    public static final Type<StopTimberHearthSoundS2C> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(TimberHearth.MOD_ID, "stop_timber_hearth_sound"));

    public static final StreamCodec<ByteBuf, StopTimberHearthSoundS2C> STREAM_CODEC =
            StreamCodec.of((o, playTimberHearthSoundS2C) -> {}, buf -> new StopTimberHearthSoundS2C());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
