//6068: 시간 관리하기
import java.io.*;
import java.util.*;
 
public class Main {
    public static void main(String[] args) throws IOException {

        //입력
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        PriorityQueue<Node> work_list = new PriorityQueue<>((o1, o2) -> o1.end_time - o2.end_time);
        for(int i = 0; i < n ; i++) {
            String str = bf.readLine();
            StringTokenizer st = new StringTokenizer(str);
            int t = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            work_list.add(new Node(t, e));
        }
        //입력 끝

        int total_time = 0;
        int min_start_time = Integer.MAX_VALUE;
        boolean isDone = true;
        while(!work_list.isEmpty()) {
            Node current = work_list.poll();
            total_time += current.time;
            min_start_time = Math.min(min_start_time, current.end_time - total_time);
            if(total_time > current.end_time) {
                isDone = false;
                break;
            }
        }
        if(!isDone) System.out.println("-1");
        else System.out.println(min_start_time);
    }

    public static class Node {
        int time, end_time;

        public Node(int time, int end_time) {
            this.time = time;
            this.end_time = end_time;
        }
    }
}