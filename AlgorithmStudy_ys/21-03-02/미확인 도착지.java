package solution125;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class node implements Comparable<node>{
		int num;
		int plus;
		
		
		public node(int num, int plus) {
			super();
			this.num = num;
			this.plus = plus;
		}


		@Override
		public int compareTo(node o) {
			// TODO Auto-generated method stub
			return plus-o.plus;
		}


	
		
		
		
	}
	
	
	static int distance[];
	static ArrayList<node> list[];
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0 ; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			
			
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			
			distance = new int[n+1];
			list = new ArrayList[n+1];
			for(int j = 0 ; j <=n;j++) {
				list[j] = new ArrayList<node>();
			}
			
			for(int j = 1 ; j <=m ; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				list[a].add(new node(b, c));
				list[b].add(new node(a, c));
				
			}
			
			int x = 0;
			PriorityQueue<Integer> que = new PriorityQueue<Integer>();
			for(int j = 0 ; j < t; j++) {
				x = Integer.parseInt(br.readLine());
				dijkstra(s);
				int wayg = distance[g];
				int wayh = distance[h];
				int arrs[] = distance.clone();
				
				dijkstra(g);
				int arrg[] =distance.clone();
				wayg +=distance[h];
				
				
				dijkstra(h);
				int arrh[] =distance.clone();
				wayh +=distance[g];
				
				
//				System.out.println("쥐"+wayg+"에이피"+wayh);
				wayg +=arrh[x];
				wayh +=arrg[x];
//				System.out.println("쥐"+wayg+"에이피"+wayh);
				int ans = Math.min(wayg, wayh);
				if (ans <= arrs[x]) que.add(x);
			}
			while(!que.isEmpty()) {
				System.out.print(que.poll()+" ");
			}
			
			
		}
		
	}
	public static void dijkstra(int start) {
		Arrays.fill(distance, Integer.MAX_VALUE);
		PriorityQueue<node> pq = new PriorityQueue<node>();
		pq.add(new node(start, 0));
		distance[start] = 0;
		
		while(!pq.isEmpty()) {
			int cur = pq.peek().num;
			int plus = pq.peek().plus;
			pq.poll();
			
			if(distance[cur] < plus) continue;
			
			for(int i = 0 ; i < list[cur].size();i++) {
				int next = list[cur].get(i).num;
				int nextplus = plus + list[cur].get(i).plus;
				
				if(nextplus < distance[next]) {
					distance[next] = nextplus;
					pq.add(new node(next, nextplus));
				}
				
			}
			
			
			
		}
		
		
		
	}
	
	
	
	
}
