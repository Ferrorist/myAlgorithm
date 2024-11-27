/*
 * https://www.acmicpc.net/problem/1034
 */
import java.io.*;
import java.util.*;

public class B1034 {

    static class Situation {
        int[][] map;

        Situation(int[][] map) {
            this.map = deepCopy(map);
        }

        private static int[][] deepCopy(int[][] original) {
            if (original == null) {
                return null;
            }

            int[][] copy = new int[original.length][];
            for(int y = 0; y < original.length; y++) {
                copy[y] = Arrays.copyOf(original[y], original[y].length);
            }

            return copy;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)    return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Situation situation = (Situation) obj;
            return this.hashCode() == situation.hashCode();
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(map);
        }
    }

    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int turnCount = 0, maxCount = 0;
    private static int[][] map;
    public static void main(String[] args) throws Exception {
        inputArguments();
        solve();
        System.out.println(maxCount);
    }

    static void inputArguments() throws Exception {
        String[] inputs = in.readLine().split(" ");
        int row = Integer.parseInt(inputs[0]);
        int col = Integer.parseInt(inputs[1]);

        map = new int[row][col];
        for (int y = 0; y < row; y++) {
            String input = in.readLine();
            for (int x = 0; x < col; x++) {
                map[y][x] = (int)(input.charAt(x) - '0');
            }
        }

        turnCount = Integer.parseInt(in.readLine());
    }

    static void solve() {
        Set<Situation> mapSet = new HashSet<>();
        Queue<Situation> queue = new ArrayDeque<>();
        queue.offer(new Situation(map));

        for (int turn = 0; turn < turnCount; turn++) {
            mapSet.clear();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Situation current = queue.poll();
                turnOnSwitch(current, queue);
            }
            mapSet = new HashSet<>(queue);
            queue = new ArrayDeque<>(mapSet);
        }

        while(!queue.isEmpty()) {
            setMaxCount(queue.poll());
        }
    }

    static void setMaxCount(Situation s) {
        int count = 0;
        for(int y = 0; y < s.map.length; y++) {
            Arrays.sort(s.map[y]);
            if (Arrays.binarySearch(s.map[y], 0) < 0) {
                count++;
            }
        }

        maxCount = Math.max(maxCount, count);
    }

    static void turnOnSwitch(Situation s, Queue<Situation> queue) {
        for(int x = 0; x < map[0].length; x++) {
            turnOn(s.map, x);
            queue.offer(new Situation(s.map));
            turnOn(s.map, x);
        }
    }

    static void turnOn(int[][] map, int col) {
        for (int y = 0; y < map.length; y++) {
            map[y][col] = (map[y][col] + 1) % 2;
        }
    }

    static void arraycopy(int[][] src, int[][] dest) {
        for (int y = 0; y < src.length; y++) {
            System.arraycopy(src[y], 0, dest[y], 0, src[y].length);
        }
    }
}
