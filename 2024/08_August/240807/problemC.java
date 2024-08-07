/*
 * https://codeforces.com/contest/1999/problem/C
 */
import java.io.*;
import java.util.*;
public class problemC {
    static class Line implements Comparable<Line>{
        int start;
        int end;

        public Line(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line o){
            if(this.start == o.start)   return this.end - o.end;
            return this.start - o.start;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int Testcase = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        while(Testcase-- > 0){
            String[] input = in.readLine().split(" ");
            
            // (1 <= n <= 2 * 10^5)
            int n = Integer.parseInt(input[0]);

            // ( 1 <= s, m <= 10^9)
            int s = Integer.parseInt(input[1]);
            int m = Integer.parseInt(input[2]);

            List<Line> list = new ArrayList<>();

            for(int i = 0; i < n; i++){
                input = in.readLine().split(" ");
                int left = Integer.parseInt(input[0]);
                int right = Integer.parseInt(input[1]);
                list.add(new Line(left, right));
            }
            Collections.sort(list);
            
            int spot = 0;
            boolean canShower = false;
            for(Line line : list){
                if(line.start - spot >= s){
                    canShower = true;
                }
                spot = line.end;
            }

            if(!canShower && m - spot >= s) canShower = true;

            if(canShower)   sb.append("YES");
            else sb.append("NO");

            sb.append("\n");
        }
        System.out.println(sb);
    }
}