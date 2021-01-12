package algostudy09_BOJ_17352;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int num[] = new int[n+1];
		int connect[][] = new int[n-2][2];
		for(int i = 1 ;i <= n ; i++) {
			num[i] = i	;
		}
		
		for(int i = 0 ;i < n-2 ; i++) {
			String tmp[] = br.readLine().split(" ");
			for(int j = 0 ; j < 2; j++) {
				connect[i][j] = Integer.parseInt(tmp[j]);
			}
		}

		for(int i = 0 ; i < connect.length; i++) {
			// 다리 연결
			union(num, connect[i][0], connect[i][1]);
			
		}
		
		
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 1 ;i  < num.length; i++) {
			set.add(getparents(num, num[i]));
		}
		
		for(int i : set) {
			System.out.println(i+" ");
		}
		
		
	}

	public static int getparents(int num[],int a) {
		if(num[a] == a) {
			return a;
		}else {
			return num[a] = getparents(num, num[a]);
		}
	}
	
	public static void union(int num[],int a,int b) {
		a = getparents(num, a);
		b = getparents(num, b);
		
		if(a < b) {
			num[b] =a;
		}else {
			num[a] = b;
		}
		
		
	}
	
	
	
}
