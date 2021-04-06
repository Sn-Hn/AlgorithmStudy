package boj_20210309_최소스패닝트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 전기가부족해_10423 {
	private static int N, M, K;
	private static int parent[];
	private static int power[];
	private static class Node implements Comparable<Node> {
		int x, y, cost;
		public Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return cost - o.cost;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		power = new int[K+1];
		
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= K; i++) {
			power[i] = Integer.parseInt(st.nextToken());
			parent[power[i]] = -1;
		}
		
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			q.add(new Node(a, b, c));
		}
		
		solve(q);
		
		br.close();
	}
	
	private static void solve(PriorityQueue<Node> q) {
		int max = 0;
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			if(!findCycle(node.x, node.y)) {
				union(node.x, node.y);
				max += node.cost;
				
				if(isConnect()) break;
			}
		}
		
		System.out.println(max);
	}
	
	private static boolean isConnect() {
		for(int i = 1; i < parent.length; i++) {
			if(parent[i] != -1) return false;
		}
		
		return true;
	}
	
	private static int find(int x) {
		if(parent[x] == -1) return -1;
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) {
			if(a == -1) {
				parent[b] = -1;
			}else if(b == -1) {
				parent[a] = -1;				
			}else {
				if(a < b) parent[b] = a;
				else parent[a] = b;
			}
		}
	}
	
	private static boolean findCycle(int a, int b) {
		return find(a) == find(b);
	}
}
