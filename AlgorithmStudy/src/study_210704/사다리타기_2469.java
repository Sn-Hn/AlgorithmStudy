package study_210704;

/*

사다리 타기 출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	1383	544	422	38.716%
문제
k명의 참가자들이 사다리 타기를 통하여 어떤 순서를 결정한다. 
참가자들은 알파벳 대문자 첫 k개로 표현되며, 사다리 타기를 시작할 때의 순서는 아래 그림과 같이 항상 알파벳 순서대로이다. 

k=10 인 예를 들어 보자. 10명의 A, B, C, D, E, F, G, H, I, J 참가자들이 사다리 타기를 준비한다. 
아래 그림은 10개의 세로 줄과 5개의 가로 줄을 가지고 있는 사다리의 한 예를 보여주고 있다.  



이 사다리에서 점선은 가로 막대가 없음을, 굵은 가로 실선은 옆으로 건너갈 수 있는 가로 막대가 있음을 나타내고 있다.  

따라서 위에 제시된 사다리를 타면 그 최종 도달된 순서는 왼쪽으로부터 A, C, G, B, E, D, J, F, I, H 가 된다. 

사다리 타기는 세로 막대를 타고 내려오는 중에 가로막대를 만나면 그 쪽으로 옮겨 가면서 끝까지 내려가는 과정이다.  
따라서 사다리 타기의 규칙 특성상 아래 그림과 같이 두 가로 막대가 직접 연결될 수는 없으므로 이 상황은 이 문제에서 고려할 필요가 없다.



우리는 하나의 가로 줄이 감추어진 사다리를 받아서 그 줄의 각 칸에 가로 막대를 적절히 넣어서 참가자들의 최종 순서가 원하는 순서대로 나오도록 만들려고 한다.  

입력에서 사다리의 전체 모양은 각 줄에 있는 가로 막대의 유무로 표현된다. 
각 줄에서 가로 막대가 없는 경우에는 ‘*’(별)문자, 있을 경우에는 ‘-’(빼기) 문자로 표시된다. 
그리고 감추어진 특정 가로 줄은 길이 k-1인 ‘?’ (물음표) 문자열로 표시되어 있다.   

입력
첫 줄에는 참가한 사람의 수 k가 나온다(3 ≤ k ≤ 26). 
그 다음 줄에는 가로 막대가 놓일 전체 가로 줄의 수를 나타내는 n이 나온다(3 ≤ n ≤ 1,000). 
그리고 세 번째 줄에는 사다리를 타고 난 후 결정된 참가자들의 최종 순서가 길이 k인 대문자 문자열로 들어온다.  

k와 n, 그리고 도착순서 문자열이 나타난 다음, 이어지는 n개의 줄에는 앞서 설명한 바와 같이 ‘*’와 ‘-’ 문자로 이루어진 길이 k-1인 문자열이 주어진다. 
그 중 감추어진 가로 줄은 길이가 k-1인 ‘?’ 문자열로 표시되어 있다.

출력
입력 파일 세 번째 줄에서 지정한 도착순서가 해당 사다리에서 만들어질 수 있도록 감추어진 가로 줄을 구성해야 한다. 

여러분은 감추어진 가로 줄의 상태를 재구성하여 이를 ‘*’(별) 문자와 ‘-’(빼기) 문자로 구성된 길이 k-1인 문자열로 만들어 출력하면 된다.

그런데 어떤 경우에는 그 감추어진 가로 줄을 어떻게 구성해도 원하는 순서를 얻을 수 없는 경우도 있다.  
이 경우에는  ‘x’(소문자 엑스)로 구성된 길이 k-1인 문자열을 출력해야 한다.

예제 입력 1 
10
5
ACGBEDJFIH
*-***-***
-*-******
?????????
-**-***-*
**-*-*-*-
예제 출력 1 
**-*-***-
예제 입력 2 
11
5
CGBEDJFKIHA
*-***-****
-*-******-
??????????
-**-***-*-
**-*-*-*-*
예제 출력 2 
xxxxxxxxxx

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 사다리타기_2469 {
	private static int K;
	private static int N;
	private static String finalOrder;
	private static char[] fo;
	private static char[][] line;
	private static char[] alphabet = new char[26];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		fo = new char[K];
		line = new char[N][K - 1];
		
		finalOrder = br.readLine();
		fo = finalOrder.toCharArray();
		
		for (int i = 0; i < N; i++) {
			line[i] = br.readLine().toCharArray();
		}
		
		br.close();
	}
	
	private static void startLadderGame(int index, int order) {
		
	}
	
	private static int rideLadder(int index, int layer, int order, int position) {
		if (layer == N - 1) {
			if (index == order) {
				
			}
		}
		
		if (line[layer][0] != '?') {
			rideLadder(getDirection(index, layer), layer + 1, order, position);			
		}else {
			rideLadder(index, layer + 1, order, 0);
			rideLadder(index + 1, layer + 1, order, 1);
			rideLadder(index - 1, layer + 1, order, 2);
		}
		
		return index;
	}
	
	private static int getDirection(int index, int layer) {
		if (line[layer][index] == '-') {
			return index + 1;
		}
		
		if (line[layer][index - 1] == '-') {
			return index - 1;
		}
		
		return index;
	}
	
	private static void setString(StringBuilder result, int position) {
		if (position == 0) {
			result.append("*");
		}
	}
}
