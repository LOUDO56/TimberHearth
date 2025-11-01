package fr.loudo.timberhearth.mixin;

import fr.loudo.timberhearth.network.StopTimberHearthSoundS2C;
import fr.loudo.timberhearth.platform.Services;
import fr.loudo.timberhearth.sound.ModSounds;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    @Shadow
    private PlayerList playerList;

    @Inject(method = "onGameRuleChanged", at = @At("HEAD"))
    private void timberHeart$onGameRuleChanged(String gameRule, GameRules.Value<?> value, CallbackInfo ci) {
        if (value instanceof GameRules.BooleanValue booleanValue) {
            if (gameRule.equalsIgnoreCase("doDaylightCycle") && !booleanValue.get()) {
                playerList.getPlayers().forEach(player -> Services.PACKET_SENDER.sendToPlayer(player, new StopTimberHearthSoundS2C()));
            }
        }
    }
}
