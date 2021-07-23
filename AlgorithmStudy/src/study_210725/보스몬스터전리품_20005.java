package study_210725;

/*

보스몬스터 전리품 출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	355	134	97	35.531%
문제
멤멤월드에서는 일정 주기마다 랜덤한 위치에서 보스몬스터가 소환된다.

이 보스몬스터의 전리품은 아주 좋아 모든 멤멤월드의 플레이어들은 소환 알림만을 기다린다고 한다. 
전리품은 한 대라도 때렸다면 피해를 준 비율대로 지급된다고 한다.

현재 멤멤월드의 지도와 플레이어들의 정보, 보스몬스터의 체력이 주어졌을 때 최대 몇 명의 플레이어가 전리품을 가져갈 수 있는지 계산해보자.

단, 모든 플레이어는 보스몬스터가 소환되면 보스몬스터의 위치로 최대한 빠른 경로로 이동하며 이동한 경우 공격을 바로 시작한다. 
공격에 소모되는 시간은 1초이며 보스와 같은 위치에 있는 모든 플레이어의 공격은 동시에 이뤄진다.
그리고 플레이어는 상, 하, 좌, 우로 이동할 수 있고 이동에 소요되는 시간은 1초이다. 또한 한 지점에 여러명의 플레이어가 위치할 수 있다.

입력
입력의 첫째 줄에는 멤멤월드의 지도의 크기를 나타내는 두 정수 M(6 ≤ M ≤ 1000), N(6 ≤ N ≤ 1000)과 플레이어의 수 P(1 ≤ P ≤ 26)가 주어진다. 
M은 지도의 세로 길이, N은 지도의 가로 길이이다.

입력의 둘째 줄부터 M개의 줄까지 지도의 정보가 주어진다. 
이때 ‘.’은 이동할 수 있는 길, ‘X’는 이동할 수 없는길, 알파벳 소문자는 플레이어의 아이디이며 ‘B’는 보스몬스터의 위치이다.

그 다음 줄부터 P개의 줄까지 플레이어의 아이디와 dps(1 ≤ dps ≤ 10000)가 주어진다. 
아이디는 영문 소문자이다. dps란 1초당 얼만큼의 보스몬스터의 체력을 줄일 수 있는지 의미한다. 
그 다음 줄은 보스몬스터의 HP(10 ≤ HP ≤ 1000000)가 주어진다. dps와 HP는 정수이다.

아무 플레이어도 보스몬스터를 잡으러 갈 수 없는 경우의 입력은 주어지지 않는다.

출력
전리품을 가져갈 수 있는 플레이어의 수의 최댓값을 출력한다.

예제 입력 1 
6 6 3
b.Bc..
......
.a....
......
...X..
.....X
a 36
b 19
c 39
79
예제 출력 1 
2
예제 입력 2 
6 6 5
.....B
e...X.
.d....
c.....
....a.
.....b
a 4
b 22
c 29
d 98
e 94
253
예제 출력 2 
4
예제 입력 3 
6 6 4
......
...a..
..bBc.
...d..
......
......
a 4
b 4
c 4
d 4
10
예제 출력 3 
4

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 보스몬스터전리품_20005 {
	private static final int[] dx = {0, 0, 1, -1};
	private static final int[] dy = {1, -1, 0, 0};
	
	private static int N;
	private static int M;
	private static int P;
	private static char map[][];
	private static int[] players = new int[26];
	private static List<Pos> playersPos = new ArrayList<Pos>();
	private static int bossHP;
	private static int maxCnt = 0;
	private static boolean visited[][][];
	
	private static class Pos {
		int x;
		int y;
		char name;
		int time;
		
		public Pos(int x, int y, char name, int time) {
			this.x = x;
			this.y = y;
			this.name = name;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[26][N][M];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '.' || map[i][j] == 'B' || map[i][j] == 'X') {
					continue;
				}
				playersPos.add(new Pos(i, j, map[i][j], 0));
			}
		}
		
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			char name = st.nextToken().charAt(0);
			int dps = Integer.parseInt(st.nextToken());
			players[parseInt(name)] = dps;
		}
		
		bossHP = Integer.parseInt(br.readLine());
		
		movePlayer();
		
		System.out.println(maxCnt);
		
		br.close();
	}
	
	private static void movePlayer() {
		Queue<Pos> q = new LinkedList<Pos>();
		boolean first = false;
		int[] arrival = new int[26];
		for (Pos pos : playersPos) {
			q.add(pos);
		}
		
		while (!q.isEmpty()) {
			Pos pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			
			System.out.println(x + ", " + y + ", " + pos.name);
			
			if (arrival[parseInt(pos.name)] != 0) {
				continue;
			}
			
			if (map[x][y] == 'B') {
				System.out.println("도착 : " + x + ", " + y + ", " + pos.name);
				if (arrival[parseInt(pos.name)] == 0) {
					if (isAttackBoss(pos, arrival, first)) {
						break;
					}
					first = true;
					arrival[parseInt(pos.name)] = pos.time;
				}
				
				continue;
			}
			
			for (int i = 0; i < dx.length; i++) {
				int X = x + dx[i];
				int Y = y + dy[i];

				if (isValid(X, Y)) {
					if (!visited[parseInt(pos.name)][X][Y]) {
						visited[parseInt(pos.name)][X][Y] = true;
						q.add(new Pos(X, Y, pos.name, pos.time + 1));						
					}
				}
			}
		}
		
		maxCnt = getMaxCount(arrival);
	}
	
	private static boolean isAttackBoss(Pos pos, int[] arrival, boolean first) {
		if (!first) {
			return false;
		}
		int HP = bossHP;
		for (int i = 0; i < 26; i++) {
			if (arrival[i] == 0) {
				continue;
			}
			HP -= players[i] * (pos.time - arrival[i]);
			System.out.println("HP : " + HP);
		}
		
		if (HP <= 0) {
			return true;
		}
		
		return false;
	}
	
	private static int getMaxCount(int[] arrival) {
		int count = 0;
		for (int i = 0; i < 26; i++) {
			if (arrival[i] != 0) {
				count ++;
			}
		}
		
		return count;
	}
	
	private static int parseInt(char ch) {
		return ch - 'a';
	}
	
	private static boolean isValid(int X, int Y) {
		if (X >= 0 && X < N && Y >= 0 && Y < M && map[X][Y] != 'X') {
			return true;
		}
		
		return false;
	}
}
