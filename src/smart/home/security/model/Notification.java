package smart.home.security.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The POJO for a device notification and its details.
 * @author archana
 */
public class Notification {

    /**
     * The name of the device that generated this notification.
     */
    private String deviceName;
    
    /**
     * The status of this device that generated this notification. ("Opened" or "Closed")
     */
    private String deviceStatus;
    
    /**
     * The date time the notification was generated.
     */
    private String dateTime;

    /**
     * The constructor of the notification.
     * @param deviceName - The name of the device that generated this notification.
     * @param deviceOpen - The device status of this device that generated this notification.
     */
    public Notification(String deviceName, boolean deviceOpen) {
        // Set the device name.
        this.deviceName = deviceName;
        // Set the device status to "Opened" or "Closed".
        this.deviceStatus = deviceOpen ? "Opened" : "Closed";
        // Set the current date time.
        this.dateTime = dateTime();
    }

    /**
     * The name of the device.
     * @return the string name of the device.
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * The device status.
     * @return the string status of this device that generated this notification.
     */
    public String deviceStatus() {
        return deviceStatus;
    }

    /**
     * The date time at which this notification was generated.
     * @return the string date time at which this notification was generated.
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Helper to get the current formatted date time.
     * @return the string formatted date time of now.
     */
    private String dateTime() {
        // Create a date time formatter with format: "MM/dd/yyyy HH:mm:ss"
        SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        // Get the current date time.
        Date date = new Date();
        // Return the formatted date time.
        return simpleDate.format(date);
    }
}
