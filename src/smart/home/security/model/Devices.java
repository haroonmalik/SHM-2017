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

    public List<Device> getEnabledDevices() {
        List<Device> enabledDevices = new ArrayList<>();
        for (Device device : devices) {
            if (device.isEnabled()) {
                enabledDevices.add(device);
            }
        }
        return enabledDevices;
    }

    public List<Device> getDisabledDevices() {
        List<Device> disabledDevices = new ArrayList<>();
        for (Device device : devices) {
            if (device.isDisabled()) {
                disabledDevices.add(device);
            }
        }
        return disabledDevices;
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
        if (device != null) {
            device.enable();
        }
    }

    public void disableDevice(Device device) {
        if (device != null) {
            device.disable();
        }
    }

    public void armDevices() {
        for (Device device : devices) {
            device.arm();
        }
    }

    public void disarmDevices() {
        for (Device device : devices) {
            device.disarm();
        }
    }

    public boolean armed() {
        for (Device device : devices) {
            if (device.isEnabled() && device.isArmed()) {
                return true;
            }
        }

        return false;
    }

    public boolean canArmDevices() {
        if (devices.isEmpty()) {
            return false;
        } else {
            for (Device device : devices) {
                if (device.isEnabled() && device.isDisarmed()) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean canDisarmDevices() {
        if (devices.isEmpty()) {
            return false;
        } else {
            for (Device device : devices) {
                if (device.isEnabled() && device.isArmed()) {
                    return true;
                }
            }
        }
        return false;
    }
}
