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
        String port;
        boolean isJobDone = false;
        Socket s = new Socket();
        //int[] servers = {4999, 4998, 4997};//allows 3 different sockets for 3 different JobSeekers
        int i = 0;
        //loop for multiple JobSeekers
        while (i < 3) {
            try {
                System.out.println("Inside while loop.\n");
                ServerSocket ss = new ServerSocket(4999);
                System.out.println("Socket opened.\n");
                s = ss.accept();
                System.out.println("Socket connected.\n");
                PrintWriter pr = new PrintWriter(s.getOutputStream());
                Scanner sc = new Scanner(System.in);
                Scanner sc2 = new Scanner(System.in);
                Scanner sc3 = new Scanner(System.in);
                //get job from user. one of the 4 types
                System.out.println("Enter job type: \n"
                    + "1 = Detect if an IP address/host name is online \n"
                    + "2 = Detect the status of a port at an IP address \n"
                    + "3 = Execute an ICMP flood attack against a port at an IP address \n"
                    + "\t\t Warning: Doing this against a valid IP address may count as an illegal DDoS attack. Please use dummy IP 0.0.0.0. \n"
                    + "4 = Execute a TCP flood attack against a port at an IP address \n"
                    + "\t\t Warning: Doing this against a valid IP address may count as an illegal DDoS attack. Please use dummy IP 0.0.0.0 \n");
                jobType = sc.nextLine();
                pr.println(jobType);
                pr.flush();
                //get ip address from user
                System.out.println("Enter an IP Address/host name:");
                ip = sc2.nextLine();
                pr.println(ip);
                pr.flush();
                //If job type is not Job 1, get port number from user
                if (!jobType.equalsIgnoreCase("1")) {
                    System.out.println("Enter a port number:");
                    port = sc3.nextLine();
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
            //if job type is 1 or 2, there is only 1 JobSeeker, so exit while loop
            if (jobType.equalsIgnoreCase("1") || jobType.equalsIgnoreCase("2")) {
                i = 3;
            }
            //otherwise go to next JobSeeker for job types 3 and 4
            else {
                i++;
            }
        }
    }
    
}