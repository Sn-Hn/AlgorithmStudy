package boj_20210202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*

내리막 길 출처분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	34891	9167	6578	28.105%
문제
여행을 떠난 세준이는 지도를 하나 구하였다. 이 지도는 아래 그림과 같이 직사각형 모양이며 여러 칸으로 나뉘어져 있다. 
한 칸은 한 지점을 나타내는데 각 칸에는 그 지점의 높이가 쓰여 있으며, 각 지점 사이의 이동은 지도에서 상하좌우 이웃한 곳끼리만 가능하다.



현재 제일 왼쪽 위 칸이 나타내는 지점에 있는 세준이는 제일 오른쪽 아래 칸이 나타내는 지점으로 가려고 한다. 
그런데 가능한 힘을 적게 들이고 싶어 항상 높이가 더 낮은 지점으로만 이동하여 목표 지점까지 가고자 한다. 
위와 같은 지도에서는 다음과 같은 세 가지 경로가 가능하다.





지도가 주어질 때 이와 같이 제일 왼쪽 위 지점에서 출발하여 제일 오른쪽 아래 지점까지 항상 내리막길로만 이동하는 경로의 개수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에는 지도의 세로의 크기 M과 가로의 크기 N이 빈칸을 사이에 두고 주어진다. 
이어 다음 M개 줄에 걸쳐 한 줄에 N개씩 위에서부터 차례로 각 지점의 높이가 빈 칸을 사이에 두고 주어진다.
M과 N은 각각 500이하의 자연수이고, 각 지점의 높이는 10000이하의 자연수이다.

출력
첫째 줄에 이동 가능한 경로의 수 H를 출력한다. 
모든 입력에 대하여 H는 10억 이하의 음이 아닌 정수이다.

예제 입력 1 
4 5
50 45 37 32 30
35 50 40 20 25
30 30 25 17 28
27 24 22 15 10
예제 출력 1 
3

*/

public class 내리막길_1520 {
	private static int N, M;
	private static int map[][];
	private static int dp[][];
	private static boolean visited[][];
	
	private static int dx[] = {0, 0, 1, -1};
	private static int dy[] = {1, -1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		// 0, 0에서 출발해 끝에 도착한 경우들 => dp[0][0]
		System.out.println(dfs(0, 0));
		
		br.close();
	}
	
	private static int dfs(int x, int y) {
		// 끝까지 도착 했다면 경로 수 한 개를 찾음
		if(x == N-1 && y == M-1) {
			return 1;
		}
		
		// dp 배열에 저장되어 있다면 dp 리턴 (메모이제이션)
		// dp 배열을 -1로 초기화 하지 않으면 36%에서 시간 초과
		if(dp[x][y] != -1) {
			return dp[x][y];
		}
		
		// dp배열을 -1로 초기화시켰으므로
		// 현재까지의 개수를 다시 0으로 초기화
		dp[x][y] = 0;
		
		for(int i = 0; i < 4; i++) {
			int X = x + dx[i];
			int Y = y + dy[i];
			
			// 끝에 도착하지 않았다면 0
			// 도착했다면 1이 리턴되므로
			// 끝에 도착한 경우만 1개씩 dp[x][y]에 더해진다.
			if(X >= 0 && X < N && Y >= 0 && Y < M && map[x][y] > map[X][Y]) {
				dp[x][y] += dfs(X, Y);
			}
		}
		
		return dp[x][y];
	}
}
