package fr.loudo.timberhearth.mixin;

import fr.loudo.timberhearth.util.UtilServer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.commands.TimeCommand;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TimeCommand.class)
public class TimeCommandMixin {

    @Inject(method = "setTime", at = @At("RETURN"))
    private static void timberHearth$setTimeUpdateOSTSeconds(
            CommandSourceStack source, int time, CallbackInfoReturnable<Integer> cir) {
        timberHearth$updateOSTSeconds(
                source.getServer().getPlayerList(), source.getLevel().getDayTime());
    }

    @Inject(method = "addTime", at = @At("RETURN"))
    private static void timberHearth$addTimeUpdateOSTSeconds(
            CommandSourceStack source, int time, CallbackInfoReturnable<Integer> cir) {
        timberHearth$updateOSTSeconds(
                source.getServer().getPlayerList(), source.getLevel().getDayTime());
    }

    @Unique
    private static void timberHearth$updateOSTSeconds(PlayerList playerList, long time) {
        UtilServer.updateOSTSeconds(playerList.getPlayers(), time);
    }
}
