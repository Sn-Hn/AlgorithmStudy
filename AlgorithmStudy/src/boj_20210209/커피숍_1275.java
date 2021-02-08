package boj_20210209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

최솟값 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	10677	4654	3215	47.321%
문제
N(1 ≤ N ≤ 100,000)개의 정수들이 있을 때, a번째 정수부터 b번째 정수까지 중에서 제일 작은 정수를 찾는 것은 어려운 일이 아니다. 
하지만 이와 같은 a, b의 쌍이 M(1 ≤ M ≤ 100,000)개 주어졌을 때는 어려운 문제가 된다. 이 문제를 해결해 보자.

여기서 a번째라는 것은 입력되는 순서로 a번째라는 이야기이다. 
예를 들어 a=1, b=3이라면 입력된 순서대로 1번, 2번, 3번 정수 중에서 최솟값을 찾아야 한다. 
각각의 정수들은 1이상 1,000,000,000이하의 값을 갖는다.

입력
첫째 줄에 N, M이 주어진다. 다음 N개의 줄에는 N개의 정수가 주어진다. 다음 M개의 줄에는 a, b의 쌍이 주어진다.

출력
M개의 줄에 입력받은 순서대로 각 a, b에 대한 답을 출력한다.

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
5
38
20
5


*/

public class 커피숍_1275 {
	private static int N, Q;
	private static int arr[];
	private static long tree[];
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		tree = new long[N*4];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		init(1, N, 1);
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if(x > y) {
				int temp = x;
				x = y;
				y = temp;
			}
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			sb.append(sum(1, N, 1, x, y) + "\n");
			update(1, N, 1, a, b);
		}
		
		System.out.println(sb.toString());
		
		br.close();
	}
	
	private static long init(int start, int end, int node) {
		if(start == end) return tree[node] = arr[start];
		int mid = (start + end) / 2;
		return tree[node] = init(start, mid, node*2) + init(mid+1, end, node*2+1);
	}
	
	private static long sum(int start, int end, int node, int left, int right) {
		if(left > end || right < start) {
			return 0;
		}
		
		if(left <= start && right >= end) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		
		return sum(start, mid, node*2, left, right) + sum(mid+1, end, node*2+1, left, right);
	}
	
	private static long update(int start, int end, int node, int idx, int val) {
		if(idx < start || idx > end) {
			return tree[node];
		}
		
		if(start == end) return tree[node] = val;
		
		int mid = (start + end) / 2;
		
		return tree[node] = update(start, mid, node*2, idx, val) + update(mid+1, end, node*2+1, idx, val);
	}
}
