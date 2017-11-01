package co.edu.uniandes.miso4208.event;

import co.edu.uniandes.miso4208.config.Emulator;

public class RotateEvent extends Event {
    @Override
    public String getName() {
        return "rotate";
    }

    @Override
    protected EventType type() {
        return EventType.telnet;
    }

    @Override
    protected String command(Emulator emulator) {
        return "rotate";
    }

}
