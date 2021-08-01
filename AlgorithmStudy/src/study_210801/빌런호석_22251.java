package study_210801;

/*

빌런 호석 성공출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	512 MB	222	105	86	46.237%
문제
치르보기 빌딩은 층부터 층까지 이용이 가능한 엘리베이터가 있다. 엘리베이터의 층수를 보여주는 디스플레이에는 최대  자리의 수가 보인다. 수는 으로 시작할 수도 있다. 부터 까지의 각 숫자가 디스플레이에 보이는 방식은 아래와 같다. 각 숫자는 7개의 표시등 중의 일부에 불이 들어오면서 표현된다.



예를 들어 인 경우에 층과 층은 아래와 같이 보인다.

                  

빌런 호석은 치르보기 빌딩의 엘리베이터 디스플레이의 LED 중에서 최소 개, 최대 개를 반전시킬 계획을 세우고 있다. 반전이란 켜진 부분은 끄고, 꺼진 부분은 켜는 것을 의미한다. 예를 들어 숫자 을 로 바꾸려면 총 5개의 LED를 반전시켜야 한다. 또한 반전 이후에 디스플레이에 올바른 수가 보여지면서  이상  이하가 되도록 바꿔서 사람들을 헷갈리게 할 예정이다. 치르보기를 사랑하는 모임의 회원인 당신은 호석 빌런의 행동을 미리 파악해서 혼쭐을 내주고자 한다. 현재 엘리베이터가 실제로는 층에 멈춰있을 때, 호석이가 반전시킬 LED를 고를 수 있는 경우의 수를 계산해보자.

입력
 가 공백으로 주어지고 첫째 줄에 주어진다.

출력
호석 빌런이 엘리베이터 LED를 올바르게 반전시킬 수 있는 경우의 수를 계산해보자.

제한
 
예제 입력 1 
9 1 2 5
예제 출력 1 
4
LED를 2개까지 바꿀 수 있을 때, 5층에서 3층, 6층, 8층, 그리고 9층으로 바꿔버릴 수 있다.

예제 입력 2 
48 2 5 35
예제 출력 2 
30

*/

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class 빌런호석_22251 {
	private static final int DIGITCOUNT = 10; 
	private static int N;
	private static int K;
	private static int P;
	private static int X;
	
	private static int count = 0;
	
	private static Integer[][] reverseDigital = 
		{
				{0, 4, 3, 3, 4, 3, 2, 3, 1, 2},
				{0, 0, 5, 3, 2, 5, 6, 1, 5, 4},
				{0, 0, 0, 2, 5, 4, 3, 4, 2, 3},
				{0, 0, 0, 0, 3, 2, 3, 2, 2, 1},
				{0, 0, 0, 0, 0, 3, 4, 3, 3, 2},
				{0, 0, 0, 0, 0, 0, 1, 4, 2, 1},
				{0, 0, 0, 0, 0, 0, 0, 5, 1, 2},
				{0, 0, 0, 0, 0, 0, 0, 0, 4, 3},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		setSymmetry();
		
		getList();
		
		System.out.println(count);
		
		
		br.close();
	}
	
	private static void getReverseAllCase(List<Integer> nowFloor, int p, int sum, int depth) {
		if (depth == K) {
			if (sum <= N && sum != X && sum > 0) {
				System.out.println("sum : " + sum);
				count++;
			}
			return;
		}
		
		int now = nowFloor.get(depth);
		
		for (int i = 0; i < DIGITCOUNT; i++) {
			int cnt = reverseDigital[now][i];
			int cur = merge(sum, i);
			if (p >= cnt && N >= cur) {
				System.out.println(p + ", " + cur);
				getReverseAllCase(nowFloor, p - cnt, cur, depth + 1);
			}
		}
	}
	
	private static int merge(int a, int b) {
		return 10 * a + b;
	}
	
	private static void getList() {
		List<Integer> nowFloor = getDigits(X);
		List<Integer> limits = getDigits(N);
		
		while (limits.size() > nowFloor.size()) {
			nowFloor.add(0, 0);
		}
		
		getReverseAllCase(nowFloor, P, 0, 0);
	}
	
	private static List<Integer> getDigits(int n) {
		List<Integer> digits = new ArrayList<>();
		while(n > 0) {
			digits.add(0, n % 10);
			n /= 10;
		}
		
		return digits;
	}
	
	private static void setSymmetry() {
		for (int i = 0; i < DIGITCOUNT; i++) {
			for (int j = 0; j < i; j++) {
				reverseDigital[i][j] = reverseDigital[j][i];
			}
		}
	}
	
}
