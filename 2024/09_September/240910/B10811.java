import java.io.*;
import java.util.StringTokenizer;
class B10811 {
    static int[] buckets;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken()); // 바구니 개수 ( 1 <= N <= 100 )
        int M = Integer.parseInt(st.nextToken()); // 순서 변환 횟수 ( 1 <= M <= 100 )

        buckets = new int[N+1];
        for(int i = 1; i < buckets.length; i++) buckets[i] = i;

        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            swap(start, end);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < buckets.length; i++) sb.append(buckets[i]).append(" ");

        System.out.println(sb);
    }

    static void swap(int start, int end){
        while(start < end){
            int temp = buckets[end];
            buckets[end] = buckets[start];
            buckets[start] = temp;
            
            start += 1; end -= 1;
        }
    }
}