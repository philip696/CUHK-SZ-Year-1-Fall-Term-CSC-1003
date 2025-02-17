import java.util.*;

public class TestMathExpr {

    static Scanner input = new Scanner(System.in);

    // Here is the function you need to implement
    public static void parse_line(String s1, String s2, String s3) {
        int num1 = Integer.parseInt(s1);
        int num3 = Integer.parseInt(s3);
        int result = 0;
        if(num1 < 501 && num3 < 501 && num1 >= 0 && num3 >= 0){
            if(s2.equals("+")){ //Handle multiplication function if sign = +
                result = num1 + num3;
                System.out.println(result);
            }
            else if(s2.equals( "-")){ //Handle subtraction function if sign = -
                    result = num1 - num3;
                    System.out.println(result);
            }
            else if(s2.equals( "*")){ //Handle times function if sign = *
                    result = num1 * num3;
                    System.out.println(result);
            }
            else if(s2.equals( "/")){
                if(num3 == 0){  //Handle error if the divisor = 0
                    System.out.println("invalid");
                } else { //Handle the proccess of division if divisor other than 0
                    result = num1 / num3;
                    System.out.println(result);
                }      
        }
        }
    }
    public static void main(String[] args) throws Exception{
        int line_number = Integer.parseInt(input.nextLine());
        for (int i = 0; i < line_number; i++) {
            String s = input.nextLine();
            String[] t = s.split(" ");
            TestMathExpr.parse_line(t[0], t[1], t[2]);
        }
    }

}