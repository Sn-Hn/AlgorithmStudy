package boj_20210613;

/*

뉴스 전하기 실패분류
시간 제한   메모리 제한   제출   정답   맞은 사람   정답 비율
2 초   128 MB   1768   656   544   38.885%
문제
민식이는 회사의 매니저이다.
그리고, 민식이는 회사의 중요한 뉴스를 모든 직원에게 빠르게 전달하려고 한다.
민식이의 회사는 트리 구조이다.
모든 직원은 정확하게 한 명의 직속 상사가 있다.
자기자신은 그들 자기 자신의 직접 또는 간접 상사가 아니고, 모든 직원은 민식이의 직접 또는 간접적인 부하이다.

민식이는 일단 자기 자신의 직속 부하에게 한 번에 한 사람씩 전화를 한다.
뉴스를 들은 후에, 각 부하는 그의 직속 부하에게 한 번에 한 사람씩 전화를 한다.
이 것은 모든 직원이 뉴스를 들을 때 까지 계속된다.
모든 사람은 자신의 직속 부하에게만 전화를 걸 수 있고, 전화는 정확하게 1분 걸린다.
이때 모든 직원이 소식을 듣는데 걸리는 시간의 최솟값을 구하는 프로그램을 작성하시오.

오민식의 사원 번호는 0이고, 다른 사원의 번호는 1부터 시작한다.

입력
첫째 줄에 직원의 수 N이 주어진다.
둘째 줄에는 0번 직원부터 그들의 상사의 번호가 주어진다.
반드시 0번 직원 (오민식)의 상사는 -1이고, -1은 상사가 없다는 뜻이다.
N은 50보다 작거나 같은 자연수이다.

출력
첫째 줄에 모든 소식을 전하는데 걸리는 시간의 최솟값을 출력한다.

예제 입력 1
3
-1 0 0
예제 출력 1
2

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1135
public class 뉴스정하기_1135 {
    private static int N;
    private static List<Node>[] company;

    private static class Node implements Comparable<Node> {
        int child;
        int time;

        public Node(int child, int time) {
            this.child = child;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return o.time - time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        company = new ArrayList[N + 1];
        initCompany();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int boss = Integer.parseInt(st.nextToken());
            if (boss == -1) {
                continue;
            }
            company[boss].add(new Node(i, 0));
        }

        System.out.println(getCallTime(0));

        br.close();
    }

    private static int getCallTime(int boss) {
        int maxTime = 0;

        for (int i = 0; i < company[boss].size(); i++) {
            int child = company[boss].get(i).child;
            company[boss].get(i).time = 1 + getCallTime(child);
        }

        Collections.sort(company[boss]);

        for (int i = 0; i < company[boss].size(); i++) {
            company[boss].get(i).time += i;
            maxTime = Math.max(maxTime, company[boss].get(i).time);
        }

        return maxTime;
    }

    private static void initCompany() {
        for (int i = 0; i <= N; i++) {
            company[i] = new ArrayList<>();
        }
    }
}