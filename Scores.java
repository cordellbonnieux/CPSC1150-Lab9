import java.io.File; 
import java.io.PrintWriter;
import javax.swing.*;
import java.util.*;
public class Scores {
    public static void main(String[] args) throws Exception {

        // create scanners
        Scanner num = new Scanner(System.in);
        Scanner words = new Scanner(System.in);
        Scanner doc;

        // holds user's menu choice
        int choice = 0;
        
        while (choice != 4) {

            // user chooses from main menu
            choice = readChoice(num);

            // scores file object is created
            File file = new File("StudentScores.txt");

            //assign docs
            doc = new Scanner(file);

            // decide option
            switch(choice) {
                case 1: newScoresFile(file);
                    break;
                case 2: searchScores(file, words, doc);
                    break;
                case 3: addScores();
                    break;
                case 4: System.out.println("Exiting Program...");
                    break;
                default: System.out.println("Error: invalid selection, try again...");           
            }

            doc.close();
        }

        // close the scanners
        num.close();
        words.close();

        // end 
        System.exit(0);
    }
    /**
     * Reads an integer between 1 and 4 inclusive, if the integer is outside this range the method is called again.
     * @return an integer between 1 and 4 (inclusive)
     */
    public static int readChoice(Scanner input) {

        // display options - print for now
        System.out.print("Options: (1)create new scores file, (2)search the existing scores, (3)add to scores, (4)exit the program: ");

        int choice = input.nextInt();

        return choice;
    }

    /**
     * Creates a new StudentScores.txt file if one does not exist,
     * if it does then the program wipes the data from the file.
     * @param file a .txt file in the program's relative path
     * @throws Exception FileNotFoundException 
     */
    public static void newScoresFile(File file) throws Exception {

        // check for existing file
        Boolean created = file.exists();

        PrintWriter newFile = new PrintWriter(file);

        if (created) 
            System.out.println("Current scores file has been wiped");
        else {
            System.out.println("A new file has been created");
        }

        newFile.close();
    }

    /**
     * Searches a file and prints the related results
     * @return
     */
    public static void searchScores(File file, Scanner input, Scanner scan) throws Exception {

        // if the file cannot be read return to main menu
        if (!file.canRead()) {
            System.out.println("Error: cannot write to file.");
            main(null);
        }

        // prompt user
        System.out.print("Enter a student name or score you'd like to find:");

        // get a search from user
        String search = input.nextLine();

        System.out.println(search);

        // create a scanner to read the file
        /*Scanner scan = new Scanner(file);

        int numOfResults = 0;

        while(scan.hasNext()) {

            if (scan.next().contains(search)) {
                numOfResults++;
                System.out.printf("Result %d : %s", numOfResults, scan.next());
            }
        }

        scan.close();
        */

    }

    public static void addScores() {
        // add an entry to scores file
    }
}