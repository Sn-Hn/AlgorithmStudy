package study_210725;

/*

얼음 미로 출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초 (추가 시간 없음)	1024 MB	383	95	74	27.106%
문제


탐험가 테라는 얼음 미로에 갇혔다. 
얼음 미로의 바닥은 빙판으로 되어 있어 발을 내디디면 바위에 부딪힐 때까지 미끄러진다. 
예를 들어, 위 그림에서 테라가 왼쪽 방향으로 이동한다면 중간에 멈출 수 없고 왼쪽 바위에 부딪힐 때까지 미끄러진다. 
얼음 미로 바깥은 절벽이기 때문에 빠지면 탈출할 수 없다.

얼음 미로에는 가지 오브젝트가 있다.

  테라 : 얼음 미로에 갇힌 탐험가. 상하좌우 방향으로 이동할 수 있다. 얼음 미로에 단 명의 테라만 존재한다. 테라가 최초 위치한 빙판의 미끌 시간은 0이다.
  바위 : 통과할 수 없다. 미끄러지다 부딪히면 앞에서 멈춘다.
  구멍 : 이곳에 빠지면 영영 탈출할 수 없다.
  출구 : 이곳에 방문하는 즉시 얼음 미로를 탈출한다. 얼음 미로에 단 개의 출구만 존재한다.
어떤 빙판 위에서 미끄러지는 데 걸리는 시간을 미끌 시간이라고 하자. 각 빙판마다 미끌 시간은 다를 수 있다.

테라가 어느 한쪽 방향으로 이동할 때, 테라가 미끄러지는 동안 위치한 빙판의 미끌 시간을 더하면 이동 시간을 구할 수 있다. 
단, 이동 시간 계산과 관련하여 두 가지 규칙이 있다.



테라가 어느 한쪽 방향으로 이동을 시작할 때, 시작 빙판의 미끌 시간은 포함하지 않는다.
테라가 출구로 들어갈 때, 출구 빙판의 미끌 시간은 포함하지 않는다.
위 그림에서 테라가 위로 이동할 때의 이동 시간을 계산하자. 
테라가 현재 서 있는, 시작 빙판의 미끌 시간 와 출구 빙판의 미끌 시간 을 제외하면  만큼의 시간이 걸린 뒤 출구를 통해 탈출함을 알 수 있다.

저체온증이 시작된 테라는 얼음 미로를 가능한 한 빨리 탈출하고 싶다. 
얼음 미로를 탈출하는 데 걸리는 최단 시간을 계산하자.

입력
첫 번째 줄에는 얼음 미로의 가로 크기를 나타내는 정수 (), 세로 크기를 나타내는 정수 ()가 주어진다.

두 번째 줄부터 개의 줄에 걸쳐 얼음 미로에 대한 정보가 주어진다.

테라는 T, 바위는 R, 구멍은 H, 출구는 E로 나타낸다.

빙판의 미끌 시간 는  이상  이하의 정수로 나타낸다.

출력
얼음 미로를 탈출할 수 있다면 최단 탈출 시간을 출력한다.

얼음 미로를 탈출할 수 없다면 -1을 출력한다.

예제 입력 1 
5 5
2E115
32411
11313
R42TH
124R6
예제 출력 1 
9
 

예제 입력 2 
4 5
11R1
1E1R
1911
1911
1T1R
예제 출력 2 
4
 

예제 입력 3 
3 3
111
TRE
111
예제 출력 3 
-1


예제 입력 4 
5 5
TRRRR
1R11R
1RE1R
1111R
RRRRR
예제 출력 4 
9

3 3
51H
RE1
T11

5 5
11R2T
11121
1E121
R1115
222RR

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 얼음미로_20926 {
	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};
	
	private static int N;
	private static int M;
	private static char[][] map;
	private static boolean[][] visited;
	private static Pos start;
	private static int minTime = Integer.MAX_VALUE;
	
	private static class Pos implements Comparable<Pos> {
		int x;
		int y;
		int time;
		
		public Pos(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
		
		@Override
		public int compareTo(Pos o) {
			// TODO Auto-generated method stub
			return time - o.time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'T') {
					start = new Pos(i, j, 0);
				}
			}
		}
		
		escapeMaze();
		
		System.out.println(minTime == Integer.MAX_VALUE ? -1 : minTime);
		
		br.close();
	}
	
	private static void escapeMaze() {
		PriorityQueue<Pos> q = new PriorityQueue<Pos>();
		
		q.add(start);
		
		while (!q.isEmpty()) {
			Pos pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			
			if (visited[x][y]) {
				continue;
			}
			
			visited[x][y] = true;
			
			for (int i = 0; i < dx.length; i++) {
				int X = x;
				int Y = y;
				int time = 0;
				
				while (isValid(X + dx[i], Y + dy[i]) && isValidMap(X + dx[i], Y + dy[i])) {
					 X += dx[i];
					 Y += dy[i];
					 
					 if (map[X][Y] >= '0' && map[X][Y] <= '9') {
						 time += map[X][Y] - '0';
					 }
				}
				
				if (map[X][Y] == 'H') {
					continue;
				}
				
				int nextX = X + dx[i];
				int nextY = Y + dy[i];
				if (isValid(nextX, nextY) && map[nextX][nextY] == 'E') {
					minTime = Math.min(minTime, pos.time + time);
				}
				
				if (isValid(nextX, nextY) && map[nextX][nextY] == 'R') {
					q.add(new Pos(X, Y, pos.time + time));
				}
				
			}
		}
		
	}
	
	private static boolean isValid(int X, int Y) {
		if (X >= 0 && X < N && Y >= 0 && Y < M) {
			return true;
		}
		
		return false;
	}
	
	private static boolean isValidMap(int X, int Y) {
		if ((map[X][Y] >= '0' && map[X][Y] <= '9') || map[X][Y] == 'T') {
			return true;
		}
		
		return false;
	}
}
