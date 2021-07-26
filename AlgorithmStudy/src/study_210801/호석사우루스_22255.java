package study_210801;

/*

호석사우루스 출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	512 MB	217	66	48	28.402%
문제
음머... 미련한 소인 호석사우루스는 융통성 따위 일절 가지지 않는다. 
자신의 철칙에 맞게 우직하게 미궁을 탈출하려고 한다. 
미궁은 행 열의 격자로 이루어져 있고 각 칸마다 입장하는 순간 받는 충격량이 있다. 
같은 방을 여러 번 들어가면, 들어갈 때마다 같은 충격량을 받게 된다. 
당연히 똑똑한 소라면 최소의 충격을 받으면서 미궁을 탈출하던가, 애초에 미궁에 안 빠지도록 머리를 썼겠지만, 호석사우루스는 그런 거 없다!

그의 철칙은, 이동 방식에 있다. 매 이동 시 마다 움직일 수 있는 방향이 다르다.



 번째 이동 시에는 상, 하, 좌, 우로 인접한 곳 중 한 칸으로 이동할 수 있다.
 번째 이동 시에는 상, 하로 인접한 곳 중 한 칸으로 이동할 수 있다.
 번째 이동 시에는 좌, 우로 인접한 곳 중 한 칸으로 이동할 수 있다.
만약 이동하려는 곳에 벽이 있으면 이동할 수 없다.
최초의 이동은 1번째 이동이고, 이후에 2번째, 3번째 이동이다.
자신의 철칙을 지키되, 아픈 건 싫어하는 호석사우루스를 도와서 탈출구까지의 최소 충격량을 구해주자!

입력
첫 번째 줄에 격자의 크기 , 이 주어진다.

두 번째 줄에 시작 지점과 도착 지점의 정보인 , , ,  가 공백으로 구분되어 주어진다. 
시작 지점이 행 열이며 도착 지점이 행 열임을 의미한다. 시작 지점과 도착 지점은 항상 다르다.

세 번째 줄부터  개 줄에 걸쳐서 지도의 정보가 주어진다. 
각 줄마다  개의 정수가 주어진다. 
번 줄의 번째 숫자는 행 열에 위치한 격자의 충격량을 의미한다. 
만약 충격량 정보가 이라면 해당 격자는 벽임을 의미한다.

시작점과 도착점의 충격량은 0 임이 보장된다.

출력
첫 번째 줄에 호석사우루스가 탈출하는 과정에서 받는 최소 충격량을 출력한다. 
만약 탈출하지 못한다면  을 출력한다.

제한
1 ≤ ,  ≤ 100
1 ≤  ≤ 
1 ≤  ≤ 
-1 ≤ 각 칸의 충격량 ≤ 300
예제 입력 1 
5 5
1 1 5 5
0 -1 1 -1 1
1 1 1 1 1
-1 1 1 1 1
1 1 -1 1 1
1 1 1 1 0
예제 출력 1 
7
(1, 1) -> (2, 1) -> (2, 2) -> (2, 3) -> (3, 3) -> (3, 4) -> (4, 4) -> (5, 4) -> (5, 5)

8번 만에 갈 수 있고 이게 최소이다.

예제 입력 2 
4 4
1 1 1 4
0 1 1 0
1 1 -1 -1
-1 -1 -1 -1
-1 -1 -1 -1
예제 출력 2 
7
예제 입력 3 
2 6
1 1 2 6
0 2 1 3 1 5
6 1 2 1 0 0
예제 출력 3 
14
예제 입력 4 
4 2
3 1 1 1
0 -1
1 0
0 0
0 0
예제 출력 4 
1

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 호석사우루스_22255 {
	
	private static final int[][] dx = {
			{-1, 1, 0, 0},
			{-1, 1},
			{0, 0}
	};
	private static final int[][] dy = {
			{0, 0, -1, 1},
			{0, 0},
			{-1, 1}
	};
	private static final int DIRECTION = 3;
	
	private static int N;
	private static int M;
	private static Pos startPos;
	private static Pos endPos;
	private static int[][] map;
	private static boolean[][][] visited;
	
	private static class Pos implements Comparable<Pos>{
		int x;
		int y;
		int moveCount;
		int impulse;
		
		public Pos(int x, int y, int moveCount, int impulse) {
			this.x = x;
			this.y = y;
			this.moveCount = moveCount;
			this.impulse = impulse;
		}
		
		@Override
		public int compareTo(Pos o) {
			// TODO Auto-generated method stub
			return impulse - o.impulse;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M + 1];
		visited = new boolean[3][N + 1][M + 1];
		
		st = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(st.nextToken());
		int startY = Integer.parseInt(st.nextToken());
		startPos = new Pos(startX, startY, 1, 0);
		
		int endX = Integer.parseInt(st.nextToken());
		int endY = Integer.parseInt(st.nextToken());
		endPos = new Pos(endX, endY, 0, 0);
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = escape();
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
		
		br.close();
	}
	
	private static int escape() {
		PriorityQueue<Pos> q = new PriorityQueue<Pos>();
		int impulse = Integer.MAX_VALUE;
		q.add(startPos);
		
		while(!q.isEmpty()) {
			Pos pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			int moveCnt = pos.moveCount;
			
//			System.out.println(x + ", " + y + ", " + "방향 : " + moveCnt + ", value : " + pos.impulse);
			
			if (x == endPos.x && y == endPos.y) {
				impulse = Math.min(impulse, pos.impulse);
			}
			
			if (visited[moveCnt][x][y]) {
				continue;
			}
			visited[moveCnt][x][y] = true;
			
			for (int i = 0; i < dx[moveCnt].length; i++) {
				int X = x + dx[moveCnt][i];
				int Y = y + dy[moveCnt][i];
				
				if (!isValid(X, Y, (moveCnt + 1) % DIRECTION)) {
					continue;
				}
				
				q.add(new Pos(X, Y, (moveCnt + 1) % DIRECTION, pos.impulse + map[X][Y]));
			}
		}
		
		return impulse;
	}
	
	private static boolean isValid(int X, int Y, int moveCnt) {
		if (X > 0 && X <= N && Y > 0 && Y <= M && !visited[moveCnt][X][Y] && map[X][Y] > -1) {
			return true;
		}
		
		return false;
	}
}