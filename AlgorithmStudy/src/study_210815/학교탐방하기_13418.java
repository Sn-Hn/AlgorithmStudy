package study_210815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 학교탐방하기_13418 {
	private static int BEST = 1;
	private static int WORST = -1;
	
	private static int N;
	private static int M;
	private static PriorityQueue<Road> bestRoad;
	private static PriorityQueue<Road> worstRoad;
	private static int[] parent;
	
	private static class Road {
		int a;
		int b;
		int value;
		
		public Road(int a, int b, int value) {
			this.a = a;
			this.b = b;
			this.value = value;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		
		bestRoad = getPriorityQueue(BEST);
		worstRoad = getPriorityQueue(WORST);
		
		for (int i = 0; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			bestRoad.add(new Road(a, b, value));
			worstRoad.add(new Road(a, b, value));
		}
		
		getFatigueDifference();
		
		br.close();
	}
	
	private static void getFatigueDifference() {
		int bestFatigue = findFatigue(bestRoad);
		int worstFatigue = findFatigue(worstRoad);
		
		System.out.println(worstFatigue * worstFatigue - bestFatigue * bestFatigue);
		System.out.println(bestFatigue);
		System.out.println(worstFatigue);
	}
	
	private static int findFatigue(PriorityQueue<Road> q) {
		init();
		int fatigue = 0;
		int count = 0;
		while (!q.isEmpty()) {
			Road road = q.poll();
			int a = road.a;
			int b = road.b;
			
			if (!isCycle(a, b)) {
				union(a, b);
				if (road.value == 0) {
					fatigue ++;					
				}
				count++;
			}
			
			if (count == N) {
				break;
			}
			
		}
		
		return fatigue;
	}
	
	private static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		
		return parent[x] = find(parent[x]);
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a > b) {
			parent[a] = b;
			return;
		}
		
		parent[b] = a;
	}
	
	private static boolean isCycle(int a, int b) {
		return find(a) == find(b);
	}
	
	private static PriorityQueue<Road> getPriorityQueue(int flag) {
		return new PriorityQueue<Road>((o1, o2) -> (o2.value - o1.value) * flag);
	}
	
	private static void init() {
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}
}
