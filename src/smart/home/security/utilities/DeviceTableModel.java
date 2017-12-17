package smart.home.security.utilities;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import smart.home.security.model.Device;

/**
 * The default table model for a list of devices.
 * @author archana
 */
public class DeviceTableModel {
    
    /**
     * The default table model for the list of devices.
     * @param devices - The list of devices.
     * @return the device DefaultTableModel for the JTable.
     */
    public static DefaultTableModel defaultTableModel(List<Device> devices) {        
        // Create the default table model with name and address column.
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Address");
        
        // Add each device row to the name and address columns.
        for (Device device : devices) {
            // Add the device name and address row.
            model.addRow(new String[] {device.getName(), device.getMacAddress()});
        }
        
        return model;
    }    
}
