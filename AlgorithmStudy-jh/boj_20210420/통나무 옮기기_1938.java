package boj;

import java.io.*;
import java.util.*;

/*
https://www.acmicpc.net/problem/1938
 */
public class Boj1938 {
    static int n;
    static String[][] map;
    static boolean[][][] visited;
    // U, D, L, R, Turn 용 네방향 대각선
    static int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new String[n][n];
        visited = new boolean[n][n][2];
        int startCnt, endCnt;
        startCnt = endCnt = 0;
        Pair start = new Pair();
        Pair end = new Pair();
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                map[i][j] = split[j];
                if (map[i][j].equals("B")) {
                    startCnt++;
                    // 통나무 처음위치 중심점 위치 마킹
                    markLog(startCnt, start, i, j);
                }
                if (map[i][j].equals("E")) {
                    endCnt++;
                    // 통나무 최종위치 중심점 위치 마킹
                    markLog(endCnt, end, i, j);
                }
            }
        }

        System.out.println(bfs(start, end));
    }

    private static int bfs(Pair start, Pair end) {
        int ret = 0;
        Queue<Pair> q = new LinkedList<>();
        q.offer(start);
        visited[start.y][start.x][start.direction] = true;

        while (!q.isEmpty()) {
            Pair now = q.poll();

            // 이동한 통나무가 최종위치랑 같다면 break
            if (now.equals(end)) {
                ret = now.cnt;
                break;
            }

            // 방향 이동
            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                if (!isMoveImpossible(ny, nx, now.direction)) continue;
                visited[ny][nx][now.direction] = true;
                q.offer(new Pair(ny, nx, now.direction, now.cnt + 1));
            }

            // 회전
            int nd = now.direction==0?1:0;
            if (!isTurnImpossible(now.y, now.x, nd)) continue;
            visited[now.y][now.x][nd] = true;
            q.offer(new Pair(now.y, now.x, nd, now.cnt + 1));
        }
        return ret;
    }

    private static boolean isTurnImpossible(int y, int x, int d) {
        if (visited[y][x][d]) return false;

        for (int i = 0; i < 8; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || nx >= n || ny >= n) return false;
            if (map[ny][nx].equals("1")) return false;
        }
        return true;
    }

    private static boolean isMoveImpossible(int ny, int nx, int direction) {
        if (ny < 0 || nx < 0 || nx >= n || ny >= n) return false;
        if (visited[ny][nx][direction] || map[ny][nx].equals("1")) return false;

        if (direction == 0) { // 가로
            int left = nx - 1;
            int right = nx + 1;
            if (left < 0 || right >= n ||
                    map[ny][left].equals("1") || map[ny][right].equals("1"))
                return false;
        } else { // 세로
            int up = ny - 1;
            int down = ny + 1;
            if (up < 0 || down >= n ||
                    map[up][nx].equals("1") || map[down][nx].equals("1"))
                return false;
        }
        return true;
    }

    private static void markLog(int cnt, Pair start, int i, int j) {
        if (start.y == i)
            start.direction = 0; // 가로
        else
            start.direction = 1; // 세로

        if (cnt == 1) start.y = i;

        if (cnt == 2) {
            start.y = i;
            start.x = j;
        }
    }

    static class Pair {
        int y;
        int x;
        int direction; //0: 가로, 1: 세로
        int cnt;

        public Pair() {
        }

        public Pair(int y, int x, int direction, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.direction = direction;
        }

        @Override
        public boolean equals(Object o) {
            Pair pair = (Pair) o;
            return y == pair.y && x == pair.x && direction == pair.direction;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "y=" + y +
                    ", x=" + x +
                    ", cnt=" + cnt +
                    ", direction=" + direction +
                    '}';
        }
    }
}