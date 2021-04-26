package boj;

import java.io.*;

/*
https://www.acmicpc.net/problem/5557
 */
public class Boj5557 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // dp[i][j] : i 번째 연산(단계)에서 j 값이 나오는 경우의 수
        long[][] dp = new long[n+1][21];
        String[] input = br.readLine().split(" ");

        dp[0][Integer.parseInt(input[0])] = 1;
        for (int i = 1; i < n; i++) {
            dp(dp, input[i], i);
        }
        int resultNumber = Integer.parseInt(input[n-1]);
        System.out.println(dp[n-2][resultNumber]);
    }

    private static void dp(long[][] dp, String s, int i) {
        int now = Integer.parseInt(s);
        for (int pre = 0; pre < 21; pre++) {
            if (dp[i-1][pre] == 0) continue;
            if (pre + now <= 20)
                dp[i][pre + now] += dp[i-1][pre];
            if (pre - now >= 0)
                dp[i][pre - now] += dp[i-1][pre];
        }
    }
}
