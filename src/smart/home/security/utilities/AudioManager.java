package smart.home.security.utilities;

import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Manager for the audio resources.
 * @author archana
 */
public class AudioManager {
    
    /**
     * System armed audio string.
     */
    public static String SYSTEM_ARMED = "arm.wav";
    
    /**
     * System disarmed audio string.
     */
    public static String SYSTEM_DISARMED = "disarm.wav";
    
    /**
     * System alert audio string.
     */
    public static String SYSTEM_ALERT = "alert.wav";
    
    /**
     * The location of the audio resources.
     */
    private static String RESOURCE_FILEPATH = "/smart/home/security/resources/audio/";
    
    /**
     * Play the audio given the name.
     * @param audioName - The name of the audio to be played.
     */
    public void playAudio(String audioName) {
        try {
            // Append the audio file name to the resource file path.
            String filePath = RESOURCE_FILEPATH + audioName;
            
            // Create an input stream of the audio file.
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            AudioStream audioStream = new AudioStream(inputStream);
            
            // Play the audio stream.
            AudioPlayer.player.start(audioStream);
        } catch (IOException e) {
            // Log an error if the audio fails to play.
            System.out.println("Failed to play audio: " + audioName);
            System.out.println(e);
        }
    }    
}
