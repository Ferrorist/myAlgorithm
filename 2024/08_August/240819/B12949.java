class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];
        
        for(int y = 0; y < arr1.length; y++){
            for(int x = 0; x < arr2[0].length; x++){
                int sum = 0;
                for(int k = 0; k < arr1[0].length; k++){
                    sum += (arr1[y][k] * arr2[k][x]);
                }
                answer[y][x] = sum;
            }
        }
        
        return answer;
    }
}