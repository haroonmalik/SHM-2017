/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart.home.security.utilities;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import smart.home.security.model.Device;

/**
 *
 * @author chana
 */
public class DeviceTableModel {
    public static DefaultTableModel defaultTableModel(List<Device> devices) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Address");
        
        for (Device device : devices) {
            model.addRow(new String[] {device.getName(), device.getAddress()});
        }
        
        return model;
    }    
}
