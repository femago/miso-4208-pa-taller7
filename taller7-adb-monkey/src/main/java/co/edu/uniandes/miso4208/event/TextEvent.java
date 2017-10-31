package co.edu.uniandes.miso4208.event;

public class TextEvent extends Event {
    @Override
    public String getName() {
        return "text";
    }

    @Override
    protected EventType type() {
        return EventType.adb;
    }

    @Override
    protected String command() {
        return null;
    }


}
