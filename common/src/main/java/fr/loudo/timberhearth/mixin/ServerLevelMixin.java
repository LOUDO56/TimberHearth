package fr.loudo.timberhearth.mixin;

import fr.loudo.timberhearth.client.TimberHearthSoundControl;
import fr.loudo.timberhearth.sound.ModSounds;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.storage.ServerLevelData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {

    @Shadow
    @Final
    private ServerLevelData serverLevelData;

    @Redirect(
            method = "tickTime",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;setDayTime(J)V"))
    private void timerHeart$overrideTickTime(ServerLevel instance, long time) {
        if (time % 24000L >= 23998 || time % 24000L <= 1 && (!instance.getLevel().isRaining() && instance.getLevel().isThundering())) {
            instance.getServer()
                    .getPlayerList()
                    .broadcastAll(new ClientboundStopSoundPacket(ModSounds.TIMBER_HEARTH, SoundSource.AMBIENT));
            Holder<SoundEvent> holder = Holder.direct(SoundEvent.createVariableRangeEvent(ModSounds.TIMBER_HEARTH));
            instance.getServer()
                    .getPlayerList()
                    .broadcastAll(new ClientboundSoundPacket(
                            holder,
                            SoundSource.AMBIENT,
                            0,
                            0,
                            0,
                            0.3f,
                            1.0f,
                            instance.getRandom().nextLong()));
        }
        instance.setDayTime(instance.getDayTime() + 3L);
        instance.getServer().forceTimeSynchronization();
    }

    @Inject(method = "setWeatherParameters", at = @At("HEAD"))
    private void timberHearth$stopSoundIfRain(
            int clearTime, int weatherTime, boolean isRaining, boolean isThundering, CallbackInfo ci) {
        boolean flag = isRaining || isThundering;
        // If it tries to clear but the weather is already cleared
        if (!flag && (!this.serverLevelData.isRaining() && !this.serverLevelData.isThundering())) {
            return;
        }
        TimberHearthSoundControl.reset(flag, TimberHearthSoundControl.SoundType.RAIN);
    }
}
