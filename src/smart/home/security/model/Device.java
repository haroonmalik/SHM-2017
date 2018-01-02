package smart.home.security.model;

/**
 * The POJO that represents a device and its details.
 * @author archana
 */
public class Device {

    /**
     * The name of the device.
     */
    private String name;
    
    /**
     * The IP address of the device.
     */
    private String address;
    
    /**
     * The MAC address of the device.
     */
    private String macAddress;
    
    /**
     * True if this device is enabled, otherwise false.
     */
    private boolean enabled;
    
    /**
     * True if the device is armed, otherwise disarmed.
     */
    private boolean active;
    
    /**
     * Constructor to create a device.
     * @param name - The name of the device
     * @param address - The IP address of the device
     */
    public Device(String name, String address) {
        this(name, address, true);
    }
    
    /**
     * Constructor to create a device.
     * @param name - The name of the device
     * @param address - The IP address of the device
     * @param macAddress - The mac-address of the device
     */
    public Device(String name, String address, String macAddress) {
        this(name, address, true, false, macAddress);
    }

    /**
     * Constructor to create a device.
     * @param name - The name of the device
     * @param address - The IP address of the device
     * @param enabled - True if this device is enabled, otherwise disabled
     */
    public Device(String name, String address, boolean enabled) {
        this(name, address, enabled, false);
    }
    
    /**
     * Constructor to create a device.
     * @param name - The name of the device
     * @param address - The IP address of the device
     * @param enabled - True if this device is enabled, otherwise disabled
     * @param active - True if the device is armed, otherwise disarmed
     */
    public Device(String name, String address, boolean enabled, boolean active) {
        this.name = name;
        this.address = address;
        this.enabled = enabled;
        this.active = active;
    }

    /**
     * Constructor to create a device.
     * @param name - The name of the device
     * @param address - The IP address of the device
     * @param enabled - True if this device is enabled, otherwise disabled
     * @param active - True if the device is armed, otherwise disarmed
     * @param macAddress - The MAC address of the device
     */
    public Device(String name, String address, boolean enabled, boolean active, String macAddress) {
        this.name = name;
        this.address = address;
        this.enabled = enabled;
        this.active = active;
        this.macAddress = macAddress;
    }

    /**
     * The device MAC address.
     * @return the string MAC address
     */
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * The name of this device.
     * @return the string name of this device
     */
    public String getName() {
        return name;
    }

    /**
     * The IP address of this device.
     * @return string IP address of this device.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Determines if the device is enabled or disabled.
     * @return true if the device is enabled, otherwise disabled.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Enables the device.
     */
    public void enable() {
        this.enabled = true;
    }

    /**
     * Determines if the device is disabled or enabled.
     * @return true if the device is disabled, otherwise enabled.
     */
    public boolean isDisabled() {
        return !enabled;
    }

    /**
     * Disables the device.
     */
    public void disable() {
        disarm();
        this.enabled = false;
    }

    /**
     * Determines if the device is armed or disarmed.
     * @return true if the device is armed, otherwise disarmed.
     */
    public boolean isArmed() {
        return active;
    }

    /**
     * Arms this device.
     */
    public void arm() {
        // Only arm the device if it is enabled.
        if (isEnabled()) {
            this.active = true;
        }
    }

    /**
     * Determines if the device is disarmed or armed.
     * @return true if the device is disarmed otherwise armed.
     */
    public boolean isDisarmed() {
        return !active;
    }

    /**
     * Disarm the device
     */
    public void disarm() {
        this.active = false;
    }

    /**
     * The string representation of this POJO.
     * @return the description of this device.
     */
    public String toString() {
        return String.format("Name: %s, Address: %s", name, address);
    }

    /**
     * Serialize this object into a string.
     * @return the string representation of this serializable object.
     */
    public String serialize() {
        // Get the name of the device.
        String deviceName = getName();
        // Get the IP address of the device.
        String ipAddress = getAddress();
        // Get the MAC address of the device.
        String macAddress = getMacAddress();
        // Get the device enabled or disabled status.
        String enabled = isEnabled() ? "1" : "0";
        // Get the device armed or disarmed status.
        String active = isArmed() ? "1" : "0";
        
        // Format the device details into a string separated by pipes.
        return String.format("%s|%s|%s|%s|%s", deviceName, ipAddress, macAddress, enabled, active);
    }

    /**
     * De-serialize the given device into a device object.
     * @param rawDevice - The serialized string device details
     * @return the Device given the device details
     */
    public static Device deserialize(String rawDevice) {
        // Parse the raw device string by splitting on the pipe character.
        String[] tokens = rawDevice.split("\\|");
        // Parse each token with the format: "name|ip|mac|enabled|active"
        String deviceName = tokens[0];
        String ipAddress = tokens[1];
        String macAddress = tokens[2];
        String enabled = tokens[3];
        String active = tokens[4];

        // Create a device with the given name, MAC and IP address.
        Device device = new Device(deviceName, ipAddress, macAddress);
        
        // Check if device is enabled.
        if (enabled.equals("0")) {
            // Disable if the device is not enabled.
            device.disable();
        } else {
            // Enable if the device is enabled.
            device.enable();
        }
        
        // Check if the device is active.
        if (active.equals("0")) {
            // Diarm if the device is not active.
            device.disarm();
        } else {
            // Arm if the device is active.
            device.arm();
        }

        // Return the created device.
        return device;
    }
}
