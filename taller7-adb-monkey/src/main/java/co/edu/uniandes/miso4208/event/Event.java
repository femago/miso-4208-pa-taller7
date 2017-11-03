package co.edu.uniandes.miso4208.event;

import co.edu.uniandes.miso4208.config.Emulator;
import lombok.extern.java.Log;

@Log
public abstract class Event {

    enum EventClass {
        text,
        tap,
        swipe,
        key,
        sensor,
        rotate,
        net,
    }

    enum EventType {
        telnet,//
        adb,//
    }

    public abstract String getName();

    protected abstract EventType type();

    protected abstract String command(Emulator emulator);

    public final void run(Emulator emulator) {
        String commandPrefix = this.command(emulator);
        switch (type()) {
            case adb:
                String command = "shell input " + commandPrefix;
                emulator.sendAdbCommand(command);
                log.info("adb " + command);
                break;
            case telnet:
                log.info("telnet " + commandPrefix);
                //emulator.sendTelnetCommand(commandPrefix);
                break;
        }
    }

}
