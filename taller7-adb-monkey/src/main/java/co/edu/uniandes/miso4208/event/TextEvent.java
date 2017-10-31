package co.edu.uniandes.miso4208.event;

public class TextEvent implements Event {
    @Override
    public String getName() {
        return "text";
    }

    @Override
    public String run(Context execContext) {
        return null;
    }
}
