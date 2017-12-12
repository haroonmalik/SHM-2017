/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart.home.security.utilities;

import java.util.Set;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author chana
 */
public class DevicesMacAddressModel {
    public static DefaultTableModel defaultTableModel(Set<String> devicesMacAddress) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mac Address");

        for (String deviceMacAddress : devicesMacAddress ) {
            model.addRow(new String[] { deviceMacAddress });
        }
        return model;
    }
}
