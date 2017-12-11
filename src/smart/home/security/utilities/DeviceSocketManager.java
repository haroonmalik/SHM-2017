/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.swing.JOptionPane;
import smart.home.security.model.Devices;
import smart.home.security.model.Notifications;
import smart.home.security.view.SmartHomeSecurityFrame;

public class DeviceSocketManager extends Thread {

    private static DeviceSocketManager manager;
    private static Map<Device, DeviceSocketClient> deviceSockets;

    private DeviceSocketManager() {
    }

    public static DeviceSocketManager getInstance() {
        if (manager == null) {
            manager = new DeviceSocketManager();
            deviceSockets = new HashMap();
        }
        return manager;
    }

    public void run() {
        try {
            disconnectDevices();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void connectDevices() {
        for (Device device : Devices.getInstance().getDevices()) {
            connectDevice(device);
        }
    }

    public void connectDevice(Device device) {
        if (device != null) {
            DeviceSocketClient client = createSocket(device);

            if (client != null && client.userSession != null && client.userSession.isOpen()) {
                deviceSockets.put(device, client);
                sendDeviceMessage(device);
            }
        }
    }

    public void disconnectDevices() {
        for (Device device : Devices.getInstance().getDevices()) {
            disconnectDevice(device);
            deviceSockets.remove(device);
        }
    }

    public void disconnectDevice(Device device) {
        if (device != null) {
            DeviceSocketClient socket = deviceSockets.get(device);
            if (socket != null && socket.userSession.isOpen()) {
                try {
                    socket.userSession.close();
                } catch (IOException ex) {
                    Logger.getLogger(DeviceSocketManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private DeviceSocketClient createSocket(Device device) {
        try {
            URI uri = new URI(deviceURI(device));
            return new DeviceSocketClient(uri);
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
        return null;
    }

    public void sendDeviceMessage(final Device device) {
        final DeviceSocketClient clientEndPoint = deviceSockets.get(device);

        if (clientEndPoint != null) {
            // add listener
            clientEndPoint.addMessageHandler(new DeviceSocketClient.MessageHandler() {
                public void handleMessage(String message) {
                    System.out.println(message);
                    if (message.contains("NOTIFY")) {
                        Boolean deviceStatus = message.contains("'DEVICE_OPEN': 1");
                        Notifications.getInstance().addNotification(device, deviceStatus);                       
                        SmartHomeSecurityFrame.getInstance().refresh();
                    }
                }
            });
            String message = Json.createObjectBuilder()
                    .add("DEVICE_ENABLED", device.isEnabled())
                    .add("DEVICE_ARMED", device.isArmed())
                    .build()
                    .toString();

            // send message to websocket
            clientEndPoint.sendMessage(message);
        }
    }

    private String deviceURI(Device device) {
        return String.format("ws://%s:8888/", device.getAddress());
    }
}
