package boj;

import java.io.*;

/*
https://www.acmicpc.net/problem/15559
 */
public class Boj15559 {
    static int n, m;
    // N, S, W, E/ 상 하 좌 우
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static char[][] map;
    static int[][] levels;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        map = new char[n][m];
        levels = new int[n][m];
        for (int i = 0; i < n; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = input[j].charAt(0);
            }
        }

        int level = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (levels[i][j] != 0) continue;
                dfs(i, j, ++level);
            }
        }
        System.out.println(result);
    }

    // 같은 레벨에서 사이클이 발생하면 stop && result++
    private static void dfs(int i, int j, int level) {
        int moveIndex = getMoveIndex(map[i][j]);
        int ny = i + dy[moveIndex];
        int nx = j + dx[moveIndex];
        if (levels[ny][nx] == 0) {
            levels[ny][nx] = level;
            dfs(ny, nx, level);
        } else if (levels[ny][nx] == level) {
            result++;
        }
    }

    private static int getMoveIndex(char moveRule) {
        if (moveRule == 'N')
            return 0;
        else if (moveRule == 'S')
            return 1;
        else if (moveRule == 'W')
            return 2;
        else
            return 3;
    }
}
