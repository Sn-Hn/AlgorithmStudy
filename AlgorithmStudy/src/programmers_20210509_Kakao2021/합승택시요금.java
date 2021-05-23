package programmers_20210509_Kakao2021;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 합승택시요금 {
	private static int INF = 10000000;

	public static int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = 0;
		int[][] taxiGraph = initFloydWarshall(fares, n);
		floydWarshall(taxiGraph, n);

		answer = minCost(s, a, b, taxiGraph);

		return answer;
	}

	private static int minCost(int s, int a, int b, int[][] taxiGraph) {
		int min = taxiGraph[s][a] + taxiGraph[s][b];
		// 어느 지점까지 같이감 (k)
		// -> taxiGraph[s][k] + k에서 각자 감
		// -> taxiGraph[s][k] + taxiGraph[k][a] + taxiGrpa[k][b];

		for (int i = 1; i < taxiGraph.length; i++) {
			min = Math.min(min, taxiGraph[s][i] + taxiGraph[i][a] + taxiGraph[i][b]);
		}

		return min;
	}

	private static void floydWarshall(int[][] taxiGraph, int n) {
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (taxiGraph[i][j] > taxiGraph[i][k] + taxiGraph[k][j]) {
						taxiGraph[i][j] = taxiGraph[i][k] + taxiGraph[k][j];
					}
				}
			}
		}
	}

	private static int[][] initFloydWarshall(int[][] fares, int n) {
		int[][] taxiGraph = new int[n + 1][n + 1];

		for (int i = 0; i <= n; i++) {
			Arrays.fill(taxiGraph[i], INF);
			taxiGraph[i][i] = 0;
		}

		for (int i = 0; i < fares.length; i++) {
			int x = fares[i][0];
			int y = fares[i][1];
			int cost = fares[i][2];

			taxiGraph[x][y] = cost;
			taxiGraph[y][x] = cost;
		}
		return taxiGraph;
	}

	private static void printGraph(int[][] taxiGraph) {
		int len = taxiGraph.length;
		for (int i = 1; i < len; i++) {
			for (int j = 1; j < len; j++) {
				if (taxiGraph[i][j] == INF) {
					System.out.print("INF" + " ");
				} else {
					System.out.print(taxiGraph[i][j] + " ");
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[] n = { 6, 7, 6 };
		int[] s = { 4, 3, 4 };
		int[] a = { 6, 4, 5 };
		int[] b = { 2, 1, 6 };
		int[][][] fares = {
				{ { 4, 1, 10 }, { 3, 5, 24 }, { 5, 6, 2 }, { 3, 1, 41 }, { 5, 1, 24 }, { 4, 6, 50 }, { 2, 4, 66 },
						{ 2, 3, 22 }, { 1, 6, 25 } },
				{ { 5, 7, 9 }, { 4, 6, 4 }, { 3, 6, 1 }, { 3, 2, 3 }, { 2, 1, 6 } }, { { 2, 6, 6 }, { 6, 3, 7 },
						{ 4, 6, 7 }, { 6, 5, 11 }, { 2, 5, 12 }, { 5, 3, 20 }, { 2, 4, 8 }, { 4, 3, 9 } } };
		for (int i = 0; i < 3; i++) {
			System.out.println(solution(n[i], s[i], a[i], b[i], fares[i]));
		}
	}
}