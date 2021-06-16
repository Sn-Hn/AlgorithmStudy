package study_210620;

/*

가장 먼 노드
문제 설명
n개의 노드가 있는 그래프가 있습니다. 각 노드는 1부터 n까지 번호가 적혀있습니다.
1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려고 합니다.
가장 멀리 떨어진 노드란 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드들을 의미합니다.

노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때,
1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return 하도록 solution 함수를 작성해주세요.

제한사항
노드의 개수 n은 2 이상 20,000 이하입니다.
간선은 양방향이며 총 1개 이상 50,000개 이하의 간선이 있습니다.
vertex 배열 각 행 [a, b]는 a번 노드와 b번 노드 사이에 간선이 있다는 의미입니다.
입출력 예
n   vertex   return
6   [[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]   3
입출력 예 설명
예제의 그래프를 표현하면 아래 그림과 같고, 1번 노드에서 가장 멀리 떨어진 노드는 4,5,6번 노드입니다.

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 가장먼노드 {
    private static final int INF = 1000000000;
    public static void main(String[] args) {
        int n = 6;
        int[][] vertex = {
                {3, 6},
                {4, 3},
                {3, 2},
                {1, 3},
                {1, 2},
                {2, 4},
                {5, 2}
        };

        // return 3

        System.out.println(solution(n, vertex));

    }

    public static int solution(int n, int[][] edge) {
        int answer = 0;
        List<Node>[] lists = new List[n + 1];
        initLists(lists, edge);

        int result[] = dijkstra(edge, lists);

        print(result);

        answer = getMaxCount(result);

        return answer;
    }

    private static int[] dijkstra(int[][] edge, List<Node>[] lists) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        int[] distance = new int[edge.length];
        boolean[] visited = new boolean[edge.length];
        Arrays.fill(distance, INF);
        distance[1] = 0;
        visited[1] = true;
        q.add(new Node(1, 0));

        while (!q.isEmpty()) {
            Node n = q.poll();
            int index = n.index;

            if (distance[index] < n.distance) {
                continue;
            }

            for (Node node : lists[index]) {
                if (visited[node.index]) {
                    continue;
                }
                visited[node.index] = true;
                if (distance[node.index] > distance[index] + node.distance) {
                    distance[node.index] = distance[index] + node.distance;
                    q.add(new Node(node.index, distance[node.index]));
                }
            }
        }

        return distance;
    }

    // 맞왜틀
    // if 조건에 result[i] != INF를 추가 안해줘서 계속 틀림
    private static int getMaxCount(int[] result) {
        int count = 0;
        int max = -1;

        for (int i = 1; i < result.length; i++) {
            if (max < result[i] && result[i] != INF) {
                max = result[i];
                count = 1;
            }else if (max == result[i]) {
                count++;
            }
        }

        return count;
    }

    private static void initLists(List<Node>[] lists, int[][] edge) {
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];

            lists[a].add(new Node(b, 1));
            lists[b].add(new Node(a, 1));
        }
    }

    private static void print(int[] distance) {
        for (int i = 1; i < distance.length; i++) {
            System.out.print(distance[i] + " ");
        }

        System.out.println();
    }

    private static class Node implements Comparable<Node> {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return distance - o.distance;
        }
    }
}