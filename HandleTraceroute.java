import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

//Class to handle traceroute methods
public class HandleTraceroute
{
    //Runs traceroute and gets back number of hops
    HandleTraceroute(PrintWriter pr, String jobIp) throws UnknownHostException {
        System.out.println("Handling case 5");
        TraceRouteUtility traceRouteUtility = new TraceRouteUtility(InetAddress.getByName(jobIp),pr);
        int numberOfHops = traceRouteUtility.getNumberOfHopsBetweenNodes();
        pr.println("Distance between target node and nearest Job Seeker(s): "+numberOfHops +" hops away.");

    }

}
