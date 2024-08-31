/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/17687
 */
class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        int size = m * (t-1) + p;
        
        String gameBoard = getGameBoard(size, n);
        int index = p-1;
        while(answer.toString().length() < t){
            answer.append(gameBoard.charAt(index));
            index += m;
        }
        
        return answer.toString();
    }
    
    private String convertNumber(int num, int n){
        // Integer.toString(a, b) : 10진수 a를 n진수로 변환한 결과를 String으로 return
        return Integer.toString(num, n).toUpperCase();
    }
    
    private String getGameBoard(int size, int n){
        StringBuilder sb = new StringBuilder();
        
        int num = 0;
        while(sb.toString().length() < size){
            String convert = convertNumber(num++, n);
            sb.append(convert);
        }
        
        return sb.toString();
    }
}