//13418: 학교 탐방하기
import java.io.*;
import java.util.*;
 
public class Main {

    static int n, m;
    static ArrayList<Node>[] list;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {

        //입력
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String str = bf.readLine();
        StringTokenizer st = new StringTokenizer(str);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < m + 1; i++) {
            str = bf.readLine();
            st = new StringTokenizer(str);
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int road = Integer.parseInt(st.nextToken());
            // if(road == 1) {
            //     list[s].add(new Node(e, road));
            //     list[e].add(new Node(s, 0));
            // } else {
            //     list[s].add(new Node(e, road));
            //     list[e].add(new Node(s, 1));
            // }
            list[s].add(new Node(e, road));
            list[e].add(new Node(s, road)); //?!?!?!?!?! 왜맞??!??!?!
        }
        //입력 끝

        pq = new PriorityQueue<>((o1, o2) -> o1.road - o2.road);
        int worst_cost =  (int) Math.pow(find_mst(0), 2); // 최악의 피로도 계산 

        pq = new PriorityQueue<>((o1, o2) -> o2.road - o1.road);
        int best_cost = (int) Math.pow(find_mst(0), 2); //최소의 피로도 계산
        System.out.println(worst_cost - best_cost);
    }   

    public static int find_mst(int start) {
        boolean[] visited = new boolean[n + 1];
        pq.offer(new Node(start, -1));

        int uphill_count = 0;
        while(!pq.isEmpty()) {
            Node current = pq.poll();

            if(!visited[current.end]) visited[current.end] = true;
            else continue; 

            if(current.road == 0) uphill_count++;
            for(int i = 0; i < list[current.end].size(); i++) {
                Node next = list[current.end].get(i);
                pq.offer(next);
            }
        }
        return uphill_count;
    }

    public static class Node {
        int end, road;

        public Node(int end, int road) {
            this.end = end;
            this.road = road;
        }
    }
}