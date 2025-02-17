import java.util.*;

public class Queens {
    
    public static int countWaysToPlaceQueens(int m, int n, int p) {
        // If we have more queens than rows or columns, it's impossible to place them
        if (p > m || p > n) {
            return 0;
        }
        
        Set<Integer> columns = new HashSet();  // To track used columns
        Set<Integer> diag1 = new HashSet();    // To track primary diagonals (row - col)
        Set<Integer> diag2 = new HashSet();    // To track secondary diagonals (row + col)
        
        return backtrack(m, n, p, 0, columns, diag1, diag2);
    }
    
    private static int backtrack(int m, int n, int p, int row, Set<Integer> columns, Set<Integer> diag1, Set<Integer> diag2) {
        // If we've placed p queens, it's a valid configuration
        if (p == 0) {
            return 1;
        }

        // If we've reached the end of the rows, stop
        if (row == m) {
            return 0;
        }

        int ways = 0;

        // Try placing a queen in each column of the current row
        for (int col = 0; col < n; col++) {
            if (!columns.contains(col) && !diag1.contains(row - col) && !diag2.contains(row + col)) {
                // Place the queen
                columns.add(col);
                diag1.add(row - col);
                diag2.add(row + col);

                // Recur to place the next queen in the next row, with p-1 queens left
                ways += backtrack(m, n, p - 1, row + 1, columns, diag1, diag2);

                // Backtrack: remove the queen
                columns.remove(col);
                diag1.remove(row - col);
                diag2.remove(row + col);
            }
        }
        
        // Optionally, skip placing a queen in the current row (this is allowed)
        ways += backtrack(m, n, p, row + 1, columns, diag1, diag2);

        return ways;
    }

    public static void main(String[] args) {
        // Input: m rows, n columns, p queens
        Scanner scanner = new Scanner(System.in);
        String[] lineInput = scanner.nextLine().split(" ");
        int m = Integer.parseInt(lineInput[0]);
        int n = Integer.parseInt(lineInput[1]);
        int p = Integer.parseInt(lineInput[2]);
        
        int result = countWaysToPlaceQueens(m, n, p);
        System.out.println("Number of ways to place " + p + " queens on a " + m + "x" + n + " board: " + result);
    }
}
