package boj_20210105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*

구슬 탈출 분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	512 MB	4553	1441	1028	32.511%
문제
스타트링크에서 판매하는 어린이용 장난감 중에서 가장 인기가 많은 제품은 구슬 탈출이다. 
구슬 탈출은 직사각형 보드에 빨간 구슬과 파란 구슬을 하나씩 넣은 다음, 빨간 구슬을 구멍을 통해 빼내는 게임이다.

보드의 세로 크기는 N, 가로 크기는 M이고, 편의상 1×1크기의 칸으로 나누어져 있다. 
가장 바깥 행과 열은 모두 막혀져 있고, 보드에는 구멍이 하나 있다. 
빨간 구슬과 파란 구슬의 크기는 보드에서 1×1크기의 칸을 가득 채우는 사이즈이고, 각각 하나씩 들어가 있다. 
게임의 목표는 빨간 구슬을 구멍을 통해서 빼내는 것이다. 이때, 파란 구슬이 구멍에 들어가면 안 된다.

이때, 구슬을 손으로 건드릴 수는 없고, 중력을 이용해서 이리 저리 굴려야 한다. 
왼쪽으로 기울이기, 오른쪽으로 기울이기, 위쪽으로 기울이기, 아래쪽으로 기울이기와 같은 네 가지 동작이 가능하다.

각각의 동작에서 공은 동시에 움직인다. 
빨간 구슬이 구멍에 빠지면 성공이지만, 파란 구슬이 구멍에 빠지면 실패이다. 
빨간 구슬과 파란 구슬이 동시에 구멍에 빠져도 실패이다. 
빨간 구슬과 파란 구슬은 동시에 같은 칸에 있을 수 없다. 
또, 빨간 구슬과 파란 구슬의 크기는 한 칸을 모두 차지한다. 기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때 까지이다.

보드의 상태가 주어졌을 때, 10번 이하로 빨간 구슬을 구멍을 통해 빼낼 수 있는지 구하는 프로그램을 작성하시오.

입력
첫 번째 줄에는 보드의 세로, 가로 크기를 의미하는 두 정수 N, M (3 ≤ N, M ≤ 10)이 주어진다. 
다음 N개의 줄에 보드의 모양을 나타내는 길이 M의 문자열이 주어진다. 
이 문자열은 '.', '#', 'O', 'R', 'B' 로 이루어져 있다. 
'.'은 빈 칸을 의미하고, '#'은 공이 이동할 수 없는 장애물 또는 벽을 의미하며, 'O'는 구멍의 위치를 의미한다. 
'R'은 빨간 구슬의 위치, 'B'는 파란 구슬의 위치이다.

입력되는 모든 보드의 가장자리에는 모두 '#'이 있다. 구멍의 개수는 한 개 이며, 빨간 구슬과 파란 구슬은 항상 1개가 주어진다.

출력
파란 구슬을 구멍에 넣지 않으면서 빨간 구슬을 10번 이하로 움직여서 빼낼 수 있으면 1을 없으면 0을 출력한다.

예제 입력 1 
5 5
#####
#..B#
#.#.#
#RO.#
#####
예제 출력 1 
1
예제 입력 2 
7 7
#######
#...RB#
#.#####
#.....#
#####.#
#O....#
#######
예제 출력 2 
1
예제 입력 3 
7 7
#######
#..R#B#
#.#####
#.....#
#####.#
#O....#
#######
예제 출력 3 
1
예제 입력 4 
10 10
##########
#R#...##B#
#...#.##.#
#####.##.#
#......#.#
#.######.#
#.#....#.#
#.#.#.#..#
#...#.O#.#
##########
예제 출력 4 
0
예제 입력 5 
3 7
#######
#R.O.B#
#######
예제 출력 5 
1
예제 입력 6 
10 10
##########
#R#...##B#
#...#.##.#
#####.##.#
#......#.#
#.######.#
#.#....#.#
#.#.##...#
#O..#....#
##########
예제 출력 6 
1
예제 입력 7 
3 10
##########
#O.....BR#
##########
예제 출력 7 
0

6 7
#######
#R....#
#.###.#
#....##
#..#BO#
#######

5 7
#######
#######
###.###
#O.BR##
#######

5 7
#######
#######
#######
##ORB##
#######


*/

// 100% 에서 틀림
public class BeadEscape_13459 {
	private static int N, M;
	private static char map[][];
	private static boolean visited[][][][];
	private static int redBead[] = new int[2];
	private static int blueBead[] = new int[2];
	private static int hole[] = new int[2];
	private static int result = 0;
	
	private static Pair beadList;
	
	// 동 서 남 북
	private static int dx[] = {0, 0, 1, -1};
	private static int dy[] = {1, -1, 0, 0};
	
	private static class Pair {
		int redX, redY, blueX, blueY, count;
		public Pair(int redX, int redY, int blueX, int blueY, int count) {
			this.redX = redX;
			this.redY = redY;
			this.blueX = blueX;
			this.blueY = blueY;
			this.count = count;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][N][M];
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'R') {
					redBead[0] = i;
					redBead[1] = j;
				}else if(map[i][j] == 'B') {
					blueBead[0] = i;
					blueBead[1] = j;
				}else if(map[i][j] == 'O') {
					hole[0] = i;
					hole[1] = j;
				}
			}
		}
		
		beadList = new Pair(redBead[0], redBead[1], blueBead[0], blueBead[1], 0);
		
		escapeBead();
		System.out.println(result);
		
		br.close();
	}
	
	private static void escapeBead() {
		Queue<Pair> q = new LinkedList<Pair>();
		boolean flag = false;
		q.add(beadList);
		visited[redBead[0]][redBead[1]][blueBead[0]][blueBead[1]] = true;
		
		while(!q.isEmpty()) {
			Pair bead = q.poll();
			int redX, redY, blueX, blueY;
			int count = bead.count;
			
			// 10번 넘게 움직였으므로 실패
			if(count >= 10) {
				break;
			}
			
			/*  5 7
				#######
				#######
				#######
				##ORB##
				#######
				위의 예제를 처리할 때 아래 if문이 없으면 1이 출력
				
			*/
			if(bead.redX == hole[0] && bead.redY == hole[1]) {
				continue;
			}
			
			// 빨간 공과 파란 공의 위치에 따라 달라짐...
			for(int i = 0; i < 4; i++) {
				// 회전을 할때마다 기존의 구슬 값을 받아옴
				redX = bead.redX;
				redY = bead.redY;
				blueX = bead.blueX;
				blueY = bead.blueY;
				
				// 기울여지기 시작
				// while이 끝나면 구슬들이 한쪽으로 쏠림
				while(true) {
					int rx = redX + dx[i];
					int ry = redY + dy[i];
					int bx = blueX + dx[i];
					int by = blueY + dy[i];
					
					// 한쪽으로 기울여질때 빨간구슬과 파란구슬이 동시에 들어오는 것을 구분하기 위해 flag를 사용
					if(redX == hole[0] && redY == hole[1]) {
						flag = true;
					}
					
					
					if(blueX == hole[0] && blueY == hole[1]) {
						flag = false;
						break;
					}
					
					// 다음에 전진할 빨간 구슬의 위치가 벽이 아니라면 
					if(map[rx][ry] != '#') {
						redX += dx[i];
						redY += dy[i];
					}
					
					// 다음에 전진할 파란 구슬의 위치가 벽이 아니라면 
					if(map[bx][by] != '#') {
						blueX += dx[i];
						blueY += dy[i];
					}
					
					// 빨간 구슬 파란 구슬의 다음 위치가 둘다 벽이라면 while 종료
					if(map[rx][ry] == '#' && map[bx][by] == '#') {
						break;
					}
					
					
				}
				if(flag) {
					result = 1;
					break;
				}
				
				// 빨간 구슬과 파란 구슬이 겹쳤을 때
				if(redX == blueX && redY == blueY) {
					// 기존의 빨간구슬의 X인덱스가 파란구슬의 X인덱스보다 크다면
					if(bead.redX > bead.blueX) {
						// 아래로 기울였을 때
						if(i == 2) {
							// blueX의 전 위치를 의미
							blueX -= dx[i];
						// 위로 기울였을 때
						}else if(i == 3) {
							redX -= dx[i];								
						}
					}else {
						if(i == 2) {
							redX -= dx[i];
						}else if(i == 3) {
							blueX -= dx[i];								
						}
					}
					// 기존의 빨간구슬의 Y인덱스가 파란구슬의 Y인덱스보다 크다면
					if(bead.redY > bead.blueY) {
						// 오른쪽으로 기울였을 때
						if(i == 0) {
							blueY -= dy[i];
						// 왼쪽으로 기울였을 때
						}else if(i == 1) {
							redY -= dy[i];								
						}
					}else {
						if(i == 0) {
							redY -= dy[i];
						}else if(i == 1) {
							blueY -= dy[i];								
						}
					}
				}
				
				// 방문처리
				if(!visited[redX][redY][blueX][blueY]) {
					visited[redX][redY][blueX][blueY] = true;
					q.add(new Pair(redX, redY, blueX, blueY, count+1));
				}
			}
		}
		
	}
}
