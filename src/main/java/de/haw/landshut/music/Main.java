package de.haw.landshut.music;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws Exception {
        final var javaAndMidi = new JavaAndMidi();

        final var drumChannel = javaAndMidi.getDrumChannel();
        drumChannel.noteOn(35, 127);
        sleep(500);
        drumChannel.allNotesOff();

        final var synthesizerChannel = javaAndMidi.getSynthesizerChannel();
        synthesizerChannel.noteOn(37, 127);
        sleep(500);
        synthesizerChannel.allNotesOff();
    }
}
