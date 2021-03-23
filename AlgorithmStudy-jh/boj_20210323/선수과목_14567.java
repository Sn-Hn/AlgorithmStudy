package boj;

import java.util.*;

/*
https://www.acmicpc.net/problem/14567
 */
public class Boj14567 {
    static int n, m;
    static int[] inDegree, semester; // 진입차수, 학기 저장 배열
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        inDegree = new int[n+1];
        semester = new int[n+1];

        // 그래프 초기화
        for (int i = 0; i < n+1; i++) {
            graph.add(new ArrayList<>());
            semester[i] = 1; // 최소 이수 학기 1학기 시작
        }

        // 방향 그래프 연결
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            inDegree[b]++; // indegree 배열 : 이전에 들어야 할 과목 수만큼 증가됨
        }

        // 위상정렬
        topologicalSort();

        // 결과 답 출력
        printAnswer();
    }

    private static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i < n+1; i++) {
            if (inDegree[i] == 0) q.add(i);
        }


        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph.get(now)) {
                inDegree[next]--;
                semester[next] = semester[now] + 1;

                if (inDegree[next] == 0) q.add(next);
            }
        }
    }

    private static void printAnswer() {
        for (int i = 1; i < n+1; i++) {
            System.out.print(semester[i] + " ");
        }
    }
}
