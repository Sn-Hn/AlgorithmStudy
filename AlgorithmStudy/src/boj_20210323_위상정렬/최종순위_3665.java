package boj_20210323_위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*

최종 순위 출처다국어분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	5043	1885	1319	35.629%
문제
올해 ACM-ICPC 대전 인터넷 예선에는 총 n개의 팀이 참가했다. 
팀은 1번부터 n번까지 번호가 매겨져 있다. 
놀랍게도 올해 참가하는 팀은 작년에 참가했던 팀과 동일하다.

올해는 인터넷 예선 본부에서는 최종 순위를 발표하지 않기로 했다. 
그 대신에 작년에 비해서 상대적인 순위가 바뀐 팀의 목록만 발표하려고 한다. (작년에는 순위를 발표했다) 
예를 들어, 작년에 팀 13이 팀 6 보다 순위가 높았는데, 올해 팀 6이 팀 13보다 순위가 높다면, (6, 13)을 발표할 것이다.

창영이는 이 정보만을 가지고 올해 최종 순위를 만들어보려고 한다. 
작년 순위와 상대적인 순위가 바뀐 모든 팀의 목록이 주어졌을 때, 올해 순위를 만드는 프로그램을 작성하시오. 
하지만, 본부에서 발표한 정보를 가지고 확실한 올해 순위를 만들 수 없는 경우가 있을 수도 있고, 일관성이 없는 잘못된 정보일 수도 있다. 
이 두 경우도 모두 찾아내야 한다.

입력
첫째 줄에는 테스트 케이스의 개수가 주어진다. 
테스트 케이스는 100개를 넘지 않는다. 
각 테스트 케이스는 다음과 같이 이루어져 있다.

팀의 수 n을 포함하고 있는 한 줄. (2 ≤ n ≤ 500)
n개의 정수 ti를 포함하고 있는 한 줄. (1 ≤ ti ≤ n) ti는 작년에 i등을 한 팀의 번호이다. 
1등이 가장 성적이 높은 팀이다. 모든 ti는 서로 다르다.
상대적인 등수가 바뀐 쌍의 수 m (0 ≤ m ≤ 25000)
두 정수 ai와 bi를 포함하고 있는 m줄. (1 ≤ ai < bi ≤ n) 상대적인 등수가 바뀐 두 팀이 주어진다. 
같은 쌍이 여러 번 발표되는 경우는 없다.
출력
각 테스트 케이스에 대해서 다음을 출력한다.

n개의 정수를 한 줄에 출력한다. 
출력하는 숫자는 올해 순위이며, 1등팀부터 순서대로 출력한다. 
만약, 확실한 순위를 찾을 수 없다면 "?"를 출력한다. 
데이터에 일관성이 없어서 순위를 정할 수 없는 경우에는 "IMPOSSIBLE"을 출력한다.

예제 입력 1 
3
5
5 4 3 2 1
2
2 4
3 4
3
2 3 1
0
4
1 2 3 4
3
1 2
3 4
2 3
예제 출력 1 
5 3 2 4 1
2 3 1
IMPOSSIBLE

*/

public class 최종순위_3665 {
	private static int T, N, M;
	private static List<Integer> result = new ArrayList<Integer>();
	private static int inDegree[];
	private static int prevRank[];
	private static StringBuilder sb = new StringBuilder();
	private static int gra[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			N = Integer.parseInt(br.readLine());
			inDegree = new int[N + 1];
			prevRank = new int[N + 1];
			gra = new int[N + 1][N + 1];

			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				prevRank[j] = Integer.parseInt(st.nextToken());
			}

			for (int j = 1; j <= N; j++) {
				for (int k = j + 1; k <= N; k++) {
					gra[prevRank[j]][prevRank[k]] = 1;
					inDegree[prevRank[k]]++;
				}
			}

			M = Integer.parseInt(br.readLine());
			for (int j = 1; j <= M; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				if (gra[a][b] == 1) {
					gra[a][b] = 0;
					gra[b][a] = 1;
					inDegree[b]--;
					inDegree[a]++;
				} else {
					gra[b][a] = 0;
					gra[a][b] = 1;
					inDegree[a]--;
					inDegree[b]++;
				}
			}

			topologySort();
			print();
		}

		System.out.println(sb.toString());

		br.close();
	}

	private static void topologySort() {
		Queue<Integer> q = new LinkedList<Integer>();
		result.clear();
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				q.add(i);
			}
		}

		for (int i = 1; i <= N; i++) {
			if (q.isEmpty())
				return;

			int now = q.poll();
			result.add(now);
			for (int next = 1; next <= N; next++) {
				if (gra[now][next] == 0)
					continue;
				inDegree[next]--;
				if (inDegree[next] == 0) {
					q.add(next);
				}
			}

		}
	}

	private static void print() {
		if (result.size() == N) {
			for (int i : result) {
				sb.append(i + " ");
			}
			sb.append("\n");
		} else {
			sb.append("IMPOSSIBLE\n");
		}

	}
}
