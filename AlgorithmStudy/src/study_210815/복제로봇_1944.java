package study_210815;
/*

복제 로봇
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	3569	985	576	25.464%
문제
세준이는 어느 날 획기적인 로봇을 한 개 개발하였다. 
그 로봇은 복제 장치를 이용하면 자기 자신을 똑같은 로봇으로 원하는 개수만큼 복제시킬 수 있다. 
세준이는 어느 날 이 로봇을 테스트하기 위하여 어떤 미로에 이 로봇을 풀어 놓았다. 
이 로봇의 임무는 미로에 흩어진 열쇠들을 모두 찾는 것이다. 
그리고 열쇠가 있는 곳들과 로봇이 출발하는 위치에 로봇이 복제할 수 있는 장치를 장착해 두었다.

N*N의 정사각형 미로와 M개의 흩어진 열쇠의 위치, 그리고 이 로봇의 시작 위치가 주어져 있을 때, 모든 열쇠를 찾으면서 로봇이 움직이는 횟수의 합을 최소로 하는 프로그램을 작성하여라. 
로봇은 상하좌우 네 방향으로 움직이며, 로봇이 열쇠가 있는 위치에 도달했을 때 열쇠를 찾은 것으로 한다. (복제된 로봇이어도 상관없다) 
하나의 칸에 동시에 여러 개의 로봇이 위치할 수 있으며, 로봇이 한 번 지나간 자리라도 다른 로봇 또는 자기 자신이 다시 지나갈 수 있다. 
복제에는 시간이 들지 않으며, 로봇이 움직이는 횟수의 합은 분열된 로봇 각각이 움직인 횟수의 총 합을 말한다. 
복제된 로봇이 열쇠를 모두 찾은 후 같은 위치로 모일 필요는 없다.

입력
첫째 줄에 미로의 크기 N(4 ≤ N ≤ 50)과 열쇠의 개수 M(1 ≤ M ≤ 250) 이 공백을 사이에 두고 주어진다. 
그리고 둘째 줄부터 N+1째 줄까지 미로의 정보가 주어진다. 
미로는 1과 0, 그리고 S와 K로 주어진다. 
1은 미로의 벽을 의미하고, 0은 지나다닐 수 있는 길, S는 로봇이 출발하는 위치, K는 열쇠의 위치가 주어진다. 
S는 1개, K는 M개가 주어진다. S와 K에서만 복제를 할 수 있음에 유의한다. 
미로는 벽으로 둘러쌓여 있는 형태이다. 즉, 모든 테두리는 벽이다.

출력
첫째 줄에 모든 로봇이 움직인 횟수의 총 합을 출력한다. 
모든 열쇠를 찾는 것이 불가능한 경우 횟수 대신 첫째 줄에 -1을 출력하면 된다.

예제 입력 1 
5 2
11111
1S001
10001
1K1K1
11111
예제 출력 1 
6

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 복제로봇_1944 {
	private static final int[] dx = {0, 0, 1, -1};
	private static final int[] dy = {1, -1, 0, 0};
	
	private static int N;
	private static int M;
	private static char[][] map;
	private static Pos robotPosition;
	private static List<Pos> keys = new ArrayList<Pos>();
	private static PriorityQueue<Pos> path = new PriorityQueue<Pos>();
	private static int[][] key;
	private static boolean[][] visit;
	private static int[] parent;
	
	private static class Pos implements Comparable<Pos>{
		int x;
		int y;
		int cnt;
		
		public Pos(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Pos o) {
			// TODO Auto-generated method stub
			return cnt - o.cnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][N];
		key = new int[N][N];
		visit = new boolean[M + 2][M + 2];
		parent = new int[M + 2];
		
		int index = 1;
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'S') {
					robotPosition = new Pos(i, j, 0);
					key[i][j] = index++;
				}else if (map[i][j] == 'K') {
					keys.add(new Pos(i, j, 0));
					key[i][j] = index++;
				}
			}
		}
		
		move(robotPosition);
		for (int i = 0; i < keys.size(); i++) {
			move(keys.get(i));
		}
		int result = sumMoveCnt();
		
		System.out.println(result);
		
		br.close();
	}
	
	private static void move(Pos p) {
		Queue<Pos> q = new LinkedList<Pos>();
		boolean[][] visited = new boolean[N][N];
		q.add(p);
		visited[p.x][p.y] = true;
		int a = key[p.x][p.y];
		
		while (!q.isEmpty()) {
			Pos pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			
			for (int i = 0; i < dx.length; i++) {
				int X = x + dx[i];
				int Y = y + dy[i];
				
				if (!isValid(X, Y, visited)) {
					continue;
				}
				
				visited[X][Y] = true;
				
				int b = key[X][Y];
				
				if (map[X][Y] == 'K' || map[X][Y] == 'S') {
					if (!visit[a][b]) {
						path.add(new Pos(a, b, pos.cnt + 1));						
						visit[a][b] = true;
						visit[b][a] = true;
					}
					continue;
				}
				
				q.add(new Pos(X, Y, pos.cnt + 1));
			}
		}
	}
	
	private static int sumMoveCnt() {
		int count = 0;
		int sum = 0;
		init();
		
		while (!path.isEmpty()) {
			Pos pos = path.poll();
			int a = pos.x;
			int b = pos.y;

			if (!isCycle(a, b)) {
				union(a, b);
				sum += pos.cnt;
				count++;
			}
			
			if (count == M) {
				break;
			}
		}
		
		if (count != M) {
			sum = -1;
		}
		
		return sum;
	}
	
	private static boolean isValid(int x, int y, boolean[][] visited) {
		if (x >= 0 && x < N && y >= 0 && y < N && map[x][y] != '1' && !visited[x][y]) {
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
			return;
		}
		
		parent[b] = a;
	}
	
	private static boolean isCycle(int a, int b) {
		return find(a) == find(b);
	}
	
	private static void init() {		
		for (int i = 1; i <= M + 1; i++) {
			parent[i] = i;
		}
	}
}
