import java.util.*;
public class MultiplySparseMatrix{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        // System.out.println("Frist Matrix");
        String[] size = scanner.nextLine().split(" ");
        int row = Integer.parseInt(size[0]);
        int col = Integer.parseInt(size[1]);
        int[][] matrix = new int[row][col];
        for(int i = 0; i < row; i++){
            String[] temp = scanner.nextLine().split(" ");
            int abc = temp.length;
            int count = 1;
            for(int j = 0; j < abc; j++){
                if(count < abc){
                    String[] split_num = temp[count].split(":");
                    matrix[i][Integer.parseInt(split_num[0]) - 1] = Integer.parseInt(split_num[1]);
                    count++;
                } else{
                    break;
                }
            }
        }

        int[][] matrix2 = null;
        int col2 = 0;
        while (true) {
            // System.out.println("Second Matrix");
            String[] size2 = scanner.nextLine().split(" ");
            int row2 = Integer.parseInt(size2[0]);
            col2 = Integer.parseInt(size2[1]);
            
            if (row2 != col) {
                System.out.println("Matrix Col1 & Row2 Different!");
                continue;
            } 

            matrix2 = new int[row2][col2];

            for (int i = 0; i < row2; i++) {
                String[] temp = scanner.nextLine().split(" ");
                int count = 1;
                for (int j = 0; j < temp.length; j++) {
                    if (count < temp.length) {
                        String[] split_num = temp[count].split(":");
                        matrix2[i][Integer.parseInt(split_num[0]) - 1] = Integer.parseInt(split_num[1]);
                        count++;
                    } else {
                        break;
                    }
                }
            }
            break;
        }
        int[][] matrixRes = new int[row][col2];
        // System.out.println("Result Matrix:");
        System.out.println(row + " " + col2);
        for(int i = 0; i < row; i++){
            System.out.print((i + 1) + " ");
            for(int j = 0; j < col2; j++){
                for(int k = 0; k < col; k++){
                    matrixRes[i][j] += (matrix[i][k] * matrix2[k][j]);
                }
                if(matrixRes[i][j] != 0){
                    System.out.print((j + 1) + ":" + matrixRes[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}