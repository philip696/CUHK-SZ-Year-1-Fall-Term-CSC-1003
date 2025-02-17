// import java.util.*;
// public class MatrixMultiplication{
//     public static void main(String[] args){
//         Scanner scanner = new Scanner(System.in);
//         String[] n = scanner.nextLine().split(" ");
//         int[][] matrix_result = f(Integer.parseInt(n[0]), Integer.parseInt(n[1]), Integer.parseInt(n[2]), scanner);

//         for(int i = 0; i < Integer.parseInt(n[0]); i++){
//             for(int j = 0; j < Integer.parseInt(n[2]); j++){
//                 System.out.print(matrix_result[i][j] + " ");
//             }
//             System.out.println();
//         }
//     }
// }
// public static int[][] f(int m, int n, int p, Scanner scanner){
//     int matrix1[][] = new int[m][n];
//     int matrix2[][] = new int[n][p];
//     int matrix_result[][] = new int[m][p];


    
//     for(int i = 0; i < m; i++){
//         String[] input = scanner.nextLine().split(" ");
//         for(int j = 0; j < n; j++){
//             matrix1[i][j] = Integer.parseInt(input[j]);
//         }
//     }
//     for(int i = 0; i < n; i++){
//         String[] input = scanner.nextLine().split(" ");
//         for(int j = 0; j < p; j++){
//             matrix2[i][j] = Integer.parseInt(input[j]); 
//         }
//     }
//     for(int i = 0; i < m; i++){
//         for(int j = 0; j < p; j++){
//             for(int k = 0; k < n; k++){
//                 matrix_result[i][j] += matrix1[i][k] * matrix2[k][j]; 
//             }
//         }
//     }
//     return matrix_result;
// }
        





import java.util.*;
public class MatrixMultiplication{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String[] no = scanner.nextLine().split(" ");
        int m = Integer.parseInt(no[0]);
        int n = Integer.parseInt(no[1]);
        int p = Integer.parseInt(no[2]);
        
        int matrix1[][] = new int[m][n];
        int matrix2[][] = new int[n][p];
        int matrix_result[][] = new int[m][p];
    
    
        
        for(int i = 0; i < m; i++){
            String[] input = scanner.nextLine().split(" ");
            for(int j = 0; j < n; j++){
                matrix1[i][j] = Integer.parseInt(input[j]);
            }
        }
        for(int i = 0; i < n; i++){
            String[] input = scanner.nextLine().split(" ");
            for(int j = 0; j < p; j++){
                matrix2[i][j] = Integer.parseInt(input[j]); 
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < p; j++){
                for(int k = 0; k < n; k++){
                    matrix_result[i][j] += matrix1[i][k] * matrix2[k][j]; 
                }
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < p; j++){
                System.out.print(matrix_result[i][j]);
            }
            System.out.println();
        }

    }
}


    