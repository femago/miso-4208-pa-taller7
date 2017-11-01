package co.edu.uniandes.miso4208.event;

import co.edu.uniandes.miso4208.config.Emulator;

public class TapEvent extends Event {
    @Override
    public String getName() {
        return "tap";
    }

    @Override
    protected EventType type() {
        return EventType.adb;
    }

    @Override
    protected String command(Emulator emulator) {
        int x = emulator.getRandom().nextInt(emulator.getX() + 1);
        int y = emulator.getRandom().nextInt(emulator.getY() + 1);
        return String.format("tap %1$s %2$s", x, y);
    }

}
