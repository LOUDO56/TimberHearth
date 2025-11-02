package fr.loudo.timberhearth.events;

import fr.loudo.timberhearth.client.TimberHearthSoundControl;
import fr.loudo.timberhearth.util.UtilCommon;
import net.minecraft.client.Minecraft;

public class OnClientTick {

    private static boolean wasCave;

    public static void tick(Minecraft minecraft) {
        TimberHearthSoundControl.tick();
        if (minecraft.player == null || minecraft.level == null) return;
        boolean canTrigger = (!minecraft.level.isRaining() && !minecraft.level.isThundering())
                || TimberHearthSoundControl.currentRain() && TimberHearthSoundControl.out();
        boolean inCave = UtilCommon.inCave(minecraft.level, minecraft.player.blockPosition());
        if (inCave && !wasCave) {
            wasCave = true;
            if (canTrigger) {
                TimberHearthSoundControl.reset(true, TimberHearthSoundControl.SoundType.CAVE);
            }
        } else if (!inCave && wasCave) {
            wasCave = false;
            if (canTrigger) {
                TimberHearthSoundControl.reset(false, TimberHearthSoundControl.SoundType.CAVE);
            }
        }
    }
}
