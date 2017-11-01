package co.edu.uniandes.miso4208.config;

import co.edu.uniandes.miso4208.event.Event;
import com.google.common.collect.ImmutableList;
import lombok.Getter;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

import static co.edu.uniandes.miso4208.config.ExecutionParameters.ParamType.evt;
import static co.edu.uniandes.miso4208.config.ExecutionParameters.ParamType.num;
import static co.edu.uniandes.miso4208.config.ExecutionParameters.ParamType.s;
import static co.edu.uniandes.miso4208.config.ExecutionParameters.ParamType.tt;

@Log
public class ExecutionParameters {

    @Getter
    final private int eventCount;
    @Getter
    final private List<EventConfig> events;
    @Getter
    final private Optional<String> telnetToken;
    @Getter
    final private Optional<Long> seed;

    final private List<Event> availableEvents;

    public ExecutionParameters(String[] args, List<Event> events) {
        availableEvents = events;
        Map<String, String> normalizedParams = normalize(args);
        log.info("Parametros extraidos: " + normalizedParams.toString());
        this.events = ImmutableList.copyOf(parseEvents(normalizedParams));
        eventCount = parseEventCount(normalizedParams);
        telnetToken = Optional.ofNullable(parseTelnetToken(normalizedParams));
        seed = parseSeed(normalizedParams);
    }


    private Map<String, String> normalize(String[] args) {
        if (args.length % 2 != 0)
            fail("Incorrecto numero de parametros");
        List<String> params = Arrays.asList(args);
        HashMap<String, String> paramsByType = new HashMap<>();

        for (Iterator<String> iter = params.iterator(); iter.hasNext(); ) {
            String next = iter.next().trim();
            isValidParam(next);
            paramsByType.put(next.replace("-", ""), iter.next().trim());
        }
        return paramsByType;
    }

    private void isValidParam(String next) {
        if (!next.startsWith("-"))
            fail("Se esperaba un tipo de parametro: %1$s", next);
    }

    private int parseEventCount(Map<String, String> normalizedParams) {
        String n = normalizedParams.get(num.toString());
        try {
            return Integer.valueOf(n).intValue();
        } catch (NumberFormatException e) {
            throw fail("Valor inesperado para numero de eventos: %1$s", n);
        }
    }

    private String parseTelnetToken(Map<String, String> normalizedParams) {
        return normalizedParams.get(tt.toString());
    }

    private Optional<Long> parseSeed(Map<String, String> normalizedParams) {
        String n = normalizedParams.get(s.toString());
        try {
            if (n != null)
                return Optional.of(Long.valueOf(n).longValue());
            else
                return Optional.empty();
        } catch (NumberFormatException e) {
            throw fail("Valor inesperado para la semilla: %1$s", n);
        }
    }

    private List<EventConfig> parseEvents(Map<String, String> normalizedParams) {
        String events = normalizedParams.get(evt.toString()).trim();
        List<EventConfig> eventConfigs = new ArrayList<>();

        if (StringUtils.isBlank(events))
            fail("El listado de tipos de eventos es requeridos %1$s", "");

        StringTokenizer tokens = new StringTokenizer(events, ",");
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            Optional<Event> first = availableEvents.stream()
                    .filter(event -> event.getName().equals(token))
                    .findFirst();
            if (first.isPresent()) {
                eventConfigs.add(new EventConfig(first.get(), null));
            } else {
                fail("No se reconoce el evento con nombre %1$s", token);
            }
        }


        return eventConfigs;
    }


    private IllegalArgumentException fail(String message, Object... content) {
        return new IllegalArgumentException(String.format(message, content));
    }


    enum ParamType {
        num, // Numero de eventos
        evt, // Listado de tipos de eventos
        tt, // telnet token
        s, // semilla
    }
}
