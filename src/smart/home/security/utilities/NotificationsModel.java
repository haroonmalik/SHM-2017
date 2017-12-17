package smart.home.security.utilities;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import smart.home.security.model.Notification;

/**
 * The default table model for a list of notification.
 * @author archana
 */
public class NotificationsModel {

    /**
     * The default table model for the list of notifications.
     * @param notifications - The list of notifications.
     * @return the notification DefaultTableModel for the JTable. 
     */
    public static DefaultTableModel defaultTableModel(List<Notification> notifications) {
        // Create the default table model with date/time and device name and status column.
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Date/Time");
        model.addColumn("Device");
        model.addColumn("Status");

        // Add each notification row to the date/time and device name and status column.
        for (Notification notification : notifications) {
            // Add the notification date time, device name and status row.
            model.addRow(new String[] { 
                notification.getDateTime(), 
                notification.getDeviceName(), 
                notification.deviceStatus()
            });
        }
        return model;
    }
}
