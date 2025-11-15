import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

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
    public static ArrayList<Position> solveDFS(Maze maze) {
        System.out.println("Solving with DFS (Stack)...");
        ArrayList<Position> returnList = new ArrayList<>();
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
        while (!theStack.isEmpty()) {
            if(theStack.pop().isEqual(maze.getEnd())) {
                for (int r = 0; r < isVisited.length; r ++) {
                    for (int c = 0; c < isVisited[0].length ; c ++) {
                        returnList.add(new Position (r,c));
                    }
                }
                return returnList;
            }
            else{
                
            }


        }
        //    b. If 'current' is the end position, you're done! Use the 'parent'
        //       pointers (from the Position class) to reconstruct the path.
        //       (See reconstructPath method hint).
        //    c. If 'current' is already visited, continue (skip).
        //    d. Mark 'current' as visited.
        //    e. Get all valid neighbors (Up, Down, Left, Right) of 'current'.
        //       A neighbor is valid if it's within bounds, not a wall, and
        //       not visited. (Hint: Use maze.isValid() helper).
        //    f. For each valid neighbor, set its 'parent' to 'current' and
        //       push it onto the stack.
        //
        // 4. If the loop finishes and you haven't found the end, return null.

        Stack<Position> stack = new Stack<>();
        Position start = maze.getStart();
        stack.push(start);

        boolean[][] visited = new boolean[maze.getRows()][maze.getCols()];

        // TODO: Implement DFS loop

        return null; // No path found
    }

    /**
     * Solves the maze using Breadth-First Search (BFS) with a Queue.
     *
     * @param maze The maze to solve.
     * @return A List of Position objects representing the *shortest* path from
     * S to E, or null if no path is found.
     */
    public static List<Position> solveBFS(Maze maze) {
        System.out.println("Solving with BFS (Queue)...");

        // TODO: This is the core logic you need to implement.
        //
        // 1. Create a Queue<Position> (e.g., new LinkedList<>()) and add
        //    the maze's start position.
        //
        // 2. Create a 2D boolean array 'visited' of the same size as the maze.
        //
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

        Queue<Position> queue = new LinkedList<>();
        Position start = maze.getStart();
        queue.add(start);

        boolean[][] visited = new boolean[maze.getRows()][maze.getCols()];

        // TODO: Implement BFS loop

        return null; // No path found
    }

    /**
     * Reconstructs the path from the end node back to the start node
     * using the 'parent' pointers stored in each Position object.
     *
     * @param end The end Position (which contains a chain of parents).
     * @return A List of Positions from start to end.
     */
    private static List<Position> reconstructPath(Position end) {
        List<Position> path = new ArrayList<>();
        // TODO: Follow the 'parent' chain from 'end' back to 'start'
        // and add each position to the 'path' list.
        // Remember to reverse the list at the end!
        return path;
    }

    /**
     * Prints the maze with the solution path marked.
     *
     * @param maze The maze.
     * @param path The solution path.
     */
    public static void printSolution(Maze maze, List<Position> path) {
        // TODO: Create a copy of the maze's grid
        // Mark the path cells (all except S and E) with '*'
        // Print the modified grid
    }
}
