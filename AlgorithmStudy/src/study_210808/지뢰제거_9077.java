package study_210808;
/*

지뢰제거 출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
10 초	512 MB	510	136	95	37.698%
문제


지뢰제거를 위해서 새로운 장비가 투입되었다. 
이 장비를 이용하면 10m × 10m 정사각형 범위 안(경계 포함)에 있는 지뢰를 한꺼번에 제거할 수 있다. 
10,000m × 10,000m의 작업장에 묻힌 지뢰의 위치를 모두 알고 있다고 할 때, 
이 장치를 효과적으로 사용하기 위해서 한 번 사용하여 제거할 수 있는 최대 지뢰 개수를 계산하는 프로그램을 작성하시오. 
위의 그림은 아래 "예제 입력"의 세 번째 경우를 나타낸 것이다. 
그림에서 보이는 정사각형 영역에 이 장비를 사용하면 다섯 개의 지뢰를 한꺼번에 제거할 수 있으며, 
이 수가 한 번 사용하여 제거할 수 있는 최대 지뢰 개수이다.

입력
입력의 첫 줄에는 테스트 케이스의 개수 T(1 ≤ T ≤ 10)가 주어진다. 
각 테스트 케이스는 한 줄에 지뢰의 개수를 나타내는 하나의 정수 N (4 ≤ N ≤ 100,000)이 주어진 다음, N개의 좌표가 한 줄에 하나씩 주어진다. 
각 좌표는 0이상 10,000이하의 두 정수로 주어지는데, 첫 번째 정수는 x-좌표를, 두 번째 정수는 y-좌표를 나타낸다. 
모든 정수 사이에는 한 칸의 공백이 존재한다. 
같은 좌표에 두 개 이상의 지뢰는 존재하지 않으며, 지뢰의 크기는 무시할 정도로 작다고 가정한다. 

출력
각 테스트 케이스에 대해서 한 번에 가장 많이 제거할 수 있는 지뢰의 수를 출력한다.

예제 입력 1 
3
4
10 10
20 20
30 30
40 40
15
36 33
15 27
35 43
42 36
21 49
27 12
9 40
26 13
26 40
36 22
18 11
29 17
30 32
23 12
35 17
27
40 10
26 11
6 13
53 15
18 16
23 18
33 16
42 20
10 21
3 27
6 43
13 37
16 27
15 46
23 26
23 49
30 23
30 37
33 47
37 23
40 40
46 48
40 29
43 28
49 25
46 30
44 33

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 지뢰제거_9077 {
	private static final int MAX_MAP = 10001;
	
	private static int T;
	private static int N;
	private static List<Pos> mine = new ArrayList<Pos>();
	private static boolean[][] map;
	private static int max = 0;
	
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
		T = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();
		StringTokenizer st;
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new boolean[MAX_MAP][MAX_MAP];
			mine.clear();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = true;
				mine.add(new Pos(x, y));				
			}
			
			selectMine();
			result.append(max).append("\n");
			max = 0;
		}
		
		System.out.println(result.toString().trim());
		
		br.close();
	}
	
	private static void selectMine() {
		for (int i = 0; i < N; i++) {
			Pos pos = mine.get(i);
			
			findSectionMine(pos);
		}
	}
	
	private static void findSectionMine(Pos pos) {
		int x = pos.x;
		int y = pos.y;
		int count = 0;
		
		findMine(x - 10, y - 10, true);
		findMine1(x, y, false);
	}
	
	private static void findMine(int X, int Y, boolean check) {
		if (!isValid(X, Y)) {
			X = stoi(X);
			Y = stoi(Y);
		}
		
		int count = 0;
		
		LOOP:
		for (int i = X; i <= X + 10; i++) {
			for (int j = Y; j <= Y + 10; j++) {
				if (!isValid(i, j)) {
					break LOOP;
				}
				
				if (map[i][j]) {
					count++;					
				}
			}
		}
		max = Math.max(max, count);
		
		int bottomX = X;
		int topX = X + 10;
		int bottomY = Y;
		int topY = Y + 11;
		
		while (bottomY <= Y + 10) {
			int bottomCnt = 0;
			int topCnt = 0;
			
			for (int i = X; i < X + 10; i++) {
				if (map[i][bottomY]) {
					bottomCnt ++;
				}
				
				if (map[i][topY]) {
					topCnt ++;
				}
			}
			
			count -= bottomCnt;
			count += topCnt;
			
			max = Math.max(max, count);
			bottomY ++;
			topY ++;
		}
	}
	
	private static void findMine1(int X, int Y, boolean check) {
		if (!isValid(X, Y)) {
			X = stoi(X);
			Y = stoi(Y);
		}
		
		int count = 0;
		
		LOOP:
		for (int i = X; i <= X + 10; i++) {
			for (int j = Y; j <= Y + 10; j++) {
				if (!isValid(i, j)) {
					break LOOP;
				}
				
				if (map[i][j]) {
					count++;					
				}
			}
		}
		max = Math.max(max, count);
		
		int bottomX = X;
		int topX = X + 10;
		int bottomY = Y;
		int topY = Y + 11;
		
		while (bottomY <= Y + 10) {
			int bottomCnt = 0;
			int topCnt = 0;
			
			for (int i = X; i < X + 10; i++) {
				if (map[i][bottomY]) {
					bottomCnt ++;
				}
				
				if (map[i][topY]) {
					topCnt ++;
				}
			}
			
			count -= bottomCnt;
			count += topCnt;
			
			max = Math.max(max, count);
			bottomY ++;
			topY ++;
		}
	}
	
	private static void findSectionMine2(Pos pos) {
		int x = pos.x;
		int y = pos.y;
		int count = 0;
		
		LOOP:
		for (int i = x; i <= x + 10; i++) {
			for (int j = y; j <= y + 10; j++) {
				if (!isValid(i, j)) {
					break LOOP;
				}
				
				if (map[i][j]) {
					count++;
				}
			}
		}
		
		max = Math.max(max, count);
		count = 0;
		
		LOOP:
		for (int i = x; i >= x - 10; i--) {
			for (int j = y; j <= y + 10; j++) {
				if (!isValid(i, j)) {
					break LOOP;
				}
				
				if (map[i][j]) {
					count++;
				}
			}
		}
		
		max = Math.max(max, count);
		count = 0;
		
		LOOP:
		for (int i = x; i <= x + 10; i++) {
			for (int j = y; j >= y - 10; j--) {
				if (!isValid(i, j)) {
					break LOOP;
				}
				
				if (map[i][j]) {
					count++;
				}
			}
		}
		
		max = Math.max(max, count);
		count = 0;
		
		LOOP:
		for (int i = x; i >= x - 10; i--) {
			for (int j = y; j >= y - 10; j--) {
				if (!isValid(i, j)) {
					break LOOP;
				}
				
				if (map[i][j]) {
					count++;
				}
			}
		}
		max = Math.max(max, count);
	}
	
	private static int stoi(int x) {
		if (x < 0) {
			return x;
		}
		
		if (x >= MAX_MAP) {
			return MAX_MAP - 1;
		}
		
		return x;
	}
	
	private static boolean isValid(int X, int Y) {
		if (X >= 0 && X < MAX_MAP && Y >= 0 && Y < MAX_MAP) {
			return true;
		}
		
		return false;
	}
}
