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

통나무 옮기기 출처분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	6751	2044	1454	28.889%
문제
가로와 세로의 길이가 같은 평지에서 벌목을 한다. 그 지형은 0과 1로 나타나 있다. 
1은 아직 잘려지지 않은 나무를 나타내고 0은 아무 것도 없음을 나타낸다. 다음 지형을 보자.

B 0 0 1 1
B 0 0 0 0
B 0 0 0 0
1 1 0 0 0
E E E 0 0
위의 지형에서 길이 3인 통나무 BBB를 밀거나 회전시켜 EEE의 위치로 옮기는 작업을 하는 문제를 생각해 보자. 
BBB와 EEE의 위치는 임의로 주어진다. 
단 문제에서 통나무의 길이는 항상 3이며 B의 개수와 E의 개수는 같다. 
통나무를 움직이는 방법은 아래와 같이 상하좌우(Up, Down, Left, Right)와 회전(Turn)이 있다.

코드	의미
U	통나무를 위로 한 칸 옮긴다.
D	통나무를 아래로 한 칸 옮긴다.
L	통나무를 왼쪽으로 한 칸 옮긴다.
R	통나무를 오른쪽으로 한 칸 옮긴다.
T	중심점을 중심으로 90도 회전시킨다.
예를 들면, 다음과 같다. (초기상태로부터의 이동)

초기상태	상(U)	하(D)	좌(L)	우(R)	회전(T)
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 B B B 0 0
0 0 0 0 0 0
0 0 0 1 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 B B B 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 1 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 B B B 0 0
0 0 0 1 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
B B B 0 0 0
0 0 0 0 0 0
0 0 0 1 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 B B B 0
0 0 0 0 0 0
0 0 0 1 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 B 0 0 0
0 0 B 0 0 0
0 0 B 0 0 0
0 0 0 1 0 0
이와 같은 방식으로 이동시킬 때에 그 움직일 위치에 다른 나무, 즉 1이 없어야만 움직일 수 있다. 
그리고 움직임은 위의 그림과 같이 한 번에 한 칸씩만 움직인다. 
단 움직이는 통나무는 어떤 경우이든지 중간단계에서 한 행이나 한 열에만 놓일 수 있다. 
예를 들면 아래 그림에서 a와 같은 단계는 불가능하다. 
그리고 회전의 경우에서는 반드시 중심점을 중심으로 90도 회전을 해야 한다. (항상 통나무의 길이가 3이므로 중심점이 있음)

그리고 이런 회전(Turn)이 가능하기 위해서는 그 통나무를 둘러싸고 있는 3*3 정사각형의 구역에 단 한 그루의 나무도 없어야만 한다. 
즉, 아래그림 b, d와 같이 ?로 표시된 지역에 다른 나무, 즉 1이 없어야만 회전시킬 수 있다. 
따라서 c와 같은 경우에, 통나무는 왼쪽 아직 벌채되지 않은 나무 때문에 회전시킬 수 없다.

a	b	c	d
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 B 0 0
0 0 B 0 0 0
0 B 0 0 0 0
0 0 0 1 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 ? ? ? 0
0 0 B B B 0
0 0 ? ? ? 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 1 B 0 0
0 0 0 B 0 0
0 0 0 B 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 ? B ? 0
0 0 ? B ? 0
0 0 ? B ? 0
0 0 0 0 0 0
문제는 통나무를 5개의 기본동작(U, D, L, R, T)만을 사용하여 처음위치(BBB)에서 최종위치(EEE)로 옮기는 프로그램을 작성하는 것이다. 
단, 최소 횟수의 단위 동작을 사용해야 한다.

입력
첫째 줄에 주어진 평지의 한 변의 길이 N이 주어진다. (4<=N<=50) 주어진다.
이어서 그 지형의 정보가 0, 1, B, E로 이루어진 문자열로 주어진다.
한 줄에 입력되는 문자열의 길이는 N이며 입력 문자 사이에는 빈칸이 없다.
통나무와 최종 위치의 개수는 1개이다.

출력
첫째 줄에 최소 동작 횟수를 출력한다. 이동이 불가능하면 0만을 출력한다.

예제 입력 1 
5
B0011
B0000
B0000
11000
EEE00
예제 출력 1 
9

5
B0011
B0000
B0000
11110
EEE00

*/

public class 통나무옮기기_1938 {
	private static int N;
	private static int map[][];
	private static boolean visited[][][];
	private static int log = 0;
	private static List<Log> logList = new ArrayList<Log>();
	private static List<Log> finalPosition = new ArrayList<Log>();
	private static class Log {
		int x, y, log;
		public Log(int x, int y, int log) {
			this.x = x;
			this.y = y;
		}
		
		// 생성자 오버로딩 - 깊은 복사
		public Log(Log l) {
			this.x = l.x;
			this.y = l.y;
		}
		
	}
	
	private static class Move {
		List<Log> list = new ArrayList<Log>();
		int cnt, log;
		public Move(List<Log> list, int cnt, int log) {
			this.list = list;
			this.cnt = cnt;
			this.log = log;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[2][N][N];
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < N; j++) {
				char ch = input.charAt(j);
				if(ch == 'B') {
					map[i][j] = 5;
					logList.add(new Log(i, j, log));
				}else if(ch == 'E') {
					map[i][j] = 9;
					finalPosition.add(new Log(i, j, log));
				}else {
					map[i][j] = ch - '0';					
				}
			}
		}
		
		bfs();
		
//		printVisited();
		
		br.close();
	}
	
	private static void bfs() {
		Queue<Move> q = new LinkedList<Move>();
		q.add(new Move(logList, 0, log));
		boolean checked = false;
		visited[log][logList.get(1).x][logList.get(1).y] = true;
		
		while(!q.isEmpty()) {
			Move l = q.poll();
			
			for(int i = 0; i < 3; i++) {
				if(l.list.get(i).x == finalPosition.get(i).x && l.list.get(i).y == finalPosition.get(i).y) {
					checked = true;
				}else {
					checked = false;
					break;
				}
			}
			
			if(checked) {
				System.out.println(l.cnt);
				break;
			}
			
			for(int i = 0; i < 5; i++) {
				List<Log> list = new ArrayList<Log>();
				for(int j = 0; j < 3; j++) {
					list.add(new Log(l.list.get(j)));
				}
				if(i == 0) {
					if(up(list, l.log)) {
						q.add(new Move(list, l.cnt+1, l.log));
					}
				}else if(i == 1) {
					if(down(list, l.log)) {
						q.add(new Move(list, l.cnt+1, l.log));
					}
				}else if(i == 2) {
					if(left(list, l.log)) {
						q.add(new Move(list, l.cnt+1, l.log));
					}
				}else if(i == 3) {
					if(right(list, l.log)) {
						q.add(new Move(list, l.cnt+1, l.log));
					}
				}else if(i == 4) {
					if(existTree(list.get(1).x, list.get(1).y, l.log)) {
						rotate(list);
						if(l.log == 0) {
							q.add(new Move(list, l.cnt+1, 1));
						}else {
							q.add(new Move(list, l.cnt+1, 0));
						}
					}
				}
			}
		}
		
		if(!checked) {
			System.out.println(0);
		}
	}
	
	private static boolean up(List<Log> list, int log) {
		int x = list.get(0).x;
		int y = list.get(0).y;
		
		if(x <= 0) {
			return false;
		}
		
		for(int i = 0; i < 3; i++) {
			if(map[list.get(i).x-1][list.get(i).y] == 1) 
				return false;
		}

		if(!visited[log][list.get(1).x-1][list.get(1).y]) {
			visited[log][list.get(1).x-1][list.get(1).y] = true;
			for(int i = 0; i < 3; i++) {
				list.get(i).x = list.get(i).x - 1;
			}
		}else {
			return false;
		}
		
		return true;
	}
	
	private static boolean down(List<Log> list, int log) {
		int x = list.get(2).x;
		int y = list.get(2).y;
		
		if(x >= N - 1) {
			return false;
		}
		
		for(int i = 0; i < 3; i++) {
			if(map[list.get(i).x+1][list.get(i).y] == 1) 
				return false;
		}

		if(!visited[log][list.get(1).x+1][list.get(1).y]) {
			visited[log][list.get(1).x+1][list.get(1).y] = true;
			for(int i = 0; i < 3; i++) {
				list.get(i).x = list.get(i).x + 1;
			}
		}else {
			return false;
		}
		return true;
	}
	
	private static boolean left(List<Log> list, int log) {
		int x = list.get(0).x;
		int y = list.get(0).y;
		
		if(y <= 0) {
			return false;
		}
		
		for(int i = 0; i < 3; i++) {
			if(map[list.get(i).x][list.get(i).y-1] == 1) 
				return false;
		}

		if(!visited[log][list.get(1).x][list.get(1).y-1]) {
			visited[log][list.get(1).x][list.get(1).y-1] = true;
			for(int i = 0; i < 3; i++) {
				list.get(i).y = list.get(i).y - 1;
			}
		}else {
			return false;
		}
		
		return true;
	}
	
	private static boolean right(List<Log> list, int log) {
		int x = list.get(2).x;
		int y = list.get(2).y;
		
		if(y >= N - 1) {
			return false;
		}
		
		for(int i = 0; i < 3; i++) {
			if(map[list.get(i).x][list.get(i).y+1] == 1) 
				return false;
		}

		if(!visited[log][list.get(1).x][list.get(1).y+1]) {
			visited[log][list.get(1).x][list.get(1).y+1] = true;
			for(int i = 0; i < 3; i++) {
				list.get(i).y = list.get(i).y + 1;
			}
		}else {
			return false;
		}
		
		return true;
	}
	
	private static void rotate(List<Log> list) {
		int firstX = list.get(0).x;
		int firstY = list.get(0).y;
		
		int lastX = list.get(2).x;
		int lastY = list.get(2).y;
		
		// ㅣ
		if(firstX + 1 == list.get(1).x && firstY + 1 < N && lastY - 1 >= 0) {
			list.get(0).x = list.get(0).x + 1;
			list.get(0).y = list.get(0).y - 1;
			
			list.get(2).x = list.get(2).x - 1;
			list.get(2).y = list.get(2).y + 1;
			
		// ㅡ
		}else if(firstY + 1 == list.get(1).y && firstX - 1 >= 0 && lastX + 1< N) {
			list.get(0).x = list.get(0).x - 1;
			list.get(0).y = list.get(0).y + 1;
			
			list.get(2).x = list.get(2).x + 1;
			list.get(2).y = list.get(2).y - 1;
		}
	}
	
	private static boolean existTree(int x, int y, int log) {
		int ck = 0;
		if(x <= 0 || x >= N-1 || y <= 0 || y >= N-1) {
			return false;
		}
		if(log == 0) {
			ck = 1;
		}else {
			ck = 0;
		}
		if(visited[ck][x][y]) {
			return false;
		}
		
		for(int i = x-1; i <= x+1; i++) {
			for(int j = y-1; j <= y+1; j++) {
				if(map[i][j] == 1) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private static void printLogPosition(List<Log> list, int log) {
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).x + ", " + list.get(i).y + ", log : " + log);
		}
	}
	
	private static void print() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static void printVisited() {
		for(int i = 0; i < N; i++ ) {
			for(int j = 0; j < N; j++) {
				System.out.print(visited[0][i][j] + " ");
			}
			System.out.println();
		}
	}
}
