package smart.home.security.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import smart.home.security.model.Device;
import smart.home.security.model.Devices;

/**
 * Manages the device.
 * @author archana
 */
public class DeviceManager extends Thread {

    /**
     * The name of the devices file.
     */
    public static final String DEVICES_FILE_NAME = ".shs_devices.txt";

    /**
     * The thread runner call.
     */
    public void run() {
        try {
            // Saves the devices.
            saveDevices();
        } catch (Exception ex) {
            // Print the stack trace if the devices fail to save.
            ex.printStackTrace();
        }
    }

    /**
     * Load all the devices into the devices instance.
     */
    public static void loadDevices() {
        try {
            // Read the files from the file manager.
            FileManager fileManager = new FileManager();            
            List<String> rawDevices = fileManager.readFile(deviceFilePath());            
            
            // Deserialize each device and add it to the devices instance.
            for (String rawDevice : rawDevices) {                
                Devices.getInstance().addDevice(Device.deserialize(rawDevice));
            }
        } catch (IOException e) {
            // Print the stack trace if the devices fail to load.
            e.printStackTrace();
        }
    }

    /**
     * Save all the devices into the devices file.
     * @throws IOException 
     */
    public void saveDevices() throws IOException {
        // Serialize all the devices to be saved.
        List<Device> devices = Devices.getInstance().getDevices();
        List<String> rawDevices = new ArrayList<>();
        
        // Add all the serialized devices to write.
        for (Device device : devices) {            
            rawDevices.add(device.serialize());
        }
        
        // Save the files using the file manager.
        FileManager fileManager = new FileManager();
        fileManager.saveFile(rawDevices, deviceFilePath());
    }
    
    /**
     * Get the system independent device file path.
     * @return the string that represents the file path of the devices file.
     */
    private static String deviceFilePath() {
        return System.getProperty("user.home") + File.separator + DEVICES_FILE_NAME;
    }
}