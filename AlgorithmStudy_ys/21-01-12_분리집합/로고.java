package algostydy_13_BOJ_3108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static class Rec{
		int x1 ,y1 ,x2, y2;

		public Rec(int x1, int y1, int x2, int y2) {
			super();
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
	
	static int n;
	static Rec rec[];
	static boolean visited[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		visited = new boolean[n+1];
		rec = new Rec[n+1];
		
		// 원점부터 시작이므로 0 추가해줌
		rec[0] = new Rec(0, 0, 0, 0); 
		
		
		for(int i = 1 ; i <= n ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			rec[i] = new Rec(x1, y1, x2, y2);
			
		}
		
		int cnt = 0;
		for(int i = 0 ; i <=n ; i++) {
			Queue<Integer> que = new LinkedList<Integer>();
			if(visited[i]) {
				continue;
			}
			que.add(i);
			visited[i] = true;
			
			while(!que.isEmpty()) {
				int cur = que.poll();
				
				for(int j = 0 ; j <= n; j++) {
					if( j == cur || visited[j] ) {
						continue;
					}
					if(isCross(rec[cur], rec[j])) {
						visited[j] = true;
						que.add(j);
					}
				}
				
			}
			cnt++;
		}
		
		System.out.println(cnt-1);
		
	}

	public static boolean isCross(Rec a,Rec b) {
		// b가 걸치는 곳 없이 아예 안쪽에 있다.
		if(a.x1 < b.x1 && b.x2 < a.x2 &&a.y1  < b.y1 && b.y2 < a.y2)
			return false;
		//a가걸치는 곳 없이 아예 안쪽에 있다.
		if(a.x1 > b.x1 && b.x2 > a.x2 && a.y1 > b.y1 && b.y2 >a.y2)
			return false;
		//겹치는 부분이 없다
		if(a.x2 < b.x1 || b.x2 < a.x1 || a.y1 > b.y2 || b.y1 > a.y2)
			return false;
		
		return true;
	}
	

	
}
