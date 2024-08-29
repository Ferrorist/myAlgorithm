/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/131704
 * 소요 시간 : 25분
 */
import java.util.Stack;
class Solution {
    public int solution(int[] order) {
        int orderIdx = 0;
        Stack<Integer> subBelt = new Stack<>();

        search:
        for(int num = 1; num <= order.length && orderIdx < order.length; num++){
            boolean currentBool = false;
            if(num == order[orderIdx]) {
                orderIdx++; currentBool = true;
            }
            
            while(!subBelt.isEmpty() && subBelt.peek() == order[orderIdx]){
                orderIdx++; subBelt.pop();
                if(orderIdx == order.length)    break search;
            }
            
            if(!currentBool)    subBelt.push(num);
        }
        
        return orderIdx;
    }
}