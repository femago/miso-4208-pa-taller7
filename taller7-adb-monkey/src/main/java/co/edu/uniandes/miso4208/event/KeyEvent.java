package co.edu.uniandes.miso4208.event;

import co.edu.uniandes.miso4208.config.Emulator;

import static co.edu.uniandes.miso4208.event.Event.EventClass.key;

public class KeyEvent extends Event {
    @Override
    public String getName() {
        return key.toString();
    }

    @Override
    protected EventType type() {
        return EventType.adb;
    }

    @Override
    protected String command(Emulator emulator) {
        int keyCode = emulator.getRandom().nextInt(284);
        return "keyevent " + keyCode;
    }
}
