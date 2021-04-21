package boj_20210420_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*

백조의 호수 출처다국어분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	11579	2418	1380	19.140%
문제
두 마리의 백조가 호수에서 살고 있었다. 그렇지만 두 마리는 호수를 덮고 있는 빙판으로 만나지 못한다.

호수는 행이 R개, 열이 C개인 직사각형 모양이다. 어떤 칸은 얼음으로 덮여있다.

호수는 차례로 녹는데, 매일 물 공간과 접촉한 모든 빙판 공간은 녹는다. 두 개의 공간이 접촉하려면 가로나 세로로 닿아 있는 것만 (대각선은 고려하지 않는다) 생각한다.

아래에는 세 가지 예가 있다.

...XXXXXX..XX.XXX ....XXXX.......XX .....XX.......... 
....XXXXXXXXX.XXX .....XXXX..X..... ......X.......... 
...XXXXXXXXXXXX.. ....XXX..XXXX.... .....X.....X..... 
..XXXXX..XXXXXX.. ...XXX....XXXX... ....X......XX.... 
.XXXXXX..XXXXXX.. ..XXXX....XXXX... ...XX......XX.... 
XXXXXXX...XXXX... ..XXXX.....XX.... ....X............ 
..XXXXX...XXX.... ....XX.....X..... ................. 
....XXXXX.XXX.... .....XX....X..... ................. 
      처음               첫째 날             둘째 날
백조는 오직 물 공간에서 세로나 가로로만(대각선은 제외한다) 움직일 수 있다.

며칠이 지나야 백조들이 만날 수 있는 지 계산하는 프로그램을 작성하시오.

입력
입력의 첫째 줄에는 R과 C가 주어진다. 단, 1 ≤ R, C ≤ 1500.

다음 R개의 줄에는 각각 길이 C의 문자열이 하나씩 주어진다. '.'은 물 공간, 'X'는 빙판 공간, 'L'은 백조가 있는 공간으로 나타낸다.

출력
첫째 줄에 문제에서 주어진 걸리는 날을 출력한다.

예제 입력 1 
8 17
...XXXXXX..XX.XXX
....XXXXXXXXX.XXX
...XXXXXXXXXXXX..
..XXXXX.LXXXXXX..
.XXXXXX..XXXXXX..
XXXXXXX...XXXX...
..XXXXX...XXX....
....XXXXX.XXXL...
예제 출력 1 
2

*/

public class 백조의호수_3197 {
	private static int R, C;
	private static char map[][];
	private static char copyMap[][];
	private static boolean visit[][];
	private static List<Pair> swan = new ArrayList<Pair>();
	private static Queue<Pair> s = new LinkedList<Pair>();
	private static Queue<Pair> water = new LinkedList<Pair>();
	
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
		copyMap = new char[R][C];
		
		for(int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j < C; j++) {
				copyMap[i][j] = map[i][j];
				if(map[i][j] == 'L') {
					swan.add(new Pair(i, j));
				}else if(map[i][j] == '.') {
					water.add(new Pair(i, j));
				}
			}
		}
		int count = 0;
		
		while(true) {
			count++;
			
			melting();
			
//			for(int i = 0; i < R; i++) {
//				for(int j = 0; j < C; j++) {
//					if(map[i][j] == '.') {
//						melt(i, j);
//					}
//				}
//			}
			
			
			if(isMeet()) {
				break;
			}
			
			copyMap();
		}
		
		System.out.println(count);
		
		br.close();
	}
	private static void melting() {
		int size = water.size();
		for(int i = 0; i < size; i++) {
			Pair p = water.poll();
			
			for(int j = 0; j < 4; j++) {
				int X = p.x + dx[j];
				int Y = p.y + dy[j];
				if(X >= 0 && X < R && Y >= 0 && Y < C && map[X][Y] == 'X') {
					copyMap[X][Y] = '.';
					water.add(new Pair(X, Y));
				}
			}
		}
	}
	
	private static void melt(int x, int y) {
		for(int i = 0; i < 4; i++) {
			int X = x + dx[i];
			int Y = y + dy[i];
			if(X >= 0 && X < R && Y >= 0 && Y < C && map[X][Y] == 'X') {
				copyMap[X][Y] = '.';
			}
		}
	}
	
	private static boolean isMeet() {
		Queue<Pair> q = new LinkedList<Pair>();
		s.add(new Pair(swan.get(0).x, swan.get(0).y));
		boolean visited[][] = new boolean[R][C];
		visited[swan.get(0).x][swan.get(0).y] = true;

		while(!s.isEmpty()) {
			Pair p = s.poll();
			
			if(p.x == swan.get(1).x && p.y == swan.get(1).y) {
				return true;
			}
			for(int i = 0; i < 4; i++) {
				int X = p.x + dx[i];
				int Y = p.y + dy[i];
				
				if(X >= 0 && X < R && Y >= 0 && Y < C && !visited[X][Y]) {
					if(copyMap[X][Y] == 'X') {
						q.add(p);
						visited[X][Y] = true;
					}
					
					if(copyMap[X][Y] != 'X') {
						visited[X][Y] = true;
						s.add(new Pair(X, Y));
					}
				}
			}
		}
		
		s = q;
		
		return false;
	}
	
	private static void copyMap() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				map[i][j] = copyMap[i][j];
			}
		}
	}
	
	private static void print() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
