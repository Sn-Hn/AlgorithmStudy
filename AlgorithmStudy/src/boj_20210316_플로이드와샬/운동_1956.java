package boj_20210316_플로이드와샬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*

운동 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	192 MB	6178	2982	2254	46.667%
문제
V개의 마을와 E개의 도로로 구성되어 있는 도시가 있다. 
도로는 마을과 마을 사이에 놓여 있으며, 일방 통행 도로이다. 마을에는 편의상 1번부터 V번까지 번호가 매겨져 있다고 하자.

당신은 도로를 따라 운동을 하기 위한 경로를 찾으려고 한다. 
운동을 한 후에는 다시 시작점으로 돌아오는 것이 좋기 때문에, 우리는 사이클을 찾기를 원한다. 
단, 당신은 운동을 매우 귀찮아하므로, 사이클을 이루는 도로의 길이의 합이 최소가 되도록 찾으려고 한다.

도로의 정보가 주어졌을 때, 도로의 길이의 합이 가장 작은 사이클을 찾는 프로그램을 작성하시오. 
두 마을을 왕복하는 경우도 사이클에 포함됨에 주의한다.

입력
첫째 줄에 V와 E가 빈칸을 사이에 두고 주어진다. (2 ≤ V ≤ 400, 0 ≤ E ≤ V(V-1)) 
다음 E개의 줄에는 각각 세 개의 정수 a, b, c가 주어진다. 
a번 마을에서 b번 마을로 가는 거리가 c인 도로가 있다는 의미이다. (a → b임에 주의) 
거리는 10,000 이하의 자연수이다. (a, b) 쌍이 같은 도로가 여러 번 주어지지 않는다.

출력
첫째 줄에 최소 사이클의 도로 길이의 합을 출력한다. 운동 경로를 찾는 것이 불가능한 경우에는 -1을 출력한다.

예제 입력 1 
3 4
1 2 1
3 2 1
1 3 5
2 3 2
예제 출력 1 
3

*/

public class 운동_1956 {
	private static int V, E;
	private static int fw[][];
	private static int INF = 100000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		fw = new int[V+1][V+1];
		
		for(int i = 1; i <= V; i++) {
			Arrays.fill(fw[i], INF);
		}
		
		for(int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			fw[a][b] = c;
		}
		
		floydWarshall();
		
		print();
		
		br.close();
	}
	
	private static void floydWarshall() {
		for(int k = 1; k <= V; k++) {
			for(int i = 1; i <= V; i++) {
				for(int j = 1; j <= V; j++) {
					if(fw[i][j] > fw[i][k] + fw[k][j]) {
						fw[i][j] = fw[i][k] + fw[k][j];
					}
				}
			}
		}
		
		solve();
	}
	
	private static void solve() {
		int min = Integer.MAX_VALUE;
		for(int i = 1; i <= V; i++) {
			for(int j = i+1; j <= V; j++) {
				if(fw[i][j] != INF && fw[j][i] != INF) {
					min = Math.min(min, fw[i][j] + fw[j][i]);
				}
			}
		}
		if(min == Integer.MAX_VALUE || min == 0) {
			System.out.println(-1);
		}else {
			System.out.println(min);			
		}
	}
	
	private static void print() {
		for(int i = 1; i <= V; i++) {
			for(int j = 1; j <= V; j++) {
				System.out.print(fw[i][j] + " ");
			}
			System.out.println();
		}
	}
}
