/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/92341
 * 소요 시간 : 26분 20초
 */
import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> parking_lot = new HashMap<>(); // 현재 주차중인 차
        Map<String, Integer> costs = new HashMap<>(); // 주차 내역 (당일 주차 시간(분))
        
        for(String record : records){
            // input[0] : 시간, input[1]: 차 번호, input[2] : 입/출차
            String[] input = record.split(" ");
            int time = getMinute(input[0]);
            
            // 입차인 경우
            if("IN".equals(input[2])){
                parking_lot.put(input[1], time);
            }
            // 출차인 경우
            else {
                int saved = costs.getOrDefault(input[1], 0);
                int parking_time = parking_lot.get(input[1]);
                costs.put(input[1], saved + (time - parking_time));
                parking_lot.remove(input[1]);
            }
        }
        
        int deadline = getMinute("23:59");
        
        for(Map.Entry<String, Integer> entry : parking_lot.entrySet()){
            int saved = costs.getOrDefault(entry.getKey(), 0);
            int parking_time = entry.getValue();
            costs.put(entry.getKey(), saved + (deadline - parking_time));
        }
        
        String[] keys = costs.keySet().toArray(new String[costs.size()]);
        int[] answer = new int[costs.size()];
        Arrays.sort(keys);
        
        int idx = 0;
        for(String key : keys){
            int time = costs.get(key);            
            int cost = fees[1];
            if(time >= fees[0]){
                cost += Math.ceil((time - fees[0]) / (double)fees[2]) * fees[3];
            }
            answer[idx++] = cost;
        }
                
        return answer;
    }
    
    private int getMinute(String time){
        int hour = Integer.parseInt(time.split(":")[0]);
        int minute = Integer.parseInt(time.split(":")[1]);
        
        return hour * 60 + minute;
    }
}