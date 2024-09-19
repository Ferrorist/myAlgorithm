/*
 * https://www.acmicpc.net/problem/11650
 */
import java.io.*;
import java.util.*;
class B11650 {
    private static class Corr implements Comparable<Corr> {
        int x, y;

        Corr(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Corr o){
            if(this.x == o.x)   return this.y - o.y;
            return this.x - o.x;
        }

        public String toString() {
            return this.x + " " + this.y;
        }
    }

    static BufferedReader in;
    static Queue<Corr> queue;
    public static void main (String[] args) throws Exception {
        initReader();
        initArguments();
        solve();
    }

    private static void initReader() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    private static void initArguments() throws Exception {
        StringTokenizer st;
        queue = new PriorityQueue<>();

        int N = Integer.parseInt(in.readLine());

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            queue.offer(new Corr(x, y));            
        }
    }

    private static void solve() {
        StringBuilder sb = new StringBuilder();

        while(!queue.isEmpty()){
            sb.append(queue.poll().toString()).append("\n");
        }

        System.out.println(sb);
    }
}