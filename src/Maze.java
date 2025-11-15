
import java.io.*;
import java.util.*;

public class Maze {
    public char[][] theArray;
    int sizeLine; //num of cols
    int numLines;
    //num of rows
    public Maze(String filename) throws FileNotFoundException {
        Scanner txtInput = new Scanner(new File(filename));
        numLines = 1;
            while (txtInput.hasNextLine()) {
                txtInput.nextLine();
                sizeLine = txtInput.nextLine().length();
            numLines ++;
        }
        Scanner txtInput2 = new Scanner(new File(filename));
        String[] textArray = new String[numLines];
        for (String currentString : textArray) {
            currentString = txtInput2.nextLine();
        }


        for (int r = 0; r < numLines; r++) {
            for (int c = 0; c < sizeLine; c++){
                theArray[r][c] = textArray[r].charAt(c);
            }
        }

    }

    public String printMaze() {
        String returnString = "";
        for (char[] charArr : theArray) {
            for (char theChar : charArr) {
                returnString += theChar;
            }
            returnString += " ";
        }
        return returnString;
    }

    public Position getStart(){
        for(int i = 0; i < numLines; i++){
            for (int j = 0; j < numLines; j++){
                if(theArray[i][j] == 'S') {
                    return new Position(i,j);
                }
            }
        }
        return null;
    }
    public Position getEnd(){
        for(int i = 0; i < numLines; i++){
            for (int j = 0; j < numLines; j++){
                if(theArray[i][j] == 'E') {
                    return new Position(i,j);
                }
            }
        }
        return null;
    }


    public int getCols() {
        return sizeLine;
    }

    public int getRows() {
        return numLines;
    }

}
