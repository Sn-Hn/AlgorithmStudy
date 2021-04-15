package boj_20210420_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;

/*

교환 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	7006	1336	1017	20.147%
문제
0으로 시작하지 않는 정수 N이 주어진다. 이때, M을 정수 N의 자릿수라고 했을 때, 다음과 같은 연산을 K번 수행한다.

1 ≤ i < j ≤ M인 i와 j를 고른다. 그 다음, i번 위치의 숫자와 j번 위치의 숫자를 바꾼다. 이때, 바꾼 수가 0으로 시작하면 안 된다.

위의 연산을 K번 했을 때, 나올 수 있는 수의 최댓값을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 정수 N과 K가 주어진다. N은 1,000,000보다 작거나 같은 자연수이고, K는 10보다 작거나 같은 자연수이다.

출력
첫째 줄에 문제에 주어진 연산을 K번 했을 때, 만들 수 있는 가장 큰 수를 출력한다. 만약 연산을 K번 할 수 없으면 -1을 출력한다.

예제 입력 1 
16375 1
예제 출력 1 
76315
예제 입력 2 
132 3
예제 출력 2 
312

31299 2
99231

*/

public class 교환_1039 {
	private static int N, K;
	private static int inputLen = 0;
	
	private static class Point {
		int digit, cnt;
		public Point(int digit, int cnt) {
			this.digit = digit;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String input = st.nextToken();
		inputLen = input.length();
		N = Integer.parseInt(input);
		K = Integer.parseInt(st.nextToken());
		
		changeDigits();
		
		br.close();
	}
	
	private static void changeDigits() {
		boolean visited[][] = new boolean[K+1][1000001];
		int result = -1;
		
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(N, 0));
		visited[0][N] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			if(p.cnt == K) {
				if(result < p.digit) {
					result = p.digit;
				}
				continue;
			}
			
			for(int i = 0; i < inputLen-1; i++) {
				for(int j = i+1; j < inputLen; j++) {
					int next = parseInt(p.digit, i, j);
					if(next != -1 && !visited[p.cnt+1][next]) {
						visited[p.cnt+1][next] = true;
						q.add(new Point(next, p.cnt+1));
					}
				}
			}
		}
		
		System.out.println(result);
	}
	
	private static int parseInt(int num, int a, int b) {
		char[] arr = (num + "").toCharArray();
		
		if(a == 0 && arr[b] == '0') {
			return -1;
		}
		
		char temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
		
		int ret = 0;
		for(int i = 0; i < arr.length; i++) {
			ret *= 10;
			ret += arr[i] - '0';
		}
		
		return ret;
	}
	
}
