package co.edu.uniandes.miso4208.event;

import co.edu.uniandes.miso4208.config.Emulator;

import static co.edu.uniandes.miso4208.event.Event.EventClass.sensor;

public class SensorEvent extends Event {
    @Override
    public String getName() {
        return sensor.toString();
    }

    @Override
    protected EventType type() {
        return EventType.telnet;
    }

    @Override
    protected String command(Emulator emulator) {
        return "sensor set acceleration 2.23517e-07:9.77631:0.812348";
    }

}
