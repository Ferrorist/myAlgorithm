/*
 * https://www.acmicpc.net/problem/13458
 */
import java.io.*;
public class B13458 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // 시험장의 개수
        int N = Integer.parseInt(in.readLine());

        int[] people = new int[N];
        String[] input = in.readLine().split(" ");
        
        for(int i = 0; i < N; i++)  people[i] = Integer.parseInt(input[i]);

        input = in.readLine().split(" ");
        int B = Integer.parseInt(input[0]); // 총감독관 감시 가능 인원 수
        int C = Integer.parseInt(input[1]); // 부감독관 감시 가능 인원 수
        
        System.out.println(solve(B,C,people));
    }

    static long solve(int B, int C, int[] people){
        long answer = 0;

        for(int p : people){
            p -= B;
            if(p <= 0) {
                answer += 1; continue;
            }
            else answer += (long)(1 + Math.ceil((double)p/C));
        }

        return answer;
    }
}

// 총감독관 한 시험장에서 감시할 수 있는 응시자 수 B명
// 부감독관 한 시험장에서 감시할 수 있는 응시자 수 C명
// 각 시험장에 총감독관은 오직 1명, 부감독관은 여러 명 있어도 됨.
// 각 시험장마다 응시생들을 모두 감시해야 함.
