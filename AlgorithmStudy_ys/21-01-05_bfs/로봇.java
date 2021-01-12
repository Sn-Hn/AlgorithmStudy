package Solution84;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;



public class Main {
	
	static int map[][];

	static int a[] = {0,0,0,1,-1};
	static int b[] = {0,1,-1,0,0};
	static int n;
	static int m;
	static int start[];
	static int end[];
	static boolean visited[][][];
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String tmp[] = br.readLine().split(" ");
		 n = Integer.parseInt(tmp[0]);
		 m = Integer.parseInt(tmp[1]);
		
		map = new int[n][m];
		visited = new boolean[n][m][5];
		
		
		for(int i = 0 ; i < n ;i++) {
			String input[] = br.readLine().split(" ");
			for(int j = 0 ; j < input.length;j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		
		tmp = br.readLine().split(" ");
		
		//시작점
		start = new int[tmp.length];
		for(int  i= 0  ; i< tmp.length;i++) {
			start[i] = Integer.parseInt(tmp[i]);
		}
		
		tmp = br.readLine().split(" ");
		//종료
		end = new int[tmp.length];
		for(int  i= 0  ; i< tmp.length;i++) {
			end[i] = Integer.parseInt(tmp[i]);
		}
		
		bfs(start);
		
		
	}
	
	public static void bfs(int info[]) {
		Queue<int[]> que = new LinkedList<int[]>();
		
		
		visited[info[0]-1][info[1]-1][info[2]] = true;
		
		que.add(new int[] {info[0]-1,info[1]-1,info[2],0});

		while(!que.isEmpty()) {
			int robot[] = que.poll();
			int x = robot[0];
			int y = robot[1];
			int dir = robot[2];
			int cnt = robot[3];
		
			if(x == end[0]-1 && y == end[1]-1 && dir == end[2]) {
				System.out.println(cnt);
			
				return ;
			}
			
			//현재 방향으로 전진할 공간이 있다면 1칸씩 총 3칸전진
			for(int i = 1; i <=3 ; i++) {
				
				int mx = x + (a[dir]*i);
				int my = y + (b[dir]*i);
				if(mx >= 0 && my >= 0 && mx < n && my < m) {
					
					if(map[mx][my] == 0 ) {
						if(!visited[mx][my][dir]) {
							
							visited[mx][my][dir] = true;
							que.add(new int[] {mx,my,dir,cnt+1});
						}
					}else {
						break;
					}
				}
			}
			
			
			//전진한공간에서 다른 방향으로 갈 수 있다면 방향을 회전
			for(int i = 1 ; i <=4 ; i++) {
				if(!visited[x][y][i] && dir != i) {
					visited[x][y][i] = true;
					
					if (dir+i == 3 || dir+i == 7) {
					
						que.add(new int[] {x,y,i,cnt+2});
					}else {
					
						que.add(new int[] {x,y,i,cnt+1});
					}
					
				}
				
	
			}
			
			
			
			
			
		}
		
		
		
	}
	
	

	
	

	
}
