import java.util.Scanner;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.net.*;
import java.io.*;

public class UserInterface {
    public static void main(String[] args) throws Exception {
        int choice;
        boolean exitFlag = false;
        Scanner sc = new Scanner(System.in);
        //prep to store in file
        PrintStream output = new PrintStream(new FileOutputStream("outputInterface.txt"));
        System.out.println("Comp-3670 Final Project\n" + "--------------------");
        output.println("Comp-3670 Final Project\n" + "--------------------");
        do {
            System.out.print("\nWould you like to traceroute or\n"
                    + "spy on your neighbours?\n"
                    + "Enter -1 to exit\n"
                    + "Enter 1 to test Job 1: Check if JobSeeker can correctly report an IP address as online\n"
                    + "Enter 2 to test Job 1: Check if JobSeeker can correctly report an IP address as offline\n"
                    + "Enter 3 to test Job 2: Detect the status of a given port at a given IP address\n"
                    + "Enter 4 to test Job 3: Find all live hosts on the same LAN as JobSeeker\n"
                    + "Enter 5 to test Job 4: Execute a TCP flood attack against a port at an IP address\n"
                    + "Enter 6 to test Job 5: Traceroute between JobSeeker and another node in the network\n"
                    + "Enter 7 to test Job 6: Execute an ICMP flood attack against a port at an IP address\n"
                    + "Enter any other key to continue normally\n"
                    + "Choice: ");
            output.print("\nWould you like to traceroute or\n"
                    + "spy on your neighbours?\n"
                    + "Enter -1 to exit\n"
                    + "Enter 1 to test Job 1: Check if JobSeeker can correctly report an IP address as online\n"
                    + "Enter 2 to test Job 1: Check if JobSeeker can correctly report an IP address as offline\n"
                    + "Enter 3 to test Job 2: Detect the status of a given port at a given IP address\n"
                    + "Enter 4 to test Job 3: Find all live hosts on the same LAN as JobSeeker\n"
                    + "Enter 5 to test Job 4: Execute a TCP flood attack against a port at an IP address\n"
                    + "Enter 6 to test Job 5: Traceroute between JobSeeker and another node in the network\n"
                    + "Enter 7 to test Job 6: Execute an ICMP flood attack against a port at an IP address\n"
                    + "Enter any other key to continue normally\n"
                    + "Choice: ");
            try {
                choice = sc.nextInt();
                output.println(choice);
            }catch(Exception e){
                choice = 0;
            }
            if (choice == -1){
                System.out.println("Exiting...");
                output.println("Exiting...");
                exitFlag = true;
            } else if (choice == 1){
                testJob1Online(output);
                exitFlag = true;
            } else if (choice == 2){
                testJob1Offline(output);
                exitFlag = true;
            } else if (choice == 3){
                testJob2(output);
                exitFlag = true;
            } else if (choice == 4){
                testJob3(output);
                exitFlag = true;
            } else if (choice == 5){
                testJob4(output);
                exitFlag = true;
            } else if (choice == 6){
                testJob5(output);
                exitFlag = true;
            } else if (choice == 7){
                testJob6(output);
                exitFlag = true;
            } else {
                runFPJobCreator(output);
                exitFlag = true;
            }
            //close file
            output.close();
        }while(!exitFlag);
    }
    private static void testJob1Online (PrintStream output) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nMake sure the server is online.\n"
                + "Run FPJobSeeker before continuing\n"
                + "Enter any key when ready");
        output.println("\nMake sure the server is online.\n"
                + "Run FPJobSeeker before continuing\n"
                + "Enter any key when ready");
        sc.nextLine();
        try {
            Job1OnlineTestCase.main(null);
        }catch(Exception e){
            System.out.println("could not execute program");
            output.println("could not execute program");
        }
    }
    private static void testJob1Offline (PrintStream output) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nMake sure the server is online.\n"
                + "Run FPJobSeeker before continuing\n"
                + "Enter any key when ready");
        output.println("\nMake sure the server is online.\n"
                + "Run FPJobSeeker before continuing\n"
                + "Enter any key when ready");
        sc.nextLine();
        try {
            Job1OfflineTestCase.main(null);
        }catch(Exception e){
            System.out.println("could not execute program");
            output.println("could not execute program");
        }
    }
    private static void testJob2 (PrintStream output) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nMake sure the server is online.\n"
                + "Run FPJobSeeker before continuing\n"
                + "Enter any key when ready");
        output.println("\nMake sure the server is online.\n"
                + "Run FPJobSeeker before continuing\n"
                + "Enter any key when ready");
        sc.nextLine();
        try {
            Job2TestCase.main(null);
        }catch(Exception e){
            System.out.println("could not execute program");
            output.println("could not execute program");
        }
    }
    private static void testJob3 (PrintStream output) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nMake sure the server is online.\n"
                + "Run FPJobSeeker before continuing\n"
                + "Enter any key when ready");
        output.println("\nMake sure the server is online.\n"
                + "Run FPJobSeeker before continuing\n"
                + "Enter any key when ready");
        sc.nextLine();
        try {
            Job3TestCase.main(null);
        }catch(Exception e){
            System.out.println("could not execute program");
            output.println("could not execute program");
        }
    }
    private static void testJob4 (PrintStream output) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nMake sure the server is online.\n"
                + "Run FPJobSeeker before continuing\n"
                + "Enter any key when ready");
        output.println("\nMake sure the server is online.\n"
                + "Run FPJobSeeker before continuing\n"
                + "Enter any key when ready");
        sc.nextLine();
        try {
            Job4TestCase.main(null);
        }catch(Exception e){
            System.out.println("could not execute program");
            output.println("could not execute program");
        }
    }
    private static void testJob5 (PrintStream output) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nMake sure the server is online.\n"
                + "Run FPJobSeeker before continuing\n"
                + "Enter any key when ready");
        output.println("\nMake sure the server is online.\n"
                + "Run FPJobSeeker before continuing\n"
                + "Enter any key when ready");
        sc.nextLine();
        try {
            Job5TestCase.main(null);
        }catch(Exception e){
            System.out.println("could not execute program");
            output.println("could not execute program");
        }
    }
    private static void testJob6 (PrintStream output) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nMake sure the server is online.\n"
                + "Run FPJobSeeker before continuing\n"
                + "Enter any key when ready");
        output.println("\nMake sure the server is online.\n"
                + "Run FPJobSeeker before continuing\n"
                + "Enter any key when ready");
        sc.nextLine();
        try {
            Job6TestCase.main(null);
        }catch(Exception e){
            System.out.println("could not execute program");
            output.println("could not execute program");
        }
    }
    private static void runFPJobCreator (PrintStream output) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nMake sure the server is online.\n"
                + "Run FPJobSeeker before continuing\n"
                + "Enter any key when ready");
        output.println("\nMake sure the server is online.\n"
                + "Run FPJobSeeker before continuing\n"
                + "Enter any key when ready");
        sc.nextLine();
        try {
            FPJobCreator.main(null);
        }catch(Exception e){
            System.out.println("could not execute program");
            output.println("could not execute program");
        }
    }
}

