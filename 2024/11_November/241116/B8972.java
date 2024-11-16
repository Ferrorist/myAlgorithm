/*
 * https://www.acmicpc.net/problem/8972
 * 소요 시간 : 1시간 초과
 */
import java.io.*;
import java.util.*;

public class B8972 {

    static class Arudino implements Comparable<Arudino> {
        int y, x;

        Arudino(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setX(int x) {
            this.x = x;
        }

        @Override
        public int compareTo(Arudino o) {
            return this.hashCode() - o.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if(this == o)   return true;
            if(!(o instanceof Arudino)) return false;
            Arudino arudino = (Arudino) o;
            return this.hashCode() == arudino.hashCode();
        }

        @Override
        public int hashCode() {
            return this.y * lengthY + this.x;
        }
    }

    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static final char PLAYER = 'I', ENEMY = 'R', EMPTY = '.';
    private static final int[][] dir = 
        {{0, 0}, // 0번은 사용하지 않음.
        {1, -1}, {1, 0}, {1, 1}, {0, -1},
        {0, 0}, // 5번은 제자리
        {0, 1}, {-1, -1}, {-1, 0}, {-1, 1}};

    private static int lengthY = 0, lengthX = 0;
    private static Arudino playerArudino = null;
    private static List<Arudino> crazyArudinos = new ArrayList<>();
    private static String commends;
    private static int moveCount = 0;

    public static void main(String[] args) throws Exception {
        inputArguments();
        boolean result = solve();
        printResult(result);
    }

    static void inputArguments() throws Exception {
        initMap();
        initCommends();
    }

    static void initMap() throws Exception {
        String[] input = in.readLine().split(" ");

        lengthY = Integer.parseInt(input[0]);
        lengthX = Integer.parseInt(input[1]);

        inputMaps();
    }

    static void inputMaps() throws Exception {
        for (int y = 0; y < lengthY; y++) {
            String input = in.readLine();
            for (int x = 0; x < lengthX; x++) {
                char state = input.charAt(x);
                if(state != EMPTY) {
                    setArudino(y, x, state);
                }
            }
        }
    }

    static void setArudino(int y, int x, char state) {
        if(state != ENEMY) {
            playerArudino = new Arudino(y, x);
            return;
        }

        crazyArudinos.add(new Arudino(y, x));
    }

    static void initCommends() throws Exception {
        commends = in.readLine();
    }

    static boolean solve() {
        for (char cmdChar : commends.toCharArray()) {
            int cmd = (int)(cmdChar - '0');
            if(checkPlayerTurn(cmd) || checkCrazyArudinos()) {
                return false;
            }
            exploseArudinos(); // 5번 과정
        }

        return true;
    }

    static boolean checkPlayerTurn(int cmd) {
        movePlayer(cmd);        // 1번 과정
        return checkPlayer();   // 2번 과정
    }

    static void movePlayer(int cmd) {
        int cy = playerArudino.y, cx = playerArudino.x;
        cy += dir[cmd][0];  cx += dir[cmd][1];
        playerArudino.setY(cy);
        playerArudino.setX(cx);
        moveCount += 1;
    }

    static boolean checkPlayer() {
        Collections.sort(crazyArudinos);
        return Collections.binarySearch(crazyArudinos, playerArudino) >= 0;
    }

    static boolean checkCrazyArudinos() {
        for (Arudino arudino : crazyArudinos) {
            moveCrazyArudinos(arudino); // 3번 과정
        }
        return checkPlayer(); // 4번 과정
    }

    static void moveCrazyArudinos(Arudino arudino) {
        int minDistance = getInstance(arudino, playerArudino);
        int cy = arudino.y, cx = arudino.x;

        for(int i = 1; i < dir.length; i++) {
            int dy = cy + dir[i][0];
            int dx = cx + dir[i][1];
            int distance = getInstance(dy, playerArudino.y, dx, playerArudino.x);
            if(checkRange(dy, dx) && minDistance > distance) {
                arudino.setY(dy);
                arudino.setX(dx);
                minDistance = distance;
            }
        }
    }

    static void exploseArudinos() {
        Map<Arudino, Integer> hashMap = new HashMap<>();

        for (Arudino arudino : crazyArudinos) {
            hashMap.put(arudino, hashMap.getOrDefault(arudino, 0) + 1);
        }
        crazyArudinos.clear();
        for (Arudino arudino : hashMap.keySet()) {
            if(hashMap.get(arudino) == 1) {
                crazyArudinos.add(arudino);
            }
        }
    }

    static void printResult(boolean result) {
        if(!result) {
            System.out.println("kraj " + moveCount);
            return;
        }
        printMap();
    }

    static void printMap() {
        char[][] map = new char[lengthY][lengthX];
        createMap(map);
        
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                sb.append(map[y][x]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static void createMap(char[][] map) {
        for (int y = 0; y < map.length; y++) {
            Arrays.fill(map[y], EMPTY);
        }
        map[playerArudino.y][playerArudino.x] = PLAYER;
        for(Arudino arudino : crazyArudinos) {
            map[arudino.y][arudino.x] = ENEMY;
        }
    }

    static boolean checkRange(int y, int x) {
        return y >= 0 && x >= 0 && y < lengthY && x < lengthX;
    }

    static int getInstance(Arudino a, Arudino b) {
        return getInstance(a.y, b.y, a.x, b.x);
    }


    static int getInstance(int y1, int y2, int x1, int x2) {
        return Math.abs(y1 - y2) + Math.abs(x1 - x2);
    }
}