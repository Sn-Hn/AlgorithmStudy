package study_210815;

/*

미친 아두이노 출처다국어
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	1499	426	306	25.585%
문제
요즘 종수는 아두이노를 이용해 "Robots"이라는 게임을 만들었다. 
종수는 아두이노 한대를 조정하며, 미친 아두이노를 피해다녀야 한다. 
미친 아두이노는 종수의 아두이노를 향해 점점 다가온다. 하지만, 미친 아두이노의 움직임은 예측할 수 있다.

게임은 R×C크기의 보드 위에서 이루어지며, 아래와 같은 5가지 과정이 반복된다.

먼저, 종수가 아두이노를 8가지 방향(수직,수평,대각선)으로 이동시키거나, 그 위치에 그대로 놔둔다.
종수의 아두이노가 미친 아두이노가 있는 칸으로 이동한 경우에는 게임이 끝나게 되며, 종수는 게임을 지게 된다.
미친 아두이노는 8가지 방향 중에서 종수의 아두이노와 가장 가까워 지는 방향으로 한 칸 이동한다. 
즉, 종수의 위치를 (r1,s1), 미친 아두이노의 위치를 (r2, s2)라고 했을 때, |r1-r2| + |s1-s2|가 가장 작아지는 방향으로 이동한다.
미친 아두이노가 종수의 아두이노가 있는 칸으로 이동한 경우에는 게임이 끝나게 되고, 종수는 게임을 지게 된다.
2개 또는 그 이상의 미친 아두이노가 같은 칸에 있는 경우에는 큰 폭발이 일어나고, 그 칸에 있는 아두이노는 모두 파괴된다.
종수의 시작 위치, 미친 아두이노의 위치, 종수가 움직이려고 하는 방향이 주어진다. 
입력으로 주어진 방향대로 종수가 움직였을 때, 보드의 상태를 구하는 프로그램을 작성하시오. 중간에 게임에서 지게된 경우에는 몇 번째 움직임에서 죽는지를 구한다.

입력
첫째 줄에 보드의 크기 R과 C가 주어진다. (1 ≤ R, C ≤ 100)

다음 R개 줄에는 C개의 문자가 주어지며, 보드의 상태이다. '.'는 빈 칸, 'R'은 미친 아두이노, 'I'는 종수의 위치를 나타낸다.

마지막 줄에는 길이가 100을 넘지않는 문자열이 주어지며, 종수가 움직이려고 하는 방향이다. 5는 그 자리에 그대로 있는 것을 나타내고, 나머지는 아래와 같은 방향을 나타낸다.



보드를 벗어나는 입력은 주어지지 않는다.

출력
중간에 게임이 끝나는 경우에는 "kraj X"를 출력한다. X는 종수가 게임이 끝나기 전 까지 이동한 횟수이다. 그 외의 경우에는 보드의 상태를 입력과 같은 형식으로 출력한다.

예제 입력 1 
4 5
I....
.....
.R.R.
.....
6
예제 출력 1 
.I...
.RR..
.....
.....
예제 입력 2 
9 10
..........
.........R
..........
R.........
R...I.....
R.........
..........
.........R
....R.....
5558888
예제 출력 2 
....I.....
....R.....
..........
..........
..........
..........
..........
..........
..........
예제 입력 3 
12 8
...I....
........
........
........
........
RR......
......RR
R......R
........
........
........
...R....
66445394444162
예제 출력 3 
kraj 11

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미친아두이노_8972 {
	private static final int[] dx = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
	private static final int[] dy = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
	
	private static int R;
	private static int C;
	private static char[][] map;
	private static String moveDir;
	private static Pos jongsuArduio;
	private static List<Pos> crazyArduino = new ArrayList<Pos>();
	private static List<Pos> removeCrazy = new ArrayList<Pos>();
	
	private static class Pos {
		int x;
		int y;
		
		public Pos(int x, int y) {
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
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'I') {
					jongsuArduio = new Pos(i, j);
				}else if (map[i][j] == 'R') {
					crazyArduino.add(new Pos(i, j));
				}
			}
		}
		
		moveDir = br.readLine();
		
		runGame();
		
		br.close();
	}
	
	private static void runGame() {
		for (int i = 0; i < moveDir.length(); i++) {
			int dir = Character.getNumericValue(moveDir.charAt(i));
			
			if (endGame(dir, i)) {
				return;
			}
		}

		print();
	}
	
	private static boolean endGame(int dir, int index) {
		if (!isMoveJongsu(dir) || !isMoveCrazyArduino()) {
			System.out.println("kraj " + (index + 1));
			return true;
		}
		
		return false;
	}
	
	private static boolean isMoveJongsu(int dir) {
		int x = jongsuArduio.x;
		int y = jongsuArduio.y;
		
		int X = x + dx[dir];
		int Y = y + dy[dir];
		
		if (map[X][Y] == 'R') {
			return false;
		}
		
		map[x][y] = '.';
		map[X][Y] = 'I';
		
		jongsuArduio.x = X;
		jongsuArduio.y = Y;
		
		return true;
	}
	
	private static boolean isMoveCrazyArduino() {
		for (int i = 0; i < crazyArduino.size(); i++) {
			int x = crazyArduino.get(i).x;
			int y = crazyArduino.get(i).y;
			map[x][y] = '.';
			
			int X = moveCrazyArduino(x, jongsuArduio.x);
			int Y = moveCrazyArduino(y, jongsuArduio.y);
			
			if (map[X][Y] == 'I') {
				return false;
			}
			
			crazyArduino.get(i).x = X;
			crazyArduino.get(i).y = Y;
		}
		
		return isMoveCrazyArduinoAfterMap();
	}
	
	private static boolean isMoveCrazyArduinoAfterMap() {
		removeCrazy.clear();
		for (int i = 0; i < crazyArduino.size(); i++) {
			int x = crazyArduino.get(i).x;
			int y = crazyArduino.get(i).y;
			
			if (map[x][y] == 'R') {
				removeCrazy.add(new Pos(x, y));
				continue;
			}
			
			map[x][y] = 'R';
		}
		
		removeCrazy();
		refreshCrazyArduinoPos();
		return true;
	}
	
	private static void removeCrazy() {
		for (int i = 0; i < removeCrazy.size(); i++) {
			int x = removeCrazy.get(i).x;
			int y = removeCrazy.get(i).y;
			if (map[x][y] == 'R') {
				map[x][y] = '.';
			}
		}
	}
	
	private static void refreshCrazyArduinoPos() {
		crazyArduino.clear();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'R') {
					crazyArduino.add(new Pos(i, j));
				}
			}
		}
	}
	
	private static int moveCrazyArduino(int x, int compareX) {
		if (x > compareX) {
			return x - 1;
		}else if (x == compareX) {
			return x;
		}
		
		return x + 1;
	}
	
	private static void print() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				result.append(map[i][j]);
			}
			result.append("\n");
		}
		
		System.out.println(result.toString().trim());
	}
}
