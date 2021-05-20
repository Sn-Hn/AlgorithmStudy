package boj_20210520_복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

수들의 합 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	256 MB	4663	1445	1127	31.064%
문제
N개의 수 A[1], A[2], …, A[N] 이 주어졌을 때, 함수 Sum(i, j)는 A[i]+A[i+1]+…+A[j]를 구하는 함수이다. 
(i>j일 경우에는 A[j]+A[j+1]+...+A[i]) A가 주어졌을 때, Sum(i, j)를 구하는 것은 매우 쉬운 문제이다. 
이러한 (i, j)가 여러 개 주어졌을 때도 별로 어려운 문제는 아니다.

Sum함수와 더불어 Modify라는 함수를 정의하자. Modify(i, k)를 수행하면 A[i]=k가 되는 함수이다. 
Sum함수와 Modify 함수의 사용 목록이 주어졌을 때, 이에 해당하는 연산을 하는 프로그램을 작성하시오. 
두 함수를 섞어서 사용할 수도 있다.

입력
첫째 줄에는 N(1≤N≤1,000,000), M(1≤M≤1,000,000)이 주어진다. 
M은 수행한 명령의 개수이며 다음 M개의 줄에는 수행한 순서대로 함수의 목록이 주어진다. 
첫 번째 숫자는 어느 함수를 사용했는지를 나타내며, 0일 경우에는 Sum 함수를, 1일 경우에는 Modify 함수를 나타낸다. 
다음 두 수는 각 함수의 인자 (i, j)나 (i, k)를 나타낸다. 
처음에는 A[1]=A[2]=…A[N]=0이다. Modify인 경우에 1 ≤ k ≤ 100,000 이다.

출력
Sum 함수의 개수만큼 각 줄에 Sum 함수의 리턴값을 출력한다.

예제 입력 1 
3 5
0 1 3
1 1 2
1 2 3
0 2 3
0 1 3
예제 출력 1 
0
3
5

*/

public class 수들의합_2268 {
	private static int N;
	private static int M;
	private static long[] arr;
	private static long[] tree;
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new long[N + 1];
		tree = new long[N * 4];
		
		init(1, N, 1);
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a == 0) {
				if(b > c) {
					int tmp = b;
					b = c;
					c = tmp;
				}
				sb.append(sum(1, N, 1, b, c) + "\n");
			}else {
				modify(1, N, 1, b, c);
			}
		}
		
		System.out.println(sb.toString());
		
		br.close();
	}
	
	private static long init(int start, int end, int node) {
		if(start == end) {
			return tree[node] = arr[start];
		}
		
		int mid = (start + end) / 2;
		
		return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
	}
	
	private static long sum(int start,int end, int node, int left, int right) {
		if(left > end || right < start) {
			return 0;
		}
		
		if(left <= start && right >= end) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		
		return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
	}
	
	private static long modify(int start, int end, int node, int index, int value) {
		if(index < start || index > end) {
			return tree[node];
		}
		
		if(start == end) {
			return tree[node] = value;
		}
		
		int mid = (start + end) / 2;
		
		return tree[node] = modify(start, mid, node * 2, index, value) + modify(mid + 1, end, node * 2 + 1, index, value);
	}
}
