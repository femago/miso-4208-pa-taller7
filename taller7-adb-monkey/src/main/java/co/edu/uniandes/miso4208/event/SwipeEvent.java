package co.edu.uniandes.miso4208.event;

import co.edu.uniandes.miso4208.config.Emulator;

public class SwipeEvent extends Event {
    @Override
    public String getName() {
        return "swipe";
    }

    @Override
    protected EventType type() {
        return EventType.adb;
    }

    @Override
    protected String command(Emulator emulator) {
        int x1 = emulator.getRandom().nextInt(emulator.getX() + 1);
        int y1 = emulator.getRandom().nextInt(emulator.getY() + 1);
        int x2 = emulator.getRandom().nextInt(emulator.getX() + 1);
        int y2 = emulator.getRandom().nextInt(emulator.getY() + 1);
        return String.format("swipe %1$s %2$s %3$s %4$s", x1, y1, x2, y2);
    }

}
