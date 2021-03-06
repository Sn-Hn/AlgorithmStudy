package boj_20210126_backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*

소문난 칠공주 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	256 MB	4384	2083	1309	45.530%
문제
총 25명의 여학생들로 이루어진 여학생반은 5*5의 정사각형 격자 형태로 자리가 배치되었고, 
얼마 지나지 않아 이다솜과 임도연이라는 두 학생이 두각을 나타내며 다른 학생들을 휘어잡기 시작했다. 
곧 모든 여학생이 ‘이다솜파’와 ‘임도연파’의 두 파로 갈라지게 되었으며, 얼마 지나지 않아 ‘임도연파’가 세력을 확장시키며 ‘이다솜파’를 위협하기 시작했다.

위기의식을 느낀 ‘이다솜파’의 학생들은 과감히 현재의 체제를 포기하고, ‘소문난 칠공주’를 결성하는 것이 유일한 생존 수단임을 깨달았다. 
‘소문난 칠공주’는 다음과 같은 규칙을 만족해야 한다.

이름이 이름인 만큼, 7명의 여학생들로 구성되어야 한다.
강한 결속력을 위해, 7명의 자리는 서로 가로나 세로로 반드시 인접해 있어야 한다.
화합과 번영을 위해, 반드시 ‘이다솜파’의 학생들로만 구성될 필요는 없다.
그러나 생존을 위해, ‘이다솜파’가 반드시 우위를 점해야 한다. 
따라서 7명의 학생 중 ‘이다솜파’의 학생이 적어도 4명 이상은 반드시 포함되어 있어야 한다.
여학생반의 자리 배치도가 주어졌을 때, ‘소문난 칠공주’를 결성할 수 있는 모든 경우의 수를 구하는 프로그램을 작성하시오.

입력
'S'(이다‘솜’파의 학생을 나타냄) 또는 'Y'(임도‘연’파의 학생을 나타냄)을 값으로 갖는 5*5 행렬이 공백 없이 첫째 줄부터 다섯 줄에 걸쳐 주어진다.

출력
첫째 줄에 ‘소문난 칠공주’를 결성할 수 있는 모든 경우의 수를 출력한다.

예제 입력 1 
YYYYY
SYSYS
YYYYY
YSYYS
YYYYY
예제 출력 1 
2

SSSSS
SSSSS
SSSSS
SSSSS
SSSSS

*/

// map을 0, 0부터 쭉 돌면서
public class 소문난칠공주_1941 {
	private static char map[][] = new char[5][5];
	private static int pair[] = new int[2];
	private static int result[] = new int[7];
	private static List<Pair> list = new ArrayList<Pair>();
	private static boolean visited[] = new boolean[25];
	private static boolean v[][];
	private static int count = 0;
	
	private static int dx[] = {0, 0, 1, -1};
	private static int dy[] = {1, -1, 0, 0};
	private static class Pair {
		int x, y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < 5; i++) {
			String str = br.readLine();
			for(int j = 0; j < 5; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		permutation(0);
		sevenPricesses(0, 0);
		
		System.out.println(count);
		
		br.close();
	}
	
	// 25명의 좌표를 전부 뽑아서 list에 넣었다.
	private static void permutation(int depth) {
		if(depth == 2) {
			list.add(new Pair(pair[0], pair[1]));
			return;
		}
		
		for(int i = 0; i < 5; i++) {
			pair[depth] = i;
			permutation(depth+1);
		}
	}
	
	// 25명의 좌표들 중 7개를 조합으로 뽑는다.
	private static void sevenPricesses(int index, int depth) {
		if(depth == 7) {
			// '이다솜파'가 4명이 넘는지 check -> isfourCheck
			// 서로 인접하는지 check -> isAdjacentCheckDFS
			if(isFourCheck() && isAdjacentCheck()) {
				count += 1;			
			}
				
			return;
		}
		
		for(int i = index; i < 25; i++) {
			if(!visited[i]) {
				visited[i] = true;
				result[depth] = i;
				sevenPricesses(i+1, depth+1);
				visited[i] = false;
			}
		}	
	}
	
	// 인접한지 check
	private static boolean isAdjacentCheck() {
		v = new boolean[5][5];
		int x = list.get(result[0]).x;
		int y = list.get(result[0]).y;
		dfs(x, y);
		
		// result에 있는 값을 dfs돌려 
		// 전부 방문처리 되어 있다면 인접한 경우이므로 true
		for(int i : result) {
			x = list.get(i).x;
			y = list.get(i).y;
			
			if(!v[x][y]) {
				return false;
			}
		}
		return true;
	}
	
	private static void dfs(int x, int y) {
		for(int i = 0; i < 4; i++) {
			int X = x + dx[i];
			int Y = y + dy[i];
			
			for(int j = 0; j < 7; j++) {
				// 인접한 수라면 방문 체크!
				if(X == list.get(result[j]).x && Y == list.get(result[j]).y && !v[X][Y]) {
					v[X][Y] = true;
					dfs(X, Y);
				}
			}
		}
	}
	
	// 4개 이상인지 체크
	private static boolean isFourCheck() {
		int cnt = 0;
		for(int i = 0; i < 7; i++) {
			Pair now = list.get(result[i]);
			if(map[now.x][now.y] == 'S') {
				cnt += 1;
			}
		}
		
		if(cnt < 4) return false;
		return true;
	}
	
}
