package boj_20210504_구현;

/*

게리맨더링 2 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	512 MB	6815	3900	2350	54.894%
문제
재현시의 시장 구재현은 지난 몇 년간 게리맨더링을 통해서 자신의 당에게 유리하게 선거구를 획정했다. 
견제할 권력이 없어진 구재현은 권력을 매우 부당하게 행사했고, 심지어는 시의 이름도 재현시로 변경했다. 이번 선거에서는 최대한 공평하게 선거구를 획정하려고 한다.

재현시는 크기가 N×N인 격자로 나타낼 수 있다. 
격자의 각 칸은 구역을 의미하고, r행 c열에 있는 구역은 (r, c)로 나타낼 수 있다. 
구역을 다섯 개의 선거구로 나눠야 하고, 각 구역은 다섯 선거구 중 하나에 포함되어야 한다. 
선거구는 구역을 적어도 하나 포함해야 하고, 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 한다. 
구역 A에서 인접한 구역을 통해서 구역 B로 갈 수 있을 때, 두 구역은 연결되어 있다고 한다. 
중간에 통하는 인접한 구역은 0개 이상이어야 하고, 모두 같은 선거구에 포함된 구역이어야 한다.

선거구를 나누는 방법은 다음과 같다.

기준점 (x, y)와 경계의 길이 d1, d2를 정한다. (d1, d2 ≥ 1, 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N)
다음 칸은 경계선이다.
(x, y), (x+1, y-1), ..., (x+d1, y-d1)
(x, y), (x+1, y+1), ..., (x+d2, y+d2)
(x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
(x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
경계선과 경계선의 안에 포함되어있는 곳은 5번 선거구이다.
5번 선거구에 포함되지 않은 구역 (r, c)의 선거구 번호는 다음 기준을 따른다.
1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
아래는 크기가 7×7인 재현시를 다섯 개의 선거구로 나눈 방법의 예시이다.

		
x = 2, y = 4, d1 = 2, d2 = 2	x = 2, y = 5, d1 = 3, d2 = 2	x = 4, y = 3, d1 = 1, d2 = 1
구역 (r, c)의 인구는 A[r][c]이고, 선거구의 인구는 선거구에 포함된 구역의 인구를 모두 합한 값이다. 
선거구를 나누는 방법 중에서, 인구가 가장 많은 선거구와 가장 적은 선거구의 인구 차이의 최솟값을 구해보자.

입력
첫째 줄에 재현시의 크기 N이 주어진다.

둘째 줄부터 N개의 줄에 N개의 정수가 주어진다. r행 c열의 정수는 A[r][c]를 의미한다.

출력
첫째 줄에 인구가 가장 많은 선거구와 가장 적은 선거구의 인구 차이의 최솟값을 출력한다.

제한
5 ≤ N ≤ 20
1 ≤ A[r][c] ≤ 100
예제 입력 1 
6
1 2 3 4 1 6
7 8 9 1 4 2
2 3 4 1 1 3
6 6 6 6 9 4
9 1 9 1 9 5
1 1 1 1 9 9
예제 출력 1 
18


예제 입력 2 
6
5 5 5 5 5 5
5 5 5 5 5 5
5 5 5 5 5 5
5 5 5 5 5 5
5 5 5 5 5 5
5 5 5 5 5 5
예제 출력 2 
20
예제 입력 3 
8
1 2 3 4 5 6 7 8
2 3 4 5 6 7 8 9
3 4 5 6 7 8 9 1
4 5 6 7 8 9 1 2
5 6 7 8 9 1 2 3
6 7 8 9 1 2 3 4
7 8 9 1 2 3 4 5
8 9 1 2 3 4 5 6
예제 출력 3 
23


d1, d2
	1  <= x <= N - d1 - d2
1 + d1 <  y <= N - d2


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 게리맨더링2_17779 {
	private static int N;
	private static int[][] population;
	private static int[][] map;
	private static int[] localPopulation;
	private static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		population = new int[N + 1][N + 1];
		StringTokenizer st = null;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				population[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k < N; k++) {
					for (int l = 1; l < N; l++) {
						if (i <= N - k - l && 1 + k < j && j <= N - l) {
							simulation(i, j, k, l);
						}
					}
				}
			}
		}

		System.out.println(min);

		br.close();
	}

	private static void simulation(int x, int y, int d1, int d2) {
		int yStart = y;
		int yEnd = y;
		map = new int[N + 1][N + 1];
		localPopulation = new int[6];
		System.out.println("yStart : " + yStart + ", yEnd : " + yEnd);
		for (int i = x; i <= x + d1 + d2; i++) {
			for (int j = yStart; j <= yEnd; j++) {
				map[i][j] = 5;
				localPopulation[5] += population[i][j];
			}
			if (i < x + d1) {
				yStart -= 1;
			} else {
				yStart += 1;
			}

			if (i < x + d2) {
				yEnd += 1;
			} else {
				yEnd -= 1;
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] == 5)
					continue;

				// 1번 구역
				if ((i >= 1 && i < x + d1) && (j >= 1 && j <= y)) {
					map[i][j] = 1;
					localPopulation[1] += population[i][j];
					continue;
				}

				// 2번 구역
				if ((i >= 1 && i <= x + d2) && (j > y && j <= N)) {
					map[i][j] = 2;
					localPopulation[2] += population[i][j];
					continue;
				}

				// 3번 구역
				if ((i >= x + d1 && i <= N) && (j >= 1 && j < y - d1 + d2)) {
					map[i][j] = 3;
					localPopulation[3] += population[i][j];
					continue;
				}

				// 4번 구역
				if ((i > x + d2 && i <= N) && (j >= y - d1 + d2 && j <= N)) {
					map[i][j] = 4;
					localPopulation[4] += population[i][j];
					continue;
				}
			}
		}

		min = Math.min(min, calculatePeople());

		print();
		System.out.println();
	}

	private static int calculatePeople() {
		int diff = 0;
		Arrays.sort(localPopulation);
		diff = localPopulation[5] - localPopulation[1];
		return diff;
	}

	private static void print() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
