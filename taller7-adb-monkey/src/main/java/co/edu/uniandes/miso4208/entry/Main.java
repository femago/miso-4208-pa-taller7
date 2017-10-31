package co.edu.uniandes.miso4208.entry;

import co.edu.uniandes.miso4208.config.ExecutionParameters;
import co.edu.uniandes.miso4208.event.EventDirectory;

public class Main {

    public static void main(String[] args) {

        new ExecutionParameters(args, EventDirectory.availableEvents());
    }
}
