package boj_20210323_위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*

줄 세우기 스페셜 저지분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	20505	11192	7276	52.878%
문제
N명의 학생들을 키 순서대로 줄을 세우려고 한다. 
각 학생의 키를 직접 재서 정렬하면 간단하겠지만, 마땅한 방법이 없어서 두 학생의 키를 비교하는 방법을 사용하기로 하였다. 
그나마도 모든 학생들을 다 비교해 본 것이 아니고, 일부 학생들의 키만을 비교해 보았다.

일부 학생들의 키를 비교한 결과가 주어졌을 때, 줄을 세우는 프로그램을 작성하시오.

입력
첫째 줄에 N(1≤N≤32,000), M(1≤M≤100,000)이 주어진다. 
M은 키를 비교한 회수이다. 
다음 M개의 줄에는 키를 비교한 두 학생의 번호 A, B가 주어진다. 
이는 학생 A가 학생 B의 앞에 서야 한다는 의미이다.

학생들의 번호는 1번부터 N번이다.

출력
첫째 줄부터 앞에서부터 줄을 세운 결과를 출력한다. 
답이 여러 가지인 경우에는 아무거나 출력한다.

예제 입력 1 
3 2
1 3
2 3
예제 출력 1 
1 2 3
예제 입력 2 
4 2
4 2
3 1
예제 출력 2 
4 2 3 1


*/

public class 줄세우기_2252 {
	private static int N, M;
	private static int inDegree[];
	private static int result[];
	private static int answer[];
	private static StringBuilder sb = new StringBuilder();
	private static List<List<Integer>> graph = new ArrayList<List<Integer>>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		inDegree = new int[N + 1];
		result = new int[N + 1];
		answer = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph.get(a).add(b);
			inDegree[b]++;
		}

		Arrays.fill(result, 1);

		topologySolve();
//		print1();

		br.close();
	}

	private static void topologySolve() {
		Queue<Integer> q = new LinkedList<Integer>();

		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				q.add(i);
			}
		}

		for (int i = 1; i <= N; i++) {
			if (q.isEmpty())
				return;
			int now = q.poll();

			for (int next : graph.get(now)) {
				inDegree[next]--;

				if (inDegree[next] == 0) {
					q.add(next);

					result[next] = result[now] + 1;
				}
			}
			sb.append(now + " ");
		}

		System.out.println(sb.toString());
	}

	private static void print() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 1; i <= N; i++) {
			map.put(i, result[i]);
		}

		List<Integer> keySet = new ArrayList<Integer>(map.keySet());

		keySet.sort((o1, o2) -> map.get(o1) - map.get(o2));

		for (int i : keySet)
			sb.append(i + " ");

		System.out.println(sb.toString());
	}

	private static void print1() {
		PriorityQueue<Pair> q = new PriorityQueue<Pair>();

		for (int i = 1; i <= N; i++) {
			q.add(new Pair(i, result[i]));
		}

		while (!q.isEmpty()) {
			sb.append(q.poll().index + " ");
		}

		System.out.println(sb.toString());
	}

	private static class Pair implements Comparable<Pair> {
		int index, rank;

		public Pair(int index, int rank) {
			this.index = index;
			this.rank = rank;
		}

		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			return rank - o.rank;
		}
	}
}
