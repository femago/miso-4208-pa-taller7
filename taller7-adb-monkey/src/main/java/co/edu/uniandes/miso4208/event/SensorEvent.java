package co.edu.uniandes.miso4208.event;

public class SensorEvent extends Event {
    @Override
    public String getName() {
        return "sensor";
    }

    @Override
    public String run(Context execContext) {
        return null;
    }
}
