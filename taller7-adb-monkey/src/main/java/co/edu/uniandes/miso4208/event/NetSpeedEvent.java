package co.edu.uniandes.miso4208.event;

import co.edu.uniandes.miso4208.config.Emulator;

public class NetSpeedEvent extends Event {
    enum Speed{
        gsm,
        hscsd,
        gprs,
        edge,
        umts,
        hsdpa,
        lte,
        evdo,
        full,
    }

    @Override
    public String getName() {
        return "net";
    }

    @Override
    protected EventType type() {
        return EventType.telnet;
    }

    @Override
    protected String command(Emulator emulator) {
        return "network speed "+Speed.values()[emulator.getRandom().nextInt(9)];
    }

}
