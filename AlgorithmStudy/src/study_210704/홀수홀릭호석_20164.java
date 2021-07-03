package study_210704;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

홀수 홀릭 호석 출처전체 채점
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	512 MB	384	253	203	66.997%
문제
호석이는 짝수랑 홀수 중에서 이니셜이 같은 홀수를 더 좋아한다. 
운전을 하던 호석이는 앞차의 번호판이 홀수로 가득할 때 사랑스러움을 느낄 정도이다. 
전화번호도 홀수만 있고 싶다. 
그렇게 홀수 홀릭에 빠진 호석이는 가지고 있는 수 N을 일련의 연산을 거치면서, 등장하는 숫자들에서 홀수를 최대한 많이 많이 보고 싶다.

하나의 수가 주어졌을 때 호석이는 한 번의 연산에서 다음과 같은 순서를 거친다.

수의 각 자리 숫자 중에서 홀수의 개수를 종이에 적는다.
수가 한 자리이면 더 이상 아무것도 하지 못하고 종료한다.
수가 두 자리이면 2개로 나눠서 합을 구하여 새로운 수로 생각한다.
수가 세 자리 이상이면 임의의 위치에서 끊어서 3개의 수로 분할하고, 3개를 더한 값을 새로운 수로 생각한다.
호석이는 연산이 종료된 순간에 종이에 적힌 수들을 모두 더한다. 
그렇게 최종적으로 얻은 수를 최종값이라고 하자. 
예를 들어, 시작하는 수가 82019 라고 하자. 
그럼 아래와 같이 나누게 되면 5개의 홀수를 볼 수 있기 때문에, 최종값이 5가 된다.



시작할 때 호석이가 가진 수를 N 이라고 했을 때, 만들 수 있는 최종값 중 최솟값과 최댓값을 구해주자.

입력
첫번째 줄에 호석이가 처음 시작할 때 가지고 있는 수 N 이 주어진다.

출력
첫 번째 줄에 호석이가 만들 수 있는 최종값 중에서 최솟값과 최댓값을 순서대로 공백으로 구분하여 출력한다.

제한
1 ≤ N ≤ 109-1, N 은 자연수이다.
예제 입력 1 
514
예제 출력 1 
4 4
514 -> 5+1+4 = 10

10 -> 1+0 = 1

1

각 숫자에서 등장한 홀수가 2개, 1개, 1개 이므로 답은 4이다.

예제 입력 2 
82019
예제 출력 2 
4 5
예제 입력 3 
999999999
예제 출력 3 
11 18
출처
Contest > 류호석배 알고리즘 코딩 테스트 > 제1회 류호석배 알고리즘 코딩 테스트 1번

문제를 검수한 사람: BaaaaaaaaaaarkingDog, dlstj0923, tony9402
문제를 만든 사람: rhs0266
알고리즘 분류
보기

채점 및 기타 정보
102개 이상의 데이터를 맞아야 를 받는다.

*/

public class 홀수홀릭호석_20164 {
	private static int N;
	private static boolean[] isVisited = new boolean[1000000000];
	private static int maxOddCount = Integer.MIN_VALUE;
	private static int minOddCount = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
//		String n = Integer.toString(N);
		int n = (int) (Math.log10(N) + 1);
		
		getMoreThanThreeDigits(N, n, getOddCount(N, 0));
		
		System.out.println(minOddCount + " " + maxOddCount);
		
		br.close();
	}
	
	private static int getOddCount(int n, int oddCnt) {
		int remain = 0;
		while(n > 0) {
			remain = n % 10;
			if (remain % 2 == 1) {
				oddCnt++;
			}
			n /= 10;
		}
		
		return oddCnt;
	}
	
	private static void getMoreThanThreeDigits(int n, int count, int oddCnt) {		
		int copyN = 0;
		int remain = 0;
		int newN = 0;
		
		if (isVisited[n]) {
			return;
		}
		
		isVisited[n] = true;
		
//		System.out.println(n);
		
		for (int i = 1; i < n - 1; i++) {
			copyN = n;
			remain = (int) (copyN % Math.pow(10, i));
			copyN /= Math.pow(10, i);
			for (int j = 1; j < count - i; j++) {
				newN = remain;
				int copyNewN = copyN;
				remain = (int) (copyNewN % Math.pow(10, j));
				copyNewN /= Math.pow(10, j);
				newN = newN + remain + copyNewN;
				if (isVisited[newN]) {
					continue;
				}
				isVisited[newN] = true;
				int cnt = (int) (Math.log10(newN) + 1);
				if (cnt >= 3) {
					getMoreThanThreeDigits(newN, cnt, getOddCount(newN, oddCnt));
				}else if(cnt == 2) {
					getDoubleDigits(newN, oddCnt);
				}else {
					oddCnt = getOddCount(n, oddCnt);
					maxOddCount = Math.max(maxOddCount, oddCnt);
					minOddCount = Math.min(minOddCount, oddCnt);
					return;
				}
				
			}
		}
	}
	 
	
	private static void getDoubleDigits(int n, int oddCnt) {
		oddCnt = getOddCount(n, oddCnt);
		int remain = n % 10;
		n /= 10;
		
		n += remain;
		
		
		if (n > 10) {
			getDoubleDigits(n, oddCnt);
		}
		
		if (n < 10) {
			oddCnt = getOddCount(n, oddCnt);
			maxOddCount = Math.max(maxOddCount, oddCnt);
			minOddCount = Math.min(minOddCount, oddCnt);
			return;
		}
	}
}
