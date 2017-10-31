package co.edu.uniandes.miso4208.event;

public class RotateEvent implements Event {
    @Override
    public String getName() {
        return "rotate";
    }

    @Override
    public String run(Context execContext) {
        return null;
    }
}
