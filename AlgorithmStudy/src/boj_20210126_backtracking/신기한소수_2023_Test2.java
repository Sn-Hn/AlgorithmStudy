package boj_20210126_backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 신기한소수_2023_Test2 {
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		findPrimeNumber(0, 0);

		br.close();
	}

	private static void findPrimeNumber(int depth, int ans) {
		if (depth == N) {
			System.out.println(ans);
			return;
		}
		ans *= 10;
		for (int i = 0; i < 10; i++) {
			ans += i;
			if (findPN(ans)) {
				findPrimeNumber(depth + 1, ans);
			}
			ans -= i;
		}

	}

	private static boolean findPN(int n) {
		if (n == 0 || n == 1)
			return false;
		if (n == 2)
			return true;

		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0)
				return false;
		}

		return true;
	}
}
