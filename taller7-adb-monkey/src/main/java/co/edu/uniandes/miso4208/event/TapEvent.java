package co.edu.uniandes.miso4208.event;

public class TapEvent implements Event {
    @Override
    public String getName() {
        return "tap";
    }

    @Override
    public String run(Context execContext) {
        return null;
    }
}
