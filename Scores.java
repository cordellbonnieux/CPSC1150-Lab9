import java.io.File; 
import java.io.PrintWriter;
import javax.swing.*;
import java.util.*;
public class Scores {
    public static void main(String[] args) throws Exception {

        int choice = readChoice();

        File file = new File("StudentScores.txt");

        // decide option
        switch(choice) {
            case 1: newScoresFile(file);
                main(null);
                break;
            case 2: searchScores();
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

        int choice = input.nextInt();

        if (choice > 4 || choice < 1) {
            System.out.println("Invalid entry, try again.");
            return readChoice();
        }

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

    public static String[] searchScores() {

        // search for score

        return new String[]{"hey"};
    }

    public static void addScores() {
        // add an entry to scores file
    }
}