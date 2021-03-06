package boj_20210112;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

/*

전국시대 출처분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	768	243	188	32.470%
문제
전국시대엔 N개의 국가가 존재한다. 각 국가는 1부터 N까지의 번호를 가지고 있다.

또한, 모든 국가는 각자 자신의 국가의 힘을 상징하는 병력을 가지고 있다. 
이때 M개의 기록이 주어진다. 각각의 기록은 다음과 같다.

동맹 - 두 나라가 서로 동맹을 맺는다. 두 나라의 병력이 하나로 합쳐진다.
전쟁 - 두 나라가 서로 전쟁을 벌인다. 병력이 더 많은 나라가 승리하며 패배한 나라는 속국이 된다. 
이때 남은 병력은 승리한 나라의 병력에서 패배한 나라의 병력을 뺀 수치가 된다. 두 나라의 병력이 같을 경우 두 나라 모두 멸망한다.
모든 나라는 정직하기 때문에 내 동맹의 동맹도 나의 동맹이고, 내 동맹이 적과 전쟁을 시작하면 같이 참전한다. 
속국인 경우도 동맹의 경우와 마찬가지이다.

따라서, 전쟁에서 진 국가와 동맹인 다른 국가 또한 전쟁에서 이긴 국가의 속국이 된다.

모든 기록이 끝났을 때 남아있는 국가의 수를 출력하고, 그 국가들의 남은 병력의 수를 오름차순으로 출력하는 프로그램을 작성하시오.

단, 여러 국가가 서로 동맹이거나 속국 관계인 경우는 한 국가로 취급한다.

입력
첫 번째 줄에 국가의 수를 나타내는 N과 기록의 수 M이 주어진다. (1 ≤ N, M ≤ 100,000)

두 번째 줄 부터 N개의 줄에 걸쳐 i번째 국가의 병력 Ai (1 ≤ i ≤ N)가 자연수로 주어진다. (1 ≤ Ai ≤ 10,000)

다음 M개의 줄에는 기록이 3개의 정수 O, P, Q로 주어진다. O가 1인 경우 P, Q가 서로 동맹을 맺었음을 의미하고, O가 2인 경우 P, Q가 서로 전쟁을 벌였음을 의미한다.

동맹끼리 다시 동맹을 맺거나 전쟁하는 입력은 주어지지 않는다.

출력
첫째 줄에 남아있는 국가의 수를 출력한다.

다음 줄에 각 국가의 남은 병력의 수를 띄어쓰기를 간격으로 오름차순으로 출력한다.

예제 입력 1 
5 3
10
20
30
40
50
1 1 2
1 3 4
2 1 3
예제 출력 1 
2
40 50

*/

// union-find 
public class 전국시대_15809 {
	private static int N, M;
	private static int military[];
	private static int parent[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		military = new int[N+1];
		parent = new int[N+1];
		
		// 병력 배열과 부모 배열 초기화
		for(int i = 1; i <= N; i++) {
			military[i] = Integer.parseInt(br.readLine());
			parent[i] = i;
		}
		
		int a, b, c;
		
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			// a가 1이면 동맹
			if(a == 1) {
				union(b, c);
			// 전쟁
			}else {
				war(b, c);
			}
			
		}
		
		List<Integer> list = new ArrayList<Integer>();
		
		// 병력이 0이상이면 남아 있는 나라
		for(int i = 1; i <= N; i++) {
			if(military[i] > 0) {
				list.add(military[i]);
			}
		}
		
		Collections.sort(list);
		
		for(int i = 1; i <= N; i++) {
			System.out.println(parent[i]);
		}
		
		
		bw.write(list.size() + "\n");
		for(int i : list) {
			bw.write(i + " ");
		}
		
		br.close();
		bw.close();
	}
	
	// 전쟁
	private static void war(int a, int b) {
		// a와 b의 부모를 찾는다
		a = find(a);
		b = find(b);
		
		// a의 병력이 b의 병력보다 많다면
		if(military[a] > military[b]) {
			military[a] -= military[b];		// a 병력 - b 병력 = 남은 병력을 a병력에 넣음
			military[b] = 0;				// a의 승리
			// 이 조건이 들어가지 않으면 25%에서 틀림
			// 이유는 ?
			// b는 a의 속국이 된다
			parent[b] = a;
		
		// 위 if문과 반대 경우
		}else if(military[a] < military[b]) {
			military[b] -= military[a];
			military[a] = 0;
			parent[a] = b;
		
		// 두 나라의 병력이 같다면 둘다 멸망
		}else {
			military[a] = 0;
			military[b] = 0;
		}
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(military[a] > military[b]) {
			parent[b] = a;
			military[a] += military[b];
			military[b] = 0;
		}
		else {
			parent[a] = b;
			military[b] += military[a];
			military[a] = 0;
		}
	}
	
	private static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		
		return find(parent[x]);
	}
	
}
