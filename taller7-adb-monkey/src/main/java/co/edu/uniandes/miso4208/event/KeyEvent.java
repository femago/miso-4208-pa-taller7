package co.edu.uniandes.miso4208.event;

public class KeyEvent extends Event {
    @Override
    public String getName() {
        return "key";
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
