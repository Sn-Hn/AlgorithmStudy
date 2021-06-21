package study_210620;

/*

공주님의 정원 출처
시간 제한   메모리 제한   제출   정답   맞은 사람   정답 비율
1 초   192 MB   6723   1528   1058   23.600%
문제
오늘은 공주님이 태어난 경사스러운 날이다. 왕은 이 날을 기념하기 위해 늘 꽃이 피어있는 작은 정원을 만들기로 결정했다.

총 N개의 꽃이 있는 데, 꽃은 모두 같은 해에 피어서 같은 해에 진다.
하나의 꽃은 피는 날과 지는 날이 정해져 있다.
예를 들어, 5월 8일 피어서 6월 13일 지는 꽃은 5월 8일부터 6월 12일까지는 꽃이 피어 있고,
6월 13일을 포함하여 이후로는 꽃을 볼 수 없다는 의미이다. (올해는 4, 6, 9, 11월은 30일까지 있고, 1, 3, 5, 7, 8, 10, 12월은 31일까지 있으며, 2월은 28일까지만 있다.)

이러한 N개의 꽃들 중에서 다음의 두 조건을 만족하는 꽃들을 선택하고 싶다.

공주가 가장 좋아하는 계절인 3월 1일부터 11월 30일까지 매일 꽃이 한 가지 이상 피어 있도록 한다.
정원이 넓지 않으므로 정원에 심는 꽃들의 수를 가능한 적게 한다.
N개의 꽃들 중에서 위의 두 조건을 만족하는,
즉 3월 1일부터 11월 30일까지 매일 꽃이 한 가지 이상 피어 있도록 꽃들을 선택할 때,
선택한 꽃들의 최소 개수를 출력하는 프로그램을 작성하시오.

입력
첫째 줄에는 꽃들의 총 개수 N (1<=N<=100,000)이 주어진다.
다음 N개의 줄에는 각 꽃이 피는 날짜와 지는 날짜가 주어진다.
하나의 날짜는 월과 일을 나타내는 두 숫자로 표현된다.
예를 들어서, 3 8 7 31은 꽃이 3월 8일에 피어서 7월 31일에 진다는 것을 나타낸다.

출력
첫째 줄에 선택한 꽃들의 최소 개수를 출력한다.
만약 두 조건을 만족하는 꽃들을 선택할 수 없다면 0을 출력한다.

예제 입력 1
4
1 1 5 31
1 1 6 30
5 15 8 31
6 10 12 10
예제 출력 1
2

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 공주님의정원_2457 {
    private static int N;
    private static Date[] bloomAndFallDate;

    private static final int START_DATE = 301;
    private static final int END_DATE = 1201;

    private static class Date {
        int start;
        int end;

        public Date(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        bloomAndFallDate = new Date[N];
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int bloomMonth = Integer.parseInt(st.nextToken()) * 100;
            int bloomDay = Integer.parseInt(st.nextToken());

            int fallMonth = Integer.parseInt(st.nextToken()) * 100;
            int fallDay = Integer.parseInt(st.nextToken());

            bloomAndFallDate[i] = new Date(bloomMonth + bloomDay, fallMonth + fallDay);
        }

        System.out.println(getMinFlowersCount());

        br.close();
    }

    private static int getMinFlowersCount() {
        int current = START_DATE;
        int count = 0;
        boolean flag = false;

        while (current < END_DATE) {
            int max = current;

            for (Date d : bloomAndFallDate) {
                /* 중요 */
                // 시작점이 현재보다 작거나 같아야 한다. -> 그 중 제일 긴 것 (즉, end가 제일 멀리 있는 것을 찾는다)
                if (d.start <= current && max < d.end) {
                    max = d.end;
                    flag = true;
                }
            }

            if (flag) {
                current = max;
                count ++;
                flag = false;
                continue;
            }

            // 시작점이 301보다 늦거나, 중간이 빌때
            count = 0;
            break;
        }

        return count;
    }
}