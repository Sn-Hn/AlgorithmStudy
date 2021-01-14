package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제)
 * 1. 섬은 N-1개의 다리가 이어져있다 
 * 2. 무너져서 왕복 못하는 섬들이 생겼다
 * 3. 어떤 두 섬 사이든 왕복할 수 있도록 만들어라
 * 
 * 풀이)
 * 
 * 
 * */
public class BOJ_17352_여러분의다리가되어드리겠습니다 {
	
	static int N;

	static int [] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int [N+1];

		for(int i =1; i<=N;i++)
		{
			map[i] =i;
		}
		for(int i =0; i<N-2;i++)
		{
			String[] str =br.readLine().split(" ");
			int x = Integer.parseInt(str[0]);
			int y = Integer.parseInt(str[1]);
			merge(x,y);
		}

		// 1을 고정으로 넣고 출력
		int a = find(1);
		for(int i=2; i<=N; i++)
		{
			if(find(i)!= a)
			{	
				System.out.print(a +" " +i);
				break;
			}
		}
	}
	static int find(int x)
	{
		if(map[x] == x)
			return x;
		else
			return map[x] = find(map[x]);
	}
	static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x<y)
			map[y] = x;
		else 
			map[x] = y;
	}
}
