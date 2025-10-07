package datastructures.graphs;

import java.util.*;

public class GraphProblems {


    public static void print2DArray(int[][] array) {
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[row].length; col++) {
                System.out.print(array[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void print2DArray(List<List<Integer>> list) {
        for (int row = 0; row < list.size(); row++) {
            for (int col = 0; col < list.get(row).size(); col++) {
                System.out.print(list.get(row).get(col) + " ");
            }
            System.out.println();
        }
    }



    /*
    Flood fill problem

    given an image represented by a grid of pixel values, a starting pixel (row, col), and a new pixel value p,
    transform all pixels connected to the starting pixel to the new pixel value

    e.g.

                        +---+---+---+---+---+---+
                        | 1 | 1 | 1 | 1 | 0 | 0 |
                        +---+---+---+---+---+---+
                        | 1 | 0 | 1 | 1 | 0 | 0 |
                        +---+---+---+---+---+---+
                        | 1 | 1 | 1 | 1 | 1 | 0 |
                        +---+---+---+---+---+---+
                        | 0 | 0 | 1 | 1 | 1 | 1 |
                        +---+---+---+---+---+---+
                        | 1 | 1 | 0 | 1 | 0 | 0 |
                        +---+---+---+---+---+---+

        connected pixels share the same value and have a path between them by moving either left, right, up and down.
        Therefore, this 2dimensional array of pixel values can be represented as a graph as shown in the link below

        http://graphonline.top/en?graph=UWmIksxMxRlDUpuI

     */


    public static int[][] floodFillBFS(int[][] img, int row, int col, int p){
        int newPixelvalue = p;
        String startPosition = row + "," + col; // creating a string of start pos, this is because we can check for duplicates if our coords are a string
        int initialPixelvalue = img[row][col]; // setting the initial pixel value to compare against neighbouring pixels to see if they are valid neighbours
        Queue<String> toVisitQueue = new LinkedList<>(); //creating a queue to keep track of the nodes that havent been visited
        HashSet<String> visitedSet = new HashSet<>();//creating a set to keep track of all visited nodes;

        toVisitQueue.add(startPosition); //adding the start position to the to visit queue

        //as long as the queue is not empty, we will continually perform the breadth first search
        while (!toVisitQueue.isEmpty()){
            String currentCoordsAsString = toVisitQueue.remove(); //removing our current coordinates from the queue
            String[] CurrentCoords = currentCoordsAsString.split(","); // splitting our string by the comma to get the current coordinates
            int currentRow = Integer.parseInt(CurrentCoords[0]);
            int currentColumn = Integer.parseInt(CurrentCoords[1]);

            visitedSet.add(currentCoordsAsString); // adding our current coordinates to the visited set
            img[currentRow][currentColumn] = newPixelvalue; //changing our pixel value to the new pixel value
            
            for (String coords : neighbours(img, currentRow, currentColumn, initialPixelvalue)){
                if (coords != null){ //checking to see if we dont have a null neighbour (i.e a pixel that doesnt share the same initial pixel value or our current pixel is on the edge
                    if (!visitedSet.contains(coords)){ // checking to see if we visited that neighbour,
                        toVisitQueue.add(coords); // if we haven't we add that neighbour to our toVisitQueue to be visited later.
                    }
                }
            }
        }
        
        return img;
    }

    private static String[] neighbours(int[][] img, int row, int col, int initialPixelValue) {
        String[] allNeighbours = new String[4]; // there will be a maximum of 4 neighbours
        int maxRowIndex = img.length - 1; //getting the maximum index for the row
        int maxColIndex = img[row].length - 1; //getting the maximum index for the column

        if(row > 0  && img[row - 1][col] == initialPixelValue){ // if the current pixel is not at the top of the image, we have a neighbour above us
            String leftNeighbour = (row - 1) + "," + col;
            allNeighbours[0] = leftNeighbour;
        }
        if (row < maxRowIndex && img[row + 1][col] == initialPixelValue){ // if the current pixel is not at the bottom of the image, we have a neighbour below us
            String bottomNeighbour = (row + 1) + "," + col;
            allNeighbours[1] = bottomNeighbour;
        }
        if (col > 0 && img[row][col - 1] == initialPixelValue){ // if the current pixel is not on the far left side of the image, we have a left neighbour
           String leftNeighbour = row + "," + (col -1);
            allNeighbours[2] = leftNeighbour;
        }
        if (col < maxColIndex && img[row][col + 1] == initialPixelValue){ // if the current pixel is not on the far right side of the image, we have a right neighbour
            String rightNeighbour = row + "," + (col+1);
            allNeighbours[3] = rightNeighbour;
        }

        return allNeighbours; // returning all possible neighbours. Note that some of the neighbours are null as they are invalid.
    }

    public static int[][] floodFillBFSRecursive(int[][] img, int row, int col, int newPixelValue) {
        int initialPixelValue = img[row][col];

        floodFillBfsHelper(img, row, col, initialPixelValue, newPixelValue);
        return img;
    }

    private static void floodFillBfsHelper(int[][] img, int row, int col, int initialPixelValue, int newPixelValue) {
        // Check bounds
        if (row < 0 || row >= img.length || col < 0 || col >= img[0].length) return;

        // Stop if the pixel doesn't match the original
        if (img[row][col] != initialPixelValue) return;

        // Change the pixel to the new value
        img[row][col] = newPixelValue;

        // Recurse in 4 directions (DFS)
        floodFillBfsHelper(img, row - 1, col, initialPixelValue, newPixelValue); // up
        floodFillBfsHelper(img, row + 1, col, initialPixelValue, newPixelValue); // down
        floodFillBfsHelper(img, row, col - 1, initialPixelValue, newPixelValue); // left
        floodFillBfsHelper(img, row, col + 1, initialPixelValue, newPixelValue); // right
    }

    public static int[][] floodFillDFS(int[][] img, int row, int col, int color){
        int initialColour = img[row][col];

        int totalRows = img.length;
        int totalColumns = img[row].length;

        Stack<int[]> toVisitStack = new Stack<>();

        int[] startingCoords = {row, col};

        toVisitStack.push(startingCoords);

        while (!toVisitStack.isEmpty()){
            int[] currentCoords = toVisitStack.pop();
            int currentRow = currentCoords[0];
            int currentColumn= currentCoords[1];

            img[currentRow][currentColumn] = color;

            if (row > 0) toVisitStack.push(new int[]{currentRow - 1, currentColumn});     // up
            if (row < totalRows - 1) toVisitStack.push(new int[]{currentRow + 1, currentColumn}); // down
            if (col > 0) toVisitStack.push(new int[]{currentRow, currentColumn - 1});     // left
            if (col < totalColumns - 1) toVisitStack.push(new int[]{currentRow, currentColumn + 1}); // right
        }

        return img;
    }



    public int[][] floodFillDFSRecursive(int[][] img, int row, int col, int newColour){
        int initialColour = img[row][col];

        floodFillDFSRecursiveHelper(img, row, col, initialColour, newColour );

        return img;

    }

    private void floodFillDFSRecursiveHelper(int[][] img, int currentRow, int currentColumn, int initialColour, int newColour) {

        int maxRowsIndex = img.length -1;
        int maxColIndex = img[currentRow].length -1;
    }

    /************************************************************************************************************************/


    public static List<List<Integer>> pacificAtlantic(int[][] heights) {

        int totalRows = heights.length;
        int totalColumns = heights[0].length;

        boolean[][] reachPacific = new boolean[totalRows][totalColumns];
        boolean[][] reachAtlantic = new boolean[totalRows][totalColumns];

        List<List<Integer>> returnedCoords = new ArrayList<>();

        // starting from the top and bottom edges edges of the graph
        for (int column = 0; column < totalColumns; column++){
            pacificAtlanticIterativeHelper(heights, 0, column, reachPacific);
            pacificAtlanticIterativeHelper(heights, totalRows - 1, column, reachAtlantic);
        }

        //starting from the left and right edges of the graph
        for (int row = 0; row < totalRows; row++){
            pacificAtlanticIterativeHelper(heights, row, 0, reachPacific);
            pacificAtlanticIterativeHelper(heights, row, totalColumns - 1, reachAtlantic);
        }

        for (int row = 0; row < totalRows; row++){
            for (int column = 0; column < totalColumns; column++){
                if (reachPacific[row][column] && reachAtlantic[row][column]) {
                    returnedCoords.add(Arrays.asList(row,column));
                }
            }
        }

        return returnedCoords;
    }


    private static void pacificAtlanticIterativeHelper(int[][] heights, int startRow, int startCol, boolean[][] visited){
        int totalRows = heights.length;
        int totalColumns = heights[0].length;

        Stack<int[]>coordsStack = new Stack<>();

        int[] startCoords = {startRow, startCol};

        coordsStack.push(startCoords);

        while (!coordsStack.isEmpty()){
            int[] currentCoords = coordsStack.pop();
            int row = currentCoords[0], column = currentCoords[1];

            if (!visited[row][column]) {
                visited[row][column] = true;

                for(int[] dir : directions){
                    int newRow = row + dir[0];
                    int newColumn = column + dir[1];

                    if (newRow >= 0 && newRow < totalRows && newColumn >= 0 && newColumn < totalColumns){


                        if (!visited[newRow][newColumn] && heights[newRow][newColumn] >= heights[row][column]){
                            int[] newCoords = {newRow, newColumn};
                            coordsStack.push(newCoords);
                        }
                    }
                }
            }
            else continue;
        }
    }


    public static final int[][] directions = { // all possible directions water can flow regardless of elavation
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
    };
    public static void main(String[] args) {
//        int[][] img1 = {
//                {1, 1, 1, 1, 0, 0},
//                {1, 0, 1, 1, 0, 0},
//                {1, 1, 1, 1, 1, 0},
//                {0, 0, 1, 1, 1, 1},
//                {1, 1, 0, 1, 0, 0}
//        };
//        int row1 = 0;
//        int col1 = 4;
//        int p1 = 5;
//
//
//        print2DArray(floodFillDFS(img1, row1, col1, p1));

        int[][] heights = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };

        print2DArray(pacificAtlantic(heights));


    }

}
