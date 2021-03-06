package boj_20210105_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Laboratory_14502 {
	private static int N, M;
	private static int map[][];
	private static int copyMap[][];
	private static List<Pair> virusList = new ArrayList<Pair>();
	
	private static int max = Integer.MIN_VALUE;
	
	private static int dx[] = {0, 0, 1, -1};
	private static int dy[] = {1, -1, 0, 0};
	private static class Pair {
		int x, y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		copyMap = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virusList.add(new Pair(i, j));
				}
			}
		}
		
		wall(0);
		System.out.println(max);
		
		br.close();
	}
	
	// 벽세우기
	private static void wall(int count) {
		if(count == 3) {
			copyMap();
			virus();
			max = Math.max(max, cntOfSafetyZone());
			return;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					wall(count+1);
					map[i][j] = 0;
				}
			}
		}
		
		
		
	}
	
	// 바이러스 퍼짐
	private static void virus() {
		Queue<Pair> q = new LinkedList<Pair>();
		for(Pair p : virusList) {
			q.add(p);
		}
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			
			
			for(int i = 0; i < 4; i++) {
				int X = p.x + dx[i];
				int Y = p.y + dy[i];
				
				if(X >= 0 && X < N && Y >= 0 && Y < M && copyMap[X][Y] == 0) {
					copyMap[X][Y] = 2;
					q.add(new Pair(X, Y));
				}
			}
		}
	}
	
	private static void copyMap() {
		for(int i = 0; i < N; i++ ) {
			for(int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
	}
	
	private static int cntOfSafetyZone() {
		int count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(copyMap[i][j] == 0) {
					count += 1;
				}
			}
		}
		return count;
	}
}
