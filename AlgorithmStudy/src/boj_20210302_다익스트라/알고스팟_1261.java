package boj_20210302_다익스트라;

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

/*

알고스팟 분류
시간 제한			메모리 제한	제출		정답		맞은 사람	정답 비율
1 초 (추가 시간 없음)	128 MB	20031	8034	5268	39.855%
문제
알고스팟 운영진이 모두 미로에 갇혔다. 
미로는 N*M 크기이며, 총 1*1크기의 방으로 이루어져 있다. 
미로는 빈 방 또는 벽으로 이루어져 있고, 빈 방은 자유롭게 다닐 수 있지만, 벽은 부수지 않으면 이동할 수 없다.

알고스팟 운영진은 여러명이지만, 항상 모두 같은 방에 있어야 한다. 
즉, 여러 명이 다른 방에 있을 수는 없다. 
어떤 방에서 이동할 수 있는 방은 상하좌우로 인접한 빈 방이다. 
즉, 현재 운영진이 (x, y)에 있을 때, 이동할 수 있는 방은 (x+1, y), (x, y+1), (x-1, y), (x, y-1) 이다. 
단, 미로의 밖으로 이동 할 수는 없다.

벽은 평소에는 이동할 수 없지만, 알고스팟의 무기 AOJ를 이용해 벽을 부수어 버릴 수 있다. 
벽을 부수면, 빈 방과 동일한 방으로 변한다.

만약 이 문제가 알고스팟에 있다면, 운영진들은 궁극의 무기 sudo를 이용해 벽을 한 번에 다 없애버릴 수 있지만, 
안타깝게도 이 문제는 Baekjoon Online Judge에 수록되어 있기 때문에, sudo를 사용할 수 없다.

현재 (1, 1)에 있는 알고스팟 운영진이 (N, M)으로 이동하려면 벽을 최소 몇 개 부수어야 하는지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 미로의 크기를 나타내는 가로 크기 M, 세로 크기 N (1 ≤ N, M ≤ 100)이 주어진다. 
다음 N개의 줄에는 미로의 상태를 나타내는 숫자 0과 1이 주어진다. 0은 빈 방을 의미하고, 1은 벽을 의미한다.

(1, 1)과 (N, M)은 항상 뚫려있다.

출력
첫째 줄에 알고스팟 운영진이 (N, M)으로 이동하기 위해 벽을 최소 몇 개 부수어야 하는지 출력한다.

예제 입력 1 
3 3
011
111
110
예제 출력 1 
3
예제 입력 2 
4 2
0001
1000
예제 출력 2 
0
예제 입력 3 
6 6
001111
010000
001111
110001
011010
100010
예제 출력 3 
2


5 5
01111
11111
11001
11111
11110

*/

// bfs로는 메모리 초과

public class 알고스팟_1261 {
	private static int N, M;
	private static int map[][];
	private static char ch[][];
	private static int min = Integer.MAX_VALUE;

	
	private static int dx[] = {1, -1, 0, 0};
	private static int dy[] = {0, 0, 1, -1};
	
	private static int dp[][];
	
	private static boolean visited[][];
	
	private static class Pair implements Comparable<Pair>{
		int x, y, wall;
		public Pair(int x, int y, int wall) {
			this.x = x;
			this.y = y;
			this.wall = wall;
		}
		
		@Override
		public int compareTo(Pair p) {
			// TODO Auto-generated method stub
			return wall - p.wall;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		ch = new char[N+1][M+1];
		visited = new boolean[N+1][M+1];
		
		dp = new int[N+1][M+1];
		
		for(int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		
		for(int i = 1; i <= N; i++) {
			ch[i] = br.readLine().toCharArray();
			for(int j = 0; j < M; j++) {
				map[i][j+1] = Character.getNumericValue(ch[i][j]);
			}
		}
		
		solve();
		
		System.out.println(dp[N][M]);
		
//		for(int i = 1; i <= N; i++) {
//			for(int j = 1; j <= M; j++) {
//				System.out.print(dp[i][j]);
//			}
//			System.out.println();
//		}
		
		br.close();
	}
	
	private static void solve() {
		PriorityQueue<Pair> q = new PriorityQueue<Pair>();
		q.add(new Pair(1, 1, 0));
		dp[1][1] = 0;
		visited[1][1] = true;
		
		while(!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.peek().y;
			int wall = q.peek().wall;
			q.poll();
			
			if(x == N && y == M) {
				break;
			}

			for(int i = 0; i < 4; i++) {
				int X = x + dx[i];
				int Y = y + dy[i];
				
				if(X > 0 && X <= N && Y > 0 && Y <= M && !visited[X][Y]) {
					if(map[X][Y] == 0) {
						visited[X][Y] = true;
						dp[X][Y] = Math.min(dp[X][Y], wall);
						q.add(new Pair(X, Y, wall));
						continue;
					}
					visited[X][Y] = true;
					dp[X][Y] = Math.min(dp[X][Y], wall+1);
					q.add(new Pair(X, Y, wall+1));
				}
			}
		}
	}
	
	
	// 2. dp 틀렸습니다.
	private static void dp() {
		dp[0][1] = 0;
		dp[1][1] = 0;
		int m = 0;
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) ;
				if(map[i][j] == 1) {
					dp[i][j] += 1;
				}
			}
		}
	}
	
	
	
	// 1. bfs 메모리 초과
	private static void bfs() {
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(0, 0, 0));
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			
			if(p.x == N-1 && p.y == M-1) {
				min = Math.min(min, p.wall);
			}
			
			visited[p.x][p.y] = true;
			for(int i = 0; i < 4; i++) {
				int X = p.x + dx[i];
				int Y = p.y + dy[i];
				
				if(X >= 0 && X < N && Y >= 0 && Y < M && !visited[X][Y]) {
					if(map[X][Y] == 1) {
						q.add(new Pair(X, Y, p.wall+1));
						continue;
					}
					q.add(new Pair(X, Y, p.wall));
				}
			}
		}
	}
}
