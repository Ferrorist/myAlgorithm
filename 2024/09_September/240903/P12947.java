/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/12947
 */
class Solution {
    public boolean solution(int x) {
        return x % getDivisor(x) == 0;
    }
    
    private int getDivisor(int x){
        String dividend = Integer.toString(x);
        int returnValue = 0;
        
        for(int i = 0; i < dividend.length(); i++){
            returnValue += (int)(dividend.charAt(i) - '0');
        }
        return returnValue;
    }
}