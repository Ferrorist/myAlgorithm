/*
 * https://www.acmicpc.net/problem/1260
 */

import java.io.*;
import java.util.*;
public class B1260_2 {
    
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int start_point = -1;

    private static List<Integer>[] connectList;

    public static void main(String[] args) throws Exception {
        inputArguments();
        processDFS();
        processBFS();
    }

    private static void inputArguments() throws Exception {
        String[] inputs = in.readLine().split(" ");
        int node_count = Integer.parseInt(inputs[0]);
        int edge_count = Integer.parseInt(inputs[1]);
        start_point = Integer.parseInt(inputs[2]);

        initList(node_count, edge_count);
    }

    private static void initList(int node_count, int edge_count) throws Exception {
        connectList = new ArrayList[node_count+1];
        for(int i = 0; i <= node_count; i++) {
            connectList[i] = new ArrayList<>();
        }

        for(int i = 0; i < edge_count; i++) {
            String[] inputs = in.readLine().split(" ");
            int nodeA = Integer.parseInt(inputs[0]);
            int nodeB = Integer.parseInt(inputs[1]);
            connectList[nodeA].add(nodeB);
            connectList[nodeB].add(nodeA);
        }

        sortList();
    }

    private static void sortList() {
        for(int i = 0; i < connectList.length; i++) {
            Collections.sort(connectList[i]);
        }
    }

    private static void processDFS() {
        boolean[] visited = new boolean[connectList.length];
        StringBuilder sb = new StringBuilder();

        Stack<Integer> stack = new Stack<>();
        stack.push(start_point);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (!visited[current]) {
                visited[current] = true;
                sb.append(current).append(" ");
                for(int i = connectList[current].size() - 1; i >= 0; i--) {
                    int Node = connectList[current].get(i);
                    if (!visited[Node]) stack.push(Node);
                }
            }
        }

        System.out.println(sb.toString());
    }

    private static void processBFS() {
        boolean[] visited = new boolean[connectList.length];
        StringBuilder sb = new StringBuilder();

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start_point);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (!visited[current]) {
                visited[current] = true;
                sb.append(current).append(" ");
                for(int Node : connectList[current]) {
                    if(!visited[Node]) queue.offer(Node);
                }
            }
        }

        System.out.println(sb.toString());
    }
}
