package co.edu.uniandes.miso4208.config;

import co.edu.uniandes.miso4208.event.Event;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

import static co.edu.uniandes.miso4208.config.ExecutionParameters.ParamType.evt;
import static co.edu.uniandes.miso4208.config.ExecutionParameters.ParamType.num;

public class ExecutionParameters {

    final Integer eventCount;
    final List<EventConfig> events;
    final List<Event> availableEvents;

    public ExecutionParameters(String[] args, List<Event> events) {
        availableEvents = events;
        Map<String, String> normalizedParams = normalize(args);
        this.events = ImmutableList.copyOf(parseEvents(normalizedParams));
        eventCount = parseEventCount(normalizedParams);
    }

    private Map<String, String> normalize(String[] args) {
        if (args.length % 2 != 0)
            fail("Incorrecto numero de parametros");
        List<String> params = Arrays.asList(args);
        HashMap<String, String> paramsByType = new HashMap<>();

        for (Iterator<String> iter = params.iterator(); iter.hasNext(); ) {
            String next = iter.next().trim();
            if (!next.startsWith("-"))
                fail("Se esperaba un tipo de parametro: %1$s", next);
            paramsByType.put(next.replace("-", ""), next.trim());
        }
        return paramsByType;
    }

    private Integer parseEventCount(Map<String, String> normalizedParams) {
        String n = normalizedParams.get(num.toString());
        try {
            return Integer.valueOf(n);
        } catch (NumberFormatException e) {
            throw fail("Valor inesperado para numero de eventos: %1$s", n);
        }
    }

    private List<EventConfig> parseEvents(Map<String, String> normalizedParams) {

        String events = normalizedParams.get(evt.toString()).trim();
        if (StringUtils.isBlank(events))
            fail("El listado de tipos de eventos es requeridos %1$s", "");
        new StringTokenizer(events.)
        events.

        return null;
    }

    private IllegalArgumentException fail(String message, Object... content) {
        return new IllegalArgumentException(String.format(message, content));
    }

    enum ParamType {
        num, //Numero de eventos
        evt, //Listado de tipos de eventos
        dist //Distribucion para cada tipo de evento
    }
}
