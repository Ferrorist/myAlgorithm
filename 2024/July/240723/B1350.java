/*
 * https://www.acmicpc.net/problem/1350
 * 소요 시간 : 6분 22초
 */
import java.io.*;
class B1350 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        // 파일 개수 ( 1 <= N <= 50 )
        int N = Integer.parseInt(in.readLine());
        
        String[] input = in.readLine().split(" ");
        int cluster_size = Integer.parseInt(in.readLine());
        
        long answer = 0;
        for(String str : input){
            int file_size = Integer.parseInt(str);
            if(file_size == 0)    continue;
            
            int count = file_size / cluster_size;
            if(file_size % cluster_size > 0)    count+=1;
            
            answer += count * (long)cluster_size;
        }
        
        System.out.println(answer);
    }
}