package fr.loudo.timberhearth.register;

import fr.loudo.timberhearth.TimberHearth;
import fr.loudo.timberhearth.commands.TimberHearthReSyncCommand;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod(TimberHearth.MOD_ID)
public class CommandsRegister {

    public CommandsRegister(IEventBus eventBus) {
        NeoForge.EVENT_BUS.addListener(CommandsRegister::register);
    }

    public static void register(RegisterCommandsEvent event) {
        TimberHearthReSyncCommand.register(event.getDispatcher());
    }
}
