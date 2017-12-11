/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart.home.security.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chana
 */
public class Notifications {
    private List<Notification> notifications;
    private static Notifications notificationsInstance;
    private boolean unreadNotifications;

    private Notifications() {
    }

    public static Notifications getInstance() {
        if (notificationsInstance == null) {
            notificationsInstance = new Notifications();
            notificationsInstance.notifications = new ArrayList();
        }
        return notificationsInstance;
    }
    
    public List<Notification> getNotifications() {
        return notifications;
    }
    
    public void addNotification(Device device, Boolean status) {        
        notifications.add(new Notification(device.getName(), status));
        unreadNotifications = true;
    }
    
    public void removeNotification(int index) {
        markNotificationsAsRead();
        notifications.remove(index);
    }
    
    public void removeNotifications() {
        markNotificationsAsRead();
        notifications.clear();
    }

    public boolean UnreadNotifications() {
        return unreadNotifications;
    }

    public void markNotificationsAsRead() {
        unreadNotifications = false;
    }    
}
