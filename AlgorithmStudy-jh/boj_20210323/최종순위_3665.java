package boj;

import java.io.*;
import java.util.*;

/*
https://www.acmicpc.net/problem/3665
 */
public class Boj3665 {
    static int n, m;
    static int[] inDegree, t;
    static int[][] graph;
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        int r = Integer.parseInt(br.readLine());
        for (int i = 0; i < r; i++) {
            solution();
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        graph = new int[n+1][n+1];
        inDegree = new int[n+1];
        t = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 등수에 맞는 팀 마킹 : t[0] = 1등, t[1] = 2등 ... t[n-1] = n등
        for (int i = 0; i < n; i++) {
            t[i] = Integer.parseInt(st.nextToken());
        }

        // 등수에 맞춰서 그래프 연결 정보, 진입 차수 세팅
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                inDegree[t[j]]++;
                graph[t[i]][t[j]] = 1;
            }
        }

        m = Integer.parseInt(br.readLine());
        // 상대적인 등수 바꾸기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (graph[a][b] == 1) {
                graph[a][b] = 0;
                graph[b][a] = 1;
                inDegree[b]--;
                inDegree[a]++;
            } else {
                graph[a][b] = 1;
                graph[b][a] = 0;
                inDegree[b]++;
                inDegree[a]--;
            }
        }

        List<Integer> result = tplSort();

        if (result.size() == n) {
            for (int i : result) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
        } else {
            sb.append("IMPOSSIBLE\n");
        }
    }

    private static List<Integer> tplSort() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < n+1; i++) {
            if (inDegree[i] == 0) q.offer(i);
        }

        List<Integer> result = new ArrayList<>();
        while (!q.isEmpty()) {
            int now = q.poll();
            result.add(now);
            for (int i = 1; i < n+1; i++) {
                if (graph[now][i] == 0) continue;
                inDegree[i]--;
                if (inDegree[i] == 0) q.offer(i);
            }
        }
        return result;
    }
}
