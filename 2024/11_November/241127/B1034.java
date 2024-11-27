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
    private static int turnCount = 0, maxCount = Integer.MIN_VALUE;
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

        for (int turn = 0; turn <= turnCount && !queue.isEmpty(); turn++) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Situation current = queue.poll();
                if(!mapSet.contains(current)) {
                    mapSet.add(current);
                    setMaxCount(current);
                    turnOnSwitch(current, mapSet, queue);
                }
            }
        }
    }

    static void setMaxCount(Situation s) {
        int count = 0;
        int[] array = new int[s.map[0].length];
        for(int y = 0; y < s.map.length; y++) {
            System.arraycopy(s.map[y], 0, array, 0, s.map[y].length);
            Arrays.sort(array);
            if (Arrays.binarySearch(array, 0) < 0) {
                count++;
            }
        }

        maxCount = Math.max(maxCount, count);
    }

    static void turnOnSwitch(Situation s, Set<Situation> set, Queue<Situation> queue) {
        int[][] map = new int[s.map.length][s.map[0].length];
        arraycopy(s.map, map);

        for(int x = 0; x < map[0].length; x++) {
            turnOn(map, x);
            Situation situation = new Situation(map);
            if(!set.contains(situation)) {
                queue.offer(situation);
            }
            turnOn(map, x);
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
