import java.io.*;
import java.util.*;

public class Maze {
    public char[][] theArray;
    int sizeLine; // num of cols
    int numLines; // num of rows

    public Maze(String filename) throws FileNotFoundException {
        // 1. First Pass: Calculate dimensions
        Scanner txtInput = new Scanner(new File(filename));
        numLines = 0; // Start at 0
        sizeLine = 0;

        while (txtInput.hasNextLine()) {
            String line = txtInput.nextLine(); // Read the line once
            sizeLine = line.length(); // Update width (assuming all lines are same length)
            numLines++;
        }
        txtInput.close(); // Good practice to close scanners

        // 2. Initialize the array (CRITICAL FIX)
        theArray = new char[numLines][sizeLine];

        // 3. Second Pass: Fill the data
        Scanner txtInput2 = new Scanner(new File(filename));

        // Use a standard for-loop to populate the array directly
        for (int r = 0; r < numLines; r++) {
            if (txtInput2.hasNextLine()) {
                String currentLine = txtInput2.nextLine();
                for (int c = 0; c < sizeLine; c ++) {
                    // Check to ensure we don't go out of bounds if a line is short
                    theArray[r][c] = currentLine.charAt(c);
                }
            }
        }
        txtInput2.close();
    }

    public String printMaze() {
        String returnString = "";
        for (char[] charArr : theArray) {
            for (char theChar : charArr) {
                returnString += theChar;
            }
            returnString += "\n"; // FIX: Use \n for new line, not a space
        }
        return returnString;
    }

    public Position getStart() {
        for (int i = 0; i < numLines; i++) {
            // FIX: Use sizeLine (columns) for the inner loop, not numLines
            for (int j = 0; j < sizeLine; j++) {
                if (theArray[i][j] == 'S') {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    public Position getEnd() {
        for (int i = 0; i < numLines; i++) {
            // FIX: Use sizeLine (columns) for the inner loop
            for (int j = 0; j < sizeLine; j++) {
                if (theArray[i][j] == 'E') {

                    return new Position(i, j);
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

    public boolean leftOpen(Position current) {
        if (!(current.x == 0)) {
            try {
            if (theArray[current.x - 1][current.y] == '*'|| theArray[current.x-1][current.y] == 'E') {
                return true;
            }
        }
            catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
        }
        return false;

    }

    public boolean downOpen(Position current) {
        if (!(current.y == numLines - 1)) {
            try {
                if (theArray[current.x][current.y + 1] == '*' || theArray[current.x][current.y +1] == 'E') {
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
        }
        return false;
    }

    public boolean upOpen(Position current) {
        System.out.println(!(current.y == 0));
        if (!(current.y <= 0)) {
            try {
                if (theArray[current.x][current.y - 1] == '*' || theArray[current.x][current.y - 1] == 'E') {
                    return true;
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
        }
        return false;

    }

    public boolean rightOpen(Position current) {
        if (!(current.x == sizeLine - 1)) {
            try {
                if (theArray[current.x + 1][current.y] == '*' || theArray[current.x+1][current.y] == 'E') {
                    return true;
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
        }
        return false;
        }
    }