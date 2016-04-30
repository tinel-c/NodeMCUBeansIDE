/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.yourdimensions.rs232.events;

/**
 * RS232 data has been received.
 * @author hendriksit
 */
public class DataReceivedRS232Event implements IRS232Events {
    private byte[] data;

    public DataReceivedRS232Event(byte[] data) {
        this.data = data;
    }

    /**
     * The RS232 data
     * @return 
     */
    public byte[] getData(){
       return data; 
    }
}
