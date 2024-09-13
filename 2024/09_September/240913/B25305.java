import java.io.*;
import java.util.*;
class B25305 {
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 응시자 수
        int target = Integer.parseInt(st.nextToken());
        
        Integer[] scores = new Integer[N];
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < N; i++)    scores[i] = Integer.parseInt(st.nextToken());
        
        Arrays.sort(scores, Collections.reverseOrder());
        
        System.out.println(scores[target-1]);
        
    }
}