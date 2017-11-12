package smart.home.security.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import smart.home.security.model.Device;
import smart.home.security.model.Devices;

public class DeviceManager extends Thread {

    public static final String DEVICES_FILE_PATH = "/Users/chana/code/devicesInfo.txt";

    public void run() {
        try {
            saveDevices();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void loadDevices() {
        try {
            FileManager fileManager = new FileManager();
            List<String> rawDevices = fileManager.readFile(DEVICES_FILE_PATH);
            for (String rawDevice : rawDevices) {                
                Devices.getInstance().addDevice(Device.deserialize(rawDevice));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDevices() throws IOException {
        List<Device> devices = Devices.getInstance().getDevices();
        List<String> rawDevices = new ArrayList<>();
        for (Device device : devices) {            
            rawDevices.add(device.serialize());
        }

        FileManager fileManager = new FileManager();
        fileManager.saveFile(rawDevices, DEVICES_FILE_PATH);
    }
}