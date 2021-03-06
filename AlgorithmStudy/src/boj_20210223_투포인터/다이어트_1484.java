package boj_20210223;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 다이어트_1484 {
	private static int G;
	private static int w[];
	private static int max;
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());
		max = G/2 + 1;
	
		w = new int[max+1];
		
		for(int i = 0; i < max+1; i++) {
			w[i] = i+1;
		}
		
		solve();
		if(sb.toString().isEmpty()) System.out.println(-1);
		else System.out.println(sb);
		
		br.close();
	}
	private static void solve() {
		int start = 0;
		int end = 0;
		int g = 0;
		while(end <= max) {
			g = w[end]*w[end]-w[start]*w[start];
			if(g < G) {
				end++;
			}else if(g > G) {
				if(end-start == 1) {
					break;
				}
				start++;
			}else {
				sb.append(w[end] + "\n");
				end++;
			}
		}
	}
}
