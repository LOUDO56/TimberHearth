package fr.loudo.timberhearth;

import fr.loudo.timberhearth.registers.EventsRegister;
import net.fabricmc.api.ModInitializer;

public class ExampleMod implements ModInitializer {

    @Override
    public void onInitialize() {
        CommonClass.init();
        EventsRegister.register();
    }
}
