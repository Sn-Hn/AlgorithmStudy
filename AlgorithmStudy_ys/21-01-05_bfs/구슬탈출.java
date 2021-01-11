package Algostudy08_BOJ_13459;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


//빨간 구슬을 구멍을 통해서 빼내는 것. 이때 파란 구슬이 구멍에 들어가면 안된다.
//왼쪽,오른쪽,위쪽,아래쪽 기울이기
//공은 동시에 움직인다.
//동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때 까지
//빨간구슬과 파란구슬을 동시에 같은 위치에 있을 수 없다.
//기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때 까지이다.
//10번 이하로 빨간 구슬을 구멍을 통해 빼낼 수 있는지 


public class Main {
	
	static int n;
	static int m;
	static String map[][];

	static int a[] = {-1,1,0,0};
	static int b[] = {0,0,-1,1};
	static boolean[][][][] visited;
	
	public static class Ball{
		int rx;
		int ry;
		int bx;
		int by;
		int dir;
		int cnt;
		
		public Ball(int rx, int ry, int bx, int by, int dir, int cnt) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.dir = dir;
			this.cnt = cnt;
		}
		
		
		
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String tmp[] = br.readLine().split(" ");	
		
		map = new String[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[1])];
		n = Integer.parseInt(tmp[0]);
		m = Integer.parseInt(tmp[1]);
		
		visited = new boolean[n][m][n][m];
		
		Ball ball = new Ball(0, 0, 0, 0, -1, 0);
		for(int i = 0 ;i < map.length; i++) {
			map[i] = br.readLine().split("");
			for(int j = 0 ; j < map[i].length; j++) {
				if(map[i][j].equals("B")) {
					ball.bx = i;
					ball.by = j;
				}else if(map[i][j].equals("R")) {
					ball.rx = i;
					ball.ry = j;
				}
			}
		}
		
		
		bfs(ball);
		


	}
	
	
	public static void bfs(Ball info) {
		Queue<Ball> que = new LinkedList<Main.Ball>();
		
		que.offer(info);
		
		while(!que.isEmpty()) {
			
			Ball ball = que.poll();
			visited[ball.rx][ball.ry][ball.bx][ball.by] = true;
			
			
			if(ball.cnt >= 10) {
				System.out.println(0);
				return ;
			}
			for(int i = 0 ; i < 4; i++) {
				boolean rflag = false;
				boolean bflag = false;
				
				if(ball.dir > 0 && i == ((ball.dir%4) +2)%4 ) {
					continue;
				}
				
				int rx = ball.rx;
				int ry = ball.ry;
				while(true) {
					rx +=a[i];
					ry +=b[i];
					
					if(map[rx][ry].equals("#")) {
						rx -=a[i];
						ry -=b[i];
						break;
					}else if(map[rx][ry].equals("O")){
						ball.cnt += 1;
						rflag = true;
						break;
					}
					
				}// while end
				
				int bx = ball.bx;
				int by = ball.by;
				while(true) {
					bx +=a[i];
					by +=b[i];
					if(map[bx][by].equals("#")) {
						bx -=a[i];
						by -=b[i];
						break;
					}else if(map[bx][by].equals("O")){
						bflag = true;
						break;
					}
					
				}// while end
				
				
				if(rflag && !bflag && ball.cnt <= 10) { //빨간공이 들어가고 10번 이하이면 리턴
					System.out.println(1);
					return ;
				}else if (bflag) { // 파란 공이 먼저 들어가면 무시
					continue;
				}
				
				
				if (ball.rx == rx && ball.ry == ry && ball.bx == bx && ball.by == by)
					continue;
				
				
				// 북남 서동 같은 위치일 때 원래 있었던 위치와 방향과 비교하여 한칸 뒤로 밀기
				if(rx == bx && ry == by) {
					if(i == 0) {
						if(ball.rx >ball.bx) {
							rx +=1; // 위로 기울였는데  원래 rx가 아래있었음 그러니까 +1 해주어 bx아래 위치하도록
						}else {
							bx +=1;
						}
					}else if (i == 1) { // 아래로
						if(ball.rx > ball.bx) {
							bx -=1; 
						}else {
							rx -=1;
						}
					}else if (i == 2) {
						if(ball.ry >ball.by) {
							ry +=1; 
						}else {
							by +=1;
						}
					}else if (i == 3) {
						if(ball.ry >ball.by) {
							by -=1; 
						}else {
							ry -=1;
						}
					}
					
					
				}
				
				
			 
				
				if(!visited[rx][ry][bx][by]) {
					que.add(new Ball(rx, ry, bx, by, i, ball.cnt+1));
				}
				
				
				
				
				
			}// for end
			
			
		}
		
		
		System.out.println(0);
		
	}
	
	


}

