import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class FPJobCreator {

    public static void main(String[] args) throws IOException {
        String jobType = "0";// initialize
        String ip;
        int port;
        boolean isJobDone = false;
        
        int[] servers = {4999, 4998, 4997};//allows 3 different sockets for 3 different JobSeekers
        int i = 0;
        //loop for multiple JobSeekers
        while (i < 3) {
            try {
                System.out.println("Inside while loop.\n");
                
                Socket s = new Socket("localhost", servers[i]);
                System.out.println("Socket connected.");
                PrintWriter pr = new PrintWriter(s.getOutputStream());
                Scanner sc = new Scanner(System.in);
                Scanner sc2 = new Scanner(System.in);
                Scanner sc3 = new Scanner(System.in);
                //get job from user. one of the 4 types
                System.out.println("=== MENU === \n"
                    + "1 = Detect if an IP address/host name is online \n"
                    + "2 = Detect the status of a port at an IP address \n"
                    + "3 = Execute an ICMP flood attack against a port at an IP address \n"
                    + "\t\t Warning: Doing this against a valid IP address may count as an illegal DDoS attack. Please use dummy IP 0.0.0.0. \n"
                    + "4 = Execute a TCP flood attack against a port at an IP address \n"
                    + "\t\t Warning: Doing this against a valid IP address may count as an illegal DDoS attack. Please use dummy IP 0.0.0.0 \n"
                    + "5 = Traceroute between JobSeeker and another node in the network \n"
                    + "6 = Find the nearest JobSeeker(s) to the target node \n"
                    + "7 = Report IP and MAC addresses of all live hosts connected to the same LAN as you \n"
                    + "8 = Find if JobSeeker is on the same LAN as JobCreator \n");
                System.out.print("Job Type: ");
                jobType = sc.nextLine();
                pr.println(jobType);
                pr.flush();
                //get ip address from user
                System.out.println("Enter an IP Address/host name:");
                if (!jobType.equalsIgnoreCase("3") && !jobType.equalsIgnoreCase("7")) {
                    ip = sc2.nextLine();
                }
                else {
                    ip = (InetAddress.getLocalHost().getHostAddress()).trim();//local IP address
                    System.out.println(ip);
                }
                pr.println(ip);
                pr.flush();
                //If job type is neither 1 nor 5, get port number from user
                if (!jobType.equalsIgnoreCase("1") && !jobType.equalsIgnoreCase("3") && !jobType.equalsIgnoreCase("7")) {
                    System.out.println("Enter a port number:");
                    port = sc3.nextInt();
                    pr.println(port);
                    pr.flush();
                }
                //get report from JobSeeker
                InputStreamReader in = new InputStreamReader(s.getInputStream());
                BufferedReader bf = new BufferedReader(in);

                String str = bf.readLine();
                System.out.println("JobSeeker: " + str + "\n");

                str = bf.readLine();
                System.out.println("JobSeeker: " + str);

                str = bf.readLine();
                System.out.println(str);

                int jobDoneNum = bf.read();
                if (jobDoneNum == 1) {
                    isJobDone = true;
                }
            } catch (IOException e) {
                System.out.println("Connection failed. Abort");
            }
            //if job type is neither 3 or 4, there is only 1 JobSeeker, so exit while loop
            if (!jobType.equalsIgnoreCase("4") && !jobType.equalsIgnoreCase("7")) {
                i = 3;
            }
            //otherwise go to next JobSeeker for job types 3 and 4
            else {
                i++;
            }
        }
    }
    
}