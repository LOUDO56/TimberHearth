package fr.loudo.timberhearth;

import fr.loudo.timberhearth.registers.CommandsRegister;
import fr.loudo.timberhearth.registers.PayloadRegister;
import fr.loudo.timberhearth.registers.ServerEventsRegister;
import net.fabricmc.api.ModInitializer;

public class TimberHearthFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        CommonClass.init();
        PayloadRegister.register();
        ServerEventsRegister.register();
        CommandsRegister.register();
    }
}
