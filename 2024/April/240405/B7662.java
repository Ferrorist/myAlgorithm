import java.io.*;
import java.util.*;

class B7662 {
    static HashMap<Integer, Integer> map;
	public static void main(String args[]) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int Testcase = Integer.parseInt(in.readLine());
		while(Testcase-- > 0){
			int cmd_count = Integer.parseInt(in.readLine());
            map = new HashMap<>();
            Queue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
            Queue<Integer> minQueue = new PriorityQueue<>();
            for(int cmdNum = 0; cmdNum < cmd_count; cmdNum++){
                String[] input = in.readLine().split(" ");
                char cmd = input[0].charAt(0);
                int value = Integer.parseInt(input[1]);
                switch(cmd){
                    case 'I':   // Insert
                        map.put(value, map.getOrDefault(value, 0) + 1);
                        minQueue.offer(value);
                        maxQueue.offer(value);
                        break;
                    
                    case 'D':   // Delete
                        if(value == 1){ // 최댓값 삭제
                            while(!maxQueue.isEmpty()){
                                int k = maxQueue.poll();
                                int count = map.getOrDefault(k, 0);
                                if(count < 1) continue;
                                
                                map.put(k, count-1);    
                                if(map.get(k) == 0)	map.remove(k);
                                break;
                            }
                        }
                        else { // 최솟값 삭제
                            while(!minQueue.isEmpty()){
                                int k = minQueue.poll();
                                int count = map.getOrDefault(k, 0);
                                if(count < 1)   continue;
                                
                                map.put(k, count-1);
                                if(map.get(k) == 0)	map.remove(k);
                                break;
                            }
                        }
                        break;
                }
            }
            if(map.size() == 0)    answer.append("EMPTY");
            else{
                int max, min;
                max = min = 0;
                
                while(!maxQueue.isEmpty()){
                    int k = maxQueue.poll();
                    int count = map.getOrDefault(k, 0);
                    if(count < 1)   continue;
                    max = k;    break;
                }

                while(!minQueue.isEmpty()){
                    int k = minQueue.poll();
                    int count = map.getOrDefault(k, 0);
                    if(count < 1)   continue;
                    min = k;    break;
                }

                answer.append(max).append(" ").append(min);
            }

            answer.append("\n");
		}
        System.out.println(answer);
	}
}