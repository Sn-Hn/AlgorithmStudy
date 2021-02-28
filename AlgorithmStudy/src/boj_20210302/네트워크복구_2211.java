package boj_20210302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 네트워크복구_2211 {
	private static int N, M;
	private static List<List<Node>> list;
	private static int time[];
	private static boolean visited[];
	private static int INF = Integer.MAX_VALUE;
	private static Map<Integer, Integer> hm = new HashMap<>();
	
	private static class Node implements Comparable<Node>{
		int index, time;
		public Node(int index, int time) {
			this.index = index;
			this.time = time;
		}
		
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return time - o.time;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		time = new int[N+1];
		visited = new boolean[N+1];
		list = new ArrayList<List<Node>>();
		
		for(int i = 0; i <= N; i++) {
			list.add(new ArrayList<Node>());
		}
		
		Arrays.fill(time, INF);
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list.get(a).add(new Node(b, c));
			list.get(b).add(new Node(a, c));
		}
		
		dijkstra();
		
//		for(int i = 1; i <= N; i++) {
//			System.out.print(time[i] + " ");
//		}
		
		System.out.println(hm.size());
		Iterator<Integer> keyIt = hm.keySet().iterator();
		while(keyIt.hasNext()) {
			int key = keyIt.next();
			int value = hm.get(key);
			
			System.out.println(key + " " + value);
		}
		
		
		
		br.close();
	}
	
	private static void dijkstra() {
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		
		time[1] = 0;
		q.add(new Node(1, 0));
		while(!q.isEmpty()) {
			Node n = q.poll();
			int now = n.index;
			
			if(visited[now]) continue;
			visited[now] = true;
			
			for(Node node : list.get(now)) {
				if(time[node.index] > time[now] + node.time) {
					time[node.index] = time[now] + node.time;
					q.add(new Node(node.index, time[node.index]));
//					System.out.println(now + ", " + node.index + ", " + time[node.index]);
					hm.put(node.index, now);
				}
			}
		}
		
		
	}
}
