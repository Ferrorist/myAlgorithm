class Solution {
    public static long DIV = 1234567;
    public long solution(int n) {
        long[] arr = new long[n+1];
        arr[0] = 1; arr[1] = 1;
        for(int i = 2; i <= n; i++){
            arr[i] = (arr[i-2] + arr[i-1]) % DIV;
        }
        
        return arr[n];
    }
}