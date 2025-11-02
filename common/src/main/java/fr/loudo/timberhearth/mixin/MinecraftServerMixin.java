package fr.loudo.timberhearth.mixin;

import fr.loudo.timberhearth.network.StopTimberHearthSoundS2C;
import fr.loudo.timberhearth.platform.Services;
import fr.loudo.timberhearth.util.UtilServer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {

    @Shadow
    private PlayerList playerList;

    @Shadow
    @Nullable
    public abstract ServerLevel getLevel(ResourceKey<Level> dimension);

    @Inject(method = "onGameRuleChanged", at = @At("HEAD"))
    private void timberHeart$onGameRuleChanged(String gameRule, GameRules.Value<?> value, CallbackInfo ci) {
        if (value instanceof GameRules.BooleanValue booleanValue) {
            if (gameRule.equalsIgnoreCase("doDaylightCycle")) {
                if (booleanValue.get()) {
                    UtilServer.updateOSTSeconds(
                            playerList.getPlayers(), getLevel(Level.OVERWORLD).getDayTime());
                } else {
                    playerList
                            .getPlayers()
                            .forEach(player ->
                                    Services.PACKET_SENDER.sendToPlayer(player, new StopTimberHearthSoundS2C()));
                }
            }
        }
    }
}
