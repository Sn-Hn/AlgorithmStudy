package boj_20201229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*

치즈 출처분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	7521	3346	2512	45.656%
문제
N×M (5≤N, M≤100)의 모눈종이 위에 아주 얇은 치즈가 <그림 1>과 같이 표시되어 있다. 
단, N 은 세로 격자의 수이고, M 은 가로 격자의 수이다.
이 치즈는 냉동 보관을 해야만 하는데 실내온도에 내어놓으면 공기와 접촉하여 천천히 녹는다.
그런데 이러한 모눈종이 모양의 치즈에서 각 치즈 격자(작 은 정사각형 모양)의 4변 중에서 적어도 2변 이상이 실내온도의 공기와 접촉한 것은 정확히 한시간만에 녹아 없어져 버린다.
따라서 아래 <그림 1> 모양과 같은 치즈(회색으로 표시된 부분)라면 C로 표시된 모든 치즈 격자는 한 시간 후에 사라진다.



<그림 2>와 같이 치즈 내부에 있는 공간은 치즈 외부 공기와 접촉하지 않는 것으로 가정한다. 
그러므 로 이 공간에 접촉한 치즈 격자는 녹지 않고 C로 표시된 치즈 격자만 사라진다. 
그러나 한 시간 후, 이 공간으로 외부공기가 유입되면 <그림 3>에서와 같이 C로 표시된 치즈 격자들이 사라지게 된다.



모눈종이의 맨 가장자리에는 치즈가 놓이지 않는 것으로 가정한다. 
입력으로 주어진 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간을 구하는 프로그램을 작성하시오.

입력
첫째 줄에는 모눈종이의 크기를 나타내는 두 개의 정수 N, M (5≤N, M≤100)이 주어진다. 
그 다음 N개의 줄에는 모눈종이 위의 격자에 치즈가 있는 부분은 1로 표시되고, 치즈가 없는 부분은 0으로 표시된다. 
또한, 각 0과 1은 하나의 공백으로 분리되어 있다.

출력
출력으로는 주어진 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간을 정수로 첫 줄에 출력한다.

예제 입력 1 
8 9
0 0 0 0 0 0 0 0 0
0 0 0 1 1 0 0 0 0
0 0 0 1 1 0 1 1 0
0 0 1 1 1 1 1 1 0
0 0 1 1 1 1 1 0 0
0 0 1 1 0 1 1 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
예제 출력 1 
4


*/

public class Cheese_2638 {
	private static int N, M;
	private static int map[][];
	private static boolean visited[][];
	// 치즈 정보를 담을 List
	private static List<Pair> cheese = new ArrayList<Pair>();
	
	private static int dx[] = {1, -1, 0, 0};
	private static int dy[] = {0, 0, 1, -1};
	
	// 치즈 정보를 담을 Pair클래스 선언
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
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					cheese.add(new Pair(i, j));
				}
			}
		}
		int time = 0;
		// 치즈가 전부 녹을 때까지 반복
		while(true) {
			visited = new boolean[N][M];
			time++;
			// 가장자리에는 치즈가 있을 수 없기 때문에 가장자리인 0, 0으로 외부공기를 구분
			outSideAir(0, 0);
			
			// 치즈 정보가 담겨 있는 List를 반복
			for(Pair p : cheese) {
				meltCheese(p.x, p.y, 0);
			}
			// 치즈가 남아 있는지 검사
			if(isCheese()) break;
		}
		System.out.println(time);
		br.close();
	}
	
	// 치즈가 녹는 메소드
	private static void meltCheese(int x, int y, int count) {
		
		// 재귀를 돌리지 않고 각각의 치즈 정보를 받아 그 치즈의 4방면을 검사해 -1(외부 공기)이 두개 이상이면 치즈가 녹는다.
		for(int i = 0; i < 4; i++) {
			int X = x + dx[i];
			int Y = y + dy[i];
			
			if(X >= 0 && X < N && Y >= 0 && Y < M && map[X][Y] == -1) {
				// count : 맞닿아 있는 외부공기의 수
				count++;
				// 맞닿아 있는 외부 공기가 2개 이상이면 치즈가 녹음
				if(count >= 2) {
					map[x][y] = 0;
				}
			}
		}
	}
	
	// 외부 공기와 내부 공기를 구분해야 하기 때문
	private static void outSideAir(int x, int y) {
		// -1은 외부 공기를 뜻함
		map[x][y] = -1;
		
		for(int i = 0; i < 4; i++) {
			int X = x + dx[i];
			int Y = y + dy[i];
			
			visited[x][y] = true;
			
			// dfs를 돌려 외부 공기를 -1로 만듬
			if(X >= 0 && X < N && Y >= 0 && Y < M && !visited[X][Y]) {
				if(map[X][Y] == 0 || map[X][Y] == -1) {
					outSideAir(X, Y);
 				}
			}
		}
	}
	
	// 남아 있는 치즈가 있는지 확인하는 메소드
	private static boolean isCheese() {
		boolean flag = true;
		// 치즈 정보를 클리어한 후 다시 받는다.
		// 굳이 다시 받을 이유는 없지만 이미 녹은 치즈들을 계속 검사하기 때문에 cheese List를 새로 입력
		cheese.clear();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 1) {
					flag = false;
					cheese.add(new Pair(i, j));
				}
			}
		}
		
		return flag;
	}
	
	private static void printMap() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
