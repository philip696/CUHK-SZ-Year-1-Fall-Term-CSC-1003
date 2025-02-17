import java.util.*;

public class RegionFill{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int rows = 20;
        int columns = 20;
        char[][] grid = new char[rows][columns];
        for (int i = 0; i < rows; i++) { //make input into 2d arr called grid
            String inputRow = scanner.nextLine();
            for (int j = 0; j < columns; j++) {
                if (inputRow.charAt(j) == '*' || inputRow.charAt(j) == '-') {
                    grid[i][j] = inputRow.charAt(j);
                } else {
                    System.out.println("Invalid character found. Only '*' and '-' are allowed.");
                    j--; 
                    continue;
                }
            }
        }

        String[] num = scanner.nextLine().split(", ");

        int rowStart = Integer.parseInt(num[0]);
        int colStart = Integer.parseInt(num[1]);
        paint(rowStart, colStart, grid);

        for (int i = 0; i < rows; i++) { //print grid
            for (int j = 0; j < columns; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }

        
    }

    public static void paint(int row, int col, char[][] grid){
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
            return; // out of the grid so return
        }

        if(grid[row][col] != '*'){ // if char is not * then it doesnt need to be filled, so return.
            return;
        }

        grid[row][col] = '-';

        paint(row - 1, col, grid); // Up
        paint(row + 1, col, grid); // Down
        paint(row, col - 1, grid); // Left
        paint(row, col + 1, grid); // Right
    }
}