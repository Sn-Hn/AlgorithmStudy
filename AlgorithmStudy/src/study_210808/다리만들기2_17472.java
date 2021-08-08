package study_210808;

/*

다리 만들기 2
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	512 MB	12513	4249	2431	29.675%
문제
섬으로 이루어진 나라가 있고, 모든 섬을 다리로 연결하려고 한다. 이 나라의 지도는 N×M 크기의 이차원 격자로 나타낼 수 있고, 격자의 각 칸은 땅이거나 바다이다.

섬은 연결된 땅이 상하좌우로 붙어있는 덩어리를 말하고, 아래 그림은 네 개의 섬으로 이루어진 나라이다. 색칠되어있는 칸은 땅이다.



다리는 바다에만 건설할 수 있고, 다리의 길이는 다리가 격자에서 차지하는 칸의 수이다. 다리를 연결해서 모든 섬을 연결하려고 한다. 섬 A에서 다리를 통해 섬 B로 갈 수 있을 때, 섬 A와 B를 연결되었다고 한다. 다리의 양 끝은 섬과 인접한 바다 위에 있어야 하고, 한 다리의 방향이 중간에 바뀌면 안된다. 또, 다리의 길이는 2 이상이어야 한다.

다리의 방향이 중간에 바뀌면 안되기 때문에, 다리의 방향은 가로 또는 세로가 될 수 밖에 없다. 방향이 가로인 다리는 다리의 양 끝이 가로 방향으로 섬과 인접해야 하고, 방향이 세로인 다리는 다리의 양 끝이 세로 방향으로 섬과 인접해야 한다.

섬 A와 B를 연결하는 다리가 중간에 섬 C와 인접한 바다를 지나가는 경우에 섬 C는 A, B와 연결되어있는 것이 아니다. 

아래 그림은 섬을 모두 연결하는 올바른 2가지 방법이고, 다리는 회색으로 색칠되어 있다. 섬은 정수, 다리는 알파벳 대문자로 구분했다.

	
다리의 총 길이: 13

D는 2와 4를 연결하는 다리이고, 3과는 연결되어 있지 않다.

다리의 총 길이: 9 (최소)

다음은 올바르지 않은 3가지 방법이다

		
C의 방향이 중간에 바뀌었다	D의 길이가 1이다.	가로 다리인 A가 1과 가로로 연결되어 있지 않다.
다리가 교차하는 경우가 있을 수도 있다. 교차하는 다리의 길이를 계산할 때는 각 칸이 각 다리의 길이에 모두 포함되어야 한다. 아래는 다리가 교차하는 경우와 기타 다른 경우에 대한 2가지 예시이다.

	
A의 길이는 4이고, B의 길이도 4이다.

총 다리의 총 길이: 4 + 4 + 2 = 10

다리 A: 2와 3을 연결 (길이 2)

다리 B: 3과 4를 연결 (길이 3)

다리 C: 2와 5를 연결 (길이 5)

다리 D: 1과 2를 연결 (길이 2)

총 길이: 12

나라의 정보가 주어졌을 때, 모든 섬을 연결하는 다리 길이의 최솟값을 구해보자.

입력
첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. 둘째 줄부터 N개의 줄에 지도의 정보가 주어진다. 각 줄은 M개의 수로 이루어져 있으며, 수는 0 또는 1이다. 0은 바다, 1은 땅을 의미한다.

출력
모든 섬을 연결하는 다리 길이의 최솟값을 출력한다. 모든 섬을 연결하는 것이 불가능하면 -1을 출력한다.

제한
1 ≤ N, M ≤ 10
3 ≤ N×M ≤ 100
2 ≤ 섬의 개수 ≤ 6
예제 입력 1 
7 8
0 0 0 0 0 0 1 1
1 1 0 0 0 0 1 1
1 1 0 0 0 0 0 0
1 1 0 0 0 1 1 0
0 0 0 0 0 1 1 0
0 0 0 0 0 0 0 0
1 1 1 1 1 1 1 1
예제 출력 1 
9
예제 입력 2 
7 8
0 0 0 1 1 0 0 0
0 0 0 1 1 0 0 0
1 1 0 0 0 0 1 1
1 1 0 0 0 0 1 1
1 1 0 0 0 0 0 0
0 0 0 0 0 0 0 0
1 1 1 1 1 1 1 1
예제 출력 2 
10
예제 입력 3 
7 8
1 0 0 1 1 1 0 0
0 0 1 0 0 0 1 1
0 0 1 0 0 0 1 1
0 0 1 1 1 0 0 0
0 0 0 0 0 0 0 0
0 1 1 1 0 0 0 0
1 1 1 1 1 1 0 0
예제 출력 3 
9
예제 입력 4 
7 7
1 1 1 0 1 1 1
1 1 1 0 1 1 1
1 1 1 0 1 1 1
0 0 0 0 0 0 0
1 1 1 0 1 1 1
1 1 1 0 1 1 1
1 1 1 0 1 1 1
예제 출력 4 
-1

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 다리만들기2_17472 {
	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};
	private static final int INF = Integer.MAX_VALUE;
	
	private static int N;
	private static int M;
	private static int[][] map;
	private static boolean[][] visited;
	private static int number = 1;
	private static int[] parent;
	private static List<Pos> island = new ArrayList<Pos>();
	private static int[][] connectIsland;
	private static PriorityQueue<Pos> q = new PriorityQueue<Pos>();
	
	private static class Pos implements Comparable<Pos>{
		int x;
		int y;
		int count;
		
		public Pos(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
		
		@Override
		public int compareTo(Pos o) {
			// TODO Auto-generated method stub
			return count - o.count;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		divideIsland();
		initParent();
		
		printMap();

		makeBridge();
		
		int result = connectAllIsland();
		
		System.out.println(result);
		
		br.close();
	}
	
	private static void makeBridge() {
		visited = new boolean[N][M];
		for (int i = 0; i < island.size(); i++) {
			getMinBridgeLength(island.get(i));	
		}
		
	}
	
	private static void getMinBridgeLength(Pos pos) {
		int x = pos.x;
		int y = pos.y;
		int number = pos.count;
		visited[x][y] = true;
		
		for (int i = 0; i < dx.length; i++) {
			int X = x + dx[i];
			int Y = y + dy[i];
			
			if (!isValid(X, Y)) {
				continue;
			}
			
			if (map[X][Y] == number && !visited[X][Y]) {
				getMinBridgeLength(new Pos(X, Y, pos.count));
				continue;
			}
			
			int count = 0;
			while (isValid(X, Y) && map[X][Y] == 0) {
				count++;
				X += dx[i];
				Y += dy[i];
			}
			
			if (!isValid(X, Y)) {
				continue;
			}
			
			if (count >= 2) {
				int a = map[x][y];
				int b = map[X][Y];
//				connectIsland[a][b] = Math.min(connectIsland[a][b], count);
				q.add(new Pos(a, b, count));
			}
		}
	}
	
	private static int connectAllIsland() {
		int cnt = 1;
		int min = 0;
		
		while (!q.isEmpty() && cnt < number) {
			Pos pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			
			if (!isCycle(x, y)) {
				union(x, y);
				min += pos.count;
				cnt++;
			}
		}
		
		if (cnt != number - 1) {
			min = -1;
		}
		
		return min;
	}
	
	private static void divideIsland() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					getIslandNo(i, j, number);
					island.add(new Pos(i, j, number));
					number++;
				}
			}
		}
	}
	
	private static void getIslandNo(int x, int y, int number) {
		visited[x][y] = true;
		map[x][y] = number;
		
		for (int i = 0; i < dx.length; i++) {
			int X = x + dx[i];
			int Y = y + dy[i];
			
			if (!isValid(X, Y)) {
				continue;
			}
			
			if (map[X][Y] == 1 && !visited[X][Y]) {
				getIslandNo(X, Y, number);
			}
		}
	}
	
	private static boolean isValid(int X, int Y) {
		if (X >= 0 && X < N && Y >= 0 && Y < M) {
			return true;
		}
		
		return false;
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
		}else {
			parent[b] = a;
		}
	}
	
	private static boolean isCycle(int a, int b) {
		return find(a) == find(b);
	}
	
	private static void initParent() {
		parent = new int[number];
		connectIsland = new int[number][number];
		
		for (int i = 0; i < number; i++) {
			parent[i] = i;
			Arrays.fill(connectIsland[i], INF);
		}
	}
	
	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
