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
        this(name, address, enabled, true);
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
}
