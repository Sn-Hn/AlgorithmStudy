package algostudy11_BOJ_10216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		int enemy[][];
		int node[];
		for(int i = 0 ;i < T;i++) {
			int N = Integer.parseInt(br.readLine());
			enemy = new int[N][3];
			node = new int[N];
			for(int j = 0 ; j < N ; j++) {
				node[j] = j;
				String[] tmp = br.readLine().split(" ");
				for(int k = 0 ; k < tmp.length; k++) {
					enemy[j][k]= Integer.parseInt(tmp[k]);
				}
			}
			
			int ans = N;

			for(int j = 0 ; j < N ; j++) {
				for(int k = j+1 ; k <N; k++) {
					//두 점 사이의 거리가 반지름 (R) 보다 작거나 같으면 범위가 겹치므로 union
					if(Math.sqrt(  Math.pow(enemy[j][0]-enemy[k][0], 2) + Math.pow(enemy[j][1]-enemy[k][1], 2) ) <= enemy[j][2]+enemy[k][2])  {
						if(getparent(node, j) != getparent(node, k)) {
							union(node, j,k);
							ans--;
						}
					}
					
					
				}
			}
			
			System.out.println(ans);
			
			
			
			
			
			
		}// for end;
	}
	
	
	public static int getparent(int node[],int a) {
		if(node[a] == a) {
			return a;
		}else {
			return getparent(node,node[a]);
		}
	}
	
	public static void union(int node[],int a,int b) {
		a = getparent(node, a);
		b = getparent(node, b);
		
		if(a< b) {
			node[b] = a;
		}else {
			node[a] = b;
		}
		
		
	}
	
	
	
	
	
	
}
