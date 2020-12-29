package boj_dfs_20201229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*

미네랄 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	6079	1581	978	23.859%
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

마지막 줄에는 막대를 던진 높이가 주어지며, 공백으로 구분되어져 있다. 모든 높이는 1과 R사이이며, 높이 1은 행렬의 가장 바닥, R은 가장 위를 의미한다. 첫 번째 막대는 왼쪽에서 오른쪽으로 던졌으며, 두 번째는 오른쪽에서 왼쪽으로, 이와 같은 식으로 번갈아가며 던진다.

공중에 떠 있는 미네랄 클러스터는 없으며, 두 개 또는 그 이상의 클러스터가 동시에 떨어지는 경우도 없다. 클러스터가 떨어질 때, 그 클러스터 각 열의 맨 아래 부분 중 하나가 바닥 또는 미네랄 위로 떨어지는 입력만 주어진다.

출력
입력 형식과 같은 형식으로 미네랄 모양을 출력한다.

예제 입력 1 
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
예제 출력 1 
........
........
........
........
.....x..
..xxxx..
..xxx.x.
..xxxxx.

*/

// 메모리 초과
public class Mineral_2933 {
	private static int R, C, N;
	private static char map[][];
	private static boolean visited[][];
	private static int stick[];
	private static List<Pair> list = new ArrayList<Pair>();
	private static List<Pair> separateMineral = new ArrayList<Pair>();
	
	private static int dx[] = {1, -1, 0, 0};
	private static int dy[] = {0, 0, 1, -1};
	
	private static class Pair {
		int x, y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		for(int i = 0; i < R; i++) {
			String str = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		N = Integer.parseInt(br.readLine());
		stick = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			stick[i] = R-Integer.parseInt(st.nextToken());
		}
		int sum = 0;
				
		for(int i = 0; i < N; i++) {
			separateMineral.clear();
			removeMineral(stick[i], i);
			findList();
			floorMineral();
			if(!separateMineral.isEmpty()) {
				dropMineral();
			}
			
			
		}
		
		printMap();
		
		br.close();
	}
	
	// 미네랄 제거
	private static void removeMineral(int stick, int index) {
		if(index%2 == 0) {
			for(int j = 0; j < C; j++) {
				if(map[stick][j] == 'x') {
					map[stick][j] = '.';
					break;
				}
			}
		}else {
			for(int j = C-1; j >= 0; j--) {
				if(map[stick][j] =='x') {
					map[stick][j] = '.';
					break;
				}
			}
		}
		
	}
	
	// 바닥에 붙어있는 미네랄 방문처리
	private static void floorMineral() {
		visited = new boolean[R][C];
		Queue<Pair> q = new LinkedList<Pair>();
		
		// 바닥에 붙어있는 미네랄
		for(int i = 0; i < C; i++) {
			if(map[R-1][i] == 'x') {
				q.add(new Pair(R-1, i));
			}
		}
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			
			visited[p.x][p.y] = true;
			
			for(int i = 0; i < 4; i++) {
				int X = p.x + dx[i];
				int Y = p.y + dy[i];
				
				if(X >= 0 && X < R && Y >= 0 && Y < C && !visited[X][Y]) {
					if(map[X][Y] == 'x') {
						q.add(new Pair(X, Y));
					}
				}
			}
		}
		
		// 미네랄이 들어 있는 리스트에서 방문하지 않은 list를 dfs돌려 분리되어 있는 미네랄을 찾음
		for(Pair p : list) {
			if(!visited[p.x][p.y]) {
				separateMineral.add(new Pair(p.x, p.y));
			}
		}
	}
	
	// 바닥에 붙어있지 않은 미네랄
	private static void findMineral(int x, int y) {
		visited[x][y] = true;
		separateMineral.add(new Pair(x, y));
		for(int i = 0; i < 4; i++) {
			int X = x + dx[i];
			int Y = y + dy[i];
			
			if(X >= 0 && X < R && Y >= 0 && Y < C && !visited[X][Y]) {
				if(map[X][Y] == 'x') {
					findMineral(X, Y);
				}
			}
		}
	}
	
	// 미네랄을 찾는 메소드
	private static void findList() {
		list.clear();
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == 'x') {
					list.add(new Pair(i, j));
				}
			}
		}
	}
	
	// 분리된 미네랄을 떨어뜨린다.
	private static void dropMineral() {
		int drop = 0;
		for(Pair p : separateMineral) {
			map[p.x][p.y] = '.'; 
		}
		
		LOOP:
		for(int i = 1; i < R; i++ ) {
			for(Pair p : separateMineral) {
				if(p.x + i >= R || map[p.x + i][p.y] == 'x') {
					break LOOP;
				}
			}
			drop = i;
		}
		
		
		for(Pair p : separateMineral) {
			map[p.x+drop][p.y] = 'x'; 
		}
		
		
	}
	
	private static void printMap() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
