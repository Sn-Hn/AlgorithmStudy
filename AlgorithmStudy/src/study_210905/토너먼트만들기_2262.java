package study_210905;

/*

토너먼트 만들기
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	1449	683	545	51.270%
문제
월드시에서는 매년 n명의 사람들이 모여 월드 크래프트라는 게임의 토너먼트 대회를 치른다. 
이 게임은 특성상 실력만이 승패를 좌우하기 때문에, 아무리 실력이 엇비슷한 사람이 시합을 치러도 랭킹이 높은 사람이 반드시 이기게 된다. 
따라서 월드시에서는 게임을 흥미진진하게 만들기 위해서, 부전승을 여러 번 만들더라도 각 시합에 임하는 선수들의 랭킹 차이를 비슷하게 만들려고 한다.

토너먼트를 만들 때에는 이미 추첨이 된 순서대로 선수들을 배치하고, 왼쪽에서 오른쪽의 순서가 어긋나지 않도록 시합을 정한다. 
물론 부전승을 임의로 만들 수 있지만, 토너먼트가 꼬여서는 안 된다. 
또한, 각 시합에 임하는 두 선수의 랭킹의 차이의 합이 최소가 되도록 하려 한다.



예를 들어 추첨 결과가 차례로 랭킹 1, 6, 2, 5, 3, 4위의 선수들이었을 때의 토너먼트 세 개가 위에 있다. 
<A>의 경우는 각 시합이 (1 6), (2 5), (3 4), (1 2), (1 3)으로 랭킹 차이의 합이 5+3+1+1+2=12가 된다. 
반면에 <B>는 11이, <C>는 10이 된다.

토너먼트 추첨 결과가 주어졌을 때, 각 시합에 임하는 두 선수의 랭킹 차이의 총 합의 최솟값을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 자연수 n(1≤n≤256)이 주어진다. 
다음 줄에는 추첨 결과를 나타내는 n명의 선수들의 랭킹이 주어진다. 
각 선수의 랭킹은 1부터 n까지의 자연수로 나타나며, 랭킹이 같은 경우는 없다고 가정하자.

출력
첫째 줄에 답을 출력한다.

예제 입력 1 
6
1 6 2 5 3 4
예제 출력 1 
9

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 토너먼트만들기_2262 {
	private static int N;
	private static List<Integer> rank = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			rank.add(Integer.parseInt(st.nextToken()));
		}

		System.out.println(play());
		
		br.close();
	}
	
	private static int play() {
		int lowRank = N;
		int sumDiffRanking = 0;
		while (rank.size() > 1) {
			for (int i = 0; i < rank.size(); i++) {
				if (rank.get(i) == lowRank) {
					int idx = getRankingDifferenceIndex(i, rank.size());
					sumDiffRanking += rank.get(i) - rank.get(idx);
					lowRank --;
					rank.remove(i);
					break;
				}
			}
		}
		
		return sumDiffRanking;
	}
	
	private static int getRankingDifferenceIndex(int idx, int size) {
		if (idx == 0) {
			return idx + 1;
		}
		
		if (idx == size - 1 || rank.get(idx - 1) > rank.get(idx + 1)) {
			return idx - 1;
		}
		
		return idx + 1;
	}
}
