package fr.loudo.timberhearth.mixin;

import fr.loudo.timberhearth.network.StopTimberHearthSoundS2C;
import fr.loudo.timberhearth.platform.Services;
import fr.loudo.timberhearth.util.UtilServer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.commands.TickCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TickCommand.class)
public class TickCommandMixin {

    @Inject(method = "setFreeze", at = @At("HEAD"))
    private static void timberHearth$controlOstTickFreeze(
            CommandSourceStack source, boolean frozen, CallbackInfoReturnable<Integer> cir) {
        if (frozen) {
            source.getServer()
                    .getPlayerList()
                    .getPlayers()
                    .forEach(player -> Services.PACKET_SENDER.sendToPlayer(player, new StopTimberHearthSoundS2C()));
        } else {
            UtilServer.updateOSTSeconds(
                    source.getServer().getPlayerList().getPlayers(),
                    source.getLevel().getDayTime());
        }
    }
}
