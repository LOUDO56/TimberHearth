package fr.loudo.timberhearth.client;

import fr.loudo.timberhearth.audio.VolumeAudio;
import fr.loudo.timberhearth.sound.ModSounds;
import fr.loudo.timberhearth.util.UtilClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;

public class TimberHearthSoundControl {

    private static final SoundInstance TIMBER_HEARTH_INSTANCE = SimpleSoundInstance.forLocalAmbience(
            SoundEvent.createVariableRangeEvent(ModSounds.TIMBER_HEARTH), 1.0f, 0.3f);
    private static final SoundManager soundManager = Minecraft.getInstance().getSoundManager();

    private static final int TOTAL_TICK_RAIN = (int) (5.0 * 20.0);
    private static final int TOTAL_TICK_CAVE = (int) (3.0 * 20.0);

    public enum SoundType {
        RAIN(TOTAL_TICK_RAIN),
        CAVE(TOTAL_TICK_CAVE);

        private final int totalTick;

        SoundType(int totalTick) {
            this.totalTick = totalTick;
        }

        public int getTotalTick() {
            return totalTick;
        }
    }

    private static SoundType currentSoundType = SoundType.CAVE;
    private static boolean flag;
    private static int tick;
    private static float currentVolume, breakVolumePoint;

    public static void reset(boolean flag, SoundType type) {
        breakVolumePoint = -1f;
        TimberHearthSoundControl.flag = flag;
        currentSoundType = type;
        if (isTransitioning()) {
            breakVolumePoint = currentVolume;
        }
        if (type == SoundType.RAIN && UtilClient.inCave()) return;
        tick = 0;
    }

    public static void play() {
        stop();
        soundManager.play(TIMBER_HEARTH_INSTANCE);
        ClientLevel level = Minecraft.getInstance().level;
        if (level.isRaining() || level.isThundering()) {
            ((VolumeAudio) soundManager).timberHearth$setVolume(TIMBER_HEARTH_INSTANCE, 0.0f);
        }
    }

    public static void stop() {
        soundManager.stop(TIMBER_HEARTH_INSTANCE);
    }

    public static boolean in() {
        return TimberHearthSoundControl.flag;
    }

    public static boolean out() {
        return !TimberHearthSoundControl.flag;
    }

    public static boolean currentRain() {
        return currentSoundType == SoundType.RAIN;
    }

    public static boolean currentCave() {
        return currentSoundType == SoundType.CAVE;
    }

    public static boolean isTransitioning() {
        return (double) tick / currentSoundType.getTotalTick() < 1.0;
    }

    public static void tick() {
        if (tick < currentSoundType.getTotalTick()) {
            tick++;
        }
    }

    public static void fade(float partialTick) {
        if (tick == currentSoundType.getTotalTick()) return;

        if (!soundManager.isActive(TIMBER_HEARTH_INSTANCE)) return;

        double t = Mth.clamp((tick + partialTick) / currentSoundType.getTotalTick(), 0.0, 1.0);
        float volume = calculateVolume(t);

        currentVolume = volume;
        if (volume == 1.0f || volume == 0.0f) {
            breakVolumePoint = -1f;
        }
        ((VolumeAudio) soundManager).timberHearth$setVolume(TIMBER_HEARTH_INSTANCE, volume);
    }

    private static float calculateVolume(double t) {
        return (float)
                (flag
                        ? Mth.lerp(t, breakVolumePoint != -1 ? breakVolumePoint : 1.0, 0.0)
                        : Mth.lerp(t, breakVolumePoint != -1 ? breakVolumePoint : 0.0, 1.0));
    }
}
