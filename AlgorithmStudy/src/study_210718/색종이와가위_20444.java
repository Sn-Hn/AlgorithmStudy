package study_210718;

/*

색종이와 가위 출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
0.1 초	1024 MB	187	76	68	43.312%
문제
오늘도 역시 준성이는 어김없이 색종이와 쿼리를 푸는 데 실패하였다!!

색종이에 열등감을 느낀 준성이는 가위로 눈에 보이는 색종이를 모두 잘라 버리려고 한다!!

색종이를 자를 때는 다음과 같은 규칙을 따른다.

색종이는 직사각형이며, 색종이를 자를 때는 한 변에 평행하게 자른다.
자르기 시작했으면, 경로 상의 모든 색종이를 자를 때까지 멈추지 않는다.
이미 자른 곳을 또 자를 수 없다.
분노에 찬 가위질을 하던 준성이는 갑자기 하나의 색종이를 정확히 n번의 가위질로 k개의 색종이 조각으로 만들 수 있는지 궁금해졌다.
궁금하지만 화가 나서 코딩에 집중할 수 없는 준성이 대신 코드를 작성해주도록 하자.

입력
첫 줄에 정수 n, k가 주어진다. (1 ≤ n ≤ 231-1, 1 ≤ k ≤ 263-1)

출력
첫 줄에 정확히 n번의 가위질로 k개의 색종이 조각을 만들 수 있다면 YES, 아니라면 NO를 출력한다.

예제 입력 1 
4 9
예제 출력 1 
YES


예제 입력 2 
4 6
예제 출력 2 
NO

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이와가위_20444 {
	private static long N;
	private static long K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Long.parseLong(st.nextToken());
		K = Long.parseLong(st.nextToken());
		
		if (isCut()) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
		
		br.close();
	}
	
	private static boolean isCut() {
		long start = 0;
		long end = N / 2 + 1;
		long mid = 0;
		
		while (start <= end) {
			mid = (start + end) / 2;
			
			long count = getCutPaperCount(mid);
			System.out.println(count);
			
			if (count > K) {
				end = mid - 1;
			}else if (count < K) {
				start = mid + 1;
			}else {
				return true;
			}
		}
		
		return false;
	}
	
	private static long getCutPaperCount(long mid) {
		return (mid + 1) * (N - mid + 1);
	}
	
	private static boolean isCutBruteForce() {
		long count = 0;
		for (int i = 1; i <= N; i++) {
			// N + 1, 2N, 3(N - 1), 4(N - 2), 5(N - 3), ...
			// N + 1, 2N, 3N - 3, 4N- 6, 5N - 15, ...
			count = (i * N) - (i * (i - 2));
			if (count == K) {
				return true;
			}
		}
		
		return false;
	}
}
