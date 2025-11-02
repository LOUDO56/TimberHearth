package fr.loudo.timberhearth.client;

import fr.loudo.timberhearth.audio.SoundExtension;
import fr.loudo.timberhearth.sound.ModSounds;
import fr.loudo.timberhearth.util.UtilCommon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

public class TimberHearthSoundControl {

    private static final SoundInstance TIMBER_HEARTH_INSTANCE = SimpleSoundInstance.forLocalAmbience(
            SoundEvent.createVariableRangeEvent(ModSounds.TIMBER_HEARTH), 1.0f, 0.3f);
    private static final SoundManager SOUND_MANAGER = Minecraft.getInstance().getSoundManager();

    private static final int TOTAL_TICK_RAIN = (int) (5.0 * 20.0);
    private static final int TOTAL_TICK_JOIN = (int) (2.0 * 20.0);
    private static final int TOTAL_TICK_CAVE = (int) (3.0 * 20.0);

    public enum SoundType {
        RAIN(TOTAL_TICK_RAIN),
        JOIN(TOTAL_TICK_JOIN),
        CAVE(TOTAL_TICK_CAVE);

        private final int totalTick;

        SoundType(int totalTick) {
            this.totalTick = totalTick;
        }

        public int getTotalTick() {
            return totalTick;
        }
    }

    private static SoundType currentSoundType = SoundType.RAIN;
    private static boolean flag;
    private static int tick;
    private static float currentVolume, breakVolumePoint;

    public static void reset(boolean flag, SoundType type) {
        breakVolumePoint = -1f;
        if (Minecraft.getInstance().player.level().dimension() != Level.OVERWORLD) return;
        if (!UtilCommon.isDayTime(Minecraft.getInstance().level.getDayTime())) return;
        TimberHearthSoundControl.flag = flag;
        currentSoundType = type;
        if (isTransitioning()) {
            breakVolumePoint = currentVolume;
        }
        if (type == SoundType.RAIN
                && UtilCommon.inCave(
                        Minecraft.getInstance().level,
                        Minecraft.getInstance().player.blockPosition())) return;
        tick = 0;
        if (type == SoundType.JOIN) {
            SOUND_MANAGER.setVolume(TIMBER_HEARTH_INSTANCE, 0.0f);
        }
    }

    public static void disconnect() {
        breakVolumePoint = -1f;
        tick = SoundType.RAIN.totalTick;
    }

    public static void play(double seconds) {
        if (UtilCommon.inCave(
                Minecraft.getInstance().level, Minecraft.getInstance().player.blockPosition())) return;
        stop();
        ((SoundExtension) SOUND_MANAGER).timberHearth$playAt(TIMBER_HEARTH_INSTANCE, seconds);
        ClientLevel level = Minecraft.getInstance().level;
        if (level.isRaining() || level.isThundering()) {
            SOUND_MANAGER.setVolume(TIMBER_HEARTH_INSTANCE, 0.0f);
        }
    }

    public static void stop() {
        SOUND_MANAGER.stop(TIMBER_HEARTH_INSTANCE);
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

        if (!SOUND_MANAGER.isActive(TIMBER_HEARTH_INSTANCE)) {
            play(UtilCommon.getOSTSecondsFromDayTime(
                    Minecraft.getInstance().level.getDayTime()));
        }

        double t = Mth.clamp((tick + partialTick) / currentSoundType.getTotalTick(), 0.0, 1.0);
        float volume = Mth.clamp(calculateVolume(t), 0.0f, 1.0f);

        currentVolume = volume;
        if (volume == 1.0f || volume == 0.0f) {
            breakVolumePoint = -1f;
        }
        SOUND_MANAGER.setVolume(TIMBER_HEARTH_INSTANCE, volume);
    }

    private static float calculateVolume(double t) {
        return (float)
                (flag
                        ? Mth.lerp(t, breakVolumePoint != -1 ? breakVolumePoint : 1.0, 0.0)
                        : Mth.lerp(t, breakVolumePoint != -1 ? breakVolumePoint : 0.0, 1.0));
    }
}
