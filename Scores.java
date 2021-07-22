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

        // create scanner
        Scanner doc;

        // holds user's menu choice 
        int choice = 0;
        
        while (choice != 4) {

            // user chooses from main menu
            choice = readChoice();

            // scores file object is created
            File file = new File("StudentScores.txt");

            //assign docs
            doc = new Scanner(file);

            // decide option
            switch(choice) {
                case 1: newScoresFile(file);
                    break;
                case 2: searchScores(file, doc);
                    break;
                case 3: addScores(file, doc);
                    break;
                case 4: JOptionPane.showMessageDialog(null, "Exiting Program...");
                    break;
                default: JOptionPane.showMessageDialog(null, "Error: invalid selection, try again...");           
            }

            doc.close();
        }

        System.exit(0);
    }

    /**
     * Reads an integer between 1 and 4 inclusive, if the integer is outside this range the method is called again.
     * @return an integer between 1 and 4 (inclusive)
     */
    public static int readChoice() {

        // create a nice menu layout
        JPanel cPanel = new JPanel();
        cPanel.setLayout(new BoxLayout(cPanel, BoxLayout.Y_AXIS));

        // add all 4 labels to the panel
        JLabel one = new JLabel("Enter 1 to create a new scores file.");
        JPanel oneP = new JPanel();
        oneP.add(one);
        cPanel.add(oneP);
        JLabel two = new JLabel("Enter 2 to search into an existing scores file.");
        JPanel twoP = new JPanel();
        twoP.add(two);
        cPanel.add(twoP);
        JLabel three = new JLabel("Enter 3 to add to an existing course scores file.");
        JPanel threeP = new JPanel();
        threeP.add(three);
        cPanel.add(threeP);
        JLabel four = new JLabel("Enter 4 to quit the program.");
        JPanel fourP = new JPanel();
        fourP.add(four);
        cPanel.add(fourP);

        String getChoice = JOptionPane.showInputDialog(null, cPanel);
        
        try {
            int choice = Integer.parseInt(getChoice);
            return choice;
        
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: invalid selection, try again...");
            return readChoice();
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

        if (created) 

        
            //String wipe = JOptionPane.showInputDialog(null, "A file already exists, Enter 1 to overwrite, Enter 2 to return to main menu");

            JOptionPane.showMessageDialog(null, "Current scores file has been wiped");
        else {
            JOptionPane.showMessageDialog(null, "A new file has been created");
        }

        PrintWriter newFile = new PrintWriter(file);

        newFile.close();
    }

    /**
     * Searches a file based on a read string then prints any related results
     * @return Only returns if the file cannot be read
     * @param file a file to be searched
     * @param scan a scanner object used to search the file
     */
    public static void searchScores(File file, Scanner scan) {

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
     * Takes a files and appends a new entry (entered by the user) on a new line in the file.
     * @param file the file to be added to
     * @param scan a scanner object used to scane the file
     * @throws Exception FileNotFoundException 
     */
    public static void addScores(File file, Scanner scan) throws Exception{

        // if the file cannot be read return to main menu
        if (!file.canRead()) {
            JOptionPane.showMessageDialog(null, "Error: cannot write to file.");
            return;
        }

        // read file length
        int length = 0;
        while (scan.hasNextLine()) {
            length++;
            scan.nextLine();
        }

        scan.close();
        
        // put each line into an array element
        scan = new Scanner(file);

        String[] data = new String[length];

        for (int i = 0; i < length; i++)
            data[i] = scan.nextLine();

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
        for (int i = 0; i < length; i++)
            output.println(data[i]);

        // add the new line to the file
        output.println(newLine);

        // notify the user
        JOptionPane.showMessageDialog(null, "Your record has been added to the file.");

        // close
        output.close();
    }
}