package study_210808;

/*

문자열 게임 2 출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	1024 MB	499	235	183	47.781%
문제
작년에 이어 새로운 문자열 게임이 있다. 게임의 진행 방식은 아래와 같다.

알파벳 소문자로 이루어진 문자열 W가 주어진다.
양의 정수 K가 주어진다.
어떤 문자를 정확히 K개를 포함하는 가장 짧은 연속 문자열의 길이를 구한다.
어떤 문자를 정확히 K개를 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이를 구한다.
위와 같은 방식으로 게임을 T회 진행한다.

입력
문자열 게임의 수 T가 주어진다. (1 ≤ T ≤ 100)

다음 줄부터 2개의 줄 동안 문자열 W와 정수 K가 주어진다. (1 ≤ K ≤ |W| ≤ 10,000) 

출력
T개의 줄 동안 문자열 게임의 3번과 4번에서 구한 연속 문자열의 길이를 공백을 사이에 두고 출력한다.

만약 만족하는 연속 문자열이 없을 시 -1을 출력한다.

예제 입력 1 
2
superaquatornado
2
abcdefghijklmnopqrstuvwxyz
5
예제 출력 1 
4 8
-1
첫 번째 문자열에서 3번에서 구한 문자열은 aqua, 4번에서 구한 문자열은 raquator이다.

두 번째 문자열에서는 어떤 문자가 5개 포함된 문자열을 찾을 수 없으므로 -1을 출력한다.

예제 입력 2 
1
abaaaba
3
예제 출력 2 
3 4

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 문자열게임2_20437 {
	private static final int[] alphabet = new int[26];
	
	private static int T;
	private static int min = Integer.MAX_VALUE;
	private static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < T; i++) {
			Arrays.fill(alphabet, 0);
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			String input = br.readLine();
			int n = Integer.parseInt(br.readLine());
			
			runGame(input, n);
			
			if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
				result.append(-1).append("\n");
				continue;
			}
			result.append(min).append(" ").append(max).append("\n");
		}
		
		System.out.println(result.toString().trim());
		
		br.close();
	}
	
	private static void runGame2(String input, int n) {
		int maxLen = input.length();
		for (int i = 0; i < maxLen; i++) {
			char now = input.charAt(i);
			int index = ctoi(now);
			alphabet[index] ++;
			
			if (alphabet[index] >= n) {
				int[] copyAlphabet = alphabet.clone();
				
				for (int j = 0; j <= i; j++) {
					char first = input.charAt(j);
					if (first == now && copyAlphabet[ctoi(first)] == n) {
						min = Math.min(min, i - j + 1);
						max = Math.max(max, i - j + 1);
						break;
					}
					copyAlphabet[ctoi(first)] --;
				}
			}
		}
	}
	
	private static int ctoi(char ch) {
		return ch - 'a';
	}
}
