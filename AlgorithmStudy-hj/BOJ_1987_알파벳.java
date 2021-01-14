package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1987_알파벳 {
	static int R,C;
	static int [][] board;
	static boolean [] visited;
	static int result=0;//최대 칸수 
	static int [][]dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] str = br.readLine().split(" ");
		R = Integer.parseInt(str[0]);
		C  = Integer.parseInt(str[1]);
		board = new int[R][C];
		visited = new boolean[26];
		for(int i = 0; i<R; i++)
		{
			String str1 = br.readLine();
			for(int j =0; j<C; j++)
			{
				board[i][j] = str1.charAt(j)-'A';
			}
		}
		dfs(0,0,1);
		System.out.println(result);
	}
	static void dfs(int a, int b, int count) {
		visited[board[a][b]]= true;
		for(int i =0; i<4 ; i++) {
			int dx = a+ dir[i][0];
			int dy = b+ dir[i][1];
			
			if(dx>=0 && dy>=0 && dx<R && dy<C)
			{
				if(!visited[board[dx][dy]]) {
					dfs(dx, dy, count+1);
				}
			}
		}
		visited[board[a][b]]= false;
		
		result = Math.max(result, count);
	}

}
