package boj;

import java.io.*;

/*
https://www.acmicpc.net/problem/1398
 */
public class Boj1398 {
    public static void main(String[] args) throws IOException {
        int[] dp = new int[101];
        for (int i = 1; i < 101; i++) {
            dp[i] = Math.min(i, dp[i - 1] + 1);
            if (i >= 10) dp[i] = Math.min(dp[i], dp[i - 10] + 1);
            if (i >= 25) dp[i] = Math.min(dp[i], dp[i - 25] + 1);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            long price = Long.parseLong(br.readLine());
            int cnt = 0;
            while (price != 0) {
                cnt += dp[(int)(price % 100)];
                price /= 100;
            }
            System.out.println(cnt);
        }
    }
}
