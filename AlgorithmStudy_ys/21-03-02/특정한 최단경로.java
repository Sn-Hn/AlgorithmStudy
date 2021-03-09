package solution122;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class Main {
	
	static class node implements Comparable<node> {
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
	
	static ArrayList<node> list[];
	static int n;
	static int e;
	static int v1;
	static int v2;
	static int distance[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		distance = new int[n+1];
		for(int i = 0 ; i <n+1; i++) {
			list[i] = new ArrayList<node>();
		}
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		for(int i = 0 ; i < e; i++) {
			String tmp[] = br.readLine().split(" ");
			int a = Integer.parseInt(tmp[0]);
			int b = Integer.parseInt(tmp[1]);
			int c = Integer.parseInt(tmp[2]);
			
			list[a].add(new node(b, c));			
			list[b].add(new node(a, c));			
				
		}
		
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		if(e == 0) {
			System.out.println(-1);
		}else {
			int ans1 = 0;
			int ans2 = 0;
			Arrays.fill(distance, Integer.MAX_VALUE);
			dijkstra(1);
			ans1 +=distance[v1];
			ans2 +=distance[v2];
			Arrays.fill(distance, Integer.MAX_VALUE);
			dijkstra(v1);
			ans1+=distance[v2];
			ans2+=distance[n];
			Arrays.fill(distance, Integer.MAX_VALUE);
			dijkstra(v2);
			ans1+=distance[n];
			ans2+=distance[v1];
			
			System.out.println(Math.min(ans1, ans2));
		}
		
		
	}
	
	
	public static void dijkstra(int start) {
		distance[start] = 0;
		PriorityQueue<node> que = new PriorityQueue<node>();
		que.add(new node(start, 0));
		
		while(!que.isEmpty()) {
			int cur = que.peek().num;
			int plus = que.peek().plus;
			que.poll();
			
			
			
			if(distance[cur] < plus) continue;
			
			for(int i = 0 ; i < list[cur].size();i++) {
				int next = list[cur].get(i).num;
				int nextplus = plus+list[cur].get(i).plus;
				
			
					
				if(nextplus < distance[next]) {
					distance[next] = nextplus;
					que.add(new node(next, nextplus));
				}

				
			}
			
			
		}
		
		
		
	}
	
	
}


