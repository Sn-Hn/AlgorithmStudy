package boj_20210112_UnionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

로고 출처다국어분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	2638	802	521	27.363%
문제
로고는 주로 교육용에 쓰이는 프로그래밍 언어이다. 로고의 가장 큰 특징은 거북이 로봇인데, 사용자는 이 거북이 로봇을 움직이는 명령을 입력해 화면에 도형을 그릴 수 있다.

거북이는 위치와 각도로 표현할 수 있다. 거북이는 입에 연필을 물고 있는데, 연필을 내리면 움직일 때 화면에 선을 그리고, 올리면 선을 그리지 않고 그냥 지나가기만 한다.

제일 처음에 거북이는 (0,0)에 있고, 거북이가 보고 있는 방향은 y축이 증가하는 방향이다. 또한 연필은 내리고 있다.

사용자는 다음과 같은 다섯가지 명령으로 거북이를 조정할 수 있다.

FD x: 거북이를 x만큼 앞으로 전진
LT a: 거북이를 반시계 방향으로 a도 만큼 회전
RT a: 거북이를 시계 방향으로 a도 만큼 회전
PU: 연필을 올린다
PD: 연필을 내린다.
축에 평행한 직사각형 N개가 주어졌을 때, 이 직사각형을 그리는데 필요한 PU 명령의 최솟값을 구하는 프로그램을 작성하시오.

거북이는 같은 선을 여러 번 그릴 수 있지만, 문제에 주어진 직사각형 N개를 제외한 어떤 것도 그릴 수 없다. 
거북이의 크기는 아주 작아서 좌표 평면의 한 점이라고 생각하면 된다. 직사각형의 변은 축에 평행하다.

입력
첫째 줄에 직사각형의 개수 N이 주어진다. (1 ≤ N ≤ 1000)

다음 N개의 줄에는 직사각형의 좌표 x1, y1, x2, y2가 주어진다. 
(−500 ≤ x1 < x2 ≤ 500), (−500 ≤ y1 < y2 ≤ 500) 
(x1, y1)는 직사각형의 한 꼭짓점 좌표이고, (x2, y2)는 그 점의 대각선 방향의 반대 꼭짓점의 좌표이다.

출력
N개의 직사각형을 그리는 필요한 PU명령의 최솟값을 출력한다.

예제 입력 1 
5
1 1 4 4
3 3 6 6
4 4 5 5
5 0 8 3
6 1 7 2
예제 출력 1 
2

*/

public class 로고_3108 {
	private static int N;
	private static int coordinate[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		coordinate = new int[N][4];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			coordinate[i][0] = Integer.parseInt(st.nextToken());
			coordinate[i][1] = Integer.parseInt(st.nextToken());
			coordinate[i][2] = Integer.parseInt(st.nextToken());
			coordinate[i][3] = Integer.parseInt(st.nextToken());
		}
		
		
		br.close();
	}
}
