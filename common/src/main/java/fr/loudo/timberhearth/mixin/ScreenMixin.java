package fr.loudo.timberhearth.mixin;

import fr.loudo.timberhearth.client.TimberHearthSoundControl;
import fr.loudo.timberhearth.util.UtilCommon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ScreenMixin {

    @Shadow
    protected Minecraft minecraft;

    @Inject(method = "onClose", at = @At("RETURN"))
    private void timberHearth$onPauseStop(CallbackInfo ci) {
        if (((Object) this) instanceof PauseScreen) {
            if (UtilCommon.inCave(minecraft.level, minecraft.player.blockPosition())
                    || minecraft.level.isRaining()
                    || minecraft.level.isThundering()) {
                TimberHearthSoundControl.stop();
            }
        }
    }
}
