package co.edu.uniandes.miso4208.event;

public class KeyEvent implements Event {
    @Override
    public String getName() {
        return "key";
    }

    @Override
    public String run(Context execContext) {
        return null;
    }
}
