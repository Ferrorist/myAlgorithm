import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class B1005 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        List<Integer>[] lists;
        int Testcase = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        while(Testcase-- > 0){
            String[] input = in.readLine().split(" ");
            int N = Integer.parseInt(input[0]), K = Integer.parseInt(input[1]);
            lists = new ArrayList[N+1];
            int[] buildTimes = new int[N+1];
            int[] totalTimes = new int[N+1];
            boolean[] visited = new boolean[N+1];
            int[] linked_count = new int[N+1];
            input = in.readLine().split(" ");
            for(int i = 1; i <= N; i++) {
                buildTimes[i] = Integer.parseInt(input[i-1]);
                lists[i] = new ArrayList<>();
            }
            for(int i = 0; i < K; i++){
                input = in.readLine().split(" ");
                int from = Integer.parseInt(input[0]);
                int to = Integer.parseInt(input[1]);
                lists[from].add(to);
                linked_count[to]++;
            }
            Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1];
                }
            });
            for(int i = 1; i <= N; i++) queue.offer(new int[] {i, linked_count[i]});
            int target = Integer.parseInt(in.readLine());

            while(!queue.isEmpty()){
                int[] current = queue.poll();
                int num = current[0];
                if(visited[num])    continue;
                totalTimes[num] += buildTimes[num];
                visited[num] = true;
                int listSize = lists[num].size();
                for(int i = 0; i < listSize; i++){
                    int to = lists[num].get(i);
                    totalTimes[to] = Math.max(totalTimes[num], totalTimes[to]);
                    linked_count[to]--;
                    queue.offer(new int[]{to, linked_count[to]});
                }
                if(num == target)   break;
            }
            sb.append(totalTimes[target]).append("\n");
        }
        System.out.println(sb);
    }
}
