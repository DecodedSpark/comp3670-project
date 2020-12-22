
import org.shortpasta.icmp2.IcmpPingRequest;
import org.shortpasta.icmp2.IcmpPingResponse;
import org.shortpasta.icmp2.IcmpPingUtil;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

//Class to carry out traceroute between job seeker and target node
public class TraceRouteUtility {

    //constructor
    TraceRouteUtility(InetAddress toAddress, PrintWriter pr) throws UnknownHostException {
        this.toAddress = toAddress;
        this.pr = pr;
    }

    final private InetAddress toAddress;
    final private PrintWriter pr;
    private int ttl = 1;
    private int fails = 0;
    private boolean destinationReached = false;
    private final int maxHops = 30;
    private String tracerouteReport = "Traceroute Report:";

    //get the number of hops between nodes using traceroute
    public int getNumberOfHopsBetweenNodes(){
        System.out.println("\nTraceroute Report:");

        while(!destinationReached && ttl < maxHops) {
            sendPing();
            ttl=ttl+1;
        }
        ttl=ttl-1;
        System.out.println("\nFinished doing traceroute with hops " + ttl);

        //Send to Job Creator:
       // pr.println(tracerouteReport);
       // pr.flush();
        // System.out.println("About to send results...");

        return ttl;
    }

    //sends ICMP packets to the target node to get the path from source to destination
    private void sendPing() {
        IcmpPingRequest icmpRequest = IcmpPingUtil.createIcmpPingRequest();
        icmpRequest.setHost(toAddress.getHostAddress());
        icmpRequest.setTtl(ttl);

        IcmpPingResponse response = IcmpPingUtil.executePingRequest(icmpRequest);
        if(response.getTimeoutFlag()) {
            fails=fails+1;
            String output = "Request timed out at" + ttl;
            System.out.println(output);
        }

        String currentResult = ttl  + "   " + response.getRtt() + " ms   " + response.getHost();
        System.out.println(currentResult);

        tracerouteReport = tracerouteReport + System.getProperty("line.separator") + currentResult;

        if(response.getSuccessFlag()) {
           // pr.flush();
            destinationReached = true;


        }
    }
}