package boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
https://www.acmicpc.net/problem/2056
 */
public class Boj2056 {
    static int n;
    static int[] inDegree, times, result;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        inDegree = new int[n+1];
        times = new int[n+1];
        result = new int[n+1];
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 관계, 진입 차수 세팅
        for (int i = 1; i < n + 1; i++) {
            int time = sc.nextInt();
            int count = sc.nextInt();
            times[i] = result[i] = time;
            for (int j = 0; j < count; j++) {
                int beforeNumber = sc.nextInt();
                graph.get(beforeNumber).add(i);
                inDegree[i]++;
            }
        }

        // 위상정렬
        tplSort();

        // 결과 출력
        printResult();
    }

    private static void tplSort() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i < n+1; i++) {
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
        int max = 0;
        for (int i : result) {
            max = Math.max(max, i);
        }

        System.out.println(max);
    }
}
