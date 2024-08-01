/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/49994
 * 기존에는 그냥 무식하게 풀었었는데, HashSet을 이용해서 문제를 해결하는 방법이 있었다...
 * 이를 적용한 코드이다.
 */
import java.util.*;
class Solution {
    // 1 <= dirs.length() <= 500
    public int solution(String dirs) {
        int answer = 0;

        Set<String> set = new HashSet<>();
        int cur_y = 5, cur_x = 5;
        
        char[] moves = dirs.toCharArray();
        for(char c : moves){
            int next_Y = cur_y, next_X = cur_x;
            switch(c){
                case 'U':
                    next_Y -= 1;
                    break;
                case 'D':
                    next_Y += 1;
                    break;
                case 'R':
                    next_X += 1;
                    break;
                case 'L':
                    next_X -= 1;
                    break;
            }

            if(next_Y < 0 || next_Y > 10 || next_X < 0 || next_X > 10)  continue;
            
            String path1 = cur_y + "" + cur_x + "" + next_Y + "" + next_X;
            String path2 = next_Y + "" + next_X + "" + cur_y + "" + cur_x;
            
            if(!(set.contains(path1) || set.contains(path2))){
                set.add(path1);
                set.add(path2);
                answer++;
            }
            cur_y = next_Y; cur_x = next_X;
        }
        
        return answer;
    }
}