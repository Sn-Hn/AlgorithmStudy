package boj_20210209_세그먼트트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

최솟값과 최댓값 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	192 MB	11283	5288	3817	49.850%
문제
N(1 ≤ N ≤ 100,000)개의 정수들이 있을 때, a번째 정수부터 b번째 정수까지 중에서 제일 작은 정수, 또는 제일 큰 정수를 찾는 것은 어려운 일이 아니다. 
하지만 이와 같은 a, b의 쌍이 M(1 ≤ M ≤ 100,000)개 주어졌을 때는 어려운 문제가 된다. 
이 문제를 해결해 보자.

여기최솟값과 최댓값 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	192 MB	11283	5288	3817	49.850%
문제
N(1 ≤ N ≤ 100,000)개의 정수들이 있을 때, a번째 정수부터 b번째 정수까지 중에서 제일 작은 정수, 또는 제일 큰 정수를 찾는 것은 어려운 일이 아니다. 
하지만 이와 같은 a, b의 쌍이 M(1 ≤ M ≤ 100,000)개 주어졌을 때는 어려운 문제가 된다. 이 문제를 해결해 보자.

여기서 a번째라는 것은 입력되는 순서로 a번째라는 이야기이다. 
예를 들어 a=1, b=3이라면 입력된 순서대로 1번, 2번, 3번 정수 중에서 최소, 최댓값을 찾아야 한다. 
각각의 정수들은 1이상 1,000,000,000이하의 값을 갖는다.

입력
첫째 줄에 N, M이 주어진다. 다음 N개의 줄에는 N개의 정수가 주어진다. 다음 M개의 줄에는 a, b의 쌍이 주어진다.

출력
M개의 줄에 입력받은 순서대로 각 a, b에 대한 답을 최솟값, 최댓값 순서로 출력한다.

예제 입력 1 
10 4
75
30
100
38
50
51
52
20
81
5
1 10
3 5
6 9
8 10
예제 출력 1 
5 100
38 100
20 81
5 81

*/

public class 최솟값과최댓값_2357 {
	private static int N, M;
	private static int arr[];
	private static int minTree[], maxTree[];
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		minTree = new int[N * 4];
		maxTree = new int[N * 4];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		initMin(1, N, 1);
		initMax(1, N, 1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			int min = min(1, N, 1, a, b);
			int max = max(1, N, 1, a, b);
			sb.append(min + " " + max);
			sb.append("\n");
		}

		System.out.println(sb.toString());

		br.close();
	}

	private static int initMin(int start, int end, int node) {
		if (start == end) {
			return minTree[node] = arr[start];
		}

		int mid = (start + end) / 2;

		return minTree[node] = Math.min(initMin(start, mid, node * 2), initMin(mid + 1, end, node * 2 + 1));
	}

	private static int initMax(int start, int end, int node) {
		if (start == end) {
			return maxTree[node] = arr[start];
		}

		int mid = (start + end) / 2;

		return maxTree[node] = Math.max(initMax(start, mid, node * 2), initMax(mid + 1, end, node * 2 + 1));
	}

	private static int min(int start, int end, int node, int left, int right) {
		if (right < start || end < left) {
			return Integer.MAX_VALUE;
		}

		if (left <= start && end <= right) {
			return minTree[node];
		}

		int mid = (start + end) / 2;
		return Math.min(min(start, mid, node * 2, left, right), min(mid + 1, end, node * 2 + 1, left, right));
	}

	private static int max(int start, int end, int node, int left, int right) {
		if (right < start || end < left) {
			return Integer.MIN_VALUE;
		}

		if (left <= start && end <= right) {
			return maxTree[node];
		}

		int mid = (start + end) / 2;
		return Math.max(max(start, mid, node * 2, left, right), max(mid + 1, end, node * 2 + 1, left, right));
	}
}
