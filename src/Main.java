import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        boolean run = true;

        while (run) {
            System.out.println("To show inventory contents press 1");
            System.out.println("To go on an adventure press 2");
            System.out.println("To sell an item press 3");
            System.out.println("To sort inventory press 4");
            System.out.println("To search for an item press 5");
            System.out.println("To exit program press 6");

            switch (input.nextInt()) {
                case 1:

                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:

                    break;

                case 6:
                    System.out.println("Thank you come again:");
                    run = false;

                    break;
            }
        }

    }
}