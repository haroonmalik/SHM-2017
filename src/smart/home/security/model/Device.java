/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart.home.security.model;
import java.awt.Button;
import smart.home.security.model.Device;
import smart.home.security.model.Devices;

/**
 *
 * @author chana
 */
public class Device   {

    private String name;
    private String address;


    public Device(String name, String address) {
        this.name = name;
        this.address = address;
        
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
    
    public String toString() {
        return String.format("Name: %s, Address: %s", name, address);
    }
}
