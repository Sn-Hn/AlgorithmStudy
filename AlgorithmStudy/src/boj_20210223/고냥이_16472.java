package boj_20210223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

고냥이 출처분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	512 MB	458	199	159	46.765%
문제
고양이는 너무 귀엽다. 사람들은 고양이를 너무 귀여워했고, 결국 고양이와 더욱 가까워지고 싶어 고양이와의 소통을 위한 고양이 말 번역기를 발명하기로 했다. 
이 번역기는 사람의 언어를 고양이의 언어로, 고양이의 언어를 사람의 언어로 바꾸어 주는 희대의 발명품이 될 것이다.

현재 고양이말 번역기의 베타버전이 나왔다. 그러나 이 베타버전은 완전 엉망진창이다. 
베타버전의 번역기는 문자열을 주면 그 중에서 최대 N개의 종류의 알파벳을 가진 연속된 문자열밖에 인식하지 못한다. 
굉장히 별로지만 그나마 이게 최선이라고 사람들은 생각했다. 
그리고 문자열이 주어졌을 때 이 번역기가 인식할 수 있는 최대 문자열의 길이는 얼마인지가 궁금해졌다.

고양이와 소통할 수 있도록 우리도 함께 노력해보자.

입력
첫째 줄에는 인식할 수 있는 알파벳의 종류의 최대 개수 N이 입력된다. (1 < N ≤ 26)

둘째 줄에는 문자열이 주어진다. (1 ≤ 문자열의 길이 ≤ 100,000) 단, 문자열에는 알파벳 소문자만이 포함된다.

출력
첫째 줄에 번역기가 인식할 수 있는 문자열의 최대길이를 출력한다. 

예제 입력 1 
2
abbcaccba
예제 출력 1 
4

*/

public class 고냥이_16472 {
	private static int N;
	private static String str;
	private static int arr[] = new int[26];
	private static char c[] = new char[100001];
	private static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		str = br.readLine();
		
		for(int i = 0; i < str.length(); i++) {
			c[i] = str.charAt(i);
		}
		
		solution();
		
		// 87,88 참조 (if문이 없다면 81%에서 틀림)
		// max가 Integer.MIN_VALUE로 값이 변하지 않았다면 N이 문자열의 크기보다 큰 것이므로 문자열의 크기가 답이 된다.
		if(max == Integer.MIN_VALUE) max = str.length();
		
		System.out.println(max);
		
		br.close();
	}
	
	private static void solution() {
		int start = 0;
		int end = 0;
		int count = 0;
		int result = 0;
		char ch = 'A';
		
		while(end < str.length()) {
			result++;
			if(ch != c[end]) {
				if(arr[c[end]-97] == 0)
					count ++;
			}
			
			while(count > N) {
				arr[c[start]-97]--;
				if(arr[c[start]-97] == 0) {
					count --;
				}
				start++;
				result--;
			}
			// N의 크기가 str보다 클 때
			// count가 N이랑 같아질 수 없으므로 max는 Integer.MIN_VALUE가 저장된 채로 메소드가 종료된다.
			if(count == N) {
				max = Math.max(max, result);
			}
			
			arr[c[end]-97]++;
			ch = c[end];
			end++;
		}
	}
}
