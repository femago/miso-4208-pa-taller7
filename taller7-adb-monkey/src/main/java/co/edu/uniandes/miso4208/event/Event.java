package co.edu.uniandes.miso4208.event;

public interface Event {
    String getName();

    String run(Context execContext);


    class Context {
    }
}
