package boj_20210119;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

피자 굽기 출처다국어분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	256 MB	2943	789	597	27.703%
문제
월드피자 원주 지점에서 N개의 피자 반죽을 오븐에 넣고 구우려고 한다. 
그런데, 월드피자에서 만드는 피자 반죽은 지름이 제각각이다. 
그런가하면, 월드피자에서 사용하는 오븐의 모양도 몹시 오묘하다. 
이 오븐은 깊은 관처럼 생겼는데, 관의 지름이 깊이에 따라 들쭉날쭉하게 변한다. 
아래는 오븐의 단면 예시이다.



피자 반죽은 완성되는 순서대로 오븐에 들어간다. 
이렇게 N개의 피자가 오븐에 모두 들어가고 나면, 맨 위의 피자가 얼마나 깊이 들어가 있는지가 궁금하다. 
이를 알아내는 프로그램을 작성하시오.

입력
첫째 줄에 오븐의 깊이 D와 피자 반죽의 개수 N이 공백을 사이에 두고 주어진다. (1<=D, N<=300,000) 
둘째 줄에는 오븐의 최상단부터 시작하여 깊이에 따른 오븐의 지름이 차례대로 주어진다.
셋째 줄에는 피자 반죽이 완성되는 순서대로, 그 각각의 지름이 주어진다. 
오븐의 지름이나 피자 반죽의 지름은 10억 이하의 자연수이다.

출력
첫째 줄에, 마지막 피자 반죽의 위치를 출력한다. 
오븐의 최상단이 1이고, 최하단 가장 깊은 곳이 D이 된다. 
만약 피자가 모두 오븐에 들어가지 않는다면, 0을 출력한다.

예제 입력 1 
7 3
7 6 4 3 6 2 3
3 5 5
예제 출력 1 
2

*/

public class 피자굽기_1756 {
	private static int D, N;
	private static int depth;
	private static int oven[];
	private static int pizza[];
	private static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		oven = new int[D];
		pizza = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < D; i++) {
			oven[i] = Integer.parseInt(st.nextToken());
			if(i == 0) continue;
			if(oven[i] > oven[i-1]) {
				oven[i] = oven[i-1];
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			pizza[i] = Integer.parseInt(st.nextToken());
		}
		depth = D-1;
		
		// 이분탐색
		for(int i = 0; i < N; i++) {
			binary(pizza[i]);
		}
		
		result ++;
		
		System.out.println(result);
		
		br.close();
	}
	
	// 이분탐색
	private static void binary(int find) {
		int start = 0;
		int end = depth;
		int res = -1;
		// start가 end 보다 커지면 종료
		while(start <= end) {
			// 중간 값 계산
			int mid = (start + end) / 2;
			// oven의 중간값이 찾으려는 수보다 크거나 같다면
			if(oven[mid] >= find) {
				// 중간 값 + 1 -> 시작 위치
				res = mid;
				start = mid + 1;
			}else {
				end = mid - 1;
			}
		}
		depth = res-1;
		result = Math.min(result, res);
		
	}
	
}
