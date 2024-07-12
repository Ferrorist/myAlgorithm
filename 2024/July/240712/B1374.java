/*
 * https://www.acmicpc.net/problem/1374
 * 소요 시간 : 33분 55초
 */
import java.io.*;
import java.util.*;

public class B1374 {
    static class Class implements Comparable<Class> {
        int num;            // 강의 번호
        int start, end;     // 강의 시작 시간, 종료 시간

        public Class(int num, int start, int end){
            this.num = num;
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Class o){
            if(this.end == o.end) return this.start - o.start;
            return this.end - o.end;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        

        // (1 <= N <= 100,000)
        int N = Integer.parseInt(in.readLine()); // 강의 개수
        String[] input;

        Class[] classes = new Class[N];

        // 강의 입력
        for(int i = 0; i < N; i++){
            input = in.readLine().split(" ");
            int num = Integer.parseInt(input[0]); // 강의 번호
            int start = Integer.parseInt(input[1]); // 강의 시작 시간
            int end = Integer.parseInt(input[2]); // 강의 종료 시간
            // (0 <= start, end <= 1,000,000,000, start < end)
            classes[i] = new Class(num, start, end);
        }

        Arrays.sort(classes, new Comparator<Class>() {
            public int compare(Class o1, Class o2){
                if(o1.start == o2.start) return o1.end - o2.end;
                return o1.start - o2.start;
            }
        });

        Queue<Class> queue = new PriorityQueue<>();
        int answer = 0;
        for(int i = 0; i < N; i++){
            while(!queue.isEmpty() && classes[i].start >= queue.peek().end){
                queue.poll();
            } 
            queue.offer(classes[i]);
            answer = Math.max(answer, queue.size());
        }

        System.out.println(answer);
    }
}
