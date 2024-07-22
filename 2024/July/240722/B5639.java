/*
 * https://www.acmicpc.net/problem/5639
 * 소요 시간 : 18분
 */
import java.io.*;
public class B5639 {
    static class Node {
        int value;
        Node left, right;

        public Node(int value){
            this.value = value;
            left = right = null;
        }
    }
    static StringBuilder sb;
    static Node root;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        root = null;
        while((input = in.readLine()) != null && !input.isEmpty()) {
            int value = Integer.parseInt(input);
            root = makeTree(root, value);
        }

        sb = new StringBuilder();
        Search(root);

        System.out.println(sb);
    }

    static Node makeTree(Node node, int value){
        if(node == null)    node = new Node(value);
        else if(node.value < value) node.right = makeTree(node.right, value);
        else node.left = makeTree(node.left, value);
        return node;
    }

    static void Search(Node node){
        if(node.left != null)   Search(node.left);
        if(node.right != null) Search(node.right);

        sb.append(node.value).append("\n");
    }
}