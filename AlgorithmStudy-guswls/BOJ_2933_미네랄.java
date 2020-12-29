package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2933_미네랄 {
	static int R,C,N;
	static char[][] map;
	static int[][] visited;
	static boolean check;
	static int [][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < R; i++) {
				Arrays.fill(visited[i], 0);
			}
			
			int mak = Integer.parseInt(st.nextToken());
			
			if (k%2 == 0) { // 왼쪽 부터
				for (int j = 0; j < C; j++) {
					if(map[R-mak][j] == 'x') {
						map[R-mak][j] = '.';
						break;
					}
				}
			}
			else {		// 오른쪽
				for (int j = C-1; j >= 0; j--) {
					if(map[R-mak][j] == 'x') {
						map[R-mak][j] = '.';
						break;
					}
				}
			}
			
			int cnt = 1, mark =0;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(map[i][j] == 'x' && visited[i][j]==0) {
						check = false;
						dfs(i,j, cnt);
						if (!check) {
							mark = cnt;
						}
						cnt++;
					}
				}
			}
			
			if(mark > 0) {
				while(true) {
					if(move(mark)) break;
				}
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	private static boolean move(int mark) {
		boolean check = false;
		for(int i = R-1; i>=0; i--) {
			for(int j =0; j<C; j++) {
				if(visited[i][j] == mark) {
					map[i][j] = '.';
					map[i+1][j] = 'x';
					visited[i][j] = 0;
					visited[i+1][j] = mark;
					if(i+2 == R) {
						check = true;
						continue;
					}
					if(map[i+2][j] == 'x'&& visited[i+2][j] != mark ) check = true;
				}
			}
		}
		return check;
	}
	private static void dfs(int i, int j, int cnt) {
		visited[i][j] = cnt;
		for(int k = 0; k<4; k++) {
			int dx= i+ dir[i][0];
			int dy = j+ dir[i][1];
			
			if (dx>=0 && dy>=0 && dx<R && dy<C) {
				if( map[dx][dy]!='.' || visited[dx][dy] ==0) 
				if (dx==R-1) check = true;
				dfs(dx,dy,cnt);
			}
	
		}
	}
}