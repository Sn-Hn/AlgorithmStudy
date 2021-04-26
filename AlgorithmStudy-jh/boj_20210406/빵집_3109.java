package boj;

import java.io.*;

/*
https://www.acmicpc.net/problem/3109
 */
public class Boj3109 {
    static int n,m;
    static char[][] map;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            s = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = s[j].charAt(0);
            }
        }

        for (int i = 0; i < n; i++) {
            dfs(i, 0);
        }

        System.out.println(cnt);
    }

    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};
    private static boolean dfs(int i, int j) {
        if (j == m-1) {
            cnt++;
            return true;
        }
        for (int k = 0; k < 3; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];
            if (nx < 0 || nx >= n || ny >= m) continue;
            if (map[nx][ny] != '.') continue;
            map[i][j] = 'x';
            if (dfs(nx, ny)) return true;
        }
        return false;
    }
}
