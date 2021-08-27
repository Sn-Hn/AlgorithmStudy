package study_210801;

/*

트리 디자이너 호석 출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	1024 MB	68	49	36	76.596%
문제
트리를 너무나 사랑하는 효성이는 트리 분재 전문가이다. 
효성이가 기르는 모든 트리는 정점과 간선으로 이루어져 있다. 
정점은 번부터 번 정점까지 존재하며, 간선은 서로 다른 두 정점을 연결해준다. 
정점의 개수는 간선의 개수보다 정확히 한 개가 많으며, 사이클을 이루지 않는다. 
트리의 뿌리는 정점 중 하나로, 모든 정점 중 가장 낮은 높이에 존재한다. 
항상 1번 정점이 트리의 뿌리임이 보장되고, 이파리란 연결된 간선이 1개 이하인 정점을 의미한다. 
정점이 뿌리에 가까울수록 낮은 높이에 존재하며, 멀수록 높은 높이에 존재한다.

효성이가 기르는 트리는 특별하기 때문에, 각 정점에  이상  이하의 정수가 적혀 있다. 
어느 날 문득 크리스마스 트리를 만들고 싶어진 효성이는 1개 이상의 정점을 골라서 전구를 달고 싶어졌다. 
이 때, 정점을 고르는 방법은 뿌리에서 특정 이파리까지 가는 경로 위에서 고르는 것이다. 
이 때 고른 전구들이 꼭 연속적으로 존재할 필요는 없다. 
전구가 달린 정점들은 불빛이 들어오면서 정점에 적혀 있던 숫자가 밝게 빛나게 된다.

제일 왼쪽 그림이 효성이가 가지고 있는 나무라고 하자. 
가운데와 오른쪽 그림은 올바르게 전구를 고른 예시이다. 
각 그림에서 밝혀진 전구에 적힌 숫자를 아래부터 순서대로 읽으면  과  이 된다.


만약 고른 전구가 1번, 7번, 10번이라면 왼쪽과 같은데, 이런 경우에는 뿌리부터 특정 이파리까지의 경로 위에서 고른 것이 아니기 때문에 올바르지 못한 방법이다.
같은 이유로 4번, 5번, 8번, 9번 전구들을 골라도 안 된다.

트리 디자이너 호석은 효성이의 요구 사항을 들은 뒤에 한 가지 문제를 생각해냈다. 
전구를 달았을 때, 낮은 높이부터 높은 높이까지 순서대로 숫자를 보았을 때, 오름차순을 만족하도록 전구를 다는 경우의 수는 몇 가지인지 궁금해졌다. 
오름차순 수열이란 각 수가 이전보다 작아지지 않는 수열을 의미한다. 
즉, 이전과 수가 같아도 여전히 오름차순을 만족한다. 
예를 들어 은 오름차순이지만 은 오름차순이 아니다.

효성이의 나무 정보가 주어졌을 때, 호석이가 궁금한 결과를 계산해보자.

입력
첫 번째 줄에는 정점의 개수 이 주어진다.

두 번째 줄에는 번부터 번 정점에 써 있는 숫자가 공백으로 구분되어 주어진다. 
각 숫자는  이상  이하의 자연수 이다.

세 번째 줄부터 개의 줄에 걸쳐서 각 간선이 잇는 두 정점의 번호가 주어진다.

주어지는 나무는 하나의 연결된 그래프임이 보장된다.

출력
전구를 달 수 있는 경우의 수를 10억 7로 나눈 나머지를 출력하라.

제한
예제 입력 1 
4
1 1 2 2
1 2
3 2
3 4
예제 출력 1 
15
전구를 1개 이상 고르기만 하면 어떻게 골라도 항상 만족하기 때문에 총 15 가지 가능하다.

예제 입력 2 
10
3 3 2 0 2 9 6 0 8 1
6 1
1 3
3 7
2 7
4 9
4 8
1 8
10 8
8 5
예제 출력 2 
22

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 트리디자이너호석_22253 {
	private static final int BY = 1000000007;
	
	private static int N;
	private static int[] treeNumber;
	private static List<Integer>[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		treeNumber = new int[N + 1];
		tree = new ArrayList[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			treeNumber[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			tree[a].add(b);
		}
		
		
		br.close();
	}
	
	private static void getPath() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);
		
		while (!q.isEmpty()) {
			int now = q.poll();
			
			for (int next : tree[now]) {
				
			}
		}
	}
	
	private static void getPath(int depth, int n) {
		if (depth == n) {
			return;
		}
		
		for (int i = 1; i <= 9; i++) {
			
		}
	}
	
	private static void initTree() {
		for (int i = 0; i <= N; i++) {
			tree[i] = new ArrayList<Integer>();
		}
	}
}
