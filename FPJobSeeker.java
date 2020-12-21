import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.io.*;
import java.util.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.io.IOException;
//job seeker waits for jobSeeker to connect, accepts job request, does job, reports back
public class FPJobSeeker { //server

    public static void spyNeighbors(String subnet) throws IOException {
        int timeout = 1000;
        System.out.println("NOTE:\tYou can only find the MAC Address of the localhost using Java, not those of remote hosts in the same network."
        + "\n\tThat means that JobSeeker.java cannot report the MAC addresses of other live hosts sharing its LAN, it can only report their IP addresses.\n");
        for (int i = 1; i < 255; i++) {
            String host = subnet + "." + i;//get ip address
            
            if (InetAddress.getByName(host).isReachable(timeout)) {
                //code to find MAC address of given host IP
                /*InetAddress otherHost = InetAddress.getByName(host);
                
                NetworkInterface otherNet = NetworkInterface.getByInetAddress(otherHost);
                                //get hexadecimal MAC address from otherNet
                byte[] onetAddr = otherNet.getHardwareAddress();
                String[] ohex = new String[onetAddr.length];
                for (int j = 0; j < onetAddr.length; j++) {
                    ohex[j] = String.format("%02X", onetAddr[i]);
                }
                String otherMac = String.join("-", ohex);*/
                System.out.println("Host IP " + host + " is on the same LAN as JobSeeker.");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String jobString = "0";
        String jobIp;
        String jobPort;
        boolean badJob = false;
        boolean jobDone = false;
        //code to incorporate pcap4j packet building
        /*IcmpV4EchoPacket.Builder echoBuilder = new IcmpV4EchoPacket.Builder();
        echoBuilder
                .identifier((short) 1);

        IcmpV4CommonPacket.Builder icmpV4Builder = new IcmpV4CommonPacket.Builder();
        icmpV4Builder
                .type(IcmpV4Type.ECHO)
                .code(IcmpV4Code.NO_CODE)
                .payloadBuilder(echoBuilder)
                .correctChecksumAtBuild(true);
        */        
        //code for connection
        Socket s = new Socket();
        int[] servers ={4999, 4998, 4997} ;//allows 3 different sockets for 3 different JobSeekers
        int i = 0;
        
        //loop for multiple JobSeekers
        while (i < 3) {
            try {
                //start connection
                ServerSocket ss = new ServerSocket(servers[i]);
                System.out.println("Socket opened.\n");
                s = ss.accept();
                //server can only connect to one client at a time
                System.out.println("JobCreator connected. Waiting for Job...");
                InputStreamReader in = new InputStreamReader(s.getInputStream());
                BufferedReader bf = new BufferedReader(in);

                
                //get the job type from the creator
                jobString = bf.readLine();
                System.out.println("Job Type from JobCreator: " + jobString);
                //get IP address from JobCreator
                jobIp = bf.readLine();
                System.out.println("IP: " + jobIp);
                //get port number from JobCreator
                if (!jobString.equalsIgnoreCase("1")){
                    jobPort = bf.readLine();
                    System.out.println("Port: " + jobPort);
                }
                else {
                    jobPort = "Not Applicable";
                }

                try (PrintWriter pr = new PrintWriter(s.getOutputStream())) {
                    pr.println("RECEIVED. Job Type: " + jobString + "\n" + "IP: " + jobIp + ", Port: " + jobPort);
                    pr.flush();
                    //determine if job type is valid
                    switch (jobString) {
                        case "1":
                        case "2":
                        case "3":
                        case "4":
                        case "5":
                            break;
                        default:
                            badJob = true;
                            pr.println("Error. Invalid job type.");
                            pr.flush();
                            break;
                    }
                    if (badJob) {
                        System.out.println("Job incomplete. Error.");
                        pr.println("Error. Unknown job type.");
                        pr.flush();
                        pr.println(0);
                        pr.flush();
                    } else {
                        //prep pcap4j for packet building
                        /*IpV4Packet.Builder ipv4Builder = new IpV4Packet.Builder();
                        try {
                            ipv4Builder
                                    .version(IpVersion.IPV4)
                                    .tos(IpV4Rfc791Tos.newInstance((byte) 0))
                                    .ttl((byte) 100)
                                    .protocol(IpNumber.ICMPV4)
                                    //servers IP and target IP
                                    .srcAddr((Inet4Address) InetAddress.getLocalHost())
                                    .dstAddr((Inet4Address) InetAddress.getByName(targetIP))
                                    .payloadBuilder(icmpV4Builder)
                                    .correctChecksumAtBuild(true)
                                    .correctLengthAtBuild(true);
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        }*/
                        //do the job based on job type
                        //assign part of create-assign-execute-report process for each job
                        switch (jobString) {
                            case "1":
                                //assign 1st job to JobSeeker, Q1 Job 1
                                if (isOnline(jobIp)) {
                                    System.out.println("IP online");
                                    pr.println("This IP Address/hostname is online.");
                                    pr.flush();
                                } else {
                                    System.out.println("IP offline");
                                    pr.println("This IP Address/hostname is offline.");
                                    pr.flush();
                                }
                                break;
                            case "2":
                                //assign 2nd job to JobSeeker, Q1 Job 2
                                jobTwo(jobIp, jobPort, pr);
                                break;
                            case "3":
                                //assign 3rd job to JobSeeker, Q2 Job 1
                                //jobThree();
                                break;
                            case "4":
                                //assign 4th job to JobSeeker, Q2 Job 2
                                //jobFour();
                                break;
                            case "5":
                                InetAddress seekerHost = InetAddress.getLocalHost();
                                String seekerIp = (seekerHost.getHostAddress()).trim();//local IP address
                                NetworkInterface seekerNet = NetworkInterface.getByInetAddress(seekerHost);
                                //get hexadecimal MAC address from seekerNet
                                byte[] netAddr = seekerNet.getHardwareAddress();
                                String[] hex = new String[netAddr.length];
                                for (int i = 0; i < netAddr.length; i++) {
                                    hex[i] = String.format("%02X", netAddr[i]);
                                }
                                String seekerMac = String.join("-", hex);
    
                                System.out.println("JobSeeker is on Host IP " + seekerIp + " with MAC Address " + seekerMac);
                                //get subnet of seekerIp
                                String subnet = seekerIp.substring(0, seekerIp.lastIndexOf("."));
                                spyNeighbors(subnet);
                                break;
                            default:
                                pr.println("Error. Job not assigned.");
                                pr.flush();
                                break;
                        }

                        System.out.println("Job finished.");
                        pr.println("Job Complete.");
                        pr.flush();
                        jobDone = true;
                        pr.println(1);
                        pr.flush();
                    }
                }
                //end socket for this job.
                s.close();
            } catch (IOException ioe) {
                System.out.println("Error. JobCreator disconnected.");
            }
            //if job type is 1 or 2, there is only 1 JobSeeker, so exit while loop
            if (jobString.equalsIgnoreCase("1") || jobString.equalsIgnoreCase("2") || jobString.equalsIgnoreCase("5") ) {
                i = 3;
            }
            //otherwise go to next JobSeeker for job types 3 and 4
            else {
                i++;
            }
        }
    }

    //job 1 = check if given IP address is online or not
    //execute part of create-assign-execute-report process for A3P2Q1 Job 1
    public static boolean isOnline(String jobIp) throws IOException {
        return InetAddress.getByName(jobIp).isReachable(5000);
    }

    //job 2 = check status of given port number at given IP address
    //execute part of create-assign-execute-report process for A3P2Q1 Job 2
    public static String portStatus(String jobIp, String jobPort) {
        int port = Integer.parseInt(jobPort);
        Socket sckt = null;//create socket variable
        String status = "unknown";
        try {
            System.out.println("Inside portStatus try loop.");
            //connection to port at host established, new socket
            sckt = new Socket(jobIp, port);
            System.out.println(" portStatus socket created.");
        } catch (IOException e) {
            if (e.getMessage().equals("Connection refused")) {
                //returns port status as closed
                status = "closed";
            }
            if (e instanceof SocketTimeoutException) {
                //connection timed out, port blocked by firewall
                //returns port status as filtered
                status = "filtered";
            }
        } finally {
            //checks if port is not filtered
            if (sckt != null) {
                //returns port status as open
                if (sckt.isConnected()) {
                    status = "open";
                }
                try {
                    //closes connection
                    sckt.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return status;
    }

    public static void jobTwo (String jobIp, String jobPort, PrintWriter pr) {
        //detect status of port jobPort at IP address jobIP
        String pstat = portStatus(jobIp, jobPort);
        //report part of create-assign-execute-report process for A3P2Q1 Job 2
        if (pstat.equalsIgnoreCase("open")) {
            System.out.println("Port Open");
            pr.println("Port " + jobPort + " at IP address/hostname " + jobIp + " is open.");
            pr.flush();
        } else if (pstat.equalsIgnoreCase("closed")) {
            System.out.println("Port Closed");
            pr.println("Port " + jobPort + " at IP address/hostname " + jobIp + " is closed.");
            pr.flush();
        } else if (pstat.equalsIgnoreCase("filtered")) {
            System.out.println("Port Filtered");
            pr.println("Port " + jobPort + " at IP address/hostname " + jobIp + " is filtered.");
            pr.flush();
        } else {
            System.out.println("Status Unknown");
            pr.println("The status of port " + jobPort + " at IP address/hostname " + jobIp + " is unknown.");
            pr.flush();
        }
    }

    public static void jobThree () {
        //execute part of create-assign-execute-report process for A3P2Q2 Job 1
        /*InetAddress localHost = InetAddress.getLocalHost();
        Packet p = ipv4Builder.build();
        //create handle
        PcapNetworkInterface nif = Pcaps.getDevByAddress(localHost);
        int snapLen = 65536;
        PromiscuousMode mode = PromiscuousMode.PROMISCUOUS;
        int timeout = 10;
        PcapHandle handle = nif.openLive(snapLen, mode, timeout);
        try {
            handle.sendPacket(p);
        } catch (PcapNativeException | NotOpenException e) {
            e.printStackTrace();
        }
        //if successful, continuously send the packet for ICMP attack
        while (true) {
            try {
                handle.sendPacket(p);
            } catch (PcapNativeException | NotOpenException e) {
                e.printStackTrace();
            }
        }*/
    }

    public static void jobFour() {
        //execute part of create-assign-execute-report process for A3P2Q2 Job 2
                                /*InetAddress localHost = InetAddress.getLocalHost();
                                //create tcp packet
                                Packet tcpp;
                                tcpp.getBuilder().get(TcpPacket.Builder.class).srcAddr(tcpp.get(IpV4Packet.class).getHeader().getSrcAddr());
                                tcpp.getBuilder().get(TcpPacket.Builder.class).dstAddr(tcpp.get(IpV4Packet.class).getHeader().getDstAddr());
                                tcpp.getBuilder().get(TcpPacket.Builder.class).correctChecksumAtBuild(true);
                                tcpp.getBuilder().build();
                                //create handle
                                PcapNetworkInterface nif = Pcaps.getDevByAddress(localHost);
                                int snapLen = 65530;
                                PromiscuousMode mode = PromiscuousMode.PROMISCUOUS;
                                int timeout = 20;
                                PcapHandle handle = nif.openLive(snapLen, mode, timeout);
                                try {
                                    handle.sendPacket(tcpp);
                                } catch (PcapNativeException | NotOpenException e) {
                                    e.printStackTrace();
                                }
                                //if successful, continuously send the packet for TCP attack
                                while (true) {
                                    try {
                                        handle.sendPacket(tcpp);
                                    } catch (PcapNativeException | NotOpenException e) {
                                        e.printStackTrace();
                                    }
                                }*/
    }
}