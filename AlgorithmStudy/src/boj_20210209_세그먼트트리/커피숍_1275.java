package boj_20210209_세그먼트트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

커피숍2 성공분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	256 MB	8943	2494	1823	27.282%
문제
모두 알다시피 동호는 커피숍의 마담이다. (마담이 무엇인지는 본인에게 물어보도록 하자.)

어느 날 커피숍의 손님 A씨가 동호에게 게임을 하자고 했다.

그 게임은 다음과 같은 규칙을 갖는다.

N개의 정수가 있으면, 동호는 다음과 같이 말한다. “3~7번째 수의 합은 무엇이죠?” 그러면 상대방은 “그 답은 000입니다. 
그리고 8번째 수를 2로 고치도록 하죠” 그러면 동호는 “네 알겠습니다.”라고 한 뒤에 다시 상대방이 동호가 했던 것처럼 “8~9번째 수의 합은 무엇이죠?”라고 묻게된다. 
이 것을 번갈아 가면서 반복하는 게임이다.

당신은 이 게임의 심판 역을 맡았다. 요컨대, 질문에 대한 답들을 미리 알아야 한다는 것이다.

당신의 머리가 출중하다면 10만개 가량 되는 정수와 10만턴 정도 되는 게임을 기억할 수 있을 것이다. 
몇판 이 게임을 즐기던 동호는 많은 사람들이 이 게임을 하기를 바라게 되었고, 당신에게 심판 프로그램을 구현해달라고 요청했다.

입력
첫째 줄에 수의 개수 N과 턴의 개수 Q가 주어진다.(1 ≤ N, Q ≤ 100,000) 둘째 줄에는 처음 배열에 들어가 있는 정수 N개가 주어진다. 
세 번째 줄에서 Q+2번째 줄까지는 x y a b의 형식으로 x~y까지의 합을 구하여라, a번째 수를 b로 바꾸어라 라는 뜻의 데이터가 주어진다.

입력되는 모든 수는 -231보다 크거나 같고, 231-1보다 작거나 같은 정수이다.

출력
한 턴마다 구한 합을 한 줄마다 한 개씩 출력한다.

예제 입력 1 
5 2
1 2 3 4 5
2 3 3 1
3 5 4 1
예제 출력 1 
5
10

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
