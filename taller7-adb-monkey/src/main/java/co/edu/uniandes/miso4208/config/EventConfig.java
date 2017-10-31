package co.edu.uniandes.miso4208.config;

import co.edu.uniandes.miso4208.event.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EventConfig {
    final Event event;
    final Double distribution;
}
