/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart.home.security.utilities;

import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class AudioManager {
    
    public static String SYSTEM_ARMED = "arm.wav";
    public static String SYSTEM_DISARMED = "disarm.wav";
    private static String RESOURCE_FILEPATH = "/smart/home/security/resources/audio/";
    
    public void playAudio(String audioName) {
        try {            
            String filePath = RESOURCE_FILEPATH + audioName;            
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            AudioStream audioStream = new AudioStream(inputStream);
            AudioPlayer.player.start(audioStream);
        } catch (IOException e) {
            System.out.println("Failed to play audio: " + audioName);
            System.out.println(e);
        }
    }    
}
