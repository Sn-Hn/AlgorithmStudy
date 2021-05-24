package boj_20210520_복습;

/*

문제집 분류
시간 제한   메모리 제한   제출   정답   맞은 사람   정답 비율
2 초   128 MB   12201   5699   4293   46.913%
문제
민오는 1번부터 N번까지 총 N개의 문제로 되어 있는 문제집을 풀려고 한다.
문제는 난이도 순서로 출제되어 있다. 즉 1번 문제가 가장 쉬운 문제이고 N번 문제가 가장 어려운 문제가 된다.

어떤 문제부터 풀까 고민하면서 문제를 훑어보던 민오는, 몇몇 문제들 사이에는 '먼저 푸는 것이 좋은 문제'가 있다는 것을 알게 되었다.
예를 들어 1번 문제를 풀고 나면 4번 문제가 쉽게 풀린다거나 하는 식이다.
민오는 다음의 세 가지 조건에 따라 문제를 풀 순서를 정하기로 하였다.

N개의 문제는 모두 풀어야 한다.
먼저 푸는 것이 좋은 문제가 있는 문제는, 먼저 푸는 것이 좋은 문제를 반드시 먼저 풀어야 한다.
가능하면 쉬운 문제부터 풀어야 한다.
예를 들어서 네 개의 문제가 있다고 하자.
4번 문제는 2번 문제보다 먼저 푸는 것이 좋고, 3번 문제는 1번 문제보다 먼저 푸는 것이 좋다고 하자.
만일 4-3-2-1의 순서로 문제를 풀게 되면 조건 1과 조건 2를 만족한다.
하지만 조건 3을 만족하지 않는다.
4보다 3을 충분히 먼저 풀 수 있기 때문이다.
따라서 조건 3을 만족하는 문제를 풀 순서는 3-1-4-2가 된다.

문제의 개수와 먼저 푸는 것이 좋은 문제에 대한 정보가 주어졌을 때, 주어진 조건을 만족하면서 민오가 풀 문제의 순서를 결정해 주는 프로그램을 작성하시오.

입력
첫째 줄에 문제의 수 N(1 ≤ N ≤ 32,000)과 먼저 푸는 것이 좋은 문제에 대한 정보의 개수 M(1 ≤ M ≤ 100,000)이 주어진다.
둘째 줄부터 M개의 줄에 걸쳐 두 정수의 순서쌍 A,B가 빈칸을 사이에 두고 주어진다.
이는 A번 문제는 B번 문제보다 먼저 푸는 것이 좋다는 의미이다.

항상 문제를 모두 풀 수 있는 경우만 입력으로 주어진다.

출력
첫째 줄에 문제 번호를 나타내는 1 이상 N 이하의 정수들을 민오가 풀어야 하는 순서대로 빈칸을 사이에 두고 출력한다.

예제 입력 1
4 2
4 2
3 1
예제 출력 1
3 1 4 2

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 위상정렬은 방향성이 있으며 사이클이 없는 그래프여야 한다.
public class 문제집_1766 {
    private static int N;
    private static int M;
    private static int[] inDegree;
    private static List<List<Integer>> graph = new ArrayList<>();
    private static List<Integer> result = new ArrayList<>();
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inDegree = new int[N + 1];

        initGraph();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            inDegree[b]++;
        }

        topologicySort();

//        for (int answer : result) {
//            System.out.print(answer + " ");
//        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void topologicySort() {
        PriorityQueue<Integer> q = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if(inDegree[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append(" ");
//            result.add(now);

            for (int next : graph.get(now)) {
                inDegree[next]--;

                if(inDegree[next] == 0) {
                    q.add(next);
                }
            }
        }
    }

    private static void initGraph() {
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
    }
}