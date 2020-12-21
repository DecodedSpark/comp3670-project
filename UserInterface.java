import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) throws Exception {
        int choice;
        boolean choiceFlag = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Course Project");
        System.out.println("--------------------");
        do {
            System.out.println("Enter 1 to traceroute.");
            System.out.println("Enter 2 to spy on neighbours");
            System.out.println("Enter -1 to exit");
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    //traceroute stuff
                    choiceFlag = true;
                    break;
                case 2:
                    //spy stuff
                    SpyNeighbours();
                    choiceFlag = true;
                    break;
                case -1:
                    System.out.println("Exiting.");
                    choiceFlag = true;
                    break;
                default:
                    System.out.println("Invalid Input. Try again.");
                    break;
            }

        }while(!choiceFlag);
    }
    private static void SpyNeighbours () throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Make sure the server is online.");
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
