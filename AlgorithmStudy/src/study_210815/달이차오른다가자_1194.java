package study_210815;
/*

달이 차오른다, 가자.
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	9269	3415	2283	34.085%
문제
지금 민식이가 계획한 여행은 달이 맨 처음 뜨기 시작할 때 부터, 준비했던 여행길이다. 하지만, 매번 달이 차오를 때마다 민식이는 어쩔 수 없는 현실의 벽 앞에서 다짐을 포기하고 말았다.

민식이는 매번 자신의 다짐을 말하려고 노력했지만, 말을 하면 아무도 못 알아들을 것만 같아서, 지레 겁먹고 벙어리가 되어버렸다. 결국 민식이는 모두 잠든 새벽 네시 반쯤 홀로 일어나, 창 밖에 떠있는 달을 보았다.

하루밖에 남지 않았다. 달은 내일이면 다 차오른다. 이번이 마지막기회다. 이걸 놓치면 영영 못간다.

영식이는 민식이가 오늘도 여태것처럼 그냥 잠 들어버려서 못 갈지도 모른다고 생각했다. 하지만 그러기엔 민식이의 눈에는 저기 뜬 달이 너무나 떨렸다.

민식이는 지금 미로 속에 있다. 미로는 직사각형 모양이고, 여행길을 떠나기 위해 미로를 탈출하려고 한다. 미로는 다음과 같이 구성되어져있다.

빈 곳 : 언제나 이동할 수 있다. ('.‘로 표시됨)
벽 : 절대 이동할 수 없다. (‘#’)
열쇠 : 언제나 이동할 수 있다. 이 곳에 처음 들어가면 열쇠를 집는다. (a - f)
문 : 대응하는 열쇠가 있을 때만 이동할 수 있다. (A - F)
민식이의 현재 위치 : 빈 곳이고, 민식이가 현재 서 있는 곳이다. (숫자 0)
출구 : 달이 차오르기 때문에, 민식이가 가야하는 곳이다. 이 곳에 오면 미로를 탈출한다. (숫자 1)
달이 차오르는 기회를 놓치지 않기 위해서, 미로를 탈출하려고 한다. 한 번의 움직임은 현재 위치에서 수평이나 수직으로 한 칸 이동하는 것이다.

민식이가 미로를 탈출하는데 걸리는 이동 횟수의 최솟값을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 미로의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 50) 둘째 줄부터 N개의 줄에 미로의 모양이 주어진다.
같은 타입의 열쇠가 여러 개 있을 수 있고, 문도 마찬가지이다. 그리고, 영식이가 열쇠를 숨겨놓는 다면 문에 대응하는 열쇠가 없을 수도 있다. 0은 한 개, 1은 적어도 한 개 있다. 그리고, 열쇠는 여러 번 사용할 수 있다.

출력
첫째 줄에 민식이가 미로를 탈출하는데 드는 이동 횟수의 최솟값을 출력한다. 만약 민식이가 미로를 탈출 할 수 없으면, -1을 출력한다.

예제 입력 1 
1 7
f0.F..1
예제 출력 1 
7
예제 입력 2 
5 5
....1
#1###
.1.#0
....A
.1.#.
예제 출력 2 
-1
예제 입력 3 
7 8
a#c#eF.1
.#.#.#..
.#B#D###
0....F.1
C#E#A###
.#.#.#..
d#f#bF.1
예제 출력 3 
55

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 달이차오른다가자_1194 {
	private static final int dx[] = {1, -1, 0, 0};
	private static final int dy[] = {0, 0, 1, -1};
	private static final int FAIL = -1;
	
	private static int N;
	private static int M;
	private static char[][] map;
	private static boolean[][] visited;
	private static Pos startPos;
	
	private static class Pos {
		int x;
		int y;
		int moveCount;
		int[] keys = new int[6];
		boolean[][] visit = new boolean[N][M];
		
		public Pos(int x, int y, int moveCount) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.moveCount = moveCount;
		}
		
		public Pos(int x, int y, int moveCount, int[] k, boolean[][] v) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.moveCount = moveCount;
			this.keys = k.clone();
			copyVisit(v);
		}
		
		public void addKey(char key) {
			keys[ctoi(key)] ++;
		}
		
		public void initVisit() {
			for (int i = 0; i < N; i++) {
				Arrays.fill(visit[i], false);
			}
		}
		
		public void copyVisit(boolean[][] v) {
			for (int i = 0; i < v.length; i++) {
				for (int j = 0; j < v[0].length; j++) {
					visit[i][j] = v[i][j];
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '0') {
					startPos = new Pos(i, j, 0);
				}
			}
		}
		
		System.out.println(escapeMaze());
		
		
		br.close();
	}
	
	private static int escapeMaze() {
		Queue<Pos> q = new LinkedList<Pos>();
		q.add(startPos);
		int index = 0;
		
		while (!q.isEmpty()) {
			Pos pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			
			for (int i = 0; i < dx.length; i++) {
				int X = x + dx[i];
				int Y = y + dy[i];
				
				if (!isValid(X, Y)) {
					continue;
				}
				
				if (pos.visit[X][Y]) {
					continue;
				}
				
				
				if (map[X][Y] >= 'A' && map[X][Y] <= 'F' && !openDoor(pos.keys, map[X][Y])) {
					continue;
				}
				
				if (map[X][Y] >= 'a' && map[X][Y] <= 'f') {
					pos.addKey(map[X][Y]);
					pos.initVisit();
					System.out.println(index++ + ": " + X + ", " + Y + ", " + map[X][Y] + ", " + Arrays.toString(pos.keys) + ", " + (pos.moveCount + 1));
				}
				
				pos.visit[X][Y] = true;
//				System.out.println(index++ + ": " + X + ", " + Y + ", " + map[X][Y] + ", " + Arrays.toString(pos.keys));
				
				if (map[X][Y] == '1') {
					return pos.moveCount + 1;
				}
				
				pos.copyVisit(pos.visit);
				q.add(new Pos(X, Y, pos.moveCount + 1, pos.keys, pos.visit));
			}
		}
		
		return FAIL;
	}
	
	private static boolean openDoor(int[] keys, char key) {
		if (keys[CTOI(key)] != 0) {
			return true;
		}
		
		return false;
	}
	
	private static boolean isValid(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < M && map[x][y] != '#') {
			return true;
		}
		
		return false;
	}
	
	private static int ctoi(char ch) {
		return ch - 'a';
	}
	
	private static int CTOI(char ch) {
		return ch - 'A';
	}
}
