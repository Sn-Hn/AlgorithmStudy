package boj_20210608;

/*

물대기 출처다국어분류
시간 제한   메모리 제한   제출   정답   맞은 사람   정답 비율
2 초   128 MB   541   217   166   40.488%
문제
선주는 자신이 운영하는 N개의 논에 물을 대려고 한다.
물을 대는 방법은 두 가지가 있는데 하나는 직접 논에 우물을 파는 것이고 다른 하나는 이미 물을 대고 있는 다른 논으로부터 물을 끌어오는 법이다.

각각의 논에 대해 우물을 파는 비용과 논들 사이에 물을 끌어오는 비용들이 주어졌을 때 최소의 비용으로 모든 논에 물을 대는 것이 문제이다.

입력
첫 줄에는 논의 수 N(1 ≤ N ≤ 300)이 주어진다.
다음 N개의 줄에는 i번째 논에 우물을 팔 때 드는 비용 Wi(1 ≤ Wi ≤ 100,000)가 순서대로 들어온다.
다음 N개의 줄에 대해서는 각 줄에 N개의 수가 들어오는데 이는 i번째 논과 j번째 논을 연결하는데 드는 비용 Pi,j(1 ≤ Pi,j ≤ 100,000, Pi,j = Pj,i, Pi,i = 0)를 의미한다.

출력
첫 줄에 모든 논에 물을 대는데 필요한 최소비용을 출력한다.

예제 입력 1
4
5
4
4
3
0 2 2 2
2 0 3 3
2 3 0 4
2 3 4 0
예제 출력 1
9

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 최소의 비용으로 모든 논에 물을 대는 것
// -> 최소의 비용으로 모든 논을 연결시켜야 한다.
// -> 즉, 크루스칼 알고리즘을 이용할 수 있다.
public class 물대기_1368 {
    private static int N;
    private static int[] cost;
    private static int[] parent;
    private static PriorityQueue<Node> paddyTree = new PriorityQueue<>();

    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        cost = new int[N + 1];
        parent = new int[N + 1];

        initParent();
        int minCost = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(br.readLine());
            paddyTree.add(new Node(i, 0, cost[i]));
        }
        int cost = 0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                cost = Integer.parseInt(st.nextToken());
                if (i == j) {
                    continue;
                }
                paddyTree.add(new Node(i, j, cost));
            }
        }

        int minPutWater = getConnectPaddy(minIndex);

        System.out.println(minPutWater);

        br.close();
    }

    private static int getConnectPaddy(int minIndex) {
        int sum = 0;
        int count = 0;
        while(!paddyTree.isEmpty()) {
            Node node = paddyTree.poll();

            if (isCycle(node.x, node.y)) {
                continue;
            }

            count++;

            union(node.x, node.y);
            sum += node.cost;

            if (count == N) {
                return sum;
            }

        }

        return sum;
    }

    private static boolean isCycle(int a, int b) {
        return find(a) == find(b);
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) {
            parent[b] = a;
        }else {
            parent[a] = b;
        }
    }

    private static void initParent() {
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }
}