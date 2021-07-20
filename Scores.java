import java.io.File; 
import javax.swing.*;
import java.util.*;
public class Scores {
    public static void main(String[] args) {

        // display options - print for now
        System.out.println("Options: (1)create new scores file, (2)search the existing scores, (3)add to scores, (4)exit the program: ");

        // decide option
        switch(choice) {
            case 1: newScores();
                break;
            case 2: searchScores(scoresFile);
                break;
            case 3: addScores(scoresFile);
                break;
            case 4: system.exit(0);            
        }



    }

    public static void newScores() {
        // make a scores file
    }

    public static String[] searchScores(File scores) {

        // search for score

        return new String[]{"hey"};
    }

    public static void addScores(File scores) {
        // add an entry to scores file
    }
}