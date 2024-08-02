/*
 * https://www.acmicpc.net/problem/5670
 * 소요 시간 : 41분 56초
 */
import java.io.*;
import java.util.*;
public class B5670 {
    static class Node {
        Node[] childs = new Node[26];
        boolean isLeaf = false;
        int child_count = 0;
        char value;

        public Node(char value){
            this.value = value;
        }

        void insert(String str, int index){
            if(str.length() == index) {
                this.isLeaf = true; return;
            }
            char curChar = str.charAt(index);
            int curCharIdx = curChar - 'a';
            if(this.childs[curCharIdx] == null){
                this.childs[curCharIdx] = new Node(curChar);
                this.child_count += 1;
            }

            this.childs[curCharIdx].insert(str, index+1);
        }

        int searchCount(String str, int index, int count){
            if(str.length() == index) return count;

            char curChar = str.charAt(index);
            int curCharIdx = curChar - 'a';

            if(this.child_count > 1 || this.isLeaf)
                return this.childs[curCharIdx].searchCount(str, index+1, count+1);

            else return this.childs[curCharIdx].searchCount(str, index+1, count);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = "";

        Node root;
        while((input = in.readLine()) != null && !input.isEmpty()){
            int N = Integer.parseInt(input); // 단어의 개수 ( 1 <= N <= 100_000 )

            String[] words = new String[N];
            for(int i = 0; i < N; i++) words[i] = in.readLine();

            Arrays.sort(words);
            root = new Node(' ');

            // trie 완성시키기
            for(String word : words){
                root.insert(word, 0);
            }

            // 검색
            int sum = 0;
            for(String word : words){
                char startChar = word.charAt(0);
                int StartCharIdx = startChar - 'a';

                sum += root.childs[StartCharIdx].searchCount(word, 1, 1);
            }

            sb.append(String.format("%.2f", (double) sum / N)).append("\n");
        }
        System.out.println(sb);
    }
}