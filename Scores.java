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
                case 4: JOptionPane.showMessageDialog(null, "Exiting Program...");
                    break;
                default: JOptionPane.showMessageDialog(null, "Error: invalid selection, try again...");           
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
            JOptionPane.showMessageDialog(null, "Current scores file has been wiped");
        else {
            JOptionPane.showMessageDialog(null, "A new file has been created");
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
            JOptionPane.showMessageDialog(null, "Error: cannot write to file.");
            return;
        }

        // get a search from user
        String search = JOptionPane.showInputDialog(null, "Enter a student name or score you'd like to find:");

        // to store the number of results
        int numOfResults = 0;

        // parent panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        while(scan.hasNext()) {

            String line = scan.nextLine();

            if (line.contains(search)) {

                JPanel row = new JPanel();

                // create a header for the results
                if (numOfResults == 0) {
                    JLabel heading = new JLabel("Results from your search of: " + search);
                    JPanel rowH = new JPanel();
                    rowH.add(heading);
                    panel.add(rowH);
                }

                // create a display for each result
                numOfResults++;
                JLabel label = new JLabel(String.format("#%d: %s \n", numOfResults, line));
                row.add(label);
                panel.add(row);
            }
        }

        if (numOfResults == 0)
            JOptionPane.showMessageDialog(null, "Error: No results for your search term");
        else
            JOptionPane.showMessageDialog(null, panel);
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
            JOptionPane.showMessageDialog(null, "Error: cannot write to file.");
            return;
        }

        // read file into an array list
        ArrayList<String> data = new ArrayList<String>();
        while (scan.hasNext())
            data.add(scan.nextLine());

        // read and store name
        String name = JOptionPane.showInputDialog(null, "Please enter a name: ");

        // read and store score
        String scoreString = JOptionPane.showInputDialog(null, "Please enter a score: ");

        int score;
 
        try {
            score = Integer.parseInt(scoreString);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: score needs to be a number...");
            return;
        } 

        // concat name and score
        String newLine = name + " " + String.valueOf(score);

        // rewrite the file
        PrintWriter output = new PrintWriter(file);
        for (int i = 0; i < data.size(); i++)
            output.println(data.get(i));

        // add the new line to the file
        output.println(newLine);

        // notify the user
        JOptionPane.showMessageDialog(null, "Your record has been added to the file.");

        // close
        output.close();
    }
}