package view;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



public class PlaySound {
    
    public synchronized void play(final String fileName) {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();

                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        getClass().getResourceAsStream("/sounds/" + fileName)
                );
                
                
                clip.open(inputStream);
                clip.start();
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
                System.out.println("ERRO: " + e.getMessage());
            }
        }).start();
    }

}
