import java.io.*;
public class B9251 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // A, B 문자열 : 최대 1_000글자
        String A = in.readLine();
        String B = in.readLine();

        int[][] DP = new int[A.length() + 1][B.length() + 1];
        

        int answer = 0;
        for(int a = 1; a <= A.length(); a++){
            char charA = A.charAt(a-1);
            for(int b = 1; b <= B.length(); b++){
                char charB = B.charAt(b-1);
                if(charA == charB){
                    DP[a][b] = DP[a-1][b-1] + 1;
                }
                else DP[a][b] = Math.max(DP[a-1][b], DP[a][b-1]);

                answer = Math.max(answer, DP[a][b]);
            }
        }
        System.out.println(answer);
    }
}
