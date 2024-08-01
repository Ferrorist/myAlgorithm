/*
 * https://softeer.ai/practice/6288
 * 소요 시간 : 12분 6초
 */
import java.io.*;
import java.util.*;

public class Main {
    static class Metal implements Comparable<Metal>{
        int weight;
        int price;

        public Metal(int weight, int price){
            this.weight = weight;
            this.price = price;
        }

        public int compareTo(Metal o){
            if(this.price == o.price) return this.weight - o.weight;
            else return o.price - this.price;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");

        int limit = Integer.parseInt(input[0]);
        int count = Integer.parseInt(input[1]);

        Queue<Metal> queue = new PriorityQueue<>();
        
        for(int i = 0; i < count; i++){
            input = in.readLine().split(" ");
            int weight = Integer.parseInt(input[0]); // 금속의 무게
            int price = Integer.parseInt(input[1]); // 무게당 가격
            Metal metal = new Metal(weight, price);
            queue.offer(metal);
        }

        int cweight = 0, cprice = 0;
        while(!queue.isEmpty()){
            Metal metal = queue.poll();
            if(limit >= cweight + metal.weight){
                cweight += metal.weight;
                cprice += metal.weight * metal.price;
            }
            else {
                int leftover = limit - cweight;
                cprice += leftover * metal.price;
                break;
            }
        }

        System.out.println(cprice);
    }
}