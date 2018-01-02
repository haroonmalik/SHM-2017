package smart.home.security.utilities;

import java.io.IOException;
import smart.home.security.model.Device;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import smart.home.security.model.Devices;
import smart.home.security.model.Notifications;
import smart.home.security.view.SmartHomeSecurityFrame;

/**
 * The device socket manager that holds all the connections to each device.
 * @author archana
 */
public class DeviceSocketManager extends Thread {

    /**
     * The singleton device manager.
     */
    private static DeviceSocketManager manager;
    
    /**
     * The map that holds each device to the device socket client.
     */
    private static Map<Device, DeviceSocketClient> deviceSockets;

    /**
     * The private DeviceSocketManager constructor
     */
    private DeviceSocketManager() {
    }

    /**
     * The singleton instance.
     * @return the DeviceSocketManager instance.
     */
    public static DeviceSocketManager getInstance() {
        // Create the manager and initialize the device sockets once.
        if (manager == null) {
            manager = new DeviceSocketManager();
            deviceSockets = new HashMap();
        }
        
        // Return the manager.
        return manager;
    }

    /**
     * The thread runner.
     */
    public void run() {
        try {
            // Disconnect the devices.
            disconnectDevices();
        } catch (Exception ex) {
            // Print the stack trace if an exception is thrown.
            ex.printStackTrace();
        }
    }

    /**
     * Connect to all the devices avaliable in the devices instance.
     */
    public void connectDevices() {
        // Iterate through all the devices.
        for (Device device : Devices.getInstance().getDevices()) {
            // Connect each device.
            connectDevice(device);
        }
    }

    /**
     * Create a connection to the given device.
     * @param device - The Device that needs to be connected to.
     */
    public void connectDevice(Device device) {
        // Create a connection only if the device is given.
        if (device != null) {
            // Create the socket conenction for the device.
            DeviceSocketClient client = createSocket(device);

            // Add the connection and the device only if a succesfull connection has been opened.
            if (client != null && client.userSession != null && client.userSession.isOpen()) {
                // Add the device and the client connection to the device sockets map. 
                deviceSockets.put(device, client);
                // Ping the device with a message now that a connection has been opened.
                sendDeviceMessage(device);
            }
        }
    }

    /**
     * Disconnect from all the devices.
     */
    public void disconnectDevices() {
        // Iterate through the list of all the device instances.
        for (Device device : Devices.getInstance().getDevices()) {
            // Disconnect the connection from each device.
            disconnectDevice(device);
            // Purge the device and the client connection from the device sockets map.
            deviceSockets.remove(device);
        }
    }

    /**
     * Disconnect the give device by closing its socket connection.
     * @param device - the Device to be disconnected.
     */
    public void disconnectDevice(Device device) {
        // Validate the device is not null.
        if (device != null) {
            // Get the socket client for the given device.
            DeviceSocketClient socket = deviceSockets.get(device);
            // Attempt to close the connection if one is open.
            if (socket != null && socket.userSession.isOpen()) {
                try {
                    // Close the socket connection.
                    socket.userSession.close();
                } catch (IOException ex) {
                    // Log a message on exception.
                    Logger.getLogger(DeviceSocketManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Create socket client with given device.
     * @param device - the Device that should be connected to.
     * @return DeviceSocketClient with the given device.
     */
    private DeviceSocketClient createSocket(Device device) {
        try {
            // Get the URI of the given device.
            URI uri = new URI(deviceURI(device));
            
            // Return the socket client from the URI. 
            return new DeviceSocketClient(uri);
        } catch (URISyntaxException ex) {
            // Print the error if an expection is caught.
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
        
        // Return null if the socket client could not be created/
        return null;
    }

    /**
     * Send a message to the given device.
     * @param device - The Device to which the message should be send.
     */
    public void sendDeviceMessage(final Device device) {
        // Get the socket client of the given device.
        final DeviceSocketClient clientEndPoint = deviceSockets.get(device);

        // Send a message only if the client endpoint is available.
        if (clientEndPoint != null) {
            // Add the message handler to the client end point.
            clientEndPoint.addMessageHandler(new DeviceSocketClient.MessageHandler() {
                /**
                 * Handler to handle the message.
                 * @param message - The String message received from the connetion.
                 */
                public void handleMessage(String message) {
                    System.out.println(message);
                    // Address the message if it is a notification.
                    if (message.contains("NOTIFY")) {
                        // Determine the status of the device by parsing the message.
                        // The status is true if the device is open otherwise false.
                        Boolean deviceStatus = message.contains("'DEVICE_OPEN': 1");
                        // Add the message to the notifications.
                        Notifications.getInstance().addNotification(device, deviceStatus);
                        // Notify the user of the notification by playing the ALERT audio.
                        AudioManager audioManager = new AudioManager();
                        audioManager.playAudio(AudioManager.SYSTEM_ALERT);
                        
                        // Refresh the frame to show the notifications banner.
                        SmartHomeSecurityFrame.getInstance().refresh();
                    }
                }
            });
            
            // Create the message that needs to be send to the server.
            // Include the device details (Enabled/Disabled, Armed/Disarmed) in the message.
            String message = Json.createObjectBuilder()
                    .add("DEVICE_ENABLED", device.isEnabled())
                    .add("DEVICE_ARMED", device.isArmed())
                    .build()
                    .toString();

            // Send message to websocket.
            clientEndPoint.sendMessage(message);
        }
    }

    /**
     * The device connection URI for the given device.
     * @param device - The Device.
     * @return string connection URI for the given device.
     */
    private String deviceURI(Device device) {
        return String.format("ws://%s:8888/", device.getAddress());
    }
}
