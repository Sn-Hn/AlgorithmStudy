package boj_20210112;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*

여러분의 다리가 되어 드리겠습니다! 스페셜 저지출처분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	512 MB	642	314	255	51.829%
문제
선린월드에는 N개의 섬이 있다. 섬에는 1, 2, ..., N의 번호가 하나씩 붙어 있다. 
그 섬들을 N - 1개의 다리가 잇고 있으며, 어떤 두 섬 사이든 다리로 왕복할 수 있다.

어제까지는 그랬다.

"왜 다리가 N - 1개밖에 없냐, 통행하기 불편하다"며 선린월드에 불만을 갖던 욱제가 다리 하나를 무너뜨렸다! 
안 그래도 불편한 통행이 더 불편해졌다. 서로 왕복할 수 없는 섬들이 생겼기 때문이다. 
일단 급한 대로 정부는 선린월드의 건축가를 고용해, 서로 다른 두 섬을 다리로 이어서 다시 어떤 두 섬 사이든 왕복할 수 있게 하라는 지시를 내렸다.

그런데 그 건축가가 당신이다! 안 그래도 천하제일 코딩대회에 참가하느라 바쁜데...

입력
첫 줄에 정수 N이 주어진다. (2 ≤ N ≤ 300,000)

그 다음 N - 2개의 줄에는 욱제가 무너뜨리지 않은 다리들이 잇는 두 섬의 번호가 주어진다.

출력
다리로 이을 두 섬의 번호를 출력한다. 여러 가지 방법이 있을 경우 그 중 아무거나 한 방법만 출력한다.

예제 입력 1 
4
1 2
1 3
예제 출력 1 
1 4
"4 1"이나 "2 4", "4 3" 등 가능한 정답은 많이 있지만, 아무거나 하나만 출력해야 한다.

예제 입력 2 
2
예제 출력 2 
1 2
예제 입력 3 
5
1 2
2 3
4 5
예제 출력 3 
3 4

*/

/*

BFS로 풀다가 시간초과..
찾아보니 union-find 알고리즘을 이용해서 풀어야 한다

- union(x, y)
  x가 속한 집합과 y가 속한 집합을 합친다. x와 y가 속한 두 집합을 합침
  
- find(x)
  x가 속한 집합의 대표값을 반환. x가 어떤 집합에 속해 있는지 찾는 연산

*/

public class 여러분의다리가되어드리겠습니다_17352 {
	private static int N;
	private static int parent[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < N-2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		
		HashSet<Integer> hash = new HashSet<Integer>();
		
		printParent();
		
		for(int i = 1; i <= N; i++) {
			hash.add(find(i));
		}
		
		for(int i : hash) {
			System.out.print(i + " ");
		}
		
		br.close();
	}
	
	// 두 부모 노드를 합치는 함수
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		// 작은 값이 부모가 된다
		if(a<b) parent[b] = a;
		else parent[a] = b;
	}
	
	// 부모 노드를 찾는 함수
	private static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		
		return find(parent[x]);
	}
	
	private static void printParent() {
		for(int i = 1; i <= N; i++) {
			System.out.println(parent[i]);
		}
	}
	
}
