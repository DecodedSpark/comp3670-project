import java.net.*;
import java.io.*;
import java.util.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.io.IOException;

public class SpyNeighbors {
   public static void SpyNeighbors(String seekerIp) throws IOException {
        //get local IP and MAC addresses
        NetworkInterface seekerNet = NetworkInterface.getByInetAddress(InetAddress.getByName(seekerIp));
        //convert to hexadecimal MAC address string
        byte[] netAddr = seekerNet.getHardwareAddress();
        String[] hex = new String[netAddr.length];
        for (int x = 0; x < netAddr.length; x++) {
            hex[x] = String.format("%02x", netAddr[x]);
        }
        String seekerMac = String.join("-", hex);
       
        System.out.println("\nAll JobSeekers are on the same device, and therefore the same LAN, as the JobCreator.");
        System.out.println("\nThe JobSeeker has IP Address " + seekerIp + " and MAC Address " + seekerMac);
        //get subnet of seekerIp
        String lan = seekerIp.substring(0, seekerIp.lastIndexOf("."));

        System.out.println("\nNOTE:\tYou can only find the MAC Address of the localhost using Java, not those of remote hosts in the same network."
        + "\n\tThat means that JobSeeker.java cannot report the MAC addresses of other live hosts sharing its LAN, it can only report their IP addresses.\n");
        for (int i = 1; i < 255; i++) {
            String hostIp = lan + "." + i;//get ip address
            if (InetAddress.getByName(hostIp).isReachable(5000)) {
                System.out.println("IP Address " + hostIp + " is a live host on the same LAN as JobSeeker.");
            }
        }
    }

    public static void main (String[] args) throws Exception {
        String testIp = InetAddress.getLocalHost().getHostAddress();
        SpyNeighbors(testIp);//spy on neighbors
    }
    
}







