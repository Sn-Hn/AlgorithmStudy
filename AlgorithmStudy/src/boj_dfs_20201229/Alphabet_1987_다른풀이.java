package boj_dfs_20201229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://mygumi.tistory.com/186
// 방문처리를 알파벳 26가지에 대해서 처리를 해줌

// 12196KB 868ms
// 메모리와 시간 확 줄어들었다..
public class Alphabet_1987_다른풀이 {
	private static int R, C;
	private static char map[][];
	private static boolean visited[];
	private static int max = Integer.MIN_VALUE;
	private static int count = 1;
	
	private static int dx[] = {1, -1, 0, 0};
	private static int dy[] = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String str = "";
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R+1][C+1];
		// 0 : A ~ 26 : Z
		visited = new boolean[26];
		
		for(int i = 0; i < R; i++) {
			str = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		int index = changeInt(map[0][0]);
		visited[index] = true;
		move(0, 0);
		
		System.out.println(max);
		
		br.close();
	}
	
	private static void move(int x, int y) {
		max = Math.max(max, count);
		
		for(int i = 0; i < 4; i++) {
			int X = x + dx[i];
			int Y = y + dy[i];
			
			if(X >= 0 && X < R && Y >= 0 && Y < C) {
				int index = changeInt(map[X][Y]);
				if(!visited[index]) {
					count++;
					visited[index] = true;
					move(X, Y);
					count--;
					visited[index] = false;
					
				}
			}
		}
		
	}
	
	private static int changeInt(char c) {
		return c-'A';
	}

}
