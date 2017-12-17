package smart.home.security.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The POJO to hold the notification history.
 * @author archana
 */
public class Notifications {
    
    /**
     * The collection of notifications.
     */
    private List<Notification> notifications;
    
    /**
     * The singleton notifications instance.
     */
    private static Notifications notificationsInstance;
    
    /**
     * Determine if there are unread notifications.
     */
    private boolean unreadNotifications;

    /**
     * Private constructor to allow singleton instance.
     */
    private Notifications() {
    }

    /**
     * The notifications instance.
     * @return the Notifications instance.
     */
    public static Notifications getInstance() {
        // Create the Instance if the notifications is null.
        if (notificationsInstance == null) {
            // Create the notifications instance.
            notificationsInstance = new Notifications();
            // Create the notifications list within the instance.
            notificationsInstance.notifications = new ArrayList();
        }
        return notificationsInstance;
    }
    
    /**
     * Get the collection of notifications.
     * @return the list of notifications.
     */
    public List<Notification> getNotifications() {
        return notifications;
    }
    
    /**
     * Add a notification with the given details.
     * @param device - The device that generated the notification.
     * @param status - The status of the device.
     */
    public void addNotification(Device device, Boolean status) {        
        // Add the notification given the device naem and status.
        notifications.add(new Notification(device.getName(), status));
        // Mark that the unread notifications to true.
        unreadNotifications = true;
    }
    
    /**
     * Remove the notification at a given index.
     * @param index - The int index at which the notification should be removed.
     */
    public void removeNotification(int index) {
        // Mark the notifications as read if one is removed.
        markNotificationsAsRead();
        // Remove the notification at the given index.
        notifications.remove(index);
    }
    
    /**
     * Remove all the notifications.
     */
    public void removeNotifications() {
        // Mark the notifications as read if all are removed.
        markNotificationsAsRead();
        // Remove all the notifications from the list.
        notifications.clear();
    }

    /**
     * Determines if there are unread notifications.
     * @return true if there are unread notifications otherwise false.
     */
    public boolean UnreadNotifications() {
        return unreadNotifications;
    }

    /**
     * Mark that all the notifications have been read.
     */
    public void markNotificationsAsRead() {
        unreadNotifications = false;
    }    
}
