package boj_20210608;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

/*

집 구하기 출처분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	2008	515	355	25.669%
문제
안양에 사는 상혁이는 4년간의 통학에 지쳐 서울에 집을 구하려고 한다. 
상혁이가 원하는 집은 세가지 조건이 있다.

맥세권 : 맥세권인 집은 맥도날드와 집 사이의 최단거리가 x이하인 집이다.
스세권 : 스세권인 집은 스타벅스와 집 사이의 최단거리가 y이하인 집이다.
맥세권과 스세권을 만족하는 집 중 최단거리의 합이 최소인 집
통학 때문에 스트레스를 많이 받은 상혁이는 집을 선택하는 데 어려움을 겪고 있다. 
똑똑한 여러분이 상혁이 대신 이 문제를 해결해 주자. 
이사 갈 지역의 지도가 그래프로 주어지고 맥도날드와 스타벅스의 위치가 정점 번호로 주어질 때 상혁이가 원하는 집의 최단거리의 합을 출력하는 프로그램을 작성하시오. 
(맥도날드와 스타벅스가 아닌 정점에는 모두 집이 있다.)



위의 예제 지도에서 사각형은 맥도날드를, 별은 스타벅스가 위치한 정점을 나타낸다. 
각 원은 집이 있는 정점을 낸다. x가 6이고 y가 4일 때 가능한 집의 정점은 6이다.
맥도날드까지의 최단거리가 2, 스타벅스까지의 최단거리가 4로 총 합이 6이 되기 때문이다. 
정점 7은 맥세권이면서 스세권이지만 맥도날드까지의 최단거리가 6, 스타벅스까지의 최단거리가 2로 총 합이 8로써 정점 6의 값보다 크므로 답이 아니다. 
그 외의 정점 2, 3, 4는 맥세권이면서 스세권인 조건을 충족하지 못하므로 답이 될 수 없다.

입력
첫줄에는 정점의 개수 V(3 ≤ V ≤ 10,000)와 도로의 개수 E(0 ≤ E ≤ 300,000)가 주어진다. 
그 다음 E줄에 걸쳐 각 도로를 나타내는 세 개의 정수 (u,v,w)가 순서대로 주어진다. 
이는 u와 v(1 ≤ u,v ≤ V)사이에 가중치가 w(1 ≤ w < 10,000)인 도로가 존재한다는 뜻이다. 
u와 v는 서로 다르며 다른 두 정점 사이에는 여러 개의 간선이 존재할 수도 있음에 유의한다. 
E+2번째 줄에는 맥도날드의 수 M(1 ≤ M ≤ V-2) 맥세권일 조건 x(1 ≤ x ≤ 100,000,000)가 주어지고 그 다음 줄에 M개의 맥도날드 정점 번호가 주어진다. 
E+4번째 줄에는 스타벅스의 수 S(1 ≤ S ≤ V-2)와 스세권일 조건 y(1 ≤ y ≤ 100,000,000)가 주어지고 그 다음 줄에 S개의 스타벅스 정점 번호가 주어진다. 

맥도날드나 스타벅스가 위치한 정점에는 집이 없다.
한 정점에 맥도날드와 스타벅스가 같이 위치할 수 있다.
집이 있는(= 맥도날드나 스타벅스가 위치하지 않은) 정점이 하나 이상 존재한다.
 

출력
 상혁이가 원하는 집의 맥도날드까지의 최단거리와 스타벅스까지의 최단거리 합을 출력한다. 
 만일 원하는 집이 존재하지 않으면 -1을 출력한다.

예제 입력 1 
8 11
1 2 2
1 4 1
2 4 2
2 3 1
2 7 8
3 7 3
4 5 2
4 6 1
6 7 6
6 8 4
7 8 2
2 6
1 5
1 4
8
예제 출력 1 
6


*/

public class 집구하기_13911 {
	private static int V;
	private static int E;
	private static int M;
	private static int Mx;
	private static int S;
	private static int Sy;
	private static long[][] distance;
	private static boolean[][] visited;
	private static final int INF = Integer.MAX_VALUE;
	
	private static List<List<Node>> road = new ArrayList<List<Node>>();
	private static List<Node>[] r;
	private static List<Integer> macdonald = new ArrayList<Integer>();
	private static List<Integer> starbucks = new ArrayList<Integer>();
	
	private static class Node implements Comparable<Node> {
		int index;
		long weight;
		
		public Node(int index, long weight) {
			this.index = index;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return (int) (weight - o.weight);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		distance = new long[V + 1][V + 1];
		visited = new boolean[V + 1][V + 1];
		initRoad();
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			road.get(u).add(new Node(v, w));
			road.get(v).add(new Node(u, w));
		}
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		Mx = Integer.parseInt(st.nextToken());
		if(M != 1) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				macdonald.add(Integer.parseInt(st.nextToken()));
			}			
		}else {
			macdonald.add(Integer.parseInt(br.readLine()));
		}
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		Sy = Integer.parseInt(st.nextToken());
		
		if (S != 1) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < S; i++) {
				starbucks.add(Integer.parseInt(st.nextToken()));
			}
		}else {
			starbucks.add(Integer.parseInt(br.readLine().trim()));			
		}
		
		for (int i = 1; i <= V; i++) {
			distance[i][i] = 0;
			if(!macdonald.contains(i) && !starbucks.contains(i)) {
				dijkstra(i);				
			}
		}
		
		long minMac = INF;
		long minStar = INF;
		long result = INF;
		for (int i = 1; i <= V; i++) {
			if(macdonald.contains(i) || starbucks.contains(i)) {
				continue;
			}
			minMac = INF;
			minStar = INF;
			for (int j = 0; j < macdonald.size(); j++) {
				minMac = Math.min(minMac, distance[i][macdonald.get(j)]);
			}
			
			for (int j = 0; j < starbucks.size(); j++) {
				minStar= Math.min(minStar, distance[i][starbucks.get(j)]);
			}
			
			if (minMac > Mx || minStar > Sy) {
				result = INF;
				continue;
			}
			
			long sum = minMac + minStar;
			
			result = Math.min(result, sum);
		}
		
		if(result == INF) {
			System.out.println(-1);
		}else {
			System.out.println(result);			
		}
		
		
		br.close();
	}
	
	private static void dijkstra(int start) {
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.add(new Node(start, 0));
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			int now = n.index;
			
			
			if (visited[start][now]) {
				continue;
			}
			visited[start][now] = true;
			
			for (Node node : road.get(now)) {
				if (distance[start][node.index] > distance[start][now] + node.weight) {
					distance[start][node.index] = distance[start][now] + node.weight;
					q.add(new Node(node.index, distance[start][node.index]));
				}
			}
		}
	}
	
	private static void initRoad() {
		for (int i = 0; i <= V; i++) {
			road.add(new ArrayList<Node>());
			Arrays.fill(distance[i], INF);
		}
	}
}
