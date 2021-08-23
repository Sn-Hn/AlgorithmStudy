package study_210829;

/*

스크루지 민호
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	512 MB	125	52	41	43.617%
문제
구두쇠로 유명한 스크루지 민호가 다스리는 천나라가 있다.
천나라에는 N 개의 도시들이 있는데 각각의 도시들 사이에는 양방향 도로로 이어져 있다. 
민호는 도시를 세울 때 최소한의 비용만을 들이고 싶어서 N - 1 개의 도로를 이용해 모든 도시들 사이에는 단 한개의 경로만이 존재하도록 도시를 세웠다.

도시를 세울 당시에 소방서를 여러개 건설하는 것이 아까웠던 스쿠르지 민호는 단 하나의 도시에 소방서를  건설하기로 했다. 
하지만 최소한의 양심이 있어서인지 소방서는 최적의 위치가 될 수 있는 도시에 건설하기로 했다. 
최적의 위치라는 것은 소방서에서 소방차가 출동해 다른 도시에 도착을 할 때 이동해야 하는 거리중의 최대가 최소가 되는 지점을 의미한다. 
편의상 같은 도시 내에서 이동하는 거리는 없다고 생각하며 한 도시에서 다른 도시로 연결된 도로는 거리가 1이라고 생각한다.

천나라에 있는 도시의 수와 도로들의 연결 상태가 주어질 때 최적의 위치에 설치된 소방서에서 소방차가 출동해 다른 도시에 도착할 때 이동해야하는 거리들 중 최대 거리를 구하는 프로그램을 작성하자.

입력
첫째 줄에는 천나라에 있는 도시의 수 N (2 <= N <= 100,000) 이 주어진다.  
다음 N - 1 줄에 걸쳐 도시들의 연결 상태가 주어진다.

각각의 줄에는 공백을 기준으로 세개의 숫자가 u, v (1 <= u, v <= N) 가 주어지는데 이는 도시 u와 v가 양방향 도로로 연결이 되어 있다는 것을 의미한다.

출력
첫째 줄에 최적의 위치에 설치된 소방서에서 소방차가 출동해 다른 도시에 도착할 때까지 이동해야하는 거리들 중 최댓값을 출력한다.

예제 입력 1 
5
4 5
4 2
2 3
1 2
예제 출력 1 
2

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 스크루지민호_12896 {
	private static final int INF = Integer.MAX_VALUE;
	
	private static int N;
	private static List<Integer>[] roads;
	private static boolean[] visited;
	private static int farIndex = 0;
	private static int maxDistance = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		roads = new List[N + 1];
		visited = new boolean[N + 1];
		init();

		StringTokenizer st;
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			roads[u].add(v);
			roads[v].add(u);
		}
		
		findMinDistance(1, 0);
				
		visited = new boolean[N + 1];
		maxDistance = Integer.MIN_VALUE;
		findMinDistance(farIndex, 0);
		
		System.out.println((maxDistance + 1) / 2);

		br.close();
	}
	
	private static void findMinDistance(int now, int distance) {
		if (maxDistance < distance) {
			maxDistance = distance;
			farIndex = now;
		}
		
		visited[now] = true;
		
		for (int next : roads[now]) {
			if (visited[next]) {
				continue;
			}
			
			findMinDistance(next, distance + 1);
		}
	}
	
	private static void init() {
		for (int i = 0; i <= N; i++) {
			roads[i] = new ArrayList<>();
		}
	}
}
