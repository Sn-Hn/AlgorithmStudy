package algostudy12_BOJ_1043;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	
	static boolean chk[][];
	static boolean people[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");

		int	N = Integer.parseInt(input[0]); // 사람의 수
		int	M = Integer.parseInt(input[1]); // 파티의수
		chk = new boolean[N+1][N+1];
		
		
		people = new boolean[N+1];
		int party[] = new int[M];
		
		// 진실을 아는사람은 true
		input = br.readLine().split(" ");
		for(int i = 1 ; i < input.length;i++) {
			people[Integer.parseInt(input[i])] = true;
		}
		
		
		
		//파티 정보 
		for(int i = 0 ; i < M ; i++) {
			String t[] = br.readLine().split(" ");
			
			party[i] = Integer.parseInt(t[1]);
			
			//같은 파티끼리 연결 
			for(int j = 1 ; j < t.length-1 ; j++) {
				chk[Integer.parseInt(t[j])][Integer.parseInt(t[j+1])] = true;
				chk[Integer.parseInt(t[j+1])][Integer.parseInt(t[j])] = true;
				
			}
		}
		

		
		for(int i = 1 ; i <= N ;i++) {
			if(people[i]) {

				dfs(N,i);
			}
		}
		
		int ans = 0;
		for(int i = 0 ;i < M ; i++) {
			if(!people[party[i]]) {
				ans++;
			}
		}
		System.out.println(ans);
		
		
	}
	

	public static void dfs(int n,int x) {
	
		for(int i = 1 ;i <= n ; i++) {
			if(chk[x][i] && !people[i]) {
				people[i] = true;
				dfs(n,i);
			}
		}
		
		
	}
	
}
