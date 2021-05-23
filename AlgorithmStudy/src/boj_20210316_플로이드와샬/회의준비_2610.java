package boj_20210316_플로이드와샬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*

회의준비 스페셜 저지출처분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	5429	1538	1158	28.670%
문제
KOI 준비를 위해 회의를 개최하려 한다. 
주최측에서는 회의에 참석하는 사람의 수와 참석자들 사이의 관계를 따져 하나 이상의 위원회를 구성하려고 한다. 위원회를 구성하는 방식은 다음과 같다.

서로 알고 있는 사람은 반드시 같은 위원회에 속해야 한다.
효율적인 회의 진행을 위해 위원회의 수는 최대가 되어야 한다.
이런 방식으로 위원회를 구성한 후에 각 위원회의 대표를 한 명씩 뽑아야 한다. 
각 위원회의 대표만이 회의 시간 중 발언권을 가지며, 따라서 회의 참석자들이 자신의 의견을 말하기 위해서는 자신이 속한 위원회의 대표에게 자신의 의견을 전달해야 한다. 
그런데 각 참석자는 자신이 알고 있는 사람에게만 의견을 전달할 수 있어 대표에게 의견을 전달하기 위해서는 때로 여러 사람을 거쳐야 한다. 
대표에게 의견을 전달하는 경로가 여러 개 있을 경우에는 가장 적은 사람을 거치는 경로로 의견을 전달하며 이때 거치는 사람의 수를 참석자의 의사전달시간이라고 한다.

위원회에서 모든 참석자들의 의사전달시간 중 최댓값이 최소가 되도록 대표를 정하는 프로그램을 작성하시오.

예를 들어 1번, 2번, 3번 세 사람으로 구성되어 있는 위원회에서 1번과 2번, 2번과 3번이 서로 알고 있다고 하자. 
1번이 대표가 되면 3번이 대표인 1번에게 의견을 전달하기 위해서 2번을 거쳐야만 한다. 
반대로 3번이 대표가 되어도 1번이 대표인 3번에게 의견을 전달하려면 2번을 거쳐야만 한다. 
하지만 2번이 대표가 되면 1번과 3번 둘 다 아무도 거치지 않고 대표에게 직접 의견을 전달 할 수 있다. 
따라서 이와 같은 경우 2번이 대표가 되어야 한다.

입력
첫째 중에 회의에 참석하는 사람의 수 N이 주어진다. 
참석자들은 1부터 N까지의 자연수로 표현되며 회의에 참석하는 인원은 100 이하이다. 
둘째 줄에는 서로 알고 있는 관계의 수 M이 주어진다.
이어 M개의 각 줄에는 서로 아는 사이인 참석자를 나타내는 두개의 자연수가 주어진다.

출력
첫째 줄에는 구성되는 위원회의 수 K를 출력한다. 
다음 K줄에는 각 위원회의 대표 번호를 작은 수부터 차례로 한 줄에 하나씩 출력한다. 
한 위원회의 대표가 될 수 있는 사람이 둘 이상일 경우 그중 한 명만 출력하면 된다.

예제 입력 1 
8
7
1 2
2 3
4 5
5 6
4 6
6 7
7 4
예제 출력 1 
3
2
4
8

8
1
1 2
2 3
4 5
5 6
4 6
6 7
7 4
3 8

*/

public class 회의준비_2610 {
	private static int N, M;
	private static int fw[][];
	private static int parent[];
	private static int rep[];
	private static int INF = 100000000;
	private static StringBuilder sb = new StringBuilder();

	private static List<Integer> list = new ArrayList<Integer>();
	private static List<Integer> result = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		fw = new int[N + 1][N + 1];
		parent = new int[N + 1];
		rep = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			Arrays.fill(fw[i], INF);
			parent[i] = i;
		}

		StringTokenizer st;

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			union(a, b);

			fw[a][b] = fw[b][a] = 1;
		}
		floydWarshall();

		br.close();
	}

	private static void floydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = i + 1; j <= N; j++) {
					if (i == j || i == k || j == k)
						continue;
					if (fw[i][j] > fw[i][k] + fw[k][j]) {
						fw[i][j] = fw[j][i] = fw[i][k] + fw[k][j];
					}
				}
			}
		}

		minCommunication();
	}

	private static int find(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = find(parent[x]);
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}

	private static void findRep() {
		int answer = 0;
		for (int i : list) {
			int min = Integer.MAX_VALUE;
			for (int j = 1; j <= N; j++) {
				if (i == parent[j]) {
					if (min >= rep[j]) {
						min = rep[j];
						answer = j;
					}
				}
			}

			result.add(answer);
		}

		Collections.sort(result);

		for (int i : result)
			sb.append(i + "\n");

		System.out.println(sb.toString());
	}

	private static void findParent() {
		for (int i = 1; i <= N; i++) {
			if (parent[i] == i)
				list.add(i);
		}
		sb.append(list.size() + "\n");

		findRep();
	}

	private static void minCommunication() {
		for (int i = 1; i <= N; i++) {
			int max = -1;
			for (int j = 1; j <= N; j++) {
				if (fw[i][j] != INF) {
					max = Math.max(max, fw[i][j]);
				}
			}
			rep[i] = max;
		}

		findParent();
	}
}
