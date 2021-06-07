package boj_20210608;
/*

종이 조각 분류
시간 제한   메모리 제한   제출   정답   맞은 사람   정답 비율
2 초   512 MB   2855   1530   1118   54.697%
문제
영선이는 숫자가 쓰여 있는 직사각형 종이를 가지고 있다.
종이는 1×1 크기의 정사각형 칸으로 나누어져 있고, 숫자는 각 칸에 하나씩 쓰여 있다.
행은 위에서부터 아래까지 번호가 매겨져 있고, 열은 왼쪽부터 오른쪽까지 번호가 매겨져 있다.

영선이는 직사각형을 겹치지 않는 조각으로 자르려고 한다.
각 조각은 크기가 세로나 가로 크기가 1인 직사각형 모양이다.
길이가 N인 조각은 N자리 수로 나타낼 수 있다.
가로 조각은 왼쪽부터 오른쪽까지 수를 이어 붙인 것이고, 세로 조각은 위에서부터 아래까지 수를 이어붙인 것이다.

아래 그림은 4×4 크기의 종이를 자른 한 가지 방법이다.



각 조각의 합은 493 + 7160 + 23 + 58 + 9 + 45 + 91 = 7879 이다.

종이를 적절히 잘라서 조각의 합을 최대로 하는 프로그램을 작성하시오.

입력
첫째 줄에 종이 조각의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 4)

둘째 줄부터 종이 조각이 주어진다. 각 칸에 쓰여 있는 숫자는 0부터 9까지 중 하나이다.

출력
영선이가 얻을 수 있는 점수의 최댓값을 출력한다.

예제 입력 1
2 3
123
312
예제 출력 1
435
예제 입력 2
2 2
99
11
예제 출력 2
182
예제 입력 3
4 3
001
010
111
100
예제 출력 3
1131
예제 입력 4
1 1
8
예제 출력 4
8

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 종이조각_14391 {
    private static int N;
    private static int M;
    private static int[][] paper;
    private static boolean[][] visited;
    private static int max = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        paper = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                paper[i][j] = input.charAt(j) - '0';
            }
        }

        dfs(0, 0);

        System.out.println(max);

        br.close();
    }

    private static void dfs(int row, int col) {
        if (row >= N) {
            sum();
            return;
        }

        if (col >= M) {
            dfs(row + 1, 0);
            return;
        }
        
        // 가로
        visited[row][col] = true;
        dfs(row, col + 1);

        // 세로
        visited[row][col] = false;
        dfs(row, col + 1);
    }

    private static void sum() {
        int sum = 0;
        int cur = 0;

        for (int i = 0; i < N; i++) {
            cur = 0;
            for (int j = 0; j < M; j++) {
                if(visited[i][j]) {
                    cur *= 10;
                    cur += paper[i][j];
                }else {
                    sum += cur;
                    cur = 0;
                }
            }
            sum += cur;
        }

        for (int i = 0; i < M; i++) {
            cur = 0;
            for (int j = 0; j < N; j++) {
                if(!visited[j][i]) {
                    cur *= 10;
                    cur += paper[j][i];
                }else {
                    sum += cur;
                    cur = 0;
                }
            }
            sum += cur;
        }
        max = Math.max(max, sum);
    }

    private static void printPaper() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(paper[i][j] + " ");
            }
            System.out.println();
        }
    }
}