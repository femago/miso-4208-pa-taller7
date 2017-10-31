package co.edu.uniandes.miso4208.event;

import com.google.common.collect.ImmutableList;

import java.util.List;


public class EventDirectory {

    private static List<Event> events = null;

    public static List<Event> availableEvents() {
        if (events == null)
            initEvents();
        return events;
    }

    private static void initEvents() {
        events = ImmutableList.of(
                new TapEvent(),
                new TextEvent(),
                new SwipeEvent(),
                new KeyEvent(),
                new RotateEvent(),
                new NetSpeedEvent(),
                new SensorEvent()
        );
    }


}
