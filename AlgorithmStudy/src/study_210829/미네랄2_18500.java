package study_210829;

/*

미네랄 2 출처다국어
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	512 MB	1088	376	294	34.958%
문제
창영과 상근은 한 동굴을 놓고 소유권을 주장하고 있다. 
두 사람은 막대기를 서로에게 던지는 방법을 이용해 누구의 소유인지를 결정하기로 했다. 
싸움은 동굴에서 벌어진다. 
동굴에는 미네랄이 저장되어 있으며, 던진 막대기가 미네랄을 파괴할 수도 있다.

동굴은 R행 C열로 나타낼 수 있으며, R×C칸으로 이루어져 있다. 
각 칸은 비어있거나 미네랄을 포함하고 있으며, 네 방향 중 하나로 인접한 미네랄이 포함된 두 칸은 같은 클러스터이다.

창영은 동굴의 왼쪽에 서있고, 상근은 오른쪽에 서있다. 
두 사람은 턴을 번갈아가며 막대기를 던진다. 
막대를 던지기 전에 던질 높이를 정해야 한다. 막대는 땅과 수평을 이루며 날아간다.

막대가 날아가다가 미네랄을 만나면, 그 칸에 있는 미네랄은 모두 파괴되고 막대는 그 자리에서 이동을 멈춘다.

미네랄이 파괴된 이후에 남은 클러스터가 분리될 수도 있다. 
새롭게 생성된 클러스터가 떠 있는 경우에는 중력에 의해서 바닥으로 떨어지게 된다. 
떨어지는 동안 클러스터의 모양은 변하지 않는다. 
클러스터는 다른 클러스터나 땅을 만나기 전까지 게속해서 떨어진다. 
클러스터는 다른 클러스터 위에 떨어질 수 있고, 그 이후에는 합쳐지게 된다.

동굴에 있는 미네랄의 모양과 두 사람이 던진 막대의 높이가 주어진다. 
모든 막대를 던지고 난 이후에 미네랄 모양을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 동굴의 크기 R과 C가 주어진다. (1 ≤ R,C ≤ 100)

다음 R개 줄에는 C개의 문자가 주어지며, '.'는 빈 칸, 'x'는 미네랄을 나타낸다.

다음 줄에는 막대를 던진 횟수 N이 주어진다. (1 ≤ N ≤ 100)

마지막 줄에는 막대를 던진 높이가 주어지며, 공백으로 구분되어져 있다. 
모든 높이는 1과 R사이이며, 높이 1은 행렬의 가장 바닥, R은 가장 위를 의미한다. 
첫 번째 막대는 왼쪽에서 오른쪽으로 던졌으며, 두 번째는 오른쪽에서 왼쪽으로, 이와 같은 식으로 번갈아가며 던진다.

공중에 떠 있는 미네랄 클러스터는 없으며, 두 개 또는 그 이상의 클러스터가 동시에 떨어지는 경우도 없다.

출력
입력 형식과 같은 형식으로 미네랄 모양을 출력한다.

예제 입력 1 
5 6
......
..xx..
..x...
..xx..
.xxxx.
1
3
예제 출력 1 
......
......
..xx..
..xx..
.xxxx.
예제 입력 2 
8 8
........
........
...x.xx.
...xxx..
..xxx...
..x.xxx.
..x...x.
.xxx..x.
5
6 6 4 3 1
예제 출력 2 
........
........
........
........
.....x..
..xxxx..
..xxx.x.
..xxxxx.
예제 입력 3 
7 6
......
......
xx....
.xx...
..xx..
...xx.
....x.
2
6 4
예제 출력 3 
......
......
......
......
..xx..
xx.xx.
.x..x.

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미네랄2_18500 {
	private static final int[] dr = {1, -1, 0, 0};
	private static final int[] dc = {0, 0, 1, -1};
	
	private static int R;
	private static int C;
	private static char[][] map;
	private static int N;
	private static int[] heights;
	private static List<Pos> separateMineral = new ArrayList<Pos>();
	private static StringBuilder resultMap = new StringBuilder();
	
	private static class Pos {
		int r;
		int c;
		
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		N = Integer.parseInt(br.readLine());
		heights = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());			
		}
		
		throwStick();
		
		print();
		
		System.out.println(resultMap);
		
		br.close();
	}
	
	private static void throwStick() {
		for (int i = 0; i < N; i++) {
			breakMineral(getHeight(heights[i]), i % 2);
			notSeparateMineral();
			if (separateMineral.isEmpty()) {
				continue;
			}
			int dropHeight = findDropHeight();
			dropSeparateMineral(dropHeight, 'x');
		}
	}
	
	// dir == 0 : 왼쪽
	// dir == 1 : 오른쪽
	private static void breakMineral(int height, int dir) {
		int i = 0;
		
		if (dir == 1) {
			i = C - 1;
		}
		
		int cnt = 0;
		while (cnt < C) {			
			if (map[height][i] == 'x') {
				map[height][i] = '.';
				break;
			}
			
			if (dir == 0) {
				i ++;
			}else {
				i --;
			}
			cnt++;
		}
	}
	
	private static void notSeparateMineral() {
		Queue<Pos> q = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		int height = R - 1;
		separateMineral.clear();
		
		for (int i = 0; i < C; i++) {
			if (map[height][i] == 'x') {
				q.add(new Pos(height, i));
				visited[height][i] = true;
			}
		}
		
		while (!q.isEmpty()) {
			Pos p = q.poll();
			int r = p.r;
			int c = p.c;
			
//			System.out.println(r + " , " + c);
			for (int i = 0; i < dr.length; i++) {
				int R = r + dr[i];
				int C = c + dc[i];
				
//				System.out.println(R + ", " + C);
				if (!isValid(R, C) || visited[R][C]) {
					continue;
				}
				
				q.add(new Pos(R, C));
				visited[R][C] = true;
			}
		}
		
		findSeparateMineral(visited);
	}
	
	private static void findSeparateMineral(boolean[][] visited) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'x' && !visited[i][j]) {
					separateMineral.add(new Pos(i, j));
//					System.out.println(i + ", " + j);
				}
			}
		}
	}
	
	private static int findDropHeight() {
		dropSeparateMineral(0, '.');
		
		int dropHeight = 1;
		
		Drop:
		while (true) {
			for (Pos pos : separateMineral) {
				int r = pos.r + dropHeight;
				int c = pos.c;
				
				if (r == R || map[r][c] == 'x') {
					break Drop;
				}
			}
			
			dropHeight++;
		}
		
		return dropHeight - 1;
	}
	
	private static void dropSeparateMineral(int dropHeight, char change) {
		for (Pos pos : separateMineral) {
			int r = pos.r + dropHeight;
			int c = pos.c;
			
			map[r][c] = change;
		}
	}
	
	private static boolean isValid(int r, int c) {
		if (r >= 0 && r < R && c >= 0 && c < C && map[r][c] == 'x') {
			return true;
		}
		
		return false;
	}
	
	private static int getHeight(int height) {
		return R - height;
	}
	
	private static void print() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				resultMap.append(map[i][j]);
			}
			resultMap.append("\n");
		}
	}
}
