package fr.loudo.timberhearth.mixin;

import fr.loudo.timberhearth.client.TimberHearthSoundControl;
import fr.loudo.timberhearth.network.PlayTimberHearthSoundS2C;
import fr.loudo.timberhearth.network.TimberHearthFadeSoundS2C;
import fr.loudo.timberhearth.platform.Services;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
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

    @Shadow
    @Final
    private MinecraftServer server;

    @Redirect(
            method = "tickTime",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;setDayTime(J)V"))
    private void timerHeart$overrideTickTime(ServerLevel instance, long time) {
        if (instance.dimension() != Level.OVERWORLD) return;
        if (time % 24000L >= 23998 || time % 24000L <= 1) {
            server.getPlayerList()
                    .getPlayers()
                    .forEach(player -> Services.PACKET_SENDER.sendToPlayer(player, new PlayTimberHearthSoundS2C(0)));
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
        if (flag && (this.serverLevelData.isRaining() || this.serverLevelData.isThundering())) {
            return;
        }
        server.getPlayerList()
                .getPlayers()
                .forEach(player -> Services.PACKET_SENDER.sendToPlayer(
                        player, new TimberHearthFadeSoundS2C(flag, TimberHearthSoundControl.SoundType.RAIN)));
    }
}
