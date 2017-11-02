package co.edu.uniandes.miso4208.entry;

import co.edu.uniandes.miso4208.config.EventConfig;
import co.edu.uniandes.miso4208.config.ExecutionParameters;
import co.edu.uniandes.miso4208.config.Runner;
import co.edu.uniandes.miso4208.config.Emulator;
import co.edu.uniandes.miso4208.event.EventDirectory;

import java.util.List;

public class Main {
    /**
     * -Djava.util.logging.SimpleFormatter.format="%1$tH:%1$tM:%1$tS %4$-6s %2$s:-- %5$s%6$s%n"
     * -num 20 -evt tap,text,key,net,rotate,swipe
     * @param args
     */
    public static void main(String[] args) {

        ExecutionParameters execParameters = new ExecutionParameters(args, EventDirectory.availableEvents());
        Runner runner = new Runner(execParameters, new Emulator(execParameters));
        runner.installApp();
        runner.startApp();
    }
}
