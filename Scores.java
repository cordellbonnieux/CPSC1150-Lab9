import java.io.File; 
import java.io.PrintWriter;
import javax.swing.*;
import java.util.*;
public class Scores {
    public static void main(String[] args) throws Exception {

        // user chooses from main menu
        int choice = readChoice();

        // scores file object is created
        File file = new File("StudentScores.txt");

        // decide option
        switch(choice) {
            case 1: newScoresFile(file);
                main(null);
                break;
            case 2: searchScores(file);
                main(null);
                break;
            case 3: addScores();
                main(null);
                break;
            case 4: System.exit(0);            
        }



    }
    /**
     * Reads an integer between 1 and 4 inclusive, if the integer is outside this range the method is called again.
     * @return an integer between 1 and 4 (inclusive)
     */
    public static int readChoice() {

        // display options - print for now
        System.out.print("Options: (1)create new scores file, (2)search the existing scores, (3)add to scores, (4)exit the program: ");

        Scanner input = new Scanner(System.in);

        int choice = 0;      

        while (choice > 4 || choice < 1) {
            
            choice = input.nextInt();

            System.out.println();

            if (choice > 4 || choice < 1)
                System.out.print("Error: invalid entry, try again: ");
        }

        input.close();

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
    public static void searchScores(File file) throws Exception {

        // if the file cannot be read return to main menu
        if (!file.canRead()) {
            System.out.println("Error: cannot write to file.");
            main(null);
        }

        // creater a scanner obj
        Scanner input = new Scanner(System.in);

        // prompt user
        System.out.print("Enter a student name or score you'd like to find:");

        // get a search from user
        String search = input.nextLine();

        // re purpose input to scan the file 
        input.close();
        input = new Scanner(file);

        int numOfResults = 0;

        while(input.hasNext()) {

            if (input.next().contains(search)) {
                numOfResults++;
                System.out.printf("Result %d : %s", numOfResults, input.next());
            }
        }

        input.close();
    }

    public static void addScores() {
        // add an entry to scores file
    }
}