void main() {
    var javaAndMidi = new JavaAndMidi();

    var drumChannel = javaAndMidi.getDrumChannel();
    drumChannel.noteOn(35, 127);
    javaAndMidi.sleep(500);
    drumChannel.allNotesOff();

    var synthesizerChannel = javaAndMidi.getSynthesizerChannel();
    synthesizerChannel.noteOn(37, 127);
    javaAndMidi.sleep(500);
    synthesizerChannel.allNotesOff();
}
