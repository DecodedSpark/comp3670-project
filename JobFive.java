import java.net.*;
import java.io.*;
import java.util.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.io.IOException;

public class JobFive {
   public static void spyNeighbors(String lan) throws IOException {
        System.out.println("NOTE:\tYou can only find the MAC Address of the localhost using Java, not those of remote hosts in the same network."
        + "\n\tThat means that JobSeeker.java cannot report the MAC addresses of other live hosts sharing its LAN, it can only report their IP addresses.\n");
        for (int i = 1; i < 255; i++) {
            String hostIp = lan + "." + i;//get ip address
            if (InetAddress.getByName(hostIp).isReachable(5000)) {
                System.out.println("IP Address " + hostIp + " is a live host the same LAN as JobSeeker.");
            }
        }
    }

    public static void main(String args[]) throws Exception {
        //get local IP and MAC addresses
        String seekerIp = (InetAddress.getLocalHost().getHostAddress()).trim();//JobSeeker IP address
        NetworkInterface seekerNet = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
        //convert to hexadecimal MAC address string
        byte[] netAddr = seekerNet.getHardwareAddress();
        String[] hex = new String[netAddr.length];
        for (int x = 0; x < netAddr.length; x++) {
            hex[x] = String.format("%02x", netAddr[x]);
        }
        String seekerMac = String.join("-", hex);
       
        System.out.println("All JobSeekers are on the same device, and therefore the same LAN, as the JobCreator.");
        System.out.println("The JobSeeker has IP Address " + seekerIp + " aand MAC Address " + seekerMac);
        //get subnet of seekerIp
        String subnet = seekerIp.substring(0, seekerIp.lastIndexOf("."));
        spyNeighbors(subnet);//spy on neighbors
    }
    
}







