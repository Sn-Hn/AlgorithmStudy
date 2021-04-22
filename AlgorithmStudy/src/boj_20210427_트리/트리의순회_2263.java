package boj_20210427_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

트리의 순회 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
5 초	128 MB	10625	4071	2778	36.452%
문제
n개의 정점을 갖는 이진 트리의 정점에 1부터 n까지의 번호가 중복 없이 매겨져 있다.
이와 같은 이진 트리의 인오더와 포스트오더가 주어졌을 때, 프리오더를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 n(1≤n≤100,000)이 주어진다. 
다음 줄에는 인오더를 나타내는 n개의 자연수가 주어지고, 그 다음 줄에는 같은 식으로 포스트오더가 주어진다.

출력
첫째 줄에 프리오더를 출력한다.

예제 입력 1 
3
1 2 3
1 3 2
예제 출력 1 
2 1 3

ex)
7
4 2 7 5 1 3 6
4 7 5 2 6 3 1

*/

public class 트리의순회_2263 {
	private static int N;
	private static int inOrder[];
	private static int postOrder[];
	private static int index[];
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		inOrder = new int[N];
		postOrder = new int[N];
		index = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			inOrder[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			postOrder[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0; i < N; i++) {
			index[inOrder[i]] = i;
		}
		
		preOrder(0, N-1, 0, N-1);
		System.out.println(sb.toString());
		
		br.close();
	}
	
	private static void preOrder(int inOrderStart, int inOrderEnd, int postOrderStart, int postOrderEnd) {
		if(inOrderStart > inOrderEnd || postOrderStart > postOrderEnd) {
			return;
		}
		
		int root = postOrder[postOrderEnd];
		sb.append(root + " ");
		
		// 현재 부모에서 왼쪽 자식의 수
		int left = index[root] - inOrderStart;
		
		// left
		preOrder(inOrderStart, index[root] - 1, postOrderStart, postOrderStart + left - 1);
		
		// right
		preOrder(index[root] + 1, inOrderEnd, postOrderStart + left, postOrderEnd - 1);
	}
}
