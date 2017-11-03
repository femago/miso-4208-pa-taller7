package co.edu.uniandes.miso4208.event;

import co.edu.uniandes.miso4208.config.Emulator;
import org.apache.commons.text.RandomStringGenerator;

import static co.edu.uniandes.miso4208.event.Event.EventClass.text;
import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

public class TextEvent extends Event {
    @Override
    public String getName() {
        return text.toString();
    }

    @Override
    protected EventType type() {
        return EventType.adb;
    }

    private RandomStringGenerator randomStringGenerator;

    @Override
    protected String command(Emulator emulator) {
        initRSG(emulator);
        return String.format("text \"%1$s\"", randomStringGenerator.generate(20));
    }

    private void initRSG(Emulator emulator) {
        if (randomStringGenerator == null) {
            randomStringGenerator = new RandomStringGenerator.Builder()
                    .withinRange('0', 'z')
                    .filteredBy(LETTERS, DIGITS)
                    .usingRandom(i -> emulator.getRandom().nextInt(i)).build();
        }
    }


}
