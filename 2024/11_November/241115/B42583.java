/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/42583
 * 소요 시간 : 23분
 */
import java.util.*;

class Solution {
    
    int bridgeLength = 0, bridgeWeight = 0;
    int limitLength = 0, limitWeight = 0;
    
    Queue<Integer> bridge = new ArrayDeque<>();
    Queue<Integer> start = new ArrayDeque<>();
    Queue<Integer> finish = new ArrayDeque<>();

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        limitLength = bridge_length;   limitWeight = weight;
        
        initStart(truck_weights);
        while (finish.size() < truck_weights.length) {
            fillBridge();
            moveBridgeTruck();
            gotoBridge();
            fillBridge();
            answer++;
        }
        
        return answer;
    }
    
    private void initStart(int[] trucks) {
        start.clear();
        for(int truck : trucks) {
            start.offer(truck);  
        }
    }
    
    private void fillBridge() {
        while (bridge.size() < limitLength) {
            bridge.offer(0);
        }
    }
    
    private void moveBridgeTruck() {
        int truck = bridge.poll();
        if(truck > 0) {
            finish.offer(truck);
            bridgeWeight -= truck;
            bridgeLength -= 1;
        }
        
    }
     
    private void gotoBridge() {
        if(!start.isEmpty()) {
            int weight = start.peek();
            if(checkBridge(weight)) {
                bridgeLength += 1;
                bridgeWeight += weight;
                bridge.offer(start.poll());
            }
        }
    }
    
    private boolean checkBridge(int weight) {
        return bridgeLength + 1 <= limitLength && bridgeWeight + weight <= limitWeight;
    }
}