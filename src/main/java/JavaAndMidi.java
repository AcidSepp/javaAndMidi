import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.io.File;

public class JavaAndMidi {

    public void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final Synthesizer synthesizer;

    public JavaAndMidi() {
        try {
            synthesizer = MidiSystem.getSynthesizer();

            synthesizer.open();

            final var resource = getClass().getResource("/soundfont.sf2");
            final var file = new File(resource.toURI());

            var soundbank = MidiSystem.getSoundbank(file);

            if (soundbank != null && synthesizer.isSoundbankSupported(soundbank)) {
                synthesizer.loadAllInstruments(soundbank);
            } else {
                throw new RuntimeException("Could not initialize Synthesizer");
            }

            getSynthesizerChannel().programChange(6);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public MidiChannel getDrumChannel() {
        return synthesizer.getChannels()[9];
    }

    public MidiChannel getSynthesizerChannel() {
        return synthesizer.getChannels()[0];
    }

}
