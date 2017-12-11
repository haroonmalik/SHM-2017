/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart.home.security.utilities;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import smart.home.security.model.Notification;

/**
 *
 * @author chana
 */
public class NotificationsModel {

    public static DefaultTableModel defaultTableModel(List<Notification> notifications) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Date/Time");
        model.addColumn("Device");
        model.addColumn("Status");

        for (Notification notification : notifications) {
            model.addRow(new String[] { notification.getDateTime(), notification.getDeviceName(), notification.deviceStatus()});
        }
        return model;
    }
}
