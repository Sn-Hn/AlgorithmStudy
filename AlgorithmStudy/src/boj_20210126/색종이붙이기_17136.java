package boj_20210126;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

색종이 붙이기 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	512 MB	12950	4756	2355	32.150%
문제
<그림 1>과 같이 정사각형 모양을 한 다섯 종류의 색종이가 있다. 
색종이의 크기는 1×1, 2×2, 3×3, 4×4, 5×5로 총 다섯 종류가 있으며, 각 종류의 색종이는 5개씩 가지고 있다.



<그림 1>

색종이를 크기가 10×10인 종이 위에 붙이려고 한다. 
종이는 1×1 크기의 칸으로 나누어져 있으며, 각각의 칸에는 0 또는 1이 적혀 있다. 
1이 적힌 칸은 모두 색종이로 덮여져야 한다. 
색종이를 붙일 때는 종이의 경계 밖으로 나가서는 안되고, 겹쳐도 안 된다. 
또, 칸의 경계와 일치하게 붙여야 한다. 0이 적힌 칸에는 색종이가 있으면 안 된다.

종이가 주어졌을 때, 1이 적힌 모든 칸을 붙이는데 필요한 색종이의 최소 개수를 구해보자.

입력
총 10개의 줄에 종이의 각 칸에 적힌 수가 주어진다.

출력
모든 1을 덮는데 필요한 색종이의 최소 개수를 출력한다. 1을 모두 덮는 것이 불가능한 경우에는 -1을 출력한다.

예제 입력 1 
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
예제 출력 1 
0
예제 입력 2 
0 0 0 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 1 0 0 0 0 0
0 0 0 0 0 1 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
예제 출력 2 
4
예제 입력 3 
0 0 0 0 0 0 0 0 0 0
0 1 1 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 0 0 1 1 0 0 0 0
0 0 0 0 1 1 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
예제 출력 3 
5
예제 입력 4 
0 0 0 0 0 0 0 0 0 0
0 1 1 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 0 0 1 1 0 0 0 0
0 0 0 0 0 1 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
예제 출력 4 
-1
예제 입력 5 
0 0 0 0 0 0 0 0 0 0
0 1 1 0 0 0 0 0 0 0
0 1 1 1 0 0 0 0 0 0
0 0 1 1 1 1 1 0 0 0
0 0 0 1 1 1 1 0 0 0
0 0 0 0 1 1 1 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
예제 출력 5 
7
예제 입력 6 
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
예제 출력 6 
4
예제 입력 7 
0 0 0 0 0 0 0 0 0 0
0 1 1 1 1 1 0 0 0 0
0 1 1 1 1 1 0 0 0 0
0 0 1 1 1 1 0 0 0 0
0 0 1 1 1 1 0 0 0 0
0 1 1 1 1 1 1 1 1 1
0 1 1 1 0 1 1 1 1 1
0 1 1 1 0 1 1 1 1 1
0 0 0 0 0 1 1 1 1 1
0 0 0 0 0 1 1 1 1 1
예제 출력 7 
6
예제 입력 8 
0 0 0 0 0 0 0 0 0 0
1 1 1 1 1 0 0 0 0 0
1 1 1 1 1 0 1 1 1 1
1 1 1 1 1 0 1 1 1 1
1 1 1 1 1 0 1 1 1 1
1 1 1 1 1 0 1 1 1 1
0 0 0 0 0 0 0 0 0 0
0 1 1 1 0 1 1 0 0 0
0 1 1 1 0 1 1 0 0 0
0 1 1 1 0 0 0 0 0 1
예제 출력 8 
5

*/

// 접근 방법 : 블로그 참조
// 1. 맵을 돌다가 1을 찾는다.
// 2. 1x1 ~ 5x5의 크기인 색종이를 붙인다. (붙일 수 있어야 한다)
// 3. 색종이를 붙이면 paper 배열에서 cnt를 빼준다.
// 4. 맵의 끝까지 왔다면 색종이의 최소 값을 갱신
public class 색종이붙이기_17136 {
	private static int map[][] = new int[10][10];
	private static int paper[] = {0, 5, 5, 5, 5, 5};
	private static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0);
		if(min <= 25) {
			System.out.println(min);			
		}else {
			System.out.println(-1);
		}
		
		br.close();
	}
	
	private static void dfs(int x) {
		
		for(int i = x; i < 10; i++) {
			// dfs(int x, int y)
			// j = y로 주었다가 틀림
			// j가 다시 0으로 돌아가야 하는 이유
			// 해당 row가 끝났다면 다시 0번쨰 열부터 탐색을 해야하므로
			// j = y로 준다면 row가 바뀌어도 y부터 탐색하므로 X
			for(int j = 0; j < 10; j++) {
				// 색종이를 붙여야한다.
				if(map[i][j] == 1) {
					// 무조건 큰 색종이부터 덮어야 하는 건 아니지만
					// 큰 색종이부터 덮어야 일찍 끝날 확률이 크다.
					for(int k = 5; k >= 1; k--) {
						if(isAttachChecked(i, j, k) && paper[k] > 0) {
							attachAndDetach(i, j, k);
							paper[k]--;
							dfs(i+k);
							paper[k]++;
							attachAndDetach(i, j, k);
						}
					}
					if(map[i][j] == 1) return;
				}
			}
		}
		
		min = Math.min(min, cntPaper());
		
	}
	
	// 색종이를 붙이고 떼는 메소드
	private static void attachAndDetach(int x, int y, int index) {
		for(int i = x; i < x+index; i++) {
			for(int j = y; j < y+index; j++) {
				map[i][j] *= -1;
			}
		}
	}
	
	private static boolean isAttachChecked(int x, int y, int index) {
		// 맵의 크기를 넘어간다면 false 
		if(x+index > 10 || y+index > 10) {
			return false;
		}
		
		// 색종이를 붙일 위치에 1이 아니라면 false
		for(int i = x; i < x+index; i++) {
			for(int j = y; j < y+index; j++) {
				if(map[i][j] != 1) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	// 주어진 색종이의 개수는 총 25개
	private static int cntPaper() {
		int cnt = 25;
		for(int i = 1; i <= 5; i++) {
			cnt -= paper[i];
		}
		return cnt;
	}
	
}
