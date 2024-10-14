/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/49993
 */
import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        int[] orders = new int[26];
        Arrays.fill(orders, -1);
        for(int i = 0; i < skill.length(); i++) {
            int idx = (int)(skill.charAt(i) - 'A');
            orders[idx] = i;
        }
        
        for(String tree: skill_trees) {
            boolean flag = true;
            int orderCount = 0;
            
            for(int i = 0; i < tree.length(); i++) {
                int skillIdx = (int)(tree.charAt(i) - 'A');
                if(orders[skillIdx] == -1)  continue;
                else if(orders[skillIdx] == orderCount) orderCount++;
                else {
                    flag = false;   break;
                }
            }
            if(flag)    answer++;
        }
        
        return answer;
    }
}