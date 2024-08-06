/*
 * https://www.acmicpc.net/problem/9251
 * 소요 시간: 14분 29초
 */
import java.io.*;
public class B9251_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String strA, strB;
        strA = " " + in.readLine();
        strB = " " + in.readLine();
        
        int[][] DP = new int[strA.length()][strB.length()];

        for(int i = 1; i < strA.length(); i++){
            for(int j = 1; j < strB.length(); j++){
                if(strA.charAt(i) == strB.charAt(j))
                    DP[i][j] = DP[i-1][j-1] + 1;
                else DP[i][j] = Math.max(DP[i-1][j], DP[i][j-1]);
            }
        }
        
        System.out.println(DP[strA.length()-1][strB.length()-1]);
    }
}