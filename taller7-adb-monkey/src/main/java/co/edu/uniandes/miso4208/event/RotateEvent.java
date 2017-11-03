package co.edu.uniandes.miso4208.event;

import co.edu.uniandes.miso4208.config.Emulator;

import static co.edu.uniandes.miso4208.event.Event.EventClass.rotate;

public class RotateEvent extends Event {
    @Override
    public String getName() {
        return rotate.toString();
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
