/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/42842
 * 소요 시간 : 15분 17초 67
 */
class Solution {
    public int[] solution(int brown, int yellow) {
        int area = brown + yellow; // 카펫 전체 넓이 
        int sum = (brown + 4) / 2; // width + height
        
        int height = sum / 2, width = sum - height;
        while(height >= 1){
            if(width * height == area)  break;
            width += 1; height -= 1;
        }
        
        int[] answer = {width, height};
        return answer;
    }
}