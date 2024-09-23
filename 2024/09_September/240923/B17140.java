/*
 * https://www.acmicpc.net/problem/17140
 * 소요 시간 : 1시간 6분 21초
 */
import java.io.*;
import java.util.*;
public class B17140 {
    private static final int MAX_COUNT = 100, MAX_SIZE = 50, INIT_SIZE = 3;
    static BufferedReader in;
    static int[][] matrix;
    static int row, col, target;
    public static void main(String[] args) throws Exception {
        initReader();
        inputArguments();
        int answer = solve();
        System.out.println(answer);
    }    

    private static void initReader() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    private static void inputArguments() throws Exception {
        String[] input = in.readLine().split(" ");

        row = Integer.parseInt(input[0]) - 1;
        col = Integer.parseInt(input[1]) - 1;
        target = Integer.parseInt(input[2]);

        matrix = new int[INIT_SIZE][INIT_SIZE];
        for(int y = 0; y < INIT_SIZE; y++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int x = 0; x < INIT_SIZE; x++) matrix[y][x] = Integer.parseInt(st.nextToken());
        }
    }

    private static int solve() {
        for(int count = 0; count <= MAX_COUNT; count++){
            if(searchTarget(matrix))    return count;
            matrix = expendMatrix(matrix);
        }
        return -1;
    }

    private static boolean searchTarget(int[][] matrix){
        return matrix.length > row && matrix[0].length > col && matrix[row][col] == target;
    }

    private static int[][] expendMatrix(int[][] map) {
        int[][] returnMap;
        int max_size = 0;
        HashMap<Integer, Integer>[] hashMap;
        if(map.length >= map[0].length) { // 행의 개수 >= 열의 개수 : R 연산
            hashMap = new HashMap[map.length];
            for(int i = 0; i < map.length; i++) hashMap[i] = new HashMap<>();

            for(int y = 0; y < map.length; y++){
                for(int x = 0; x < map[0].length; x++) {
                    if(map[y][x] > 0)
                    hashMap[y].put(map[y][x], hashMap[y].getOrDefault(map[y][x], 0) + 1);
                }
                max_size = Math.min(Math.max(max_size, hashMap[y].size()), MAX_SIZE);
            }

            returnMap = new int[map.length][max_size * 2];
            for(int y = 0; y < hashMap.length; y++){
                List<Map.Entry<Integer, Integer>> list = new ArrayList<>(hashMap[y].entrySet());
                sortList(list);

                int idx = 0;
                for(Map.Entry<Integer, Integer> entry: list){
                    if(idx*2 + 1 >= returnMap[0].length)    break;

                    returnMap[y][2*idx] = entry.getKey();
                    returnMap[y][2*(idx++) + 1] = entry.getValue();
                }
            }
        }

        else { // 행의 개수 < 열의 개수 : C 연산
            hashMap = new HashMap[map[0].length];
            for(int i = 0; i < map[0].length; i++)  hashMap[i] = new HashMap<>();

            for(int x = 0; x < map[0].length; x++){
                for(int y = 0; y < map.length; y++){
                    if(map[y][x] > 0)
                    hashMap[x].put(map[y][x], hashMap[x].getOrDefault(map[y][x], 0) + 1);
                }
                max_size = Math.min(Math.max(max_size, hashMap[x].size()), MAX_SIZE);
            }

            returnMap = new int[max_size * 2][map[0].length];
            for(int x = 0; x < map[0].length; x++){
                List<Map.Entry<Integer, Integer>> list = new ArrayList<>(hashMap[x].entrySet());
                sortList(list);

                int idx = 0;
                for(Map.Entry<Integer, Integer> entry: list){
                    if(idx*2 + 1 >= returnMap.length)   break;

                    returnMap[2*idx][x] = entry.getKey();
                    returnMap[2*(idx++) + 1][x] = entry.getValue();
                }
            }
        }

        return returnMap;
    }

    private static void sortList(List<Map.Entry<Integer, Integer>> list) {
        list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2){
                if(o1.getValue() == o2.getValue()) return o1.getKey() - o2.getKey();
                else return o1.getValue() - o2.getValue();
            }
        });
    }
}