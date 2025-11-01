package fr.loudo.timberhearth;

import fr.loudo.timberhearth.registers.PayloadRegister;
import net.fabricmc.api.ModInitializer;

public class TimberHearthFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        CommonClass.init();
        PayloadRegister.register();
    }
}
