import java.io.File; 
import java.io.PrintWriter;
import javax.swing.*;
import java.util.*;
/**
 * Program Name: Scores.java
 * Description: Read, write and re-write data (student scores) to an external text document.
 * @author Cordell Bonnieux
 * @since July 20 2021 
 * Instructor: Dr. Bita Shadgar
 */
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
                case 3: addScores(file, doc, words, num);
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
     * @param input
     */
    public static int readChoice(Scanner input) {

        String getChoice = JOptionPane.showInputDialog(null, "Options: (1)create new scores file, (2)search the existing scores, (3)add to scores, (4)exit the program: ");
        
        try {
            int choice = Integer.parseInt(getChoice); //input.nextInt();
            return choice;
        
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: invalid selection, try again...");
            return readChoice(input);
        }         
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
     * @param file
     * @param input
     * @param scan
     * @throws Exception
     */
    public static void searchScores(File file, Scanner input, Scanner scan) throws Exception {

        // if the file cannot be read return to main menu
        if (!file.canRead()) {
            System.out.println("Error: cannot write to file.");
            return;
        }

        // prompt user
        System.out.print("Enter a student name or score you'd like to find:");

        // get a search from user
        String search = input.nextLine();

        // to store the number of results
        int numOfResults = 0;

        while(scan.hasNext()) {

            if (numOfResults == 1)
                System.out.println("Search results for: " + search);

            String line = scan.nextLine();

            if (line.contains(search)) {
                numOfResults++;
                System.out.printf("#%d: %s \n", numOfResults, line);
            }
        }

        if (numOfResults == 0)
            System.out.println("Error: No results for your search term");
    }
    
    /**
     * 
     * @param file
     * @param scan
     * @param textInput
     * @param numInput
     * @throws Exception
     */
    public static void addScores(File file, Scanner scan, Scanner textInput, Scanner numInput) throws Exception{

        // if the file cannot be read return to main menu
        if (!file.canRead()) {
            System.out.println("Error: cannot write to file.");
            return;
        }

        // read file into an array list
        ArrayList<String> data = new ArrayList<String>();
        while (scan.hasNext())
            data.add(scan.nextLine());

        // read and store name
        System.out.print("Please enter a name: ");
        String name = textInput.nextLine();

        // read and store score
        System.out.print("Please enter a score: ");
        int score = numInput.nextInt();

        // concat name and score
        String newLine = name + " " + String.valueOf(score);

        // rewrite the file
        PrintWriter output = new PrintWriter(file);
        for (int i = 0; i < data.size(); i++)
            output.println(data.get(i));

        // add the new line to the file
        output.println(newLine);

        // close
        output.close();
    }
}