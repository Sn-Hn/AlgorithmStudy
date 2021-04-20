package boj;

import java.io.*;
import java.util.*;

/*
https://www.acmicpc.net/problem/3197
 */
public class Boj3197 {
    static int r, c;
    static char[][] map;
    static boolean[][] swanVisited;
    static Pair[] swans = new Pair[2];
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static int day = 0;
    static Queue<Pair> swanQ = new LinkedList<>();
    static Queue<Pair> waterQ = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        r = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);
        map = new char[r][c];
        swanVisited = new boolean[r][c];


        int idx = 0;
        for (int i = 0; i < r; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < c; j++) {
                map[i][j] = input[j].charAt(0);
                if (map[i][j] == 'L') {
                    swans[idx++] = new Pair(i, j);
                    map[i][j] = '.';
                }
                if (map[i][j] == '.') {
                    waterQ.offer(new Pair(i, j));
                }
            }
        }

        // 출발 백조
        swanVisited[swans[0].y][swans[0].x] = true;
        swanQ.offer(swans[0]);

        loop: while (!isMoveSwan()) {
            meltArea();
            day++;
        }

        System.out.println(day);
    }

    private static boolean isMoveSwan() {
        Queue<Pair> nextQ = new LinkedList<>();
        while (!swanQ.isEmpty()) {
            Pair now = swanQ.poll();

            boolean flag = false;
            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                // 범위 Out
                if (ny < 0 || nx < 0 || ny >= r || nx >= c) continue;
                // 백조 만남
                if (ny == swans[1].y && nx == swans[1].x)  {
                    return true;
                }
                // 빙판 만남 -> 다음에 탐색 할 시작점임
                if (map[ny][nx] == 'X') {
                    if (!flag) {
                        nextQ.offer(now);
                        flag = true;
                    }
                    continue;
                }
                // 방문함
                if (map[ny][nx] == 'O') continue;
                map[ny][nx] = 'O';
                swanQ.offer(new Pair(ny, nx));
            }
        }
        swanQ = nextQ;
        return false;
    }

    private static void meltArea() {
        int size = waterQ.size();
        for (int i = 0; i < size; i++) {
            Pair now = waterQ.poll();

            for (int j = 0; j < 4; j++) {
                int ny = now.y + dy[j];
                int nx = now.x + dx[j];
                // 범위 Out
                if (ny < 0 || nx < 0 || ny >= r || nx >= c) continue;
                if (map[ny][nx] != 'X') continue;
                map[ny][nx] = '.';
                waterQ.offer(new Pair(ny, nx));
            }
        }
    }

    static class Pair {
        int y;
        int x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "y=" + y +
                    ", x=" + x +
                    '}';
        }
    }

    private static void print() {
        System.out.println();
        for (char[] chars : map) {
            for (char a : chars) {
                System.out.print(a);
            }
            System.out.println();
        }
    }
}