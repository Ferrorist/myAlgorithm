/*
 * https://www.acmicpc.net/problem/15683
 */
import java.io.*;
import java.util.*;
class B15683_notsolving_01 {
    private static class Camera implements Comparable<Camera> {
        int y, x;
        int type;

        Camera(int y, int x, int type){
            this.y = y;
            this.x = x;
            this.type = type;
        }

        @Override
        public int compareTo(Camera o){
            return o.type - this.type;
        }
    }

    private static BufferedReader in;
    private static char EMPTY = '0', SIGHT = '#', WALL = '6';
    private static char[][] map;
    private static Queue<Camera> cameraQueue;
    private static int EMPTY_COUNT = 0;
    public static void main(String[] args) throws Exception {
        initReader();
        inputArguments();
        solve();
    }

    private static void initReader() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    private static void inputArguments() throws Exception {
        StringTokenizer st = new StringTokenizer(in.readLine());
        int col = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());

        map = new char[col][row];
        cameraQueue = new PriorityQueue<>();

        for(int y = 0; y < map.length; y++) {
            st = new StringTokenizer(in.readLine());
            for(int x = 0; x < map[0].length; x++) {
                map[y][x] = st.nextToken().charAt(0);
                if(map[y][x] == WALL)   continue;
                else if(map[y][x] == EMPTY) EMPTY_COUNT++;
                else {
                    cameraQueue.offer(new Camera(y, x, (int)(map[y][x] - '0')));
                }
            }
        }
    }

    private static void solve() {
        final int[] UP = {-1, 0}, DOWN = {1, 0}, LEFT = {0, -1}, RIGHT = {0, 1}; // 방향
        final int[][][][] CameraDir = {
            {{{}}},
            {{UP}, {RIGHT}, {DOWN}, {LEFT}}, // 1번 카메라 종류
            {{UP, DOWN}, {RIGHT, LEFT}}, // 2번 카메라 종류
            {{UP, RIGHT}, {RIGHT, DOWN}, {DOWN, LEFT}, {LEFT, UP}}, // 3번 카메라 종류
            {{UP, RIGHT, DOWN}, {RIGHT, DOWN, LEFT}, {UP, DOWN, LEFT}, {UP, RIGHT, LEFT}}, // 4번 카메라 종류
            {{UP, RIGHT, DOWN, LEFT}} // 5번 카메라 종류
        };

        // CameraDir[3] : 3번 카메라
        // CameraDir[3][2] : 3번 카메라의 2번쨰 종류
        // CameraDir[3][2][1] : 3번 카메라의 2번째 종류의 1번째 방향
        // CameraDir[3][2][1][0] : 3번 카메라의 2번째 종류의 1번째 방향의 y 이동방향

        boolean[][] visited = new boolean[map.length][map[0].length];
        while(!cameraQueue.isEmpty() && cameraQueue.peek().type == 5){
            Camera camera = cameraQueue.poll();
            applyCamera(camera, visited, CameraDir, 0);
            checkZero();
        }

        while(!cameraQueue.isEmpty()){
            Camera camera = cameraQueue.poll();
            int max_type = -1, max = -1;
            for(int i = 0; i < CameraDir[camera.type].length; i++){
                int count = getEmptyCount(camera, visited, CameraDir, i);
                if(max < count) {
                    max_type = i;   max = count;
                }
            }

            applyCamera(camera, visited, CameraDir, max_type);
            checkZero();
        }

        System.out.println(EMPTY_COUNT);
    }

    private static int getEmptyCount(Camera camera, boolean[][] visited, int[][][][] CameraDir, int kind) {
        int value = 0;
        int type = camera.type;
        for(int d = 0; d < CameraDir[type][kind].length; d++){
            int dy = camera.y, dx = camera.x;
            while(true){
                dy += CameraDir[type][kind][d][0];    dx += CameraDir[type][kind][d][1];

                if(dy < 0 || dy >= map.length || dx < 0 || dx >= map[0].length) break;

                if(map[dy][dx] == WALL) break;
                else if(map[dy][dx] == EMPTY) value++;
            }
        }
        return value;
    }

    private static void applyCamera(Camera camera, boolean[][] visited, int[][][][] CameraDir, int kind) {
        visited[camera.y][camera.x] = true;
        int type = camera.type;
        for(int d = 0; d < CameraDir[type][kind].length; d++){
            int dy = camera.y, dx = camera.x;
            while(true){
                dy += CameraDir[type][kind][d][0];    dx += CameraDir[type][kind][d][1];

                if(dy < 0 || dy >= map.length || dx < 0 || dx >= map[0].length) break;

                if(map[dy][dx] == WALL) break;
                else if(visited[dy][dx])    continue;
                
                visited[dy][dx] = true;
                if(map[dy][dx] == EMPTY) {
                    map[dy][dx] = SIGHT;
                    EMPTY_COUNT--;
                }
            }
        }
    }

    private static void checkZero() {
        if(EMPTY_COUNT == 0){
            System.out.println(EMPTY_COUNT);    System.exit(0);
        }
    }
}
