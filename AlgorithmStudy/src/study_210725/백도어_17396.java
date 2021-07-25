package study_210725;

/*

백도어 출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	512 MB	1692	426	321	25.117%
문제
유섭이는 무척이나 게으르다. 오늘도 할 일을 모두 미뤄둔 채 열심히 롤을 하던 유섭이는 오늘까지 문제를 내야 한다는 사실을 깨달았다. 
그러나 게임은 시작되었고 지는 걸 무척이나 싫어하는 유섭이는 어쩔 수 없이 백도어를 해 게임을 최대한 빠르게 끝내기로 결심하였다.

최대한 빨리 게임을 끝내고 문제를 출제해야 하기 때문에 유섭이는 최대한 빨리 넥서스가 있는 곳으로 달려가려고 한다. 
유섭이의 챔피언은 총 N개의 분기점에 위치할 수 있다. 
0번째 분기점은 현재 유섭이의 챔피언이 있는 곳을, N-1 번째 분기점은 상대편 넥서스를 의미하며 나머지 1, 2, ..., N-2번째 분기점은 중간 거점들이다. 
그러나 유섭이의 챔피언이 모든 분기점을 지나칠 수 있는 것은 아니다.
백도어의 핵심은 안 들키고 살금살금 가는 것이기 때문에 적 챔피언 혹은 적 와드(시야를 밝혀주는 토템), 미니언, 포탑 등 상대의 시야에 걸리는 곳은 지나칠 수 없다.

입력으로 각 분기점을 지나칠 수 있는지에 대한 여부와 각 분기점에서 다른 분기점으로 가는데 걸리는 시간이 주어졌을 때, 유섭이가 현재 위치에서 넥서스까지 갈 수 있는 최소 시간을 구하여라.

입력
첫 번째 줄에 분기점의 수와 분기점들을 잇는 길의 수를 의미하는 두 자연수 N과 M이 공백으로 구분되어 주어진다.(1 ≤ N ≤ 100,000, 1 ≤ M ≤ 300,000)

두 번째 줄에 각 분기점이 적의 시야에 보이는지를 의미하는 N개의 정수 a0, a1, ..., aN-1가 공백으로 구분되어 주어진다. 
ai가 0이면 i 번째 분기점이 상대의 시야에 보이지 않는다는 뜻이며, 1이면 보인다는 뜻이다. 
추가적으로 a0 = 0, aN-1 = 1이다., N-1번째 분기점은 상대 넥서스이기 때문에 어쩔 수 없이 상대의 시야에 보이게 되며, 또 유일하게 상대 시야에 보이면서 갈 수 있는 곳이다.

다음 M개의 줄에 걸쳐 세 정수 a, b, t가 공백으로 구분되어 주어진다. (0 ≤ a, b < N, a ≠ b, 1 ≤ t ≤ 100,000) 
이는 a번째 분기점과 b번째 분기점 사이를 지나는데 t만큼의 시간이 걸리는 것을 의미한다. 
연결은 양방향이며, 한 분기점에서 다른 분기점으로 가는 간선은 최대 1개 존재한다.

출력
첫 번째 줄에 유섭이의 챔피언이 상대 넥서스까지 안 들키고 가는데 걸리는 최소 시간을 출력한다. 
만약 상대 넥서스까지 갈 수 없으면 -1을 출력한다.

예제 입력 1 
5 7
0 0 0 1 1
0 1 7
0 2 2
1 2 4
1 3 3
1 4 6
2 3 2
3 4 1
예제 출력 1 
12
위 그래프의 최단거리는 0-2-3-4 를 지나는 시간인 5(2+2+1) 이지만, 
3번 분기점이 상대의 시야에 있기 때문에 0-2-1-4를 지나는 시간인 12(2+4+6)이 최소 시간이 된다.

예제 입력 2 
5 7
0 1 0 1 1
0 1 7
0 2 2
1 2 4
1 3 3
1 4 6
2 3 2
3 4 1
예제 출력 2 
-1

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백도어_17396 {
	private static int N;
	private static int M;
	private static int[] vision;
	private static List<Time> tree[];
	private static boolean[] visited;
	private static long minTime = 0;
	
	private static class Time implements Comparable<Time> {
		int end;
		long time;
		
		public Time(int end, long time) {
			// TODO Auto-generated constructor stub
			this.end = end;
			this.time = time;
		}
		
		@Override
		public int compareTo(Time o) {
			// TODO Auto-generated method stub
			return (int) (time - o.time);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		vision = new int[N];
		tree = new ArrayList[N];
		visited = new boolean[N];
				
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			vision[i] = Integer.parseInt(st.nextToken());
		}
		
		init();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Integer.parseInt(st.nextToken());
			
			tree[a].add(new Time(b, c));
			tree[b].add(new Time(a, c));
		}
		
		if (isMinTime()) {
			System.out.println(minTime);
		}else {
			System.out.println(-1);
		}
		
		
		br.close();
	}
	
	private static boolean isMinTime() {
		PriorityQueue<Time> q = new PriorityQueue<Time>();
		for (Time time : tree[0]) {
			q.add(time);
		}
		visited[0] = true;
		
		while (!q.isEmpty()) {
			Time time = q.poll();
			int end = time.end;
			
//			System.out.println(end + ", " + time.time);
			
			if (end == N - 1) {
				minTime = time.time;
				return true;
			}
			
			if (vision[end] == 1 || visited[end]) {
				continue;
			}
						
			visited[end] = true;
			
			for (Time t : tree[end]) {
				if (end != N - 1 && vision[end] == 1) {
					continue;
				}
				
				q.add(new Time(t.end, time.time + t.time));
			}
		}
		
		return false;
		
	}
	
	private static void init() {
		for (int i = 0; i < N; i++) {
			tree[i] = new ArrayList<Time>();
		}
	}
}
