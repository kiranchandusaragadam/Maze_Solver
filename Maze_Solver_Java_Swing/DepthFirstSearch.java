import java.util.ArrayList;

public class DepthFirstSearch {
    // find the path from source to destination using following method
    /**
     * Algorithm - DFS
     * there are 4 possibilities ->
     * if current cell value is 0 - not visited then mark as visited with value 2 and call neighbor cells recursively
     * if current cell value is 1 - it's as obstacle so can't move in that direction
     * if we reached destination which is having cell value as 9 then add row, col coordinates to list and return true as path found
     * if current cell gets true from any called method in particular direction then add current cell coordinates and return true to its calling function
     * so finally we get all cell coordinates involved in path in reverse order means ending point to starting point
     */

    public static boolean serachPath(int[][] maze, int row, int col, ArrayList<Integer> path){
        // if found destination then add current cell row and col coordinates to path list and return true
        if(maze[row][col] == 9){
            path.add(row);
            path.add(col);
            return true;
        }

        // other case : if this cell is not visited means having cell value as 0
        // then mark that cell as visited and try to find path in all 4 directions if possible
        if(maze[row][col] == 0){
            maze[row][col] = 2;

            // check in all 4 directions if any path is possible or not
            // now check in left direction
            if(serachPath(maze, row, col-1, path)){
                path.add(row);
                path.add(col);
                return true;
            }

            // now check in right direction
            if(serachPath(maze, row, col+1, path)){
                path.add(row);
                path.add(col);
                return true;
            }

            // now check in bottom direction
            if(serachPath(maze, row+1, col, path)){
                path.add(row);
                path.add(col);
                return true;
            }

            // check in top direction
            if(serachPath(maze, row-1, col, path)){
                path.add(row);
                path.add(col);
                return true;
            }
        }

        // if path not found in all directions then return false means path not found
        return false;
    }
}
