package co.edu.uniandes.miso4208.event;

public abstract class Event {

    enum EventType{
        telnet,//
        adb,//
    }

    public abstract String getName();

    protected abstract EventType type();

    protected abstract String command();

    public void run(Context execContext){
        switch (type()){
            case adb:
                break;
            case telnet:
                break;
        }

    }

}
