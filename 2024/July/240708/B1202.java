/*
 * https://www.acmicpc.net/problem/1202
 * 소요 시간 : 58분 2초
 */
import java.io.*;
import java.util.*;
public class B1202 {
    static class Jewelry implements Comparable<Jewelry> {
        int weight;
        int value;

        public Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewelry o){
            if(this.weight == o.weight) return o.value - this.value;
            return this.weight - o.weight;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        int N = Integer.parseInt(input[0]); // 보석 개수
        int K = Integer.parseInt(input[1]); // 가방 개수

        Queue<Jewelry> queue = new PriorityQueue<>();
        for(int i = 0; i < N; i++){
            input = in.readLine().split(" ");
            int weight = Integer.parseInt(input[0]);
            int value = Integer.parseInt(input[1]);
            queue.offer(new Jewelry(weight, value));
        }

        
        Queue<Integer> bags = new PriorityQueue<>();
        for(int i = 0; i < K; i++)
            bags.offer(Integer.parseInt(in.readLine()));
        
        long sum = 0;
        Queue<Jewelry> tempQueue = new PriorityQueue<>(new Comparator<Jewelry>() {
            @Override
            public int compare(Jewelry o1, Jewelry o2){
                if(o1.value == o2.value) return o2.weight - o1.weight;
                return o2.value - o1.value;
            }
        });

        while(!bags.isEmpty()){
            int supply = bags.poll();
            while(!queue.isEmpty()){
                Jewelry jewelry = queue.peek();
                if(jewelry.weight <= supply) tempQueue.offer(queue.poll());
                else break;
            }

            while(!tempQueue.isEmpty()){
                Jewelry temp = tempQueue.poll();
                if(temp.weight <= supply) {
                    sum += (long)temp.value;    break;
                }
            }
        }

        System.out.println(sum);
    }
}
