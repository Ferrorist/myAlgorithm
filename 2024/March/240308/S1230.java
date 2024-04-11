/*
 * [SWEA] 1230. [S/W 문제해결 기본] 8일차 - 암호문3
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14zIwqAHwCFAYD
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class S1230 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for(int t = 1; t <= 10; t++){
            sb.append("#").append(t).append(" ");

            in.readLine();
            List<String> list = new LinkedList<>(Arrays.asList(in.readLine().split(" ")));
            in.readLine();
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            int index, count;
            while(st.hasMoreTokens()){
                String cmd = st.nextToken();
                switch(cmd){
                    case "I":
                        index = Integer.parseInt(st.nextToken());
                        count = Integer.parseInt(st.nextToken());
                        for(int i = 0; i < count; i++){
                            list.add(index + i, st.nextToken());
                        }
                        break;
                    case "D":
                        index = Integer.parseInt(st.nextToken());
                        count = Integer.parseInt(st.nextToken());
                        list.remove(index);
                        break;
                    case "A":
                        count = Integer.parseInt(st.nextToken());
                        for(int i = 0; i < count; i++)
                            list.add(st.nextToken());
                        break;
                }
            }
            for(int i = 0; i < 10; i++) sb.append(list.get(i)).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
