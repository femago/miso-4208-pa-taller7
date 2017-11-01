package co.edu.uniandes.miso4208.event;

import co.edu.uniandes.miso4208.config.Emulator;

public class SensorEvent extends Event {
    @Override
    public String getName() {
        return "sensor";
    }

    @Override
    protected EventType type() {
        return EventType.telnet;
    }

    @Override
    protected String command(Emulator emulator) {
        //TODO complete command
        return "sensor";
    }

}
