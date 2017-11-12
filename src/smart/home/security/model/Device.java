/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart.home.security.model;

/**
 *
 * @author chana
 */
public class Device {

    private String name;
    private String address;
    private boolean enabled;
    private boolean active;

    public Device(String name, String address) {
        this(name, address, true);
    }

    public Device(String name, String address, boolean enabled) {
        this(name, address, enabled, false);
    }

    public Device(String name, String address, boolean enabled, boolean active) {
        this.name = name;
        this.address = address;
        this.enabled = enabled;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void enable() {
        this.enabled = true;
    }

    public boolean isDisabled() {
        return !enabled;
    }

    public void disable() {
        disarm();
        this.enabled = false;
    }

    public boolean isArmed() {
        return active;
    }

    public void arm() {
        if (isEnabled()) {
            this.active = true;
        }
    }

    public boolean isDisarmed() {
        return !active;
    }

    public void disarm() {
        this.active = false;
    }

    public String toString() {
        return String.format("Name: %s, Address: %s", name, address);
    }

    public String serialize() {
        String deviceName = getName();
        String deviceAddress = getAddress();
        String enabled = isEnabled() ? "1" : "0";
        String active = isArmed() ? "1" : "0";
        return String.format("%s|%s|%s|%s", deviceName, deviceAddress, enabled, active);
    }

    public static Device deserialize(String rawDevice) {
        String[] tokens = rawDevice.split("\\|");
        String deviceName = tokens[0];
        String deviceAddress = tokens[1];
        String enabled = tokens[2];
        String active = tokens[3];

        Device device = new Device(deviceName, deviceAddress);
        if (enabled.equals("0")) {
            device.disable();
        } else {
            device.enable();
        }
        if (active.equals("0")) {
            device.disarm();
        } else {
            device.arm();
        }
        
        return device;
    }
}
