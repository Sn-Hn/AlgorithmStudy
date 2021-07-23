package study_210725;

/*

가운데에서 만나기 성공
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	512 MB	158	78	41	44.086%
문제
준형이는 내일 친구들을 만나기로 했다. 준형이와 친구들은 서로 다른 도시에 살고 있다.

도시를 연결하는 도로는 일방 통행만 있어서 도시 에서 도시 로 가는 시간과 도시 에서 도시 로 가는 시간이 다를 수 있다.

준형이와 친구들은 아래 조건을 만족하는 도시 를 선택하여 거기서 만나려고 한다.

왕복시간은 자신이 살고 있는 도시에서 도시 로 이동하는 시간과 도시 에서 다시 자신이 살고 있는 도시로 이동하는 시간을 합한 것이다.
준형이와 친구들이 도로를 이용하여 갈 수 있는 도시만 선택한다.
준형이와 친구들의 왕복시간 들 중 최대가 최소가 되는 도시 를 선택한다.
준형이와 친구들이 이동할 수 있는 도시가 최소한 하나 이상이 있음을 보장한다.
도시가 많다보니 계산하기 힘들다. 준형이와 친구들을 대신하여 도시 를 알려주자.

입력
첫 번째 줄에는 도시의 개수 과 도로의 개수 이 주어진다.

두 번째 줄부터 M + 1줄까지 도시 , 도시 , 도시 에서 도시 로 이동하는데 걸리는 시간 가 공백으로 구분되어 주어진다.

줄에는 준형이와 친구들의 총 인원 가 주어진다.

줄에는 준형이와 친구들이 살고 있는 도시의 번호 가 공백으로 구분되어 주어진다.

출력
위 조건을 만족하는 도시 의 번호를 출력한다. 만약 가능한 도시 가 여러 개인 경우는 도시의 번호를 오름차순으로 출력한다.

제한
예제 입력 1 
4 9
1 2 9
2 3 9
3 1 9
1 4 1
4 1 1
2 4 1
4 2 1
3 4 1
4 3 1
3
1 2 3
예제 출력 1 
4
예제 입력 2 
3 3
1 2 1
2 3 1
3 1 1
2
1 2
예제 출력 2 
1 2 3

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 가운데서만나기_21940 {
	private static int INF = 100000000;
	
	private static int N;
	private static int M;
	private static int[][] floydRoad;
	private static int K;
	private static int[] city;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		floydRoad = new int[N + 1][N + 1];
		
		initRoad();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			floydRoad[start][end] = time;
		}
		
		K = Integer.parseInt(br.readLine());
		city = new int[K + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= K; i++) {
			city[i] = Integer.parseInt(st.nextToken());
		}
		
		getRoundTripTime_FloydWarshall();
		
		List<Integer> possibleCity = getMinRoundTripTime();
		
		print(possibleCity);
		
		br.close();
	}
	
	private static List<Integer> getMinRoundTripTime() {
		int min = INF;
		List<Integer> possibleCity = new ArrayList<Integer>();
		for (int i = 1; i <= N; i++) {
			int max = -1;
			for (int j = 1; j <= K; j++) {				
				if (max < floydRoad[i][city[j]] + floydRoad[city[j]][i]) {
					max = floydRoad[i][city[j]] + floydRoad[city[j]][i];
				}
				
			}
			if (min > max) {
				min = max;
				possibleCity.clear();
				possibleCity.add(i);
			}else if (min == max) {
				possibleCity.add(i);
			}
		}
		
		return possibleCity;
	}
	
	private static void getRoundTripTime_FloydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (floydRoad[i][j] > floydRoad[i][k] + floydRoad[k][j]) {
						floydRoad[i][j] = floydRoad[i][k] + floydRoad[k][j];
					}
				}
			}
		}
	}
	
	private static void initRoad() {
		for (int i = 0; i <= N; i++) {
			Arrays.fill(floydRoad[i], INF);
			floydRoad[i][i] = 0;
		}
	}
	
	private static void print(List<Integer> possibleCity) {
		Collections.sort(possibleCity);
		StringBuilder result = new StringBuilder();
		
		for (int city : possibleCity) {
			result.append(city).append(" ");
		}
		
		System.out.println(result.toString());
	}
}
