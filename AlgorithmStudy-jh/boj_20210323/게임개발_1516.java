package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/1516
 */
public class Boj1516 {
    static int n;
    static int[] inDegree, times, result;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        inDegree = new int[n+1];
        times = new int[n+1];
        result = new int[n+1];
        // 그래프 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 간선 정보, 노드 진입 차수 세팅
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            times[i] = result[i] = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int pre = Integer.parseInt(st.nextToken());
                if (pre == -1) break;
                graph.get(pre).add(i);
                inDegree[i]++;
            }
        }

        tplSort();

        printResult();
    }

    private static void tplSort() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph.get(now)) {
                result[next] = Math.max(result[next], times[next] + result[now]);
                inDegree[next]--;

                if (inDegree[next] == 0) q.offer(next);
            }
        }
    }

    private static void printResult() {
        for (int i = 1; i <= n; i++) {
            System.out.println(result[i]);
        }
    }
}
