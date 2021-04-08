package boj_20210413_완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*

카드 섞기 출처분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	1024 MB	48	31	27	72.973%
문제
마술사 영재는 카드 더미를 이용한 마술을 개발하였다.

카드들에는 1부터 N까지의 숫자가 적혀있으며 초기 상태에는 1이 맨 위에 있으며 N개의 카드가 번호 순서대로 쌓여있다.

영재는 마술을 위해 (2, K) - 섞기를 만들었다.

(2, K) - 섞기는 총 K + 1개의 단계로 이루어져있다.

첫 번째 단계는 카드 더미 중 밑에서 2K개의 카드를 더미의 맨 위로 올린다.

이후 i(2 ≤ i ≤ K + 1)번째 단계는 직전에 맨 위로 올린 카드 중 밑에서 2K - i + 1개의 카드를 더미의 맨 위로 올린다.

예를 들어, 카드의 개수가 5개 일 때 초기 상태에서 (2, 2) - 섞기를 하는 과정은 다음과 같다.(괄호 내에서 왼쪽에 있을수록 위에 있는 카드이다.)

(1, 2, 3, 4, 5) → (2, 3, 4, 5, 1) → (4, 5, 2, 3, 1) → (5, 4, 2, 3, 1)
영재의 마술은 상대방이 초기 상태에서 (2, K) - 섞기를 2번 한 결과를 보고 2번의 (2, K) - 섞기에서 K가 각각 무엇인지 맞추는 마술이다.

마술 아이디어는 생각했지만, K를 알아내는 방법을 모르는 영재를 위해 K를 알아내는 프로그램을 만들자.

2번의 (2, K) - 섞기 후의 카드 더미 결과를 만드는 각각의 K는 유일함이 보장된다.

입력
첫 줄에 N이 주어진다.

둘째 줄에 2번의 (2, K) - 섞기 후의 카드 더미가 위에 있는 카드부터 공백으로 구분하여 주어진다.

출력
첫 번째 K와 두 번째 K를 출력한다.

제한
3 ≤ N ≤ 1,000
1≤ K, 2K < N
예제 입력 1 
5
1 3 5 4 2
예제 출력 1 
2 1

*/

public class 카드섞기_21315 {
	private static int N;
	private static int arr[];
	private static LinkedList<Integer> list = new LinkedList<Integer>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i-1] = Integer.parseInt(st.nextToken());
			list.add(i);
		}
		int k = 0;
		for(int i = 1; i <= 9; i++) {
			if(Math.pow(2, i) < N) {
				k = i;
			}
		}
		LOOP :
		for(int i = 1; i <= k; i++) {
			LinkedList<Integer> copy = new LinkedList<Integer>();
			LinkedList<Integer> result = new LinkedList<Integer>();
			copy.addAll(list);
			result.addAll(shuffle(copy, i));
			for(int j = 1; j <= k; j++) {
				LinkedList<Integer> answer = new LinkedList<Integer>();
				answer.addAll(shuffle(result, j));
				boolean isChecked = true;
				for(int a = 0; a < N; a++) {
					if(arr[a] != answer.get(a)) {
						isChecked = false;
					}
				}
				if(isChecked) {
					System.out.println(i + " " + j);
					break LOOP;
				}
			}
		}
		br.close();
	}
	
	private static LinkedList<Integer> shuffle(LinkedList<Integer> list, int k) {
		int pow = (int)Math.pow(2, k);
		for(int i = 1; i <= pow; i++) {
			list.addFirst(list.removeLast());
		}
		
		for(int i = k-1; i >= 0; i--) {
			for(int j = 1; j <= pow/2; j++) {
				list.addFirst(list.remove(pow-1));
			}
			pow = (int)Math.pow(2, i);
		}
		
		
		return list;
	}
}
