package src;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    private Clip c;

    // get sound clip
    public Sound(String fn) {
        File f = new File(fn);
        try {
            c = AudioSystem.getClip();
            c.open(AudioSystem.getAudioInputStream(f));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    // plays sound once
    public void playonce() {
        if (c == null) {
            return;
        }
        stop();
        c.start();
    }

    // plays sounds non stop
    public void play() {
        if (c == null) {
            return;
        }
        stop();
        c.loop(Clip.LOOP_CONTINUOUSLY);
    }
    // close
    public void close() {
        stop();
        c.close();
    }

    //stop
    public void stop() {
        if (c.isRunning() && c != null) {
            c.stop();
        }
    }

}
