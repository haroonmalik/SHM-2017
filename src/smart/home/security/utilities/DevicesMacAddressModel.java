package smart.home.security.utilities;

import java.util.Set;
import javax.swing.table.DefaultTableModel;

/**
 * The default table model for a list of device MAC address.
 * @author archana
 */
public class DevicesMacAddressModel {
    
    /**
     * The default table model for the list of MAC address.
     * @param devicesMacAddress - The set of device MAC address.
     * @return the MAC address DefaultTableModel for the JTable. 
     */
    public static DefaultTableModel defaultTableModel(Set<String> devicesMacAddress) {
        // Create the default table model with mac-address column.
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mac Address");

        // Add each mac-address row.
        for (String deviceMacAddress : devicesMacAddress ) {
            // Add the device MAC address row.
            model.addRow(new String[] { deviceMacAddress });
        }
        return model;
    }
}
