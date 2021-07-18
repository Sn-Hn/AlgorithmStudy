package study_210718;

/*

전구 스페셜 저지출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	1158	493	395	46.145%
문제
N개의 스위치와 N개의 전구를 가진 하나의 스위칭 박스가 있다. 이 박스의 왼편에는 스위치가 있고, 오른편에는 전구가 달려있다. 모든 스위치와 전구들은 1에서부터 N까지의 번호를 가지며 같은 번호의 스위치와 전구는 전선으로 서로 연결되어 있다.



하나의 스위치를 누르면 그 스위치와 연결된 전구에 불이 들어오게 된다. 두 개 이상의 스위치를 같이 누르는 경우, 전선이 서로 만나면 만난 전선에 연결된 전구들의 불은 켜지지 않는다.

위 그림에서 1번과 4번의 스위치를 같이 누르면 1번과 4번의 전구에는 불이 켜지지만, 1번과 2번의 스위치를 같이 누르면 1번과 2번 전구의 불은 켜지지 않는다. 1번과 3번 그리고 5번 스위치를 같이 누르면 전선이 만나는 1번과 5번 전구는 켜지지 않지만 3번 전구는 켜지게 된다.

여러분이 할 일은 가장 많은 전구가 켜지도록 스위치를 누르는 것이다. 위 그림에서는 3번과 4번 그리고 5번 스위치를 누르는 경우와 1번과 3번 그리고 4번을 누르는 경우에 세 개의 전구가 켜지게 되고, 이 두 가지 경우가 가장 많은 전구가 켜지는 경우이다.

스위치의 번호순서와 전구의 번호순서가 주어질 때, 어떤 스위치를 누르면 가장 많은 전구가 켜지는지를 알아내는 프로그램을 작성하시오.

입력
첫 번째 줄에는 스위치의 수(전구의 수)를 나타내는 정수 N (1 ≤ N ≤ 10,000)이 주어진다. 두 번째 줄에는 N개의 스위치 번호들이 위에서부터 순서대로 빈칸을 사이에 두고 주어진다. 세 번째 줄에는 N개의 전구 번호들이 위에서부터 순서대로 빈칸을 사이에 두고 주어진다.

출력
첫 번째 줄에는 가장 많은 전구가 켜지게 하는 스위치의 수를 출력한다. 두 번째 줄에는 눌러야 하는 스위치의 번호를 오름차순(번호가 커지는 순서)으로 빈칸을 사이에 두고 하나의 줄에 출력한다. 단, 두 번째 줄에 출력할 수 있는 답이 두 가지 이상일 때에는 그 중 한 가지만 출력한다.

예제 입력 1 
5
2 4 1 5 3
4 5 1 3 2
예제 출력 1 
3
3 4 5

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class 전구_2550 {
	private static int N;
	private static int[] endLight;
	private static int[] endIndex;
	private static int[] dp;
	private static Map<Integer, Integer> startLight = new HashMap<Integer, Integer>(); 
	private static int max = 0;
	private static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		endLight = new int[N + 1];
		endIndex = new int[N + 1];
		dp = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			startLight.put(Integer.parseInt(st.nextToken()), i);
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			endLight[i] = Integer.parseInt(st.nextToken());
			endIndex[i] = startLight.get(endLight[i]); 
		}
		
		LIS();
		
		print();
		
		
		System.out.println(result.toString());
		
		br.close();
	}
	
	private static void LIS() {
		for (int i = 1; i <= N; i++) {
			dp[i] = 1;
			for (int j = 1; j < i; j++) {
				if (endIndex[i] > endIndex[j] && dp[i] <= dp[j]) {
					dp[i] = dp[j] + 1;
				}
			}
			max = Math.max(max, dp[i]);
		}
		
		result.append(max).append("\n");
		
		int rank = max;
		List<Integer> list = new ArrayList<>();
		for (int i = N; i >= 1; i--) {
			if (dp[i] == rank) {
				list.add(endLight[i]);
				rank--;
			}
		}
		
		Collections.sort(list, (o1, o2) -> o1 - o2);
		
		for (int i : list) {
			result.append(i).append(" ");
		}
	}
	
	private static void print() {
		for (int i = 1; i <= N; i++) {
			System.out.print(dp[i] + " ");
		}
		System.out.println();
	}
}
