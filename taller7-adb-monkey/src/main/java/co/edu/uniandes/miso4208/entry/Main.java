package co.edu.uniandes.miso4208.entry;

import co.edu.uniandes.miso4208.config.EventConfig;
import co.edu.uniandes.miso4208.config.ExecutionParameters;
import co.edu.uniandes.miso4208.config.Runner;
import co.edu.uniandes.miso4208.config.Emulator;
import co.edu.uniandes.miso4208.event.EventDirectory;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ExecutionParameters execParameters = new ExecutionParameters(args, EventDirectory.availableEvents());
        Runner runner = new Runner(execParameters, new Emulator(execParameters));
        runner.installApp();
        runner.startApp();
    }
}
