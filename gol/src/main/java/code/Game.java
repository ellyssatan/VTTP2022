package code;

import java.io.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Game {
    char[][] grid;
    int startPos;
    int v1;
    int v2;
    int s1;
    int s2;
    int lineIndex;
    String sentence;
    List<String> text = new ArrayList<>();
    int[][] dir = {{-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}};


    public void Read(String f) throws FileNotFoundException, IOException {

        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

		// Try to use streams
        text = br.lines().collect(Collectors.toList());
        br.close();

        for (int i = 0; i < text.size(); i++) {

            this.sentence = text.get(i);
            String command = sentence.split(" ")[0];

            switch (command) {
                case "#":
                    break;

                case "GRID":
                    this.v1 = Integer.parseInt(sentence.split(" ")[1]);
                    this.v2 = Integer.parseInt(sentence.split(" ")[2]);
                    SetGrid(v1, v2);
                    break;

                case "START":
                    this.s1 = Integer.parseInt(sentence.split(" ")[1]);
                    this.s2 = Integer.parseInt(sentence.split(" ")[2]);
                    SetStartPos(s1, s2);
                    break;

                case "DATA":
                    // create populateTheGrid(i+1) method, remove for loop
                    PopulateGrid(i+1);
                    for (int k = 0; k <5; k++) {
                        Check();
                    }
                    break;
                default:
            }
        }
    }

    public void SetGrid(int x, int y){
        grid  = new char[x][y];
        // populate default grid
        System.out.println("Initial Grid:");

		// Setting the rows
        for (int row = 0; row < grid.length; row++) {
            // Setting the columns
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = '-';
            }
            System.out.println(grid[row]);  
        } 

    }

    public void PopulateGrid(int startLine) {

        // Going through rows
        for (int o = startLine; o < text.size(); o++) {     // start from line after DATA and read till end
            sentence = text.get(o);
            //System.out.println(sentence);
            // loop through individual char in string (Going through cols)
            for (int j = 0; j < sentence.length(); j++) {      
                char c = sentence.charAt(j);
                //System.out.print(c);    // can ignore this
                if (c == '*') {
                    grid[(o - startLine) + s1][j + s2] = c;
                } else {
                    grid[(o - startLine) + s1][j + s2] = '-';
                }
            }
            //System.out.println();   // can ignore this
            // Print row
        }

        //print out
        // System.out.println("Final: ");
        // for (int row = 0; row < grid.length; row++) {
        //     System.out.println(grid[row]);
        // }
    }

    public void SetStartPos(int x, int y){
        startPos = grid[x][y];
    }

    public boolean IsOutOfBounds(int x, int y) {

        return !(0 <= y && y < grid.length) || !(0 <= x && x < grid[0].length);
    }

    public void Check() {

        int count = 0;
        // Make a new grid 
        char[][] newGrid = new char[grid.length][grid[0].length];
        //loop through entire array
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                count = 0;
                
                // Check if cell is dead
                boolean isAlive = grid[row][col] == '*';
                // Check neighbours in all directions and count them
                for (int i = 0; i < dir.length; i++) {
                        int neighbourX = row + dir[i][0];
                        int neighbourY = col + dir[i][1];
                    // Check if neighbouring cell is out of bounds, if it is, skip
                    if (IsOutOfBounds(neighbourX, neighbourY)) {
                        continue;
                    }
                    // If is a neighbour, increase count by 1
                    if (grid[neighbourX][neighbourY] == '*') {
                        count++;
                    }
                }
                
                // Check if dead or alive
                if (isAlive) {
                    if (count < 2 || count > 3) {
                        newGrid[row][col] = '-';
                    } else {
                        newGrid[row][col] = '*';
                    }
                } else {
                    if (count == 3) {
                        newGrid[row][col] = '*';
                    } else {
                        newGrid[row][col] = '-';
                    }
                }
    

            }
            System.out.println(newGrid[row]);
        }
        System.out.println();
        

        // Replace the old grid with the new one
        grid = newGrid;
    }
}
