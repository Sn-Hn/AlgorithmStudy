package algostudy07_BOJ_6087;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	static String map[][];
	static int c[][];
	static int a[] = {1,-1,0,0};
	static int b[] = {0,0,-1,1};
	static int visited[][];
	static int w;
	static int h;
	
	
	
	public static class Laser{
		int x;
		int y;
		int cnt;
		int way;
		public Laser() {
			// TODO Auto-generated constructor stub
		}
		
		public Laser(int x, int y, int cnt, int way) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.way = way;
		}

		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp[] = br.readLine().split(" ");
		
		w = Integer.parseInt(tmp[0]);
		h = Integer.parseInt(tmp[1]);
		
		map = new String[h][w];
		visited = new int[h][w];
		c = new int[2][2];
		
		
		int cnt = 0;
		for(int i = 0 ; i < h ; i++) {
			map[i] = br.readLine().split("");
			for(int j = 0 ; j < w ; j++) {
				if(map[i][j].equals("C")) {
					c[cnt][0] = i;
					c[cnt][1] = j;
					cnt++;
				}
			}
			
		}
		
		bfs(c[0][0],c[0][1]);
		
		
	}
	
	
	public static void bfs(int x, int y) {
		Queue<Laser> que = new LinkedList<Laser>();
	
		
		for(int i = 0 ; i < 4; i++) {
			que.add(new Laser(x, y, 0, i));
		}
		
		while(!que.isEmpty()) {
			Laser laser = que.poll();
			x = laser.x;
			y = laser.y;
			int cnt = laser.cnt;
			int way = laser.way;
			
			for(int i = 0 ; i < 4 ; i++) {
				int mx = x + a[i];
				int my = y + b[i];
				if(mx >= 0 && my >= 0 && mx <h && my < w) {
					if( map[mx][my].equals(".") || map[mx][my].equals("C" )) {
						
						int next = way == i ? cnt : cnt+1;
						if(visited[mx][my] == 0 || (visited[mx][my] !=0 && visited[mx][my] >= next)) {
						
							visited[mx][my] = next;
							que.add(new Laser(mx, my, next, i)); // 직진
			
						}
					}
				}
			
			}
			

		}
		
		
			
		System.out.println(visited[c[1][0]][c[1][1]]);
			
			
		
		
		
		
	}
	
	
	
	
}
