/*
 * https://www.acmicpc.net/problem/14890
 * 소요 시간 : 1시간 2분 24초
 */
import java.io.*;
import java.util.*;
public class B14890 {
    static class Step{
        int height;
        int length;

        Step(int height, int length) {
            this.height = height;
            this.length = length;
        }

        boolean canSetting(int value){
            return length >= value;
        }
    }

    static BufferedReader in;
    static int map_size, length;
    static List<Step[]> roadList;
    public static void main(String[] args) throws Exception {
        initReader();
        initArgument();
        System.out.println(solve());
    }

    static void initReader() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    static void initArgument() throws Exception {
        StringTokenizer st = new StringTokenizer(in.readLine());

        map_size = Integer.parseInt(st.nextToken());   // (2 <= N <= 100, 4 <= 총 길 개수 <= 200)
        length = Integer.parseInt(st.nextToken());   // (1 <= L <= N)

        roadList = new ArrayList<>(map_size * 2);
        int[][] map = new int[map_size][map_size];
        for(int y = 0; y < map_size; y++){
            st = new StringTokenizer(in.readLine());
            for(int x = 0; x < map_size; x++)   
                map[y][x] = Integer.parseInt(st.nextToken());
            
            if(checkHeight(map[y])) roadList.add(makeLengthArray(map[y]));
        }

        for(int x = 0; x < map_size; x++){
            int[] verticalRoad = new int[map_size];
            for(int y = 0; y < map_size; y++){
                verticalRoad[y] = map[y][x];
            }
            if(checkHeight(verticalRoad))   roadList.add(makeLengthArray(verticalRoad));
        }
    }

    static boolean checkHeight(int[] road) {
        for(int i = 0; i < map_size-1; i++){
            if(Math.abs(road[i] - road[i+1]) >= 2)  return false;
        }
        return true;
    }

    static Step[] makeLengthArray(int[] road){
        List<Step> list = new ArrayList<>();
        int current = road[0], curlength = 1;
        for(int i = 1; i < road.length; i++)   {
            if(current == road[i])  curlength++;
            else {
                list.add(new Step(current, curlength));
                current = road[i];  curlength = 1;    
            }

            if(i == road.length - 1)    list.add(new Step(current, curlength));
        }

        return list.toArray(new Step[list.size()]);
    }

    static int solve() {
        int answer = 0;
        Search:
        for(Step[] road : roadList){
            for(int i = 1; i < road.length; i++){
                if(road[i-1].height > road[i].height && road[i].canSetting(length)){
                    road[i].length -= length;
                }
                else if(road[i-1].height < road[i].height && road[i-1].canSetting(length)){
                    road[i-1].length -= length;
                }
                else continue Search;
            }
            answer++;
        }
        return answer;
    }
}
