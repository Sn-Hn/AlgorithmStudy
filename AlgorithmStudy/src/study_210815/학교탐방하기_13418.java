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
	private static PriorityQueue<Road> bestRoads;
	private static PriorityQueue<Road> worstRoads;
	private static int[] parents;
//	private static int[] bestParents;
//	private static int[] worstParents;
	
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
		parents = new int[N + 1];
//		bestParents = new int[N + 1];
//		worstParents = new int[N + 1];
		
		bestRoads = getPriorityQueue(BEST);
		worstRoads = getPriorityQueue(WORST);
		
		for (int i = 0; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			bestRoads.add(new Road(a, b, value));
			worstRoads.add(new Road(a, b, value));
		}
		
		getFatigueDifference();
		
		br.close();
	}
	
	private static void getFatigueDifference() {
		int bestFatigue = findFatigue(bestRoads);
		int worstFatigue = findFatigue(worstRoads);
		
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
		if (parents[x] == x) {
			return x;
		}
		
		return parents[x] = find(parents[x]);
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a > b) {
			parents[a] = b;
			return;
		}
		
		parents[b] = a;
	}
	
	private static boolean isCycle(int a, int b) {
		return find(a) == find(b);
	}
	
	private static PriorityQueue<Road> getPriorityQueue(int flag) {
		return new PriorityQueue<Road>((o1, o2) -> (o2.value - o1.value) * flag);
	}
	
	private static void init() {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
//			bestParents[i] = i;
//			worstParents[i] = i;
		}
	}
}
