/*
 * https://www.acmicpc.net/problem/1202
 * 소요 시간 : 1시간 초과
 */
import java.io.*;
import java.util.*;
class B1202 {
    static class Jewelry implements Comparable<Jewelry> {
        int weight, value;

        public Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewelry o){
            if(o.weight == this.weight) return o.value - this.value;
            else return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        int N = Integer.parseInt(input[0]); // 보석 수
        int K = Integer.parseInt(input[1]); // 가방 수

        List<Jewelry> list = new ArrayList<>();

        // 각 보석에 대한 정보 입력
        for(int i = 0; i < N; i++){
            input = in.readLine().split(" ");
            int weight = Integer.parseInt(input[0]); // 보석의 무게
            int value = Integer.parseInt(input[1]); // 보석의 가격
            if(value <= 0) continue; 
            list.add(new Jewelry(weight, value));
        }
        Collections.sort(list);
        Queue<Jewelry> PQ = new PriorityQueue<>(list);

        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();

        // 각 가방의 최대 한도(무게) 입력
        for(int i = 0; i < K; i++){
            int limits = Integer.parseInt(in.readLine());
            if(!map.containsKey(limits)) {
                map.put(limits, new PriorityQueue<Integer>());
            }
            map.get(limits).offer(0);
        }

        Set<Integer> KeySet = map.keySet();

        while(!PQ.isEmpty()){
            Jewelry jewelry = PQ.poll();
            for(Integer i : KeySet){
                if(i < jewelry.weight) continue;
                
                if(map.get(i).peek() < jewelry.value){
                    Integer peek = map.get(i).poll();
                    map.get(i).offer(jewelry.value);
                    if(peek > 0){
                        PQ.offer(new Jewelry(i, peek));
                    }
                    break;
                }
            }
        }

        int answer = 0;
        for(Integer i : KeySet){
            PriorityQueue<Integer> MapQueue = map.get(i);
            if(MapQueue.peek() <= 0) continue;
            else{
                while(!MapQueue.isEmpty()){
                    answer += MapQueue.poll();
                }
            }
        }

        System.out.println(answer);
    }
}