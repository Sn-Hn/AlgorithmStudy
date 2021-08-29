package study_210822;

/*

가희와 읽기 쓰기 놀이 2 스페셜 저지출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2.5 초	512 MB	32	8	8	26.667%
문제
가희와 친구들은 읽기 쓰기 놀이를 하고 있습니다. 읽기 쓰기 놀이는 빈 리스트를 가지고 시작합니다. 

놀이에서 사용하는 카드에 적혀져 있는 연산은 하나입니다.

add c
리스트 뒤에 수 c를 추가합니다. 
놀이의 규칙은 다음과 같습니다.

빈 리스트로 게임을 시작합니다.
각 턴을 수행하는 사람은 1명입니다.
턴을 수행하는 사람은 가지고 있는 카드에 적혀져 있는 연산을 수행하고 턴을 종료합니다.
문자열 게임에 참가하는 사람은 N명이고, 카드는 1번 카드부터 C번 카드까지 총 C장 있습니다.

N개의 제약 조건이 주어졌을 때 제약 조건에 맞게 사람들이 카드를 내서 결과 (길이가 C인) 리스트를 만들 수 있을까요?

만들 수 있다면, 각 턴마다 어떤 사람이 카드를 냈는지 구해 주세요. 그렇지 않으면 -1을 출력해 주세요.

입력
1번째 줄에 N, C가 공백으로 구분되어 주어집니다.

2번째 줄에 게임의 결과인 리스트에 있는 수들이 주어집니다. 0번째 인덱스에 있는 수부터 C-1번째 인덱스에 있는 수까지 공백으로 구분되어 주어집니다.

3번째 줄 부터 N+2번째 줄까지 1번 제약 조건부터 N번 제약 조건까지 제약 조건 N개가 주어집니다.

제약 조건이 주어질 때에는, 내야 하는 카드의 개수와 어떤 순서로 카드를 내야 하는지가 주어집니다.

예를 들어 3번째 줄에 3 2 4 5 가 있다면, 1번 사람이 2번 카드, 4번 카드, 5번 카드를 순서대로 내야 하는 것을 의미합니다.

N+3번째 줄부터 N+C+2번째 줄까지 1번 카드부터 C번 카드에 적혀져 있는 연산이 주어집니다. 카드에 있는 연산은 하나입니다.

출력
제약 조건에 맞게 게임을 해서 결과 리스트를 만들 수 있다면, 각 턴마다 어떤 사람이 카드를 내야 하는지 공백으로 구분해서 출력해 주세요.

답이 여러 개인 경우에는 아무거나 출력하고, 그렇지 않으면 -1을 출력해 주세요.

제한
N과 C는 구간 [1, 2×104]에 속하는 정수이며, N ≤ C입니다.
제약 조건에서 내야 하는 카드의 개수는 구간 [1, C]에 속하는 정수이며, 내야 하는 카드 개수의 합은 C입니다.
제약 조건에서 내야 하는 카드의 번호는 구간 [1, C]에 속하는 정수입니다.
1번부터 C번 카드는 N개의 제약 조건에서 총 1번만 등장합니다.
1번부터 C번 카드에 적혀있는 c는 [1, 109]에 속하는 정수입니다.
결과 리스트에 있는 수는 [1, 109]에 속하는 정수이며, 결과 리스트에 2번 이상 나오는 수가 나오는 횟수를 합하면 최대 6번 입니다.
예제 입력 1 
2 3
1211 1225 1517
2 1 3
1 2
ADD 1211
ADD 1517
ADD 1225
예제 출력 1 
1 1 2
1번이 1번 카드, 3번 카드를 순서대로 낸 다음에, 2번이 2번 카드를 내면 리스트 [1211, 1225, 1517]를 만들 수 있습니다.

예제 입력 2 
2 3
1517 1225 1211
2 1 3
1 2
ADD 1211
ADD 1517
ADD 1225
예제 출력 2 
-1
1225 다음에 1211을 추가하려면, 3번 카드를 낸 다음에 1번 카드를 내야 합니다.

1번 사람이 1번 카드를 낸 다음에 3번 카드를 내야 하므로, 1225, 1211 순서대로 리스트에 추가하는 방법은 존재하지 않습니다.

예제 입력 3 
2 3
1517 1623 1211
2 1 3
1 2
ADD 1211
ADD 1517
ADD 1225
예제 출력 3 
-1
ADD 1623 이라고 써져 있는 카드는 없습니다.

결과 리스트를 만드는 방법은 없으므로 -1이 답이 됩니다.

예제 입력 4 
2 3
1211 1517 1225
2 1 3
1 2
ADD 1211
ADD 1517
ADD 1225
예제 출력 4 
1 2 1
1번 사람이 1번 카드를 낸 다음에, 2번 사람이 2번 카드를 냅니다. 다음에 1번 사람이 3번 카드를 내면 됩니다.

예제 입력 5 
2 7
1 1 1 2 2 2 3
5 1 2 3 4 5
2 6 7
ADD 1
ADD 1
ADD 1
ADD 1
ADD 1
ADD 1
ADD 1
예제 출력 5 
-1
노트
결과 리스트가 [2, 1, 2, 2, 3, 4, 2, 3]으로 주어졌다고 생각해 보겠습니다.

2 7
1 2 1 1 2 2 3
5 1 2 3 4 5
2 6 7
ADD 1
ADD 2
ADD 1
ADD 1
ADD 2
ADD 2
ADD 2

4 7
1 2 2 2 1 1 3
2 1 2
2 3 4
2 5 6
1 7
ADD 1
ADD 2
ADD 1
ADD 1
ADD 2
ADD 2
ADD 3

2 3
1517 1211 1225
2 1 3
1 2
ADD 1211
ADD 1517
ADD 1225

1 1
5
1 1
ADD 3

3 8
1 2 1 1 2 2 1 5
3 1 2 3
3 4 5 6
2 7 8
ADD 2
ADD 1
ADD 5
ADD 2
ADD 1
ADD 1
ADD 1
ADD 2


[그림 1] 유효한 결과 리스트

결과 리스트에서 두 번 이상 등장한 수는 2, 3입니다. 2가 등장한 횟수는 4번, 3이 등장한 횟수는 2번입니다.

결과 리스트에서 2번 이상 나오는 수가 나오는 횟수를 합하면 6입니다.

 

아래는 결과 리스트가 [2, 1, 2, 2, 3, 3, 2, 3]인 경우입니다.


[그림 2] 유효하지 않은 결과 리스트

결과 리스트에서 2번 이상 나오는 수는 2, 3입니다. 2가 나온 횟수는 4번, 3이 나온 횟수는 3번으로, 이 둘을 합하면 7입니다.

7은 6보다 크므로, 결과 리스트에 2번 이상 나오는 수가 나오는 횟수를 합하면 최대 6번이라는 제한을 만족하지 않습니다.

이러한 입력은 주어지지 않습니다.

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 가희와읽기쓰기놀이2_22239 {
	private static final char BLANK = ' ';
	
	private static int N;
	private static int C;
	private static int[] result;
	private static List<Deque<Integer>> constraints = new ArrayList<Deque<Integer>>();
	private static List<Integer> lists = new ArrayList<>();
	private static StringBuilder resultCount = new StringBuilder(); 
	private static int[] combi;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		result = new int[C];
		combi = new int[C];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			result[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			constraints.add(new LinkedList<Integer>());
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				int cardIndex = Integer.parseInt(st.nextToken());
				constraints.get(i).add(cardIndex);
			}
		}
		
		lists.add(0);
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			int card = Integer.parseInt(st.nextToken());
			lists.add(card);
		}
		
		findCard(0);
		
		System.out.println(-1);
		
		br.close();
	}
	
	
	private static void findCard(int depth) {
		if (depth == C) {
			getOrder();
			System.out.println(resultCount.toString().trim());
			System.exit(0);
		}
	
		int resultCard = result[depth];
		
		for (int i = 0; i < constraints.size(); i++) {
			if (!constraints.get(i).isEmpty() && lists.get(constraints.get(i).peek()) == resultCard) {
				int index = constraints.get(i).poll();
				combi[depth] = i + 1;
				findCard(depth + 1);
				constraints.get(i).addFirst(index);
			}
		}
	}
	
	private static void getOrder() {
		for (int i = 0; i < C; i++) {
			resultCount.append(combi[i]).append(BLANK);
		}
	}
	
	private static void print() {
		for (int i = 0; i < C; i++) {
			System.out.print(combi[i] + " ");
		}
		System.out.println();
	}
}
