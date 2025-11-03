package fr.loudo.timberhearth.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import fr.loudo.timberhearth.network.PlayTimberHearthSoundS2C;
import fr.loudo.timberhearth.platform.Services;
import fr.loudo.timberhearth.util.UtilCommon;
import java.util.Collection;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class TimberHearthReSyncCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("timber_hearth")
                .then(Commands.literal("resync_song")
                        .executes(context ->
                                syncMusicForPlayer(context.getSource().getPlayer(), context.getSource()))
                        .then(Commands.argument("player", EntityArgument.players())
                                .requires(source -> source.hasPermission(2))
                                .executes(context -> {
                                    Collection<ServerPlayer> players = EntityArgument.getPlayers(context, "player");
                                    return syncMusicForPlayers(players, context.getSource());
                                }))));
    }

    private static int syncMusicForPlayer(ServerPlayer player, CommandSourceStack commandSourceStack) {
        if (player == null) return 0;
        double songSecondsBasedOnTime =
                UtilCommon.getOSTSecondsFromDayTime(player.level().getDayTime());
        Services.PACKET_SENDER.sendToPlayer(player, new PlayTimberHearthSoundS2C(songSecondsBasedOnTime));
        commandSourceStack.sendSuccess(
                () -> Component.translatable("timber_hearth.command.re_sync.only_player"), false);
        return Command.SINGLE_SUCCESS;
    }

    private static int syncMusicForPlayers(Collection<ServerPlayer> players, CommandSourceStack commandSourceStack) {
        players.forEach(player -> syncMusicForPlayer(player, commandSourceStack));
        commandSourceStack.sendSuccess(() -> Component.translatable("timber_hearth.command.re_sync.players"), false);
        return Command.SINGLE_SUCCESS;
    }
}
