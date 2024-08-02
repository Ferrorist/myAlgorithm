/*
 * https://www.acmicpc.net/problem/1644
 * 소요 시간 : 21분 34초
 */
import java.io.*;
import java.util.*;
public class B1644 {
    static boolean[] isPrime;

    static void Eratos(int N) {
        for(int i = 2; i <= N; i++){
            if(!isPrime[i]) continue;
            for(int j = i*2; j <= N; j+=i){
                isPrime[j] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // ( 1 <= N <= 4_000_000)
        int N = Integer.parseInt(in.readLine());
        isPrime = new boolean[N+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        
        Eratos(N);

        List<Integer> list = new ArrayList<>();
        list.add(0);

        int sum = 0;
        for(int i = 2; i <= N; i++){
            if(isPrime[i]){
                sum += i;   list.add(sum);
            }
        }

        int start = 0, end = 1;
        int answer = 0;
        while(start <= end && end < list.size() && start < list.size()){
            int left = list.get(start);
            int right = list.get(end);

            int result = right - left;
            if(result == N) answer++;

            if(result < N) end++;
            else start++;
        }

        System.out.println(answer);
    }
}
