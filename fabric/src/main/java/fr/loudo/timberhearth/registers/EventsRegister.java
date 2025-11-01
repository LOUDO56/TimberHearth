package fr.loudo.timberhearth.registers;

import fr.loudo.timberhearth.events.OnClientTick;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class EventsRegister {
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(OnClientTick::tick);
    }
}
