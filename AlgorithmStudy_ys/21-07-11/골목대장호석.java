import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class 골목대장호석 {

    static class Node{
        int a;
        int cost;


        public Node(int a, int cost) {
            super();
            this.a = a;
            this.cost = cost;
        }


    }


    static ArrayList<Node> list[];
    static int cost[];
    static int n;
    static boolean visited[];
    static int min = Integer.MAX_VALUE;
    static int x = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        visited = new boolean [n+1];
        list = new ArrayList[n+1];
        cost = new int[n+1];

        for(int i = 1 ; i<=n ;i++) {
            list[i] = new ArrayList<Node>();
        }


        for(int i = 0 ; i < m ;i++) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());


            list[d].add(new Node(e, f));
            list[e].add(new Node(d,f));

        }

//		dijkstra(a, b, c);


        visited[a]= true;
        dfs(a,b,c,0);

        if(min == Integer.MAX_VALUE) {
            System.out.println(-1);
        }else {

            System.out.println(min);
        }
    }


    public static void dfs(int start,int end,int money,int maxCost) {

        if(start == end) {
            min = Math.min(maxCost,min);
            return ;
        }

        for(int i = 0 ; i < list[start].size();i++) {
            if(money < list[start].get(i).cost)continue;

            if(!visited[list[start].get(i).a]) {
                visited[list[start].get(i).a] = true;
                dfs(list[start].get(i).a , end , money-list[start].get(i).cost ,Math.max(maxCost,list[start].get(i).cost));
                visited[list[start].get(i).a] = false;
            }
        }


    }



//	public static void dijkstra(int start,int end,int don) {
//		Arrays.fill(cost, Integer.MAX_VALUE);
//
//		PriorityQueue<Node> pq = new  PriorityQueue<Node>(new Comparator<Node>() {
//
//			@Override
//			public int compare(Node o1, Node o2) {
//				// TODO Auto-generated method stub
//				return Integer.compare(o1.cost, o2.cost);
//			}
//		});
//		pq.add(new Node(start, 0));
//		cost[start] = 0;
//
//
//		while(!pq.isEmpty()) {
//			int cur = pq.peek().a;
//			int getcost = pq.peek().cost;
//			pq.poll();
//
//
//			if(cost[cur] < getcost)continue;
//
//
//			for(int i = 0 ; i < list[cur].size();i++) {
//				int next = list[cur].get(i).a;
//				int nextcost = list[cur].get(i).cost;
//
//
//
//
//
//				if(cost[next] > getcost+nextcost) {
//					cost[next] = getcost + nextcost;
//
//
//					pq.add(new Node(next,getcost+nextcost));
//				}
//
//			}
//
//
//		}
//
//
//
//
//
//
//
//
//	}



}
