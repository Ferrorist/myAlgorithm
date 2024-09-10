/*
 * https://www.acmicpc.net/problem/10988
 */
import java.io.*;
public class B10988 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String input = in.readLine();

        int left = 0, right = input.length() - 1;
        while(left <= right){
            if(input.charAt(left++) != input.charAt(right--)){
                System.out.println(0);  return;
            }
        }

        System.out.println(1);
    }
}
