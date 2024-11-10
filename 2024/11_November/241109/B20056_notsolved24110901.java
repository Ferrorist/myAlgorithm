/*
 * https://www.acmicpc.net/problem/20056
 * 소요 시간 : 1시간 초과
 */
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
public class B20056_notsolved24110901 {
    static class FireBall {
        private int y, x;
        private int weight, direction, speed;
        
        FireBall(int y, int x, int weight, int direction, int speed) {
            this.y = y;
            this.x = x;
            this.weight = weight;
            this.direction = direction;
            this.speed = speed;
        }

        @Override
        public String toString() {
            return "y: " + y + ", x: " + x + ", weight: " + weight + ", direction: " + direction + ", speed: " + speed;
        }
    }

    private static final int[][] dir = 
    {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int cmdCount = 0, mapLength = 0;
    private static Queue<FireBall> fireBalls = new ArrayDeque<>();
    private static Map<Integer, List<FireBall>> moveResultMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        inputArguments();
        System.out.println(solve());
    }

    private static void inputArguments() throws Exception {
        String[] input = in.readLine().split(" ");
        mapLength = Integer.parseInt(input[0]);
        int fireBallCount = Integer.parseInt(input[1]);
        cmdCount = Integer.parseInt(input[2]);

        fireBalls = new ArrayDeque<>();
        inputFireBalls(fireBallCount);
    }

    private static void inputFireBalls(int fireBallCount) throws Exception {
        for (int i = 0; i < fireBallCount; i++) {
            String[] input = in.readLine().split(" ");
            int y = Integer.parseInt(input[0]);
            int x = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);
            int speed = Integer.parseInt(input[3]);
            int direction = Integer.parseInt(input[4]);

            fireBalls.offer(new FireBall(y, x, weight, direction, speed));
        }
    }

    private static int solve() {
        for (int i = 0; i < cmdCount; i++) {
            moveFireBalls();
            checkMultiFireBalls();
            System.out.println("------------------------------------");
        }

        return getTotalWeight();
    }

    private static void moveFireBalls() {
        moveResultMap = new HashMap<>();
        while (!fireBalls.isEmpty()) {
            moveFireBall(fireBalls.poll());
        }
    }

    private static void moveFireBall(FireBall ball) {
        int cy = ball.y, cx = ball.x;
        int speed = ball.speed, direction = ball.direction;

        int dy = move(cy, dir[direction][0] * speed);
        int dx = move(cx, dir[direction][1] * speed);

        ball.y = dy; ball.x = dx;
        int idx = dy * mapLength + dx;
        List<FireBall> list = moveResultMap.getOrDefault(idx, new ArrayList<>());
        list.add(ball);
        moveResultMap.put(idx, list);
    }

    private static int move(int current, int speed) {
        int result = current + speed;
        return (result > mapLength) ? mapLength : (result < 1) ? 1 : result;
    }

    private static void checkMultiFireBalls() {
        for (Map.Entry<Integer, List<FireBall>> entry : moveResultMap.entrySet()) {
            List<FireBall> list = entry.getValue();
            if (list.size() > 1) {
                getDivideFireBalls(list);
            } else {
                System.out.println(list.get(0));
                fireBalls.offer(list.get(0));
            }
        }
    }

    private static void getDivideFireBalls(List<FireBall> list) {
        int y = list.get(0).y, x = list.get(0).x;
        int weight = 0, speed = 0;
        double doubledWeight = 0, doubledSpeed = 0;
        int startDirection = equalsOddsOrEvens(list) ? 0 : 1;

        for (FireBall ball : list) {
            doubledWeight += ball.weight;
            doubledSpeed += ball.speed;
        }

        weight = (int)Math.floor(doubledWeight / 5.0);
        speed = (int)Math.floor(doubledSpeed / list.size());
        
        if (weight > 0) {
            for(int i = 0; i < 4; i++) {
                FireBall dividedBall = new FireBall(y, x, weight, startDirection + (i * 2), speed);
                System.out.println(dividedBall);
                fireBalls.offer(dividedBall);
            }
        }
    }

    private static boolean equalsOddsOrEvens(List<FireBall> list) {
        int value = list.get(0).direction % 2;

        for (int i = 1; i < list.size(); i++) {
            if (value != list.get(i).direction % 2) {
                return false;
            }
        }

        return true;
    }

    private static int getTotalWeight() {
        int result = 0;
        System.out.println("[result]");
        while (!fireBalls.isEmpty()) {
            System.out.println(fireBalls.peek());
            result += fireBalls.poll().weight;
        }
        
        return result;
    }
}
