package study_210704;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*

꿈틀꿈틀 호석 애벌레 - 기능성 출처전체 채점
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	512 MB	263	129	95	53.073%
문제
꿈틀꿈틀 호석 애벌레는 N 개의 먹이가 일렬로 나열된 나뭇가지를 오른쪽으로 기어가려고 한다. 
시작하는 순간의 호석 애벌레가 0의 위치에 있고 i 번째 먹이는 오른쪽으로 i 초 기어가야 도달할 수 있다. 
또한 매초 1 만큼 오른쪽으로 무조건 진행한다.

호석 애벌레는 i 번째 먹이가 맛있을수록 높은 만족도를 얻는다. 
호석 애벌레는 절제라는 것을 모르는 욕심쟁이기 때문에 한번 먹이를 먹기 시작하면 연속적으로 계속 먹어야 하며, 
누적된 만족도가 최소 만족도 K 이상이 되거나 더 이상 먹을 먹이가 없을 때에 연속적으로 먹는 것을 멈춘다. 
만약 최소 만족도 이상이 되면 K 를 초과한 만족도만큼 탈피 에너지를 축적한다. 
직후에 호석 애벌레의 만족도는 다시 0 이 되고 먹이를 먹을 수 있게 된다. 
나뭇가지를 전부 통과했을 때에 소화를 다 못 했을 경우에도 탈피 에너지는 최소 만족도를 넘기는 순간 이미 축적한 것으로 생각하자.



예를 들어 위와 같이 9개의 먹이가 존재하면, 호석 애벌레는 미래를 도모하여 1번 먹이를 과감하게 포기한다. 
그리고 2번부터 먹기 시작해서 3번까지 먹으면 만족도가 9가 되어 3의 에너지를 축적하게 된다. 
같은 이유로 4번 먹이도 포기하고 5번부터 먹으면 7번까지 연속으로 먹어서 15의 만족도를 얻는다. 
이를 통해 9의 탈피 에너지가 쌓인다. 
8, 9번 먹이까지 먹게 되면 2의 탈피 에너지가 축적된다. 
이렇게 얻은 총 14의 탈피 에너지가 위의 예제에서는 최대치이다.

매초마다 호석 애벌레는 오른쪽으로 이동하면서 먹이를 지나치거나 먹기 시작할 수 있다. 
먹기 시작하면 만족도가 채워질때까지 먹게 될것이다. 
어떤 먹이들을 대해 먹어야 축적된 탈피 에너지가 최대가 될 수 있을까?

입력
첫번째 줄에 먹이 개수 N, 최소 만족도 K 가 공백으로 주어진다.

두번째 줄에는 1 번부터 N 번 먹이의 만족도가 순서대로 주어진다.

출력
축적된 탈피 에너지의 최댓값을 구하라. 만약 탈피를 한 번도 할 수 없다면 0을 출력한다.

제한
1 ≤ N ≤ 20, N 은 정수이다.
1 ≤ K ≤ 100, K 는 정수이다.
0 ≤ 각 먹이의 만족도 ≤ 100, 모든 만족도는 정수이다.
예제 입력 1 
3 5
3 4 5
예제 출력 1 
4
첫 번째 먹이를 포기하고 두 번째와 세 번째 먹이를 먹을 수 있다. 이 경우 탈피 에너지는 4 가 된다.

예제 입력 2 
9 6
1 5 4 4 2 3 10 3 5
예제 출력 2 
14
출처
Contest > 류호석배 알고리즘 코딩 테스트 > 제1회 류호석배 알고리즘 코딩 테스트 4번

문제를 검수한 사람: BaaaaaaaaaaarkingDog, dlstj0923, tony9402
문제를 만든 사람: rhs0266
알고리즘 분류
보기

채점 및 기타 정보
39개 이상의 데이터를 맞아야 를 받는다.

*/

public class 꿈틀꿈틀호석애벌레_기능성_20167 {
	private static int N;
	private static int K;
	private static int[] branch;
	private static List<Integer> newBranch = new ArrayList<Integer>();
	private static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		branch = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			branch[i] = Integer.parseInt(st.nextToken());
		}
		
		getMergeBranch();
		
		int result = addEnergy();
		
		System.out.println(result);
		
		print();
		
		br.close();
	}
	
	private static int addEnergy() {
		int len = newBranch.size();
		dp = new int[len];
		
		if (len == 0) {
			return 0;
		}
		
		dp[0] = newBranch.get(0);
		
		if (len == 1) {
			return dp[0];
		}
		dp[1] = Math.max(dp[0], newBranch.get(1));
		
		for (int i = 2; i < len; i++) {
			dp[i] = Math.max(dp[i - 2] + newBranch.get(i), dp[i - 1]);
			if (i >= 3) {
				dp[i] = Math.max(dp[i], dp[i - 3] + newBranch.get(i));
			}
		}
		
		return dp[len - 1];
	}
	
	private static void getMergeBranch() {
		int sum = 0;
		int prev = 0;
		for (int i = 0; i < N; i++ ) {
			sum = prev + branch[i];
			
			if (sum >= K) {
				newBranch.add(sum - K);
				prev = branch[i];
				if (branch[i] >= K) {
					prev = 0;
					newBranch.add(0);
				}
				continue;
			}
			
			prev += branch[i];
		}
	}
	
	private static void print() {
		for (int n : newBranch) {
			System.out.print(n + " ");
		}
		System.out.println();
	}
}
