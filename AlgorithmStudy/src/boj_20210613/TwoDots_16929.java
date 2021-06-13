package boj_20210613;

/*

Two Dots 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	512 MB	3854	1755	1126	43.109%
문제
Two Dots는 Playdots, Inc.에서 만든 게임이다. 
게임의 기초 단계는 크기가 N×M인 게임판 위에서 진행된다.



각각의 칸은 색이 칠해진 공이 하나씩 있다. 
이 게임의 핵심은 같은 색으로 이루어진 사이클을 찾는 것이다.

다음은 위의 게임판에서 만들 수 있는 사이클의 예시이다.

	
점 k개 d1, d2, ..., dk로 이루어진 사이클의 정의는 아래와 같다.

모든 k개의 점은 서로 다르다. 
k는 4보다 크거나 같다.
모든 점의 색은 같다.
모든 1 ≤ i ≤ k-1에 대해서, di와 di+1은 인접하다. 
또, dk와 d1도 인접해야 한다. 
두 점이 인접하다는 것은 각각의 점이 들어있는 칸이 변을 공유한다는 의미이다.
게임판의 상태가 주어졌을 때, 사이클이 존재하는지 아닌지 구해보자.

입력
첫째 줄에 게임판의 크기 N, M이 주어진다. 
둘째 줄부터 N개의 줄에 게임판의 상태가 주어진다. 
게임판은 모두 점으로 가득차 있고, 게임판의 상태는 점의 색을 의미한다. 
점의 색은 알파벳 대문자 한 글자이다.

출력
사이클이 존재하는 경우에는 "Yes", 없는 경우에는 "No"를 출력한다.

제한
2 ≤ N, M ≤ 50
예제 입력 1 
3 4
AAAA
ABCA
AAAA
예제 출력 1 
Yes
예제 입력 2 
3 4
AAAA
ABCA
AADA
예제 출력 2 
No
예제 입력 3 
4 4
YYYR
BYBY
BBBY
BBBY
예제 출력 3 
Yes
예제 입력 4 
7 6
AAAAAB
ABBBAB
ABAAAB
ABABBB
ABAAAB
ABBBAB
AAAAAB
예제 출력 4 
Yes
예제 입력 5 
2 13
ABCDEFGHIJKLM
NOPQRSTUVWXYZ
예제 출력 5 
No

2 2
AA
AA

Yes

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TwoDots_16929 {
	private static int N;
	private static int M;
	private static char[][] map;
	private static boolean[][] visited;
	private static Node nowNode;
	private static boolean flag = false;
	
	private static int[] dx = {1, -1, 0, 0};
	private static int[] dy = {0, 0, 1, -1};
	
	private static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
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
		}
		
		StringBuilder sb = new StringBuilder();
		
		LOOP:
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					nowNode = new Node(i, j);
					findCycle(i, j, map[i][j], 0);
					if(flag) {
						break LOOP;
					}
					visited[i][j] = false;
				}
			}
		}
		
		if (!flag) {
			sb.append("No");
		}else {
			sb.append("Yes");
		}
		
		System.out.println(sb.toString());
		
		br.close();
	}
	
	private static void findCycle(int x, int y, char dot, int depth) {
		for (int i = 0; i < dx.length; i++) {
			int X = x + dx[i];
			int Y = y + dy[i];
			
			if (isCycle(X, Y) && depth >= 3) {
				flag = true;
				return;
			}
			
			
			if (isValidation(X, Y, dot)) {
				visited[X][Y] = true;
				findCycle(X, Y, map[X][Y], depth + 1);
				visited[X][Y] = false;
			}
		}
	}
	
	private static boolean isValidation(int x, int y, char dot) {
		if (x >= 0 && x < N && y >= 0 && y < M && !visited[x][y] && map[x][y] == dot) {
			return true;
		}
		
		return false;
	}
	
	private static boolean isCycle(int x, int y) {
		if (x == nowNode.x && y == nowNode.y) {
			return true;
		}
		
		return false;
	}
}
