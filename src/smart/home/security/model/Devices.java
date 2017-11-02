/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart.home.security.model;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author chana
 */
public class Devices {

    private List<Device> devices;
    private static Devices devicesInstance;

    private Devices() {
    }

    public static Devices getInstance() {
        if (devicesInstance == null) {
            devicesInstance = new Devices();
            devicesInstance.devices = new ArrayList();
        }
        return devicesInstance;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void addDevice(Device device) {
        if (device != null) {
            devices.add(device);
        }
    }

    public void removeDevice(Device device) {
        if (device != null) {
            devices.remove(device);
        }
    }
    public void enableDevice(Device device) {
        
    }
    public void disableDevice(Device device) {
    
    }
}
