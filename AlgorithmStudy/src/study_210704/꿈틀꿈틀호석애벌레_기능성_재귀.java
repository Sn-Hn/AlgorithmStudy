package study_210704;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 꿈틀꿈틀호석애벌레_기능성_재귀 {
	private static int N;
	private static int K;
	private static int result = 0;
	private static int[] branch;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		branch = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			branch[i] = Integer.parseInt(st.nextToken());
		}
		
		addEnergy(0, 0, 0);
		
		System.out.println(result);
		
		
		br.close();
	}
	
	private static void addEnergy(int depth, int energy, int sum) {
		if (sum >= K) {
			energy = energy + sum - K;
			sum = 0;
		}
		
		if (depth == N) {
			result = Math.max(result, energy);
			return;
		}
		
		
		addEnergy(depth + 1, energy, sum + branch[depth]);
		
		addEnergy(depth + 1, energy, 0);
		
	}
}
