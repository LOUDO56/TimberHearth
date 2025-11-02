package fr.loudo.timberhearth.mixin;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import fr.loudo.timberhearth.network.StopTimberHearthSoundS2C;
import fr.loudo.timberhearth.platform.Services;
import fr.loudo.timberhearth.util.UtilServer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRules.BooleanValue.class)
public class GameRulesMixin {

    @Inject(method = "updateFromArgument", at = @At("HEAD"))
    private void timberHeart$onGameRuleChanged(
            CommandContext<CommandSourceStack> context, String paramName, CallbackInfo ci) {
        String param = context.getInput().split(" ")[1];
        if (param.equalsIgnoreCase("doDaylightCycle")) {
            if (BoolArgumentType.getBool(context, paramName)) {
                UtilServer.updateOSTSeconds(
                        context.getSource().getServer().getPlayerList().getPlayers(),
                        context.getSource().getLevel().getDayTime());
            } else {
                context.getSource()
                        .getServer()
                        .getPlayerList()
                        .getPlayers()
                        .forEach(player -> Services.PACKET_SENDER.sendToPlayer(player, new StopTimberHearthSoundS2C()));
            }
        }
    }
}
