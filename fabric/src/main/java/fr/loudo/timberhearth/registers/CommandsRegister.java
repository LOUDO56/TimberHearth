package fr.loudo.timberhearth.registers;

import fr.loudo.timberhearth.commands.TimberHearthReSyncCommand;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class CommandsRegister {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandBuildContext, commandSelection) -> {
            TimberHearthReSyncCommand.register(commandDispatcher);
        });
    }
}
