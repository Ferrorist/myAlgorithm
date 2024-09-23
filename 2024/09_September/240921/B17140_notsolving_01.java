/*
 * https://www.acmicpc.net/problem/17140
 */
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
class B17140 {
    static final int MAX_COUNT = 100;
    static BufferedReader in;
    static int[][] map;
    static int r, c, k;
    public static void main(String[] args) throws Exception {
        initReader();
        initArguments();
        int answer = solve(r, c, k);

        System.out.println(answer);
    }

    private static void initReader() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    private static void initArguments() throws Exception {
        String[] input = in.readLine().split(" ");

        r = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);

        map = new int[3][3];
        for(int y = 0; y < 3; y++){
            input = in.readLine().split(" ");
            for(int x = 0; x < 3; x++)  map[y][x] = Integer.parseInt(input[x]);
        }
    }

    // y, x의 최소 입력값 : 1
    private static int solve(int y, int x, int k){
        int count = 0;
        while(count <= MAX_COUNT){
            if(map[y-1][x-1] == k)  
                return count;

            // 행의 개수(map.length) >= 열의 개수 (map[0].length) 인 경우, 
            if(map.length >= map[0].length) map = expandMatrix(map, true);
            else map = expandMatrix(map, false);

            count++;
        }

        return -1;
    }

    private static int[][] expandMatrix(int[][] map, boolean isRow){
        int[][] returnMap;
        Map<Integer, Integer>[] hashMap;
        int size = 1;

        if(isRow){
            hashMap = new HashMap[map.length];
            for(int i = 0; i < map.length; i++) hashMap[i] = new HashMap<>();
            
            for(int y = 0; y < map.length; y++){
                for(int x = 0; x < map[0].length; x++) hashMap[y].put(map[y][x], hashMap[y].getOrDefault(map[y][x], 0) + 1);
                size = Math.min(Math.max(size, hashMap[y].size()), 50);
            }
            returnMap = new int[map.length][size * 2];

            for(int y = 0; y < hashMap.length; y++){
                List<Map.Entry<Integer, Integer>> list = new ArrayList<>(hashMap[y].entrySet());
                list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
                    @Override
                    public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2){
                        if(o1.getValue() == o2.getValue()) return o1.getKey() - o2.getKey();
                        return o1.getValue() - o2.getValue();
                    }
                });
                for(int x = 0; (x < list.size() && x*2 + 1 < returnMap[0].length); x++){
                    returnMap[y][x*2] = list.get(x).getKey();
                    returnMap[y][x*2 + 1] = list.get(x).getValue();
                }
            }

        }
        else {
            hashMap = new HashMap[map[0].length];
            for(int i = 0; i < map[0].length; i++)  hashMap[i] = new HashMap<>();

            for(int x = 0; x < map[0].length; x++){
                for(int y = 0; y < map.length; y++) hashMap[x].put(map[y][x], hashMap[x].getOrDefault(map[y][x], 0) + 1);
                size = Math.min(Math.max(size, hashMap[x].size()), 50);
            }
            returnMap = new int[size * 2][map[0].length];

            for(int x = 0; x < hashMap.length; x++){
                List<Map.Entry<Integer, Integer>> list = new ArrayList<>(hashMap[x].entrySet());
                list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
                    @Override
                    public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2){
                        if(o1.getValue() == o2.getValue()) return o1.getKey() - o2.getKey();
                        return o1.getValue() - o2.getValue();
                    }
                });
                for(int y = 0; (y < list.size() && y*2 + 1 < returnMap.length); y++){
                    returnMap[y*2][x] = list.get(y).getKey();
                    returnMap[y*2 + 1][x] = list.get(y).getValue();
                }
            }
        }

        return returnMap;
    }
}