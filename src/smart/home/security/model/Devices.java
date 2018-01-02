package smart.home.security.model;

import java.util.List;
import java.util.ArrayList;

/**
 * The POJO to maintain a collection of devices.
 * @author archana
 */
public class Devices {

    /**
     * The list of devices.
     */
    private List<Device> devices;
    
    /**
     * An singleton instance of the devices.
     */
    private static Devices devicesInstance;

    /**
     * Private constructor to allow for a singleton.
     */
    private Devices() {
    }

    /**
     * Provides the singleton instance of devices.
     * @return an instance of Devices
     */
    public static Devices getInstance() {
        if (devicesInstance == null) {
            devicesInstance = new Devices();
            devicesInstance.devices = new ArrayList();
        }
        return devicesInstance;
    }

    /**
     * Returns a list of devices.
     * @return a list of devices
     */
    public List<Device> getDevices() {
        return devices;
    }

    /**
     * Returns a list of all the enabled devices.
     * @return a list of all the enabled devices.
     */
    public List<Device> getEnabledDevices() {
        List<Device> enabledDevices = new ArrayList<>();
        
        // Iterate through all the devices.
        for (Device device : devices) {
            // Find all the devices that have been marked as enabled.
            if (device.isEnabled()) {
                enabledDevices.add(device);
            }
        }
        return enabledDevices;
    }

    /**
     * Return a list of all the disabled devices.
     * @return a list of all the disabled devices.
     */
    public List<Device> getDisabledDevices() {
        List<Device> disabledDevices = new ArrayList<>();
        
        // Iterate through all the devices.
        for (Device device : devices) {
            // Find all the devices that have been marked as disabled.
            if (device.isDisabled()) {
                disabledDevices.add(device);
            }
        }
        return disabledDevices;
    }

    /**
     * Add the given device to the list of devices.
     * @param device - The device to be added to the devices. (Ignores if null)
     */
    public void addDevice(Device device) {
        // Add the device to the collection only if it is not null.
        if (device != null) {
            devices.add(device);
        }
    }

    /**
     * Remove the given device from the list of devices.
     * @param device - The device to be removed from the devices. (Ignores if null)
     */
    public void removeDevice(Device device) {
        // Remove the device from the collection only if it is not null.
        if (device != null) {
            devices.remove(device);
        }
    }

    /**
     * Enable the given device.
     * @param device - The device to be enabled. (Ignores if null)
     */
    public void enableDevice(Device device) {
        // Enable the device from the collection only if it is not null.
        if (device != null) {
            device.enable();
        }
    }

    /**
     * Disable the given device.
     * @param device - The device to be disabled. (Ignores if null)
     */
    public void disableDevice(Device device) {
        // Disable the device from the collection only if it is not null.
        if (device != null) {
            device.disable();
        }
    }

    /**
     * Arms all the devices.
     */
    public void armDevices() {
        // Iterate through all the devices.
        for (Device device : devices) {
            // Arm each device.
            device.arm();
        }
    }

    /**
     * Disarms all the devices.
     */
    public void disarmDevices() {
        // Iterate through all the devices.
        for (Device device : devices) {
            // Disarm each device.
            device.disarm();
        }
    }

    /**
     * Determines if the devices are armed or disarmed.
     * @return true if the devices are armed, otherwise false.
     */
    public boolean armed() {
        // Iterate through all the devices.
        for (Device device : devices) {
            // If a device is enabled and armed then return armed.
            if (device.isEnabled() && device.isArmed()) {
                return true;
            }
        }
        
        // Return false if no devices are armed.
        return false;
    }

    /**
     * Determines if the devices can be armed.
     * @return true if the devices can be armed, otherwise false.
     */
    public boolean canArmDevices() {
        // Devices cannot be armed if there are no devices to arm.
        if (devices.isEmpty()) {
            return false;
        } else {
            // Iterate through all the devices.
            for (Device device : devices) {
                // If a device is enabled and disarmed then return true.
                if (device.isEnabled() && device.isDisarmed()) {
                    return true;
                }
            }
            
            // Return false if no devices can be armed.
            return false;
        }
    }

    /**
     * Determines if the devices can be disarmed.
     * @return true if the devices can be disarmed, otherwise false.
     */
    public boolean canDisarmDevices() {
        // Devices cannot be disarmed if there are no devices to disarm.
        if (devices.isEmpty()) {
            return false;
        } else {
            // Iterate through all the devices.
            for (Device device : devices) {
                // If a device is enabled and armed then return true.
                if (device.isEnabled() && device.isArmed()) {
                    return true;
                }
            }
        }
        // Return false if no devices can be disarmed.
        return false;
    }
}
