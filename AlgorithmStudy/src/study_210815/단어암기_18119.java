package study_210815;
/*

단어 암기 출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
4 초 (추가 시간 없음)	1024 MB	1812	843	553	44.525%
문제
준석이는 영어 단어를 외우려고 한다. 사전에는 N가지 단어가 적혀 있다. 모든 단어는 소문자이다. 단어 안에 있는 모든 알파벳을 알 때, 그 단어를 완전히 안다고 한다.

다음과 같은 쿼리들이 주어진다.

1 x : 알파벳 x를 잊는다.
2 x : 알파벳 x를 기억해 낸다.
처음에 모든 알파벳을 기억하는 상태고, 모음은 완벽하게 외웠기 때문에 절대 잊지 않는다.

각 쿼리마다 완전히 알고 있는 단어의 개수를 출력하여라.

입력
첫 번째 줄에는 정수 N (1 ≤ N ≤ 104)과 M (1 ≤ M ≤ 5×104)이 주어진다.

다음 N개의 줄에는 문자열이 하나씩 주어진다. 문자열의 길이는 103을 넘지 않는다.

다음 M개의 줄에는 정수 o와 문자 x가 한 줄씩 주어진다. o는 1, 2중 하나이고, x는 알파벳 소문자이다.

o가 1이면 x를 잊는다는 뜻이고, o가 2면 x를 기억해낸다는 뜻이다. o가 1일 때는 x를 기억하고 있었음이 보장되고, o가 2일 때는 x를 잊고 있었음이 보장된다.

출력
각 쿼리마다 정수 하나를 출력한다.

예제 입력 1 
5 10
apple
actual
banana
brick
courts
1 l
1 b
1 c
1 n
2 l
2 b
1 s
2 c
2 s
2 n
예제 출력 1 
3
1
0
0
1
1
1
3
4
5

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 단어암기_18119 {
	private static int N;
	private static int M;
	private static boolean[][] word;
	private static int[] checkWord;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		word = new boolean[N][26];
		checkWord = new int[N];
		
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < input.length(); j++) {
				word[i][ctoi(input.charAt(j))] = true;
			}
		}
		
		int count = 0;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken());
			char ch = st.nextToken().charAt(0);
			
			count = forgetStr(o, ch);
			result.append(count).append("\n");
		}
		
		System.out.println(result.toString().trim());
		
		br.close();
	}
	
	private static int forgetStr(int o, char ch) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (word[i][ctoi(ch)]) {
				if (o == 1) {
					checkWord[i] ++;					
				}else {
					checkWord[i] --;
				}
			}
			
			if (checkWord[i] == 0) {
				cnt ++;
			}
		}
		
		return cnt;
	}
	
	private static int ctoi(char ch) {
		return ch - 'a';
	}
}
