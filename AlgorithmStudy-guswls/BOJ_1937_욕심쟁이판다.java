package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1937_욕심쟁이판다 {
	static int [][] map;
	static int [][] visited;// 판다가 살아남는  횟수
	static int [][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	static int N, result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		N = Integer.parseInt(str);
		map = new int[N][N];
		visited = new int [N][N];
		for(int i = 0; i<N; i++)
		{
			String [] str1 = br.readLine().split(" ");
			for(int j=0; j<N; j++)
			{
				map[i][j] = Integer.parseInt(str1[j]);
			}
		}
		for(int i =0; i<N; i++) {
			for(int j=0; j<N; j++)
			{
				if(visited[i][j] ==0)
					dfs(i,j);
			}
		}
		System.out.println(result);
	}
	static void dfs(int a, int b) {
		int count=0; 
		for(int i=0; i<4; i++) {
			int dx = a + dir[i][0];
			int dy = b+ dir[i][1];
			
			if(dx>=0 && dy>=0 && dx<N && dy<N)
			{
				if(map[a][b] <map[dx][dy])
				{//판다는 다음 먹이 양이 많은 곳으로 가야하기 때문에 
					if(visited[dx][dy]==0)
						dfs(dx, dy);
					if(count<visited[dx][dy])// 현재 위치의 count 횟수 변경
						count = visited[dx][dy];
				}
			}
			visited[a][b] = count+1;
			result = Math.max(result, visited[a][b]); //max값으로 result 변경
		}
	}
}
