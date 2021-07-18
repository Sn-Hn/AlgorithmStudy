package study_210718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*

피자판매 출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	2948	1126	734	35.998%
문제
고객이 두 종류의 피자 A와 B를 취급하는 피자가게에서 피자를 주문하고자 한다. 
<그림 1>과 같이 각 종류의 피자는 다양한 크기의 여러 개의 피자조각으로 나누어져 있다.
각 조각에 쓰여진 숫자는 피자조각의 크기를 나타낸다.



<그림 1>

고객이 원하는 피자의 크기를 이야기하면, 피자가게에서는 한 종류의 피자를 2 조각 이상 판매할 때는 반드시 연속된 조각들을 잘라서 판매한다. 
이때 판매한 피자조각의 크기 합이 주문한 크기가 되어야 한다. 
판매한 피자조각은 모두 A종류이거나, 모두 B종류이거나, 또는 A와 B 종류가 혼합될 수 있다. 
예를 들어서, <그림 1> 과 같이 잘라진 피자가 있을 때, 손님이 전체 크기가 7 인 피자를 주문하면, 피자 가게에서는 <그림2>와 같이 5 가지 방법으로 피자를 판매할 수 있다.



<그림 2>

피자가게에서 손님이 원하는 크기의 피자를 판매하는 모든 방법의 가지 수를 계산하는 프로그램을 작성하시오

입력
첫 번째 줄에는 손님이 구매하고자 하는 피자크기를 나타내는 2,000,000 이하의 자연수가 주어진다. 
두 번째 줄에는 A, B 피자의 피자조각의 개수를 나타내 는 정수 m, n 이 차례로 주어진다 (3 ≤ m, n ≤ 1000). 
세 번째 줄부터 차례로 m 개의 줄에는 피자 A의 미리 잘라진 피자조각의 크기를 나타내는 정수가 주어진다. 
그 다음 n 개의 줄에는 차례로 피자B의 미리 잘라진 피자조각의 크기를 나타내는 정수가 주어진다. 
각 종류의 피자조각의 크기는 시계방향으로 차례로 주어지며, 각 피자 조각의 크기는 1000 이하의 자연수이다.

출력
첫째 줄에는 피자를 판매하는 방법의 가지 수를 나타내는 정수를 출력한다. 
피자를 판매하는 방법이 없는 경우에는 숫자 0을 출력한다.

예제 입력 1 
7
5 3
2
2
1
7
2
6
8
3
예제 출력 1 
5

*/

public class 피자판매_2632 {
	private static int pizzaSize;
	private static int N;
	private static int M;
	private static int[] pizzaA;
	private static int[] pizzaB;
	private static int cnt = 0;
	private static List<Integer> Aways = new ArrayList<Integer>();
	private static List<Integer> Bways = new ArrayList<Integer>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		pizzaSize = Integer.parseInt(br.readLine().trim());
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		pizzaA = new int[N];
		pizzaB = new int[M];
		
		for (int i = 0; i < N; i++) {
			pizzaA[i] = Integer.parseInt(br.readLine().trim());
		}
		
		for (int i = 0; i < M; i++) {
			pizzaB[i] = Integer.parseInt(br.readLine().trim());
		}
		
		getWays(pizzaA, N, Aways);
		getWays(pizzaB, M, Bways);
		
		Collections.sort(Aways);
		Collections.sort(Bways);
		
		getAllWays();
		
		System.out.println(cnt);
		
		print();
		
		
		br.close();
	}
	
	private static void getWays(int[] pizza, int std, List<Integer> ways) {
		int sum = 0;
		int index = 0;
		for (int i = 0; i < std; i++) {
			sum = pizza[i];
			for (int j = 0; j < std; j++) {
				index = (i + j) % std;
				if (j != 0) {
					sum += pizza[index];
				}
				
				if (i != 0 && j == std - 1) {
					break;
				}
				
				if (sum < pizzaSize) {
					ways.add(sum);
				}else if (sum > pizzaSize) {
					break;
				}else {
					cnt ++;
					break;
				}
			}
		}
	}
	
	private static void getAllWays() {
		int start = 0;
		int end = Bways.size() - 1;
		int sum = 0;
		
		while (start < Aways.size() && end >= 0) {
			sum = Aways.get(start) + Bways.get(end);
			
			if (sum < pizzaSize) {
				start ++;
			}else if (sum > pizzaSize) {
				end --;
			}else {
				int startCnt = 0;
				int endCnt = 0;
				
				for (int i = start; i < Aways.size(); i++) {
					if (Aways.get(start) != Aways.get(i)) {
						break;
					}
					startCnt++;
				}
				
				for (int i = end; i >= 0; i--) {
					if (Bways.get(end) != Bways.get(i)) {
						break;
					}
					endCnt++;
				}
				start += startCnt;
				end -= endCnt;
				
				cnt += (startCnt * endCnt);
			}
		}
	}
	
	private static void print() {
		for (int i : Aways) {
			System.out.print(i + " ");
		}
		
		System.out.println("\n========");
		
		for (int i : Bways) {
			System.out.print(i + " ");
		}
		
		System.out.println();
	}
}
