package boj_20201229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*

빙산 출처분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	25912	7302	4878	26.268%
문제
지구 온난화로 인하여 북극의 빙산이 녹고 있다. 빙산을 그림 1과 같이 2차원 배열에 표시한다고 하자. 
빙산의 각 부분별 높이 정보는 배열의 각 칸에 양의 정수로 저장된다. 
빙산 이외의 바다에 해당되는 칸에는 0이 저장된다. 그림 1에서 빈칸은 모두 0으로 채워져 있다고 생각한다.



그림 1. 행의 개수가 5이고 열의 개수가 7인 2차원 배열에 저장된 빙산의 높이 정보

빙산의 높이는 바닷물에 많이 접해있는 부분에서 더 빨리 줄어들기 때문에, 
배열에서 빙산의 각 부분에 해당되는 칸에 있는 높이는 일년마다 그 칸에 동서남북 네 방향으로 붙어있는 0이 저장된 칸의 개수만큼 줄어든다. 
단, 각 칸에 저장된 높이는 0보다 더 줄어들지 않는다. 
바닷물은 호수처럼 빙산에 둘러싸여 있을 수도 있다. 따라서 그림 1의 빙산은 일년후에 그림 2와 같이 변형된다.

그림 3은 그림 1의 빙산이 2년 후에 변한 모습을 보여준다. 
2차원 배열에서 동서남북 방향으로 붙어있는 칸들은 서로 연결되어 있다고 말한다. 
따라서 그림 2의 빙산은 한 덩어리이지만, 그림 3의 빙산은 세 덩어리로 분리되어 있다.



한 덩어리의 빙산이 주어질 때, 이 빙산이 두 덩어리 이상으로 분리되는 최초의 시간(년)을 구하는 프로그램을 작성하시오. 
그림 1의 빙산에 대해서는 2가 답이다. 만일 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않으면 프로그램은 0을 출력한다.

입력
첫 줄에는 이차원 배열의 행의 개수와 열의 개수를 나타내는 두 정수 N과 M이 한 개의 빈칸을 사이에 두고 주어진다. 
N과 M은 3 이상 300 이하이다. 
그 다음 N개의 줄에는 각 줄마다 배열의 각 행을 나타내는 M개의 정수가 한 개의 빈 칸을 사이에 두고 주어진다. 
각 칸에 들어가는 값은 0 이상 10 이하이다. 
배열에서 빙산이 차지하는 칸의 개수, 즉, 1 이상의 정수가 들어가는 칸의 개수는 10,000 개 이하이다. 
배열의 첫 번째 행과 열, 마지막 행과 열에는 항상 0으로 채워진다.

출력
첫 줄에 빙산이 분리되는 최초의 시간(년)을 출력한다. 만일 빙산이 다 녹을 때까지 분리되지 않으면 0을 출력한다.

예제 입력 1 
5 7
0 0 0 0 0 0 0
0 2 4 5 3 0 0
0 3 0 2 5 2 0
0 7 6 2 4 0 0
0 0 0 0 0 0 0
예제 출력 1 
2

*/

public class Iceberg_2573 {
	private static int N, M;				// 배열의 행과 열
	private static int[][] map;				// 빙산
	private static int[][] copyMap;			// 빙산 복사
	private static boolean visited[][];		// 방문처리
	private static List<Pair> list = new ArrayList<Pair>();		// 빙산이 담겨 있는 List (0이상)
	
	// 해당 빙산의 동서남북을 찾아야 하므로 dx, dy를 선언
	private static int dx[] = {1, -1, 0, 0};
	private static int dy[] = {0, 0, 1, -1};
	
	// 빙산의 위치만 BFS를 돌리기 위하여 빙산의 위치를 담아줄 Pair 클래스 선언
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
		int count = 0;	// 빙산의 수
		int time = 0;	// N년 후
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		copyMap = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				copyMap[i][j] = map[i][j];
				// 바다 이외의 빙산에 해당되는 칸을 list에 저장
				if(map[i][j] > 0) {
					list.add(new Pair(i, j));
				}
			}
		}
		
		// 빙산의 수가 2개 이상이 될 때 시간을 구하기 위해 무한루프 구현
		while(true) {
			// 매 년마다 방문처리를 위해 visited 초기화
			visited = new boolean[N][M];
			time++;			// 1년 후
			meltIce();		// 빙산이 녹고 난 후
			copyMap();		// 빙산이 녹고 난 후를 map에 복사
			count = countOfIceberg();		// 녹고 난 후 빙산의 개수를 세어줌
			if(count == 0) {
				// 빙산이 전부 녹을 때까지 두 덩어리 이상으로 분리되지 않으면 0을 출력
				System.out.println(0);
				break;
			}else if(count >= 2) {
				System.out.println(time);
				break;
			}
		}
		
		
		br.close();
	}
	
	// 빙산의 수를 세기 위한 메소드
	private static int countOfIceberg() {
		int cnt = 0;
		for(Pair p : list) {
			cnt += icebergDFS(p.x, p.y);
		}
		
		return cnt;		
	}
	
	// 빙산이 녹는 메소드 BFS
	private static void meltIce() {	
		Queue<Pair> q = new LinkedList<Pair>();
		for(Pair p : list) {
			q.add(p);
		}
		
		// 빙산의 한 위치에 대해 동서남북을 조사하는 것이므로 큐에 추가하지 않았다.
		while(!q.isEmpty()) {
			Pair pair = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int X = pair.x + dx[i];
				int Y = pair.y + dy[i];
				
				if(X >= 0 && X < N && Y >= 0 && Y < M) {
					if(map[X][Y] == 0 && copyMap[pair.x][pair.y] > 0) {
						copyMap[pair.x][pair.y]--;
					}
				}
			}
			
		}
		
	}
	
	// 빙산의 수를 세기 위한 dfs
	private static int icebergDFS(int x, int y) {
		// 방문했다면 return 0
		if(visited[x][y]) {
			return 0;
		}
		
		visited[x][y] = true;
		
		for(int i = 0; i < 4; i++) {
			int X = x + dx[i];
			int Y = y + dy[i];
			
			if(X >= 0 && X < N && Y >= 0 && Y < M) {
				if(map[X][Y] > 0 && !visited[X][Y]) {
					icebergDFS(X, Y);
				}
			}
		}
		
		return 1;
	}
	
	// 빙산 복사
	private static void copyMap() {
		list.clear();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				// 1년 후 빙산을 복사 한 후
				map[i][j] = copyMap[i][j];
				// 1년 후 남아 있는 빙산을 list에 추가
				if(copyMap[i][j] > 0) {
					list.add(new Pair(i, j));
				}
			}
		}
	}
	
	// 빙산 출력
	private static void printMap() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
