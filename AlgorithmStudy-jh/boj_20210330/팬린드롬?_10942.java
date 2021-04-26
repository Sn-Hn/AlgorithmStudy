package boj;

import java.io.*;

/*
https://www.acmicpc.net/problem/10942
 */
public class Boj10942 {
    static int n;
    static int[] input;
    static boolean[][] dp;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        input = new int[n+1];
        // dp[i][j] : i 에서 j 까지가 팰린드롬인지 여부(true/false)
        dp = new boolean[n+1][n+1];
        String[] s = br.readLine().split(" ");

        // 초기값 세팅
        for (int i = 1; i <= n; i++) {
            input[i] = Integer.parseInt(s[i-1]);
        }

        memoIsPalindrome();

        // 결과 값 출력
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            String[] question = br.readLine().split(" ");
            int start = Integer.parseInt(question[0]);
            int end = Integer.parseInt(question[1]);

            if (dp[start][end]) sb.append(1).append("\n");
            else sb.append(0).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void memoIsPalindrome() {
        // 길이가 1인 경우
        for(int i = 1; i <= n; i++) {
            dp[i][i] = true;
            // 길이가 2인 경우
            if (input[i] == input[i-1])
                dp[i - 1][i] = true;
        }
        // 길이가 3 이상인 경우 : 처음과 끝이 같고, 그 중간영역이 팰린드롬이면 전체가 팰린드롬
        for (int len = 3; len <= n; len++) {
            for (int startNum = 1; startNum <= n - len + 1; startNum++) {
                int endNum = startNum + len - 1;
                if (input[startNum] == input[endNum] && dp[startNum + 1][endNum - 1])
                    dp[startNum][endNum] = true;
            }
        }
    }
}
