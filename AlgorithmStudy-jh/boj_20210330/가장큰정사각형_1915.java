package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://www.acmicpc.net/problem/1915
 */
public class Boj1915 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int[][] arr = new int[n+1][m+1];
        int result = 0;
        for (int i = 1; i <= n; i++) {
            split = br.readLine().split("");
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Integer.parseInt(split[j-1]);
                if (arr[i][j]==0) continue;
                arr[i][j] = Math.min(arr[i][j-1], Math.min(arr[i-1][j-1],arr[i-1][j])) + 1;
                result = Math.max(result, arr[i][j]);
            }
        }
        System.out.println(result * result);
    }
}
