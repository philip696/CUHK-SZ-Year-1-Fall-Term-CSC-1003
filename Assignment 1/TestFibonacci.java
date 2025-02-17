import java.util.*;

public class TestFibonacci {
    static Scanner input = new Scanner(System.in);

    // Here is the function you need to implement
    public static void parse_line(int n, int d) {
        if(d > n){
            System.out.println("Error, Invalid Input!");
        } else {
            String[] Fibonacci = new String[n];
            int u = 0;
            int v = 1;
            Fibonacci[0] = "1";
            String[] Fibonaccix = new String[d];

            for(int l = 1; l < n; l++){ // For loop for the Fibonacci sequence
                int number = u + v;
                Fibonacci[l] = Integer.toString(number); 
                u = v;
                v = number;
            }

            for(int p = 0; p < d; p++){
               Fibonaccix[p] = Fibonacci[n - p - 1]; // for loop to print out the numbers in reverse
            }
            String FinalPrint = String.join(", ", Fibonaccix); 
            System.out.println(FinalPrint); //Print the compiled numbers
        }
    }

    public static void main(String[] args) throws Exception {
        int line_number = Integer.parseInt(input.nextLine());
        for (int i = 0; i < line_number; i++) {
            String s = input.nextLine();
            String[] t = s.split(", ");
            int n = Integer.parseInt(t[0]);
            int d = Integer.parseInt(t[1]);
            TestFibonacci.parse_line(n, d);
        }
    }
}