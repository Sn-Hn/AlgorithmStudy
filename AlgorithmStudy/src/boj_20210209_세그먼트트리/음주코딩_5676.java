package boj_20210209_세그먼트트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

음주 코딩 출처다국어분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	4221	1308	991	31.460%
문제
오늘은 ACM-ICPC 대회 전 날이다. 상근이는 긴장을 풀기 위해서 팀원들과 근처 술집으로 갔다.

상근이와 친구들은 다음 날 있을 대회를 연습하기 위해서 작은 게임을 하기로 했다.

먼저, 선영이는 상근이에게 총 N개의 정수로 이루어진 수열 X1, X2, ... XN을 적어준다. 
게임은 총 K번 라운드로 이루어져있고, 매 라운드마다 선영이는 상근이에게 명령을 하나씩 내린다. 
명령은 아래와 같이 두 가지가 있다.

변경: 이 명령은 친구가 수열의 한 값을 바꾸고 싶을 때 사용한다.
곱셈: 선영이는 상근이에게 i와 j를 말한다. 
상근이는 Xi × Xi+1 × ... × Xj-1 × Xj 의 값이 양수인지, 음수인지, 0인지를 대답한다.
곱셈 명령에서 상근이가 대답을 잘못했을 때의 벌칙은 소주 한 잔이다. 
상근이는 갑자기 대회가 걱정되기 시작했다. 
또, 상근이는 발머의 피크 이론을 증명하고 싶지 않다.

다행히 선영이는 상근이에게 노트북 사용을 허락했다. 
상근이는 자신의 수학 실력보다 코딩 실력을 더 믿는다.

상근이를 돕는 프로그램을 작성하시오.

입력
입력은 여러 개의 테스트 케이스로 이루어져 있다.

각 테스트 케이스의 첫째 줄에는 수열의 크기 N과 게임의 라운드 수 K가 주어진다. (1 ≤ N, K ≤ 105)

둘째 줄에는 총 N개의 숫자 Xi가 주어진다. (-100 ≤ Xi ≤ 100)

다음 K개 줄에는 선영이가 내린 명령이 주어진다. 명령은 C 또는 P로 시작한다.

C로 시작하는 명령은 변경 명령이고, 그 다음에 i와 V가 주어진다. 
Xi를 V로 변경하는 명령이다. (1 ≤ i ≤ N, -100 ≤ V ≤ 100)

P로 시작하는 명령은 곱셈 명령이고, 그 다음에 i와 j가 주어진다. (1 ≤ i ≤ j ≤ N)

각 테스트 케이스에 곱셈 명령은 항상 한 번 이상있다.

출력
각 테스트 케이스마다 곱셈 명령의 결과를 한 줄에 모두 출력하면 된다.
출력하는 i번째 문자는 i번째 곱셈 명령의 결과이다.
양수인 경우에는 +, 음수인 경우에는 -, 영인 경우에는 0을 출력한다.

예제 입력 1 
4 6
-2 6 0 -1
C 1 10
P 1 4
C 3 7
P 2 2
C 4 -5
P 1 4
5 9
1 5 -2 4 3
P 1 2
P 1 5
C 4 -5
P 1 5
P 4 5
C 3 0
P 1 5
C 4 -5
C 4 -5
예제 출력 1 
0+-
+-+-0

*/

public class 음주코딩_5676 {
	private static int N, K;
	private static int arr[];
	private static int tree[];
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		
		while((input = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(input);
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			arr = new int[N+1];
			tree = new int[N*4];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				int temp = Integer.parseInt(st.nextToken());
				
				arr[i] = (temp == 0) ? 0 : (temp > 0) ? 1 : -1;
			}
			init(1, N, 1);
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				char c = st.nextToken().charAt(0);
				int idx1 = Integer.parseInt(st.nextToken());
				int idx2 = Integer.parseInt(st.nextToken());
				if(c == 'C') {
					idx2 = (idx2 == 0) ? 0 : (idx2 > 0) ? 1 : -1;
					
					update(idx1, idx2, 1, N, 1);
				}else if(c == 'P'){
					int result = mul(1, N, 1, idx1, idx2);
					sb.append((result == 0) ? 0 : (result > 0) ? "+" : "-");
				}
			}
			sb.append("\n");
			
		}
		
		System.out.println(sb.toString());
		System.out.println(123);
		
		
		br.close();
	}
	
	// 트리를 쪼개면서 맨 아래까지 내려가면서 tree에 저장시킨다.
	private static int init(int start, int end, int node) {
		if(start == end) {
			return tree[node] = arr[start];
		}
		int mid = (start + end) / 2;
		return tree[node] = init(start, mid, node*2) * init(mid+1, end, node*2+1);
	}
	
	// 변경시키고 싶은 값이 있을때
	private static int update(int idx, int val, int node, int start, int end) {
		// 변경시키고 싶은 위치가 start보다 작거나 end보다 작으면 tree[node]를 반환
		// 변경을 시켜도 값에 상관이 없으므로
		if(idx < start || end < idx) {
			return tree[node];
		}
		if(start == end) {
			return tree[node] = val;
		}
		int mid = (start + end) / 2;
		
		return tree[node] = update(idx, val, node*2, start, mid) * update(idx, val, node*2+1, mid+1, end);
	}
	
	// left와 right는 값을 구하고 싶은 구간
	private static int mul(int start, int end, int node, int left, int right) {
		// 값을 구하고 싶은 구간의 left값 즉, 작은 값이 end 보다 크다면
		// 값을 구하고 싶은 구간의 right값 즉, 큰 값이 start 보다 작다면
		if(left > end || right < start) {
			return 1;
		}
		// 값을 구하고 싶은 구간의  left값 즉, 작은 값이 start 보다 작다면
		// 값을 구하고 싶은 구간의 right값 즉, 큰 값이 end보다 크다면 
		// 구하고 싶은 구간이 아니므로 저장되어있는 tree[node]를 반환
		if(left <= start && end <= right) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		return mul(start, mid, node*2, left, right) * mul(mid+1, end, node*2+1, left, right);
	}
}
