/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/42885
 * 소요 시간 : 약 42분
 * 풀이 힌트 확인한 문제.
 */
import java.util.Arrays;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int left = 0, right = people.length - 1;
        while(left <= right){
            if(left != right && people[left] + people[right] <= limit){
                left++;
            }
            right--;    answer++;
        }
        return answer;
    }
}