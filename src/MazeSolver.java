import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.Queue;

/**
 * Main class to drive the maze solving application.
 * Handles user input, loads the maze, and calls the appropriate solver.
 */
public class MazeSolver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter maze file name (e.g., maze1.txt): ");


            // 1. Load the maze
            String filename = scanner.nextLine();
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            Maze maze = new Maze(filename);
            System.out.println("Maze loaded:");

            maze.printMaze();

            // 2. Ask for solver choice
            System.out.print("\nChoose solver: (1) DFS (Stack) or (2) BFS (Queue): ");
            int choice = scanner.nextInt();

            List<Position> path = null;
            if (choice == 1) {
                path = solveDFS(maze);
            } else if (choice == 2) {
                path = solveBFS(maze);
            } else {
                System.out.println("Invalid choice.");
            }

            // 3. Display the result
            if (path != null) {
                System.out.println("\nPath found!");
                printSolution(maze, path);
                System.out.println("Steps: " + path.size());
            } else {
                System.out.println("\nNo solution found.");
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: Maze file not found!");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }

    }

    /**
     * Solves the maze using Depth-First Search (DFS) with a Stack.
     *
     * @param maze The maze to solve.
     * @return A List of Position objects representing the path from S to E,
     * or null if no path is found.
     */
    public static List<Position> solveDFS(Maze maze) {
        System.out.println("Solving with DFS (Stack)...");
        // TODO: This is the core logic you need to implement.
        //
        // 1. Create a Stack<Position> and add the maze's start position.
        //
        Stack<Position> theStack = new Stack<>();
        theStack.push(maze.getStart());
        // 2. Create a 2D boolean array 'visited' of the same size as the maze
        //    to keep track of visited cells.
        //
        Boolean[][] isVisited = new Boolean[maze.getRows()][maze.getCols()];
        // 3. Loop while the stack is not empty:
        //    a. Pop a 'current' position from the stack.
        Position current = (maze.getStart());
        Position last = current;
        while (!theStack.isEmpty()) {
            boolean isDone = false;
            Position current = theStack.pop();
                if (maze.upOpen(current)) {
                    Position currentUp = new Position(current.x, current.y - 1);
                    if (!(currentUp.y < 0) && isVisited[currentUp.x][currentUp.y] == null) {
                        currentUp.setParent(current);
                        theStack.push(currentUp);
                        isVisited[currentUp.x][currentUp.y] = true;
                        isDone = true;
                    }
                }
                if (maze.downOpen(current)) {
                    Position currentDown = new Position(current.x, current.y + 1);
                    if (!(currentDown.y >= maze.numLines) && isVisited[currentDown.x][currentDown.y] == null) {
                        theStack.push(currentDown);
                        isVisited[currentDown.x][currentDown.y] = true;
                        isDone = true;
                    }
                }
                else if (maze.leftOpen(current)) {
                    Position currentLeft = new Position(current.x - 1, current.y);
                    if (!(currentLeft.x < 0) && isVisited[currentLeft.x][currentLeft.y] == null) {
                        theStack.push(currentLeft);
                        isVisited[currentLeft.x][currentLeft.y] = true;
                        isDone = true;
                    }
                }
                else if (maze.rightOpen(current)) {
                    Position currentRight = new Position(current.x + 1, current.y);
                    if (!(currentRight.x >= maze.sizeLine) && isVisited[currentRight.x][currentRight.y] == null) {
                        theStack.push(currentRight);
                        isVisited[currentRight.x][currentRight.y] = true;
                        isDone = true;
                    }
                }
                else if (isDone == false && maze.getEnd().isEqual(current)){
                    List<Position> returnList = new LinkedList<>();
                    Stack<Position> tempStack = new Stack<>();
                    Position parent = null;
                    while (!theStack.isEmpty()) {
                        tempStack.push(theStack.pop());
                    }
                    while (!tempStack.isEmpty()) {
                        returnList = reconstructPath(tempStack.peek());
                    }
                    return returnList;
                }
        }
        System.out.println("No solution found");
        return null;
    }

            /*else if (theStack.pop().isEqual(maze.getEnd())) {
                    for (int r = 0; r < isVisited.length; r++) {
                        for (int c = 0; c < isVisited[0].length; c++) {
                            returnList.add(new Position(r, c));
                        }
                    }
                    return returnList;
                }
                else{
                    theStack.pop();
                }
            }
        }

    /**
     * Solves the maze using Breadth-First Search (BFS) with a Queue.
     *
     * @param maze The maze to solve.
     * @return A List of Position objects representing the *shortest* path from
     * S to E, or null if no path is found.
     */
    public static LinkedList<Position> solveBFS(Maze maze) {
        System.out.println("Solving with BFS (Queue)...");

        // TODO: This is the core logic you need to implement.
        //
        // 1. Create a Queue<Position> (e.g., new LinkedList<>()) and add
        //    the maze's start position.
        //
        Queue<Position> theQueue = new LinkedList<>();
        theQueue.add(maze.getStart());
        // 2. Create a 2D boolean array 'visited' of the same size as the maze.
        //
        Boolean[][] isVisited2 = new Boolean[maze.getRows()][maze.getCols()];
        // 3. Loop while the queue is not empty:
        //    a. Dequeue a 'current' position.
        //    b. If 'current' is the end position, you're done! Reconstruct the
        //       path using the 'parent' pointers.
        //    c. If 'current' is already visited, continue (skip).
        //    d. Mark 'current' as visited.
        //    e. Get all valid neighbors (Up, Down, Left, Right).
        //    f. For each valid neighbor, set its 'parent' to 'current' and
        //       enqueue it.
        //
        // 4. If the loop finishes, return null.


        // TODO: Implement BFS loop
        boolean hasMet;
        Position current = (maze.getStart());
        Position last = current;
        while (!theQueue.isEmpty()) {
                if (maze.upOpen(current)) {
                    Position currentUp = new Position(current.x, current.y - 1);
                    if (!last.isEqual(currentUp)) {
                        theQueue.add(currentUp);
                        isVisited2[currentUp.x][currentUp.y] = true;
                        last = current;
                        current = currentUp;
                        break;
                    }
                }
                else if (maze.downOpen(current)) {
                    Position currentDown = new Position(current.x, current.y + 1);
                    if (!last.isEqual(currentDown)) {
                        theQueue.add(currentDown);
                        isVisited2[currentDown.x][currentDown.y] = true;
                        last = current;
                        current = currentDown;
                        break;
                    }
                }
                else if (maze.leftOpen(current)) {
                    Position currentLeft = new Position(current.x - 1, current.y);
                    if (!last.isEqual(currentLeft)) {
                        theQueue.add(currentLeft);
                        isVisited2[currentLeft.x][currentLeft.y] = true;
                        last = current;
                        current = currentLeft;
                        break;
                    }
                }
                else if (maze.rightOpen(current)) {
                    Position currentRight = new Position(current.x + 1, current.y);
                    if (!last.isEqual(currentRight)) {
                        theQueue.add(currentRight);
                        isVisited2[currentRight.x][currentRight.y] = true;
                        last = current;
                        current = currentRight;
                        break;
                    }
                }
                if (current.isEqual(maze.getEnd())) {
                    LinkedList<Position> returnList = new LinkedList<>();
                    Position parent = null;
                    while (!theQueue.isEmpty()) {
                        for (int i = 0; i < theQueue.size(); i++) {
                            Position cur = theQueue.remove();
                            cur.setParent(parent);
                            returnList.add(cur);
                            parent = cur;
                        }
                    }
                    return returnList;
                } /*else if (theStack.pop().isEqual(maze.getEnd())) {
                    for (int r = 0; r < isVisited.length; r++) {
                        for (int c = 0; c < isVisited[0].length; c++) {
                            returnList.add(new Position(r, c));
                        }
                    }
                    return returnList;
                }*/ else {
                    theQueue.remove();
                }
        }
        return null; // No path found
    }

    /**
     * Reconstructs the path from the end node back to the start node
     * using the 'parent' pointers stored in each Position object.
     *
     * @param end The end Position (which contains a chain of parents).
     * @return A List of Positions from start to end.
     */
    public static List<Position> reconstructPath(Position end) {
        List<Position> path = new ArrayList<>();
        Stack<Position> reversePath = new Stack<>();
        Position curr = end;
        while (!curr.isEqual(null)){ //starts at the end of the path and iterates backwards, finding the position the current position is linked to until it gets to the start
            reversePath.push(curr);
            curr = curr.getParent(); //makes sure loop stops when at the first element (which has a null parent)
        }
        for(int i = 0; i < reversePath.size(); i++){ //pops the positions from the stack and puts them in the arraylist in the correct order
            path.add(reversePath.pop());
        }

        return path; //outputs the path that solves the maze
    }

    /**
     * Prints the maze with the solution path marked.
     *
     * @param maze The maze.
     * @param path The solution path.
     */
    public static void printSolution(Maze maze, List<Position> path) {
        char[][] solutionPath = new char[maze.numLines][maze.sizeLine]; //creates an empty array the size of the maze
        for (Position p: path){ //sets all positions of the array with coordinates equal to those in path to '*'
            solutionPath[p.x][p.y]='*';
        }
        for(int r = 0; r < maze.numLines; r++){ //iterates through the array
            for(int c = 0; c < maze.sizeLine; c++){ //prints 'S' if the loop is at the array's start position
                if ((r == maze.getStart().x && c == maze.getStart().y))
                    System.out.println('S');
                else if (r == maze.getEnd().x && c == maze.getEnd().y){ //prints 'E' if the loop is at the array's end position
                    System.out.println('E');
                }
                else if (solutionPath[r][c] == '\u0000'){ //prints ' ' (an empty space) if the current spot in the array is not a part of the solution path
                    System.out.println(' ');
                }
                else{
                    System.out.println('*'); //prints '*' if the current spot in the array is a part of the solution path
                }
                }
            }
            }
        }
        // TODO: Create a copy of the maze's grid
        // Mark the path cells (all except S and E) with '*'
        // Print the modified grid