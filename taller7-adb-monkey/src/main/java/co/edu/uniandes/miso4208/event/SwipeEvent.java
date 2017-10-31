package co.edu.uniandes.miso4208.event;

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
    public String run(Context execContext) {
        return null;
    }
}
