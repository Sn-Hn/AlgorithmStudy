package study_210725;

/*

창영이와 퇴근 출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	512 MB	373	107	78	27.465%
문제
창영이의 퇴근길은 출근길과 조금 다르다. 
창영이는 건강을 위해 따릉이를 빌려 타고 퇴근하는 습관을 기르고 있다.

창영이의 퇴근길은 N×N 크기의 격자로 표현된다. 
창영이는 A1,1에서 출발하여 AN,N까지 이동할 계획이다. 
창영이는 상하좌우 인접한 격자로 한 번에 한 칸씩 이동할 수 있다. 
각 격자 Ar,c에는 자연수가 적혀 있는데, 이는 해당 지역의 높이를 뜻한다. 인접한 격자 사이의 높이 차이의 절댓값을 경사라고 하고, 경사가 클수록 경사가 가파르다고 하자.

따릉이는 가격에 따라 성능이 다르다. 
비싼 따릉이는 경사가 가파르더라도 내리지 않고 타고 갈 수 있지만, 값싼 따릉이는 경사가 가파르면 힘들고 위험하기 때문에 내려서 이동해야 한다.

창영이는 최소한의 비용으로 따릉이를 빌려서, 따릉이에서 한 번도 내리지 않고 집에 도착하고 싶다. 
그러기 위해선 창영이가 지날 수 있는 최대 경사의 최솟값을 알아야만 한다. 
여러분들이 창영이를 도와주자.

입력
첫째 줄에 격자의 크기 N이 주어진다.

둘째 줄부터 N개의 줄에 걸쳐 각 격자의 높이 정보가 주어진다. 
첫 번째로 주어지는 값이 A1,1이고, 마지막으로 주어지는 값이 AN,N이다.

출력
A1,1에서 AN,N까지, 경로상의 최대 경사의 최솟값을 출력한다.

제한
1 ≤ N ≤ 1,000
1 ≤ Ar,c ≤ 1,000,000,000
예제 입력 1 
4
1 1 1 1
1 1 1 1
1 1 1 1
1 1 1 1
예제 출력 1 
0
예제 입력 2 
3
3 4 3
2 5 2
5 2 2
예제 출력 2 
1

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 창영이와퇴근_22116 {
	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};
	
	private static int N;
	private static int map[][];
	private static boolean[][][] visited;
	private static int min = Integer.MAX_VALUE;
	
	private static class Pos implements Comparable<Pos> {
		int x;
		int y;
		int slope;
		
		public Pos(int x, int y, int slope) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.slope = slope;
		}
		
		@Override
		public int compareTo(Pos o) {
			// TODO Auto-generated method stub
			return slope - o.slope;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		visited = new boolean[4][N + 1][N + 1];
		StringTokenizer st = null;
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		leaveWork();
		
		System.out.println(min);
		
		br.close();
	}

	private static void leaveWork() {
		PriorityQueue<Pos> q = new PriorityQueue<Pos>();
		q.add(new Pos(1, 1, 0));
		
		while (!q.isEmpty()) {
			Pos pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			
			
			if (x == N && y == N) {
				min = Math.min(min, pos.slope);
				break;
			}
			
			for (int i = 0; i < dx.length; i++) {
				int X = x + dx[i];
				int Y = y + dy[i];
				
				if (isValid(X, Y, i)) {
					int slope = Math.abs(map[x][y] - map[X][Y]);
					visited[i][X][Y] = true;
					q.add(new Pos(X, Y, Math.max(slope, pos.slope)));
				}
			}
		}
	}
	
	private static boolean isValid(int X, int Y, int dir) {
		if (X > 0 && X <= N && Y > 0 && Y <= N && !visited[dir][X][Y]) {
			return true;
		}
		
		return false;
	}
}
