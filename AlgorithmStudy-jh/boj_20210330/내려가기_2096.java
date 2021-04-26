package boj;

import java.io.*;

/*
https://www.acmicpc.net/problem/2096
 */
public class Boj2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // dp[i][j] = [i][0] : i 번째에 가능한 수중 제일 큰수, [i][1] : i 번째에 가능한 수중 제일 작은수
        int[][] dp = new int[3][2];
        String[] split = br.readLine().split(" ");

        // 초기 값 세팅
        dp[0][0] = dp[0][1] = Integer.parseInt(split[0]);
        dp[1][0] = dp[1][1] = Integer.parseInt(split[1]);
        dp[2][0] = dp[2][1] = Integer.parseInt(split[2]);
        for (int i = 1; i < n; i++) {
            split = br.readLine().split(" ");
            int nowA = Integer.parseInt(split[0]);
            int nowB = Integer.parseInt(split[1]);
            int nowC = Integer.parseInt(split[2]);
            int preA = dp[0][0];
            int preB = dp[1][0];
            int preC = dp[2][0];
            dp[0][0] = Math.max(preA + nowA, preB + nowA);
            dp[1][0] = Math.max(preA + nowB, Math.max(preB + nowB, preC + nowB));
            dp[2][0] = Math.max(preB + nowC, preC + nowC);

            preA = dp[0][1];
            preB = dp[1][1];
            preC = dp[2][1];
            dp[0][1] = Math.min(preA + nowA, preB + nowA);
            dp[1][1] = Math.min(preA + nowB, Math.min(preB + nowB, preC + nowB));
            dp[2][1] = Math.min(preB + nowC, preC + nowC);
        }
        int max = Math.max(dp[0][0], Math.max(dp[1][0], dp[2][0]));
        int min = Math.min(dp[0][1], Math.min(dp[1][1], dp[2][1]));
        System.out.print(max + " " + min);
    }
}
