package boj;

import java.io.*;
import java.util.*;

/*
https://www.acmicpc.net/problem/1039
 */
public class Boj1039 {
    static int n,m,k;
    static int result = -1;
    static boolean visited[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = ("" + n).length();
        k = Integer.parseInt(s[1]);

        visited = new boolean[1_000_001][k+1];

        bfs();

        System.out.println(result);
    }

    private static void bfs() {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(n, 0));
        visited[n][0] = true;

        while (!q.isEmpty()) {
            Pair now = q.poll();
            if (now.k == k) {
                result = Math.max(result, now.n);
                continue;
            }

            for (int i = 0; i < m-1; i++) {
                for (int j = i+1; j < m; j++) {
                    int next = swap(now.n, i, j);
                    if (next == -1 || visited[next][now.k + 1]) continue;
                    q.offer(new Pair(next, now.k+1));
                    visited[next][now.k+1] = true;
                }
            }
        }
    }

    private static int swap(int n, int i, int j) {
        String[] split = (n + "").split("");
        if (i == 0 && split[j].equals("0"))
            return -1;

        String tmp = split[i];
        split[i] = split[j];
        split[j] = tmp;

        int ret = 0;
        for (int k = 0; k < m; k++) {
            ret *= 10;
            ret += Integer.parseInt(split[k]);
        }
        return ret;
    }

    static class Pair {
        int n;
        int k;

        public Pair(int a, int b) {
            this.n = a;
            this.k = b;
        }
    }
}
