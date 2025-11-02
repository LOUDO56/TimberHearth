package fr.loudo.timberhearth.mixin;

import fr.loudo.timberhearth.events.OnChangeDimensionEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.DimensionTransition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayer.class)
public class ServerPlayerFabric {

    @Inject(method = "changeDimension", at = @At("RETURN"))
    private void timberHeart$fabricChangeDimensionEvent(
            DimensionTransition transition, CallbackInfoReturnable<Entity> cir) {
        if (cir.getReturnValue() instanceof ServerPlayer player) {
            OnChangeDimensionEvent.changeDimensionEvent(transition.newLevel().dimension(), player);
        }
    }
}
