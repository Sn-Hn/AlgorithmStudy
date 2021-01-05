package boj_20210105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*

https://www.acmicpc.net/problem/16236

아기 상어 분류 골드4
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	512 MB	23891	10069	5585	38.359%
문제
N×N 크기의 공간에 물고기 M마리와 아기 상어 1마리가 있다. 
공간은 1×1 크기의 정사각형 칸으로 나누어져 있다. 한 칸에는 물고기가 최대 1마리 존재한다.

아기 상어와 물고기는 모두 크기를 가지고 있고, 이 크기는 자연수이다. 
가장 처음에 아기 상어의 크기는 2이고, 아기 상어는 1초에 상하좌우로 인접한 한 칸씩 이동한다.

아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 모두 지나갈 수 있다. 
아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다. 
따라서, 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 칸은 지나갈 수 있다.

아기 상어가 어디로 이동할지 결정하는 방법은 아래와 같다.

더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
아기 상어의 이동은 1초 걸리고, 물고기를 먹는데 걸리는 시간은 없다고 가정한다. 
즉, 아기 상어가 먹을 수 있는 물고기가 있는 칸으로 이동했다면, 이동과 동시에 물고기를 먹는다. 물고기를 먹으면, 그 칸은 빈 칸이 된다.

아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다. 예를 들어, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이 된다.

공간의 상태가 주어졌을 때, 아기 상어가 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 공간의 크기 N(2 ≤ N ≤ 20)이 주어진다.

둘째 줄부터 N개의 줄에 공간의 상태가 주어진다. 공간의 상태는 0, 1, 2, 3, 4, 5, 6, 9로 이루어져 있고, 아래와 같은 의미를 가진다.

0: 빈 칸
1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
9: 아기 상어의 위치
아기 상어는 공간에 한 마리 있다.

출력
첫째 줄에 아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간을 출력한다.

예제 입력 1 
3
0 0 0
0 0 0
0 9 0
예제 출력 1 
0
예제 입력 2 
3
0 0 1
0 0 0
0 9 0
예제 출력 2 
3
예제 입력 3 
4
0 0 2 1
0 0 0 0
0 0 9 0
1 2 0 0
예제 출력 3 
14
예제 입력 4 
6
5 4 3 2 3 4
4 3 2 3 4 5
3 2 9 5 6 6
2 1 2 3 4 5
3 2 1 6 5 4
6 6 6 6 6 6
예제 출력 4 
60
예제 입력 5 
6
6 0 6 0 6 1
0 0 0 0 0 2
2 3 4 5 6 6
0 0 0 0 0 2
0 2 0 0 0 0
3 9 3 0 0 1
예제 출력 5 
48
예제 입력 6 
6
1 1 1 1 1 1
2 2 6 2 2 3
2 2 5 2 2 3
2 2 2 4 6 3
0 0 0 0 0 6
0 0 0 0 0 9
예제 출력 6 
39


*/

// BFS 문제
public class BabyShark_16236 {
	private static int N;
	private static int map[][];
	private static Shark shark;
	private static Shark eatableFish = new Shark(0, 0, 0);		// 아기 상어가 먹을 물고기
	private static List<Shark> fishList = new ArrayList<Shark>();
	private static boolean[][] visited;
	private static boolean flag = true;
	
	private static int dx[] = {1, -1, 0, 0};
	private static int dy[] = {0, 0, 1, -1};
	
	private static class Shark implements Comparable<Shark> {
		int x, y, depth;
		int count = 0;
		int size = 2;
		
		public Shark(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
		
		public Shark(int x, int y, int depth, int size, int count) {
			this.x = x;
			this.y = y;
			this.depth = depth;
			this.size = size;
			this.count = count;
		}
		
		// 아기 상어가 물고기를 잡아먹는 메소드
		public void eatFish(int count) {
			this.count = count;
			// 잡아먹은 물고기 수가 몸집과 같거나 크다면
			if(count >= size) {
				size += 1;
				this.count = 0;
			}
		}
		
		// Comparable을 통한 정렬
		@Override
		public int compareTo(Shark o) {
			// TODO Auto-generated method stub
			// depth가 작은것부터 정렬 (오름차순) -> 최소 경로인 것들이 맨 앞으로
			if(this.depth > o.depth) {
				return 1;
				
			// depth가 같을 때
			}else if(this.depth == o.depth) {
				// x가 작은 것 부터 정렬 (오름차순) -> 위쪽에 있는 것들이 맨 앞으로
				if(this.x > o.x) {
					return 1;
				// x가 같을 때
				}else if(this.x == o.x) {
					// y가 작은 것 부터 정렬 (오름차순) -> 왼쪽에 있는 것들이 맨 앞으로
					if(this.y > o.y) {
						return 1;
					}
				}				
			}
			return -1;
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) { 
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark = new Shark(i, j, 0);
				}
			}
		}
		int result = 0;
		while(flag) {
			visited = new boolean[N][N];
			result += bfs();
		}
		System.out.println(result);
		
		br.close();
	}
	
	private static int bfs() {
		Queue<Shark> q = new LinkedList<>();
		int depth = 0;
		q.add(shark);
		
		while(!q.isEmpty()) {
			Shark s = q.poll();
			visited[s.x][s.y] = true;
			
			for(int i = 0; i < 4; i++) {
				int X = s.x + dx[i];
				int Y = s.y + dy[i];
				if(X >= 0 && X < N && Y >= 0 && Y < N) {
					// 
					if((map[X][Y] == 0 || map[X][Y] == s.size) && !visited[X][Y]) {
						depth = s.depth + 1;
						visited[X][Y] = true;
						q.add(new Shark(X, Y, depth, s.size, s.count));						
					// 아기 상어가 먹을 수 있는 물고기들을 fishList에 저장
					}else if((map[X][Y] >= 1 && map[X][Y] < s.size) && !visited[X][Y]) {
						depth = s.depth + 1;
						visited[X][Y] = true;
						// 가장 가까운 물고기들
						fishList.add(new Shark(X, Y, depth, s.size, s.count));
					}
					
				}
			}
		}
		
		if(eatableFish()) {
			// 아기 상어가 먹을 수 있는 물고기의 자리로 감
			map[shark.x][shark.y] = 0;
			map[eatableFish.x][eatableFish.y] = 9;
			shark.eatFish(shark.count+1);
			// 아기 상어가 물고기를 먹었으니 새로 초기화
			shark = new Shark(eatableFish.x, eatableFish.y, 0, shark.size, shark.count);
			depth = eatableFish.depth;
			fishList.clear();
			flag = true;
		}else {
			flag = false;
			return 0;
		}
		
		return depth;
	}
	
	private static boolean eatableFish() {
		// 먹을수 있는 물고기가 없다면 return false;
		if(fishList.size() == 0) {
			return false;
		}
		
		// 조건에 맞게 정렬
		Collections.sort(fishList);
		
		// depth가 가장 작은 것들을 제외하고 전부 제거
		// 먹이가 기준보다 아래쪽인것들 제거
		// 먹이가 기준보다 오른쪽인것들 제거
		eatableFish = fishList.get(0);
		
		return true;
	}
	
	private static boolean isEat(Shark s) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] >= 1 && map[i][j] < s.size) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static void printMap() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
