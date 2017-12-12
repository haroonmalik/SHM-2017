/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart.home.security.view;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import smart.home.security.model.Device;
import smart.home.security.model.Devices;
import smart.home.security.model.Notification;
import smart.home.security.model.Notifications;
import smart.home.security.utilities.DevicesMacAddressModel;
import smart.home.security.utilities.NotificationsModel;

/**
 *
 * @author chana
 */
public class AvailableDevicesPanel extends javax.swing.JPanel {
    Map<String, String> deviceDetails;

    private void scanDevices() {
        deviceDetails = new HashMap();
        try {
            Scanner s = new Scanner(Runtime.getRuntime().exec("arp -a").getInputStream()).useDelimiter("\n");
            while (s.hasNext()) {
                String token = s.next();
                String replace = token.substring(0, token.indexOf(" on")).replace("? (", "").replace(") at ", "-");
                String[] deviceInfo = replace.split("-");
                deviceDetails.put(deviceInfo[1], deviceInfo[0]);
            }

        } catch (IOException ex) {
            Logger.getLogger(AvailableDevicesPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void filterDevices() {
        for (Device device : Devices.getInstance().getDevices()) {
            deviceDetails.remove(device.getMacAddress());
        }
    }

    /**
     * Creates new form AvailableDevicePanel
     */
    public AvailableDevicesPanel() {
        initComponents();        
        scanDevices();
        filterDevices();
        DefaultTableModel model = DevicesMacAddressModel.defaultTableModel(deviceDetails.keySet());
        availableMacAddressTable.setModel(model);

//        List<String> ips = findAvailableDevices();
//        
//        for (String ip : ips) {
//            System.out.print("IP: " + ip);
//            try {
//                NetworkInterface network = NetworkInterface.getByInetAddress(InetAddress.getByName(ip));
//                if (network != null) {
//                    byte[] mac = network.getHardwareAddress();
//                    
//
//                    StringBuilder sb = new StringBuilder();
//                    for (int i = 0; i < mac.length; i++) {        
//                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
//                    }
//                    System.out.println(" - MAC: " + sb.toString());
//                }                
//                else {
//                    System.out.println();
//                }
//            } catch (SocketException ex) {
//                Logger.getLogger(AvailableDevicesPanel.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (UnknownHostException ex) {
//                Logger.getLogger(AvailableDevicesPanel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }        
    }
//    
//    private List<String> findAvailableDevices() {
//        List<String> ips = new ArrayList();
//        try {
//            String subnet = getSubnet();
//            ExecutorService executorService = Executors.newFixedThreadPool(100);
//            
//            List<Callable<String>> callableTasks = new ArrayList<>();
//            for (int i = 1; i < 255; i++) {
//                String host = subnet + i;
//                Callable<String> callableTask = () -> {
//                    return InetAddress.getByName(host).isReachable(1000) ? host : null;
//                };
//                callableTasks.add(callableTask);
//            }
//            List<Future<String>> futures = executorService.invokeAll(callableTasks);
//            
//            for (Future future : futures) {
//                String ip = (String) future.get();
//                if (ip != null) {
//                    ips.add(ip);
//                }
//            }
//            executorService.shutdown();
//        } catch (InterruptedException | ExecutionException ex) {
//            Logger.getLogger(AvailableDevicesPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return ips;
//    }
//    
//    private String getSubnet() {
//        String subnet = null;
//        try {
//            InetAddress ipAddr = InetAddress.getLocalHost();
//            String ip = ipAddr.getHostAddress();
//            subnet = ip.substring(0, ip.lastIndexOf('.') + 1);
//        } catch (UnknownHostException ex) {
//            Logger.getLogger(AvailableDevicesPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return subnet;
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private SmartHomeSecurityFrame getSmartHomeSecurityFrame() {
        return (SmartHomeSecurityFrame) SwingUtilities.getWindowAncestor(this);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        availableMacAddressTable = new javax.swing.JTable();
        nextButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Available Devices"));
        setPreferredSize(new java.awt.Dimension(397, 311));

        availableMacAddressTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Device Mac Address"
            }
        ));
        jScrollPane2.setViewportView(availableMacAddressTable);

        nextButton.setText("Next");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextButton)
                    .addComponent(cancelButton))
                .addContainerGap(28, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        int selectedIndex = availableMacAddressTable.getSelectedRow();
        if (selectedIndex >= 0) {
            String mac = (String) availableMacAddressTable.getValueAt(selectedIndex, 0);
            String ip = deviceDetails.get(mac);
            getSmartHomeSecurityFrame().replaceFramePanel(new AddDevicePanel(mac, ip));            
        }        
    }//GEN-LAST:event_nextButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        getSmartHomeSecurityFrame().replaceFramePanel(new MainPanel());
    }//GEN-LAST:event_cancelButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable availableMacAddressTable;
    private javax.swing.JButton cancelButton;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton nextButton;
    // End of variables declaration//GEN-END:variables

}
