package co.edu.uniandes.miso4208.event;

import co.edu.uniandes.miso4208.config.Emulator;
import lombok.extern.java.Log;

@Log
public abstract class Event {

    enum EventType {
        telnet,//
        adb,//
    }

    public Event() {
        // Verify running emulator
        // Init adb

    }

    public abstract String getName();

    protected abstract EventType type();

    protected abstract String command(Emulator emulator);

    public final void run(Emulator emulator) {
        String commandPrefix = this.command(emulator);
        switch (type()) {
            case adb:
                String command = "shell input " + commandPrefix;
                log.info("adb "+command);
                //emulator.sendAdbCommand(command);
                break;
            case telnet:
                log.info("telnet "+commandPrefix);
                //emulator.sendTelnetCommand(commandPrefix);
                break;
        }
    }

}
