package co.edu.uniandes.miso4208.event;

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
    public String run(Context execContext) {
        return null;
    }
}
