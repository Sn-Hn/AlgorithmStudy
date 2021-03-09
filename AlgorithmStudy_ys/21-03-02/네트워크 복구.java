
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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

	static int distance[];
	static int ans[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken()); 
		
		list = new ArrayList[n+1];
		ans = new int[n+1];
	
		distance = new int[n+1];
		for(int i = 1 ; i <= n; i++) {
			list[i] = new ArrayList<node>();
			
		}
		
		
		for(int i = 0 ; i < m ;i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()); 
			int c = Integer.parseInt(st.nextToken()); 
			
			list[a].add(new node(b, c));
			list[b].add(new node(a, c));
			
			
		}
		
		dijkstra(1);
		System.out.println(n-1);
		for(int i = 0 ; i < ans.length;i++) {
			if(ans[i] != 0) {
				System.out.println(i+" "+ans[i]);
			}
		}
		
	}
	
	
	public static void dijkstra(int start) {
		Arrays.fill(distance, Integer.MAX_VALUE);
		PriorityQueue<node> pq = new PriorityQueue<node>();
		distance[start] = 0;
		pq.add(new node(start, 0));
		
		while(!pq.isEmpty()) {
			int cur = pq.peek().num;
			int plus = pq.peek().plus;
			pq.poll();
			if(distance[cur] < plus) continue;
			
			int last = 0;
			for(int i = 0 ; i < list[cur].size();i++) {
				int next = list[cur].get(i).num;
				int nextplus = plus + list[cur].get(i).plus;
				
				if(nextplus < distance[next]) {
					distance[next] = nextplus;
					pq.add(new node(next, nextplus));
					last = next;
					ans[next] = cur;
		
					
							
			}
		
			
			
		}
		
		
		
	}
	
	
	}
}
	
