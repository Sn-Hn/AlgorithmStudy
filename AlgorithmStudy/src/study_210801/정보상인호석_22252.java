package study_210801;

/*

정보 상인 호석 성공출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	512 MB	338	157	103	43.277%
문제
암흑가의 권력은 주먹과 정보에서 나온다. 주먹은 한 명에게 강하고, 정보는 세계를 가지고 놀 수 있기 때문에 호석이는 세상 모든 정보를 모으는 "정보 상인"이 되고 싶다. 정보 상인은 정보를 사고파는 사람을 의미한다.

호석이는 아직 상인계의 새싹이기 때문에, 초기 투자를 통해서 여러 명의 "정보 고릴라"들로부터 정보를 모으려고 한다. 정보 고릴라란 여기저기서 정보를 수집하는 사람들을 의미한다. 일단 정보를 긁어모으기 위해서 호석이는 여러 정보 고릴라들에게 정보를 구매하려고 한다.



암흑가의 연락망은 빼곡하기 때문에 누가 어떤 정보를 얻었는지에 대한 찌라시들이 수시로 퍼진다. 찌라시로 알 수 있는 것은, 어떤 이름을 가진 고릴라가 , , ...,  만큼의 가치가 있는 정보 k 개를 얻었다는 점이다.

호석이는 이를 바탕으로 임의의 시점에 특정 고릴라에게 정보를 몇 개 살 것인지를 정할 수 있다. 이때 가치 순으로 가장 비싼 정보들을 구매한다. 예를 들어 고릴라가 가진 정보가 10개이고, 호석이가 사고 싶은 정보 개수가 4개라면, 고릴라는 10개 중에서 가치 순으로 가장 비싼 4개를 팔 것이다. 한 번 거래한 정보는 호석이에게 더 이상 가치가 없기 때문에 고릴라도 그 정보를 파기한다.

당신은 암흑가의 주먹이며 양대 산맥이 될 가능성이 있는 호석이를 주시하고 있다. 관찰하면서 얻은 정보는 총  개이다. 각 정보는 다음의 2가지 중 하나이다.

1 Name   : 이름이 [Name]인 고릴라가  개의 정보를 얻었으며, 각 가치는  부터  이다.
2 Name  : 호석이가 이름이 [Name]인 고릴라에게  개의 정보를 구매한다. 이때 고릴라가 가진 정보들 중 가장 비싼  개를 구매하며, 고릴라가 가진 정보가 개 이하이면 가진 모든 정보를 구매한다.
 견제를 위해서 호석이가 가진 정보들의 가치 총합, 즉 호석이가 정보들을 구매하는 데에 쓴 돈의 총합을 구하자.

입력
고릴라들이 정보를 얻는 사건과 호석이가 거래하는 정보가 시간순으로 주어진다. 첫 번째 줄에는 쿼리의 개수  가 주어진다.

이어서  개의 줄에 걸쳐서 각 줄에 쿼리가 주어진다. 쿼리는 1이나 2로 시작한다. 1로 시작하는 경우에는 정보를 얻은 정보 고릴라의 이름과  가 주어지며 이어서  개의 정보 가치 가 자연수로 주어진다. 모든 는 1 이상 100,000 이하이다. 2로 시작하는 경우에는 호석이가 거래하려는 정보 고릴라의 이름과 구매하려는 정보의 개수 가 주어진다. 

출력
모든 쿼리가 종료되었을 때에 호석이가 얻게 되는 정보 가치의 총합을 출력하라.

제한
1 ≤  ≤ 100,000,  는 자연수
모든 Name은 알파벳 소문자 혹은 대문자로 이루어져 있고 공백이 없으며 길이는 1 이상 15 이하이다.
1 ≤  ≤ 100,000,  는 자연수
1 ≤  ≤ 100,000,  는 자연수
1 ≤  ≤ 100,000,  는 자연수
모든 쿼리에 대한  의 합은 1,000,000 을 넘지 않는다. 
예제 입력 1 
7
1 Cpp 5 10 4 2 8 4
1 Java 2 8 2
2 Cpp 2
1 Cpp 2 10 3
2 Cpp 3
2 Java 1
2 Python 10
예제 출력 1 
44

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 정보상인호석_22252 {
	private static int Q;
	private static long sum = 0;
	private static Map<String, List<Integer>> info = new HashMap<String, List<Integer>>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Q = Integer.parseInt(br.readLine().trim());
		StringTokenizer st = null;
		
		for (int i = 0; i < Q; i++) {
			List<Integer> values = new ArrayList<Integer>();
			st = new StringTokenizer(br.readLine().trim());
			int n = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			int k = Integer.parseInt(st.nextToken());
			if (n == 1) {
				if (info.containsKey(name)) {
					values = info.get(name);
				}
				for (int j = 0; j < k; j++) {
					values.add(Integer.parseInt(st.nextToken()));
				}
				System.out.println("1 : " + values);
				info.put(name, values);
			}else {
				int j = 0;
				values = info.get(name);
				if (values == null) {
					continue;
				}
				Collections.sort(values, (o1, o2) -> o2 - o1);
				System.out.println("2 : " + values);
				while (!values.isEmpty()) {
					if (j == k) {
						break;
					}
					sum += values.get(0);
					values.remove(0);
					j++;
				}
			}
		}
		
		System.out.println(sum);
		
		
		br.close();
	}
}
