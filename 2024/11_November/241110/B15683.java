/*
 * https://www.acmicpc.net/problem/15683
 * 소요 시간 : 1시간 초과
 */
import java.io.*;
import java.util.*;

public class B15683 {

    static class Camera implements Comparable<Camera> {
        int y, x;
        int type;

        Camera(int y, int x, int type) {
            this.y = y;
            this.x = x;
            this.type = type;
        }

        @Override
        public int compareTo(Camera o) {
            return o.type - this.type;
        }
    }

    private static final char EMPTY = '0', WALL = '6';
    private static final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static final int[] UP = dir[0], RIGHT = dir[1], DOWN = dir[2], LEFT = dir[3];

    private static List<List<int[]>[]> cameraLists = new ArrayList<>();
    private static int answer = Integer.MAX_VALUE;
    private static char[][] map;
    private static int[][] sightMap;

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static Queue<Camera> queue = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        inputArguments();
        setCameras();
        solve();
        System.out.println(answer);
    }

    private static void inputArguments() throws Exception {
        String[] input = in.readLine().split(" ");
        int row = Integer.parseInt(input[0]);
        int col = Integer.parseInt(input[1]);

        map = new char[row][col];
        sightMap = new int[row][col];
        inputMap(row, col);
    }

    private static void inputMap(int row, int col) throws Exception {
        for (int y = 0; y < row; y++) {
            char[] inputs = in.readLine().replaceAll(" ", "").toCharArray();
            for (int x = 0; x < col; x++) {
                map[y][x] = inputs[x];
                if (map[y][x] == WALL) {
                    sightMap[y][x] = -1;
                } else if (map[y][x] != EMPTY && map[y][x] != WALL) {
                    queue.offer(new Camera(y, x, (int)(map[y][x] - '0')));
                    sightMap[y][x] += 1;
                }
            }
        }
    }

    private static void setCameras() {
        cameraLists.add(null);
        cameraLists.add(new List[]{List.of(UP), List.of(RIGHT), List.of(DOWN), List.of(LEFT)});
        cameraLists.add(new List[]{List.of(UP, DOWN), List.of(LEFT, RIGHT)});
        cameraLists.add(new List[]{List.of(UP, RIGHT), List.of(RIGHT, DOWN), List.of(DOWN, LEFT), List.of(LEFT, UP)});
        cameraLists.add(new List[]{List.of(LEFT, UP, RIGHT), List.of(UP, RIGHT, DOWN), List.of(RIGHT, DOWN, LEFT), List.of(DOWN, LEFT, UP)});
        cameraLists.add(new List[]{List.of(UP, RIGHT, DOWN, LEFT)});
    }

    private static void solve() {
        while (!queue.isEmpty() && queue.peek().type == 5) {
            applyCamera5(queue.poll());
        }        
        
        List<Camera> list = new ArrayList<>(queue);
        DFS(list, 0);
    }

    private static void applyCamera5(Camera camera) {
        int cy = camera.y, cx = camera.x;
        for (int i = 0; i < 4; i++) {
            int dy = cy, dx = cx;
            while (true) {
                dy += dir[i][0];    dx += dir[i][1];
                if (checkRange(dy, dx) && map[dy][dx] != WALL) {    
                    sightMap[dy][dx]++;
                }
                else break;
            }
        }
    }

    private static void DFS(List<Camera> list, int idx) {
        if(idx >= list.size()) {
            answer = Math.min(answer, countEmptySight());
            return;
        }

        Camera camera = list.get(idx);
        List<int[]>[] sightList = cameraLists.get(camera.type);

        for(List<int[]> sights : sightList) {
            addSight(camera, sights);
            DFS(list, idx+1);
            removeSight(camera, sights);
        }
    }

    private static void addSight(Camera camera, List<int[]> sightList) {
        int cy = camera.y, cx = camera.x;
        for (int[] direction : sightList) {
            int dy = cy, dx = cx;
            while (true) {
                dy += direction[0]; dx += direction[1];
                if (checkRange(dy, dx) && map[dy][dx] != WALL) {
                    sightMap[dy][dx] += 1;
                }
                else break;
            }
        }
    }

    private static void removeSight(Camera camera, List<int[]> sightList) {
        int cy = camera.y, cx = camera.x;
        for (int[] direction : sightList) {
            int dy = cy, dx = cx;
            while (true) {
                dy += direction[0]; dx += direction[1];
                if (checkRange(dy, dx) && map[dy][dx] != WALL) {
                    sightMap[dy][dx] -= 1;
                }
                else break;
            }
        }
    }

    private static int countEmptySight() {
        int count = 0;

        for (int y = 0; y < sightMap.length; y++) {
            for (int x = 0; x < sightMap[0].length; x++) {
                if(sightMap[y][x] == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean checkRange(int y, int x) {
        return y >= 0 && x >= 0 && y < map.length && x < map[0].length;
    }
}
