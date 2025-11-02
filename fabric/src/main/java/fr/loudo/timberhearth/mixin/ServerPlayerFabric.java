package fr.loudo.timberhearth.mixin;

import fr.loudo.timberhearth.events.OnChangeDimensionEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.portal.TeleportTransition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayer.class)
public class ServerPlayerFabric {

    @Inject(
            method =
                    "teleport(Lnet/minecraft/world/level/portal/TeleportTransition;)Lnet/minecraft/server/level/ServerPlayer;",
            at = @At("RETURN"))
    private void timberHeart$fabricChangeDimensionEvent(
            TeleportTransition teleportTransition, CallbackInfoReturnable<ServerPlayer> cir) {
        OnChangeDimensionEvent.changeDimensionEvent(
                teleportTransition.newLevel().dimension());
    }
}
