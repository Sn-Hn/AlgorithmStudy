package boj_20210420_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*

내 선물을 받아줘 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	512 MB	974	378	290	39.726%
문제
욱제는 구사과의 열렬한 팬이다. 오늘 욱제는 구사과에게 선물()을 전달해주려고 한다. 
지난 며칠간의 관찰 끝에 욱제는 구사과의 이동 패턴을 모두 파악했다.

구사과가 있는 곳은 N×M 크기의 직사각형 지도로 나타낼 수 있으며, 1×1크기의 정사각형으로 나누어져 있다. 
구사과의 위치는 (i, j)로 나타낼 수 있으며, (i, j)는 위에서부터 i번째 칸, 왼쪽에서부터 j번째 칸을 의미한다.

지도의 각 칸에는 N, W, E, S 중의 한 문자가 쓰여져 있는데, 구사과는 이 문자를 이용해서 이동한다.
구사과의 위치가 (i, j)인 경우에 N이 쓰여져 있는 칸에 서 있었다면,
(i-1, j)로, S의 경우에는 (i+1, j)로, W의 경우에는 (i, j-1), E의 경우에는 (i, j+1)로 순간이동한다. 
구사과는 지치지 않기 때문에, 계속해서 이동한다.

욱제는 구사과의 위치를 모르기 때문에, 구사과가 이동을 시작하는 위치와 관계없이 선물을 주는 방법을 알아내려고 한다. 
최소 몇 개의 칸 위에 선물을 놓으면, 구사과가 항상 선물을 가져가는지 구하는 프로그램을 작성하시오. 
선물이 놓여진 칸에 구사과가 이동하면, 구사과는 항상 선물을 가져간다.

입력
첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 1,000, 1 < N×M ≤ 1,000,000)

둘째 줄부터 N개의 줄에는 구사과가 있는 곳의 지도가 주어진다. 

지도에 쓰여 있는대로 이동했을 때, 지도를 벗어나는 경우는 없다.

출력
첫째 줄에 최소 몇 개의 칸에 선물을 놓아야 하는지 출력한다.

예제 입력 1 
3 4
SWWW
SEWN
EEEN
예제 출력 1 
2

ex
3 4
SSWW
SESW
EEEN

*/

public class 내선물을받아줘_15559 {
	private static int N, M;
	private static char map[][];
	private static int visited[][];
	private static int level = 0;
	private static int count = 0;
	
	//						    N, S, W, E
	private static int dx[] = {-1, 1, 0, 0};
	private static int dy[] = {0, 0, -1, 1};
	
	private static class Pair {
		int x, y;
		char direction;
		public Pair(int x, int y, char direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new int[N][M];
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// 구사과의 처음 위치를 모르기 때문에 전체를 돌려본다.
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(visited[i][j] == 0) {
					level++;
					bfs(i, j, map[i][j]);
				}
			}
		}
		
		System.out.println(count);
		
		print();
		
		br.close();
	}
	
	private static void bfs(int x, int y, char direction) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(x, y, direction));
		visited[x][y] = level;
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			
			int index = direction(p.direction);
			
			int X = p.x + dx[index];
			int Y = p.y + dy[index];
			if(visited[X][Y] == 0) {
				visited[X][Y] = level;
				q.add(new Pair(X, Y, map[X][Y]));
			}else if(visited[X][Y] == level) {
				count++;
			}
		}
		
	}
	
	private static int direction(int direction) {
		if(direction == 'N') {
			return 0;
		}else if(direction == 'S') {
			return 1;
		}else if(direction == 'W') {
			return 2;
		}else if(direction == 'E'){
			return 3;
		}
		
		return -1;
	}
	
	private static void print() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(visited[i][j] + " ");
			}
			System.out.println();
		}
	}
}
