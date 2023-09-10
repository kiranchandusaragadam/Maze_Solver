import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MazeGUI extends JFrame {
    /**
     * take an integer 2d array to represent the maze
     * each cell value representation as follows
     * maze[row][col] =
     * 0 -> not visited cell
     * 1 -> obstacle, can't move to that cell
     * 2 -> visited cell
     * 9 -> destination cell
     * all boundary cells have value 1 to prevent ArrayIndexOutOfBound exception
    */
    private int[][] maze = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,0,1,0,1,0,0,0,0,0,1},
            {1,0,1,0,0,0,1,0,1,1,1,0,1},
            {1,0,0,0,1,1,1,0,0,0,0,0,1},
            {1,0,1,0,0,0,0,0,1,1,1,0,1},
            {1,0,1,0,1,1,1,0,1,0,0,0,1},
            {1,0,1,0,1,0,0,0,1,1,1,0,1},
            {1,0,1,0,1,1,1,0,1,0,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,1,9,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    // to store the path row and col coordinates create an arraylist
    public ArrayList<Integer> path = new ArrayList<>();

    // constructor
    public MazeGUI(){
        // now create basic setting for this gui frame
        setTitle("MAZE_SOLVER"); // project title shows on top of the frame
        setLayout(null); // default layout is card layout -> in which every component takes whole area
        setSize(640, 480); // set size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // by closing window frame java program also gets terminated
        setLocationRelativeTo(null); // by default window placed on left top corner, inorder to get window to middle using this method

        // call DFS class search path method to find the path and get coordinates of path in the form of array list
        DepthFirstSearch.serachPath(maze, 1, 1, path);
    }

    /**
     * now make that 2d array visible on our frame
     * to make visible need to color the cells based on their value representation

     * coloring conventions are as follows:
     * set size of each cell -> 30*30
     * set color for 0 -> white
     * for 1 -> black
     * for 9 -> red (destination cell)
     * for starting cell -> yellow
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // to positioning our 2d array (Maze)
        g.translate(75, 75);

        // fill the maze cells with appropriate colors
        for(int r=0; r<maze.length; r++){
            for(int c=0; c<maze[0].length; c++){
                // starting cell represent with yellow color
                if(r == 1 && c == 1){
                    g.setColor(Color.yellow);
                    g.fillRect(30*c, 30*r, 30, 30);
                    g.setColor(Color.red);
                    g.drawRect(30*c, 30*r, 30, 30);
                    continue;
                }
                Color clr;
                int val = maze[r][c];
                switch(val){
                    case 1: clr = Color.BLACK;
                    break;
                    case 9: clr = Color.RED;
                    break;
                    default : clr = Color.white;
                }
                g.setColor(clr);
                g.fillRect(30*c, 30*r, 30, 30);
                g.setColor(Color.red);
                g.drawRect(30*c, 30*r, 30, 30);
            }
        }

        // now fill the all cells with green color through which path is passing
        for(int i=0; i<path.size(); i += 2){
            // row number represents y axis in our frame
            // and col number represents x axis
            int row = path.get(i); // getting row number
            int col = path.get(i+1); // getting col number

            Color clr = Color.green;

            g.setColor(clr);
            g.fillOval(30*col+5, 30*row+5, 20, 20);
        }
    }


    // main method
    public static void main(String[] args) {
        // create object of this class
        MazeGUI mazeObj = new MazeGUI();
        mazeObj.setVisible(true); // to make frame visible
    }
}
