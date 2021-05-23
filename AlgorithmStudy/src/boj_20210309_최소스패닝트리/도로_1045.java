package boj_20210309_최소스패닝트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

도로 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	555	126	92	22.829%
문제
0부터 N-1까지의 번호가 매겨져 있는 N개의 도시와 두 도시를 연결하는 도로가 있다. 
도로에는 우선순위가 있는데, 
A와 B가 (A < B) 도로 x로 연결되어 있고, C와 D가 (C < D) 도로 y로 연결되어 있을 때, 튜플 (A, B) < (C, D)이면 x > y, 즉 x의 우선순위가 더 높다. 
여기서 ai ≠ bi인 가장 작은 양의 정수 i에 대해 ai < bi이면 (a1, ..., ak) < (b1, ..., bk)로 정의한다.

도로의 집합은 하나 이상의 도로가 우선순위에 대한 내림차순으로 정렬되어 있는 것이다. 
집합 사이에도 우선순위가 있는데, 두 집합을 튜플로 나타냈을 때의 우선순위를 따른다.
 한 집합에 있는 도로만으로 임의의 도시에서 임의의 도시로 이동할 수 있을 때, 그 집합은 연결되어 있다고 한다.

김지민이 할 일은 M개의 도로를 가진 도로의 집합 중 연결되어 있으면서 우선 순위가 가장 높은 것을 찾는 것이다.

입력
첫째 줄에 도시의 개수 N과 M이 주어진다. 
N은 50보다 작거나 같은 자연수이고, M은 N-1보다 크거나 같고, 1,000보다 작거나 같은 정수이다. 
둘째 줄부터 N개의 줄에는 인접행렬이 주어진다. 
즉 i번째 행의 j번째 열이 Y이면 도시 i와 j를 연결하는 도로가 존재하고, N이면 존재하지 않는다. 
i와 j가 연결되어 있으면 j와 i도 연결되어 있음이 보장되고, i와 i는 연결되어 있지 않다.

출력
만약 정답이 없을 때는 -1을 출력한다. 
정답이 존재하면, 그 집합에 속하는 도로 중 0을 끝점으로 갖는 도로의 개수, 1을 끝점으로 갖는 도로의 개수, ..., N-1을 끝점으로 갖는 도로의 개수를 차례로 출력한다.

예제 입력 1 
3 2
NYY
YNY
YYN
예제 출력 1 
2 1 1

*/

public class 도로_1045 {
	private static int N, M;
	private static char road[][];

	private static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			road[i] = br.readLine().toCharArray();
		}

		br.close();
	}
}
