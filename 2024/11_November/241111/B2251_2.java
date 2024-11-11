/*
 * https://www.acmicpc.net/problem/2251
 * 소요 시간 : 38분
 */
import java.io.*;
import java.util.*;

public class B2251_2 {
    
    static class Situation {
        int A, B, C;

        Situation(int A, int B, int C) {
            this.A = A;
            this.B = B;
            this.C = C;
        }

        Situation(Situation o) {
            this.A = o.A;
            this.B = o.B;
            this.C = o.C;
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if(!(o instanceof Situation)) return false;
            Situation situation = (Situation) o;
            return this.hashCode() == situation.hashCode();
        }

        @Override
        public int hashCode() {
            return this.A + this.B * 2_000 + this.C * 3_200_000;
        }

        public int getWater(int idx) {
            if(idx == 0)    return this.A;
            else if(idx == 1)   return this.B;
            else return this.C;
        }

        public void setWater(int idx, int value) {
            if(idx == 0)    this.A = value;
            else if(idx == 1)   this.B = value;
            else this.C = value;
        }

    }
    
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private static Set<Situation> set = new HashSet<>();
    private static Queue<Situation> queue = new ArrayDeque<>();
    private static Set<Integer> results = new HashSet<>();
    private static int[] limits = new int[3];

    public static void main(String[] args) throws Exception {
        inputArguments();
        setInitSituation();
        List<Integer> resultList = solve();
        printResult(resultList);
    }

    private static void inputArguments() throws Exception {
        String[] inputs = in.readLine().split(" ");
        int idx = 0;
        for(String input : inputs) {
            limits[idx++] = Integer.parseInt(input);
        }
    }

    private static void setInitSituation() {
        queue.offer(new Situation(0, 0, getLimit(2)));
    }

    private static List<Integer> solve() {
        BFS();
        List<Integer> list = new ArrayList<>(results);
        Collections.sort(list);
        return list;
    }

    private static void printResult(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for(Integer value : list){
            sb.append(value).append(" ");
        }

        System.out.println(sb.toString());
    }

    private static void BFS() {
        while(!queue.isEmpty()) {
            Situation current = queue.poll();
            if(!set.contains(current)){
                set.add(current);
                addResults(current);
                processBFS(current);
            }
        }
    }

    private static void addResults(Situation current) {
        if(current.getWater(0) == 0) {
            results.add(current.getWater(2));
        }
    }

    private static void processBFS(Situation current) {
        for(int start = 0; start < 3; start++) {
            for(int end = 0; end < 3; end++) {
                if(start == end) continue;
                Situation situation = process(current, start, end);
                if(!set.contains(situation)) {
                    queue.offer(situation);
                }
            }
        }
    }

    private static int getLimit(int idx) {
        return limits[idx];
    }

    private static Situation process(Situation current, int start, int end) {
        if(start == end)    return current;

        Situation situation = new Situation(current);
        pourWater(situation, start, end);
        return situation;
    }

    private static void pourWater(Situation current, int start, int end) {
        int limitEnd = getLimit(end);
        int value = Math.min(limitEnd - current.getWater(end), current.getWater(start));

        current.setWater(end, current.getWater(end) + value);        
        current.setWater(start, current.getWater(start) - value);
    }
}