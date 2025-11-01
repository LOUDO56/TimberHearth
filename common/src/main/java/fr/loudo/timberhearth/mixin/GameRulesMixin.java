package fr.loudo.timberhearth.mixin;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import fr.loudo.timberhearth.sound.ModSounds;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.sounds.SoundSource;
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
        if (paramName.equalsIgnoreCase("doDaylightCycle") && BoolArgumentType.getBool(context, paramName)) {
            context.getSource()
                    .getServer()
                    .getPlayerList()
                    .broadcastAll(new ClientboundStopSoundPacket(ModSounds.TIMBER_HEARTH, SoundSource.AMBIENT));
        }
    }
}
