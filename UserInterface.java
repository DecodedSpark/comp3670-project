import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) throws Exception {
        int choice;
        boolean exitFlag = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Course Project");
        System.out.println("--------------------");
        do {
            System.out.println("\nWould you like to traceroute or");
            System.out.println("spy on your neighbours?");
            System.out.println("Enter any key to continue");
            System.out.println("Enter -1 to exit");
            try {
                choice = sc.nextInt();
            }catch(Exception e){
                choice = 0;
            }
            if (choice == -1){
                System.out.println("Exiting...");
                exitFlag = true;
            }else{
                runFPJobCreator();
            }

        }while(!exitFlag);
    }
    private static void runFPJobCreator () throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nMake sure the server is online.");
        System.out.println("Run FPJobSeeker before continuing");
        System.out.println("Enter any key when ready");
        sc.nextLine();
        try {
            FPJobCreator.main(null);
        }catch(Exception e){
            System.out.println("could not execute program");
        }
    }
}

