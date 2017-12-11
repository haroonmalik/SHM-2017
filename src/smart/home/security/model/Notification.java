/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart.home.security.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author chana
 */
public class Notification {

    private String deviceName;
    private String deviceStatus;
    private String dateTime;

    public Notification(String deviceName, boolean deviceOpen) {
        this.deviceName = deviceName;
        this.deviceStatus = deviceOpen ? "Opened" : "Closed";
        this.dateTime = dateTime();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String deviceStatus() {
        return deviceStatus;
    }

    public String getDateTime() {
        return dateTime;
    }

    private String dateTime() {
        SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        return simpleDate.format(date);
    }
}
