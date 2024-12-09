/*
 * https://www.acmicpc.net/problem/1068
 */

import java.io.*;
import java.util.*;

public class B1068 {

    static class Node {
        private int number;
        private List<Integer> childs;

        Node(int number) {
            this.number = number;
            childs = new ArrayList<>();
        }

        public void addChild(Node node) {
            this.childs.add(node.getNumber());
        }

        public int getNumber() {
            return this.number;
        }

        public List<Integer> getChilds() {
            return this.childs;
        }
    }
    

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int[] parents;
    private static Node[] nodes;
    private static int removeNode = -1;
    public static void main(String[] args) throws Exception {
        inputArguments();
        Node root = setTree();
        removeNode();
        int count = getLeafNodeCount(root);
        System.out.println(count);
    }

    private static void inputArguments() throws Exception {
        int nodeCount = Integer.parseInt(in.readLine());
        nodes = new Node[nodeCount];
        parents = new int[nodeCount];

        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < nodeCount; i++) {
            nodes[i] = new Node(i);
            parents[i] = Integer.parseInt(st.nextToken());
        }

        removeNode = Integer.parseInt(in.readLine());
    }

    private static Node setTree() {
        Node root = new Node(-1);

        for(int num = 0; num < parents.length; num++) {
            if (parents[num] < 0) {
                root.addChild(nodes[num]);
            } else {
                int parentNum = parents[num];
                nodes[parentNum].addChild(nodes[num]);
            }
        }

        return root;
    }

    private static void removeNode() {
        if(removeNode >= 0 && removeNode < nodes.length) {
            nodes[removeNode] = null;
        }
    }

    private static int getLeafNodeCount(Node root) {
        int count = 0;

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int childCount = 0;
            for (Integer child : node.getChilds()) {
                if(nodes[child] != null) {
                    queue.offer(nodes[child]);
                    childCount++;
                }
            }

            if (node.number >= 0 && childCount == 0) {
                count++;
            }
        }

        return count;
    }
}