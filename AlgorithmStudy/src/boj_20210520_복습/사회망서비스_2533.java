package boj_20210520_복습;
/*

사회망 서비스(SNS) 출처분류
시간 제한   메모리 제한   제출   정답   맞은 사람   정답 비율
3 초   256 MB   9343   3399   2168   34.566%
문제
페이스북, 트위터, 카카오톡과 같은 사회망 서비스(SNS)가 널리 사용됨에 따라, 사회망을 통하여 사람들이 어떻게 새로운 아이디어를 받아들이게 되는가를 이해하는 문제가 중요해졌다.
사회망에서 사람들의 친구 관계는 그래프로 표현할 수 있는데,  이 그래프에서 사람은 정점으로 표현되고, 두 정점을 잇는 에지는 두 정점으로 표현되는 두 사람이 서로 친구 관계임을 표현한다.

예를 들어, 철수와 영희, 철수와 만수, 영희와 순희가 서로 친구 관계라면 이를 표현하는 친구 관계 그래프는 다음과 같다.



친구 관계 그래프를 이용하면 사회망 서비스에서 어떤 새로운 아이디어가 전파되는 과정을 이해하는데 도움을 줄 수 있다.
어떤 새로운 아이디어를 먼저 받아들인 사람을 얼리 아답터(early adaptor)라고 하는데, 사회망 서비스에 속한 사람들은 얼리 아답터이거나 얼리 아답터가 아니다.
얼리 아답터가 아닌 사람들은 자신의 모든 친구들이 얼리 아답터일 때만 이 아이디어를 받아들인다.

어떤 아이디어를 사회망 서비스에서 퍼뜨리고자 할 때, 가능한 한 최소의 수의 얼리 아답터를 확보하여 모든 사람이 이 아이디어를 받아들이게 하는  문제는 매우 중요하다.

일반적인 그래프에서 이 문제를 푸는 것이 매우 어렵다는 것이 알려져 있기 때문에, 친구 관계 그래프가 트리인 경우,
즉 모든 두 정점 사이에 이들을 잇는 경로가 존재하면서 사이클이 존재하지 않는 경우만 고려한다.

예를 들어, 8명의 사람으로 이루어진 다음 친구 관계 트리를 생각해보자.
2, 3, 4번 노드가 표현하는 사람들이 얼리 아답터라면, 얼리 아답터가 아닌 사람들은 자신의 모든 친구가 얼리 아답터이기 때문에 새로운 아이디어를 받아들인다.



친구 관계 트리가 주어졌을 때, 모든 개인이 새로운 아이디어를 수용하기 위하여 필요한 최소 얼리 어답터의 수를 구하는 프로그램을 작성하시오.

입력
첫 번째 줄에는 친구 관계 트리의 정점 개수 N이 주어진다. 단, 2 <= N <= 1,000,000이며, 각 정점은 1부터 N까지 일련번호로 표현된다.
두 번째 줄부터 N-1개의 줄에는 각 줄마다 친구 관계 트리의 에지 (u, v)를 나타내는 두 정수 u 와 v가 하나의 빈칸을 사이에 두고 주어진다.

출력
주어진 친구 관계 그래프에서 아이디어를 전파하는데 필요한 얼리 아답터의 최소 수를 하나의 정수로 출력한다.

예제 입력 1
8
1 2
1 3
1 4
2 5
2 6
4 7
4 8
예제 출력 1
3

*/

// https://www.acmicpc.net/problem/2533
// boj_20210427_트리 - 우수마을_1949 문제와 비슷

// Tree DP
// 이 문제는 얼라이답터가 아닌 사람들은 자신이 연결된 모든 사람이 얼라이답터여야 한다.
// 얼라이답터일 경우, 얼라이답터가 아닐 경우 두 가지 경우만 찾으면 되는 쉬운 DP문제
// 1) 얼라이답터일 경우 다음은 얼라이답터가 나올 수도, 나오지 않을 수도 있다.
// 2) 얼라이답터가 아닐 경우 친구는 얼라이답터이여만 한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 사회망서비스_2533 {
    private static int N;
    private static int[][] dp;
    private static List<Integer> tree[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        dp = new int[N + 1][2];

        initTree();

        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            
            // 트리는 양방향 노선이기 때문에 서로 연결
            tree[parent].add(child);
            tree[child].add(parent);
        }

        dfs(1, -1);

        System.out.println(Math.min(dp[1][1], dp[1][0]));

        br.close();
    }

    // 최소 얼라이답터의 수
    private static void dfs(int cur, int parent) {
        // 최소 얼라이답터의 수를 구하는 것이기 때문에
        // 얼라이답터가 아닌 사람은 0, 얼라이답터인 사람은 1로 초기화
        dp[cur][0] = 0;         // 얼라이답터가 아닌 사람
        dp[cur][1] = 1;         // 얼라이답터인 사람

        for (int child : tree[cur]) {
            // 자식과 부모가 같지 않아야 한다
            // 재귀를 통해 부모 -> 자식으로 들어갔는데
            // 자식 -> 부모로 다시 올라오게 되면 (부모 -> 자식 -> 부모 -> 자식 ...)
            // 위와 같이 무한루프가 발생하기 때문에 부모와 자식이 같지 않아야 한다.
            if (child != parent) {
                // 자식이 현재 노드가 되고 현재 노드가 부모 노드가 된다.
                dfs(child, cur);

                // 현재 노드가 얼라이답터가 아니라면 자식 노드는 얼라이답터이여만 한다.
                dp[cur][0] += dp[child][1];

                // 현재 노드가 얼라이답터라면 자식 노드는 얼라이답터 혹은 얼라이답터가 아닐 수도 있다.
                dp[cur][1] += Math.min(dp[child][0], dp[child][1]);
            }
        }
    }

    private static void initTree() {
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
    }
}