package boj_20210528_이분탐색_문자열;

/*

방 번호 분류
시간 제한   메모리 제한   제출   정답   맞은 사람   정답 비율
2 초   128 MB   1996   491   371   25.639%
문제
이번에 VIP 회장으로 새로 부임한 백은진은 빅뱅의 위대함을 세계에 널리 알리기 위해서 사무실을 하나 임대했다.

빅뱅은 위대하기 때문에, 사무실의 번호도 되도록이면 커야 한다고 생각한다.
따라서 지금 가지고 있는 돈 전부를 가지고 방 번호를 만들려고 한다.

1층에 있는 문방구에서는 숫자를 판다.
각 숫자의 가격은 서로 다를 수 있기 때문에, 현재 가지고 있는 돈을 이용해서 만들 수 있는 가장 큰 숫자를 만들려고 한다.

예를 들어, 문방구에서 파는 숫자가 0, 1, 2이고, 각 숫자의 가격이 6, 7, 8이고,
백은진이 현재 가지고 있는 돈이 21이라면, 백은진이 만들 수 있는 가장 큰 수는 210(8+7+6=21)이다.


입력
문방구에서 파는 숫자의 개수 N이 주어진다.
N은 10보다 작거나 같은 자연수이다.
문방구에서 파는 숫자는 0보다 크거나 같고, N-1보다 작거나 같은 정수이다.
예를 들어, N=4이면, 문방구에서 파는 숫자는 0,1,2,3인 것이다.
둘째 줄에 각 숫자를 사는데 드는 비용이 작은 숫자부터 주어진다.
이 비용은 50보다 작거나 같은 자연수이다.
마지막 줄에는 백은진이 현재 가지고 있는 돈이 주어진다.
돈은 50보다 작거나 같은 자연수이다.

출력
첫째 줄에 백은진이 가지고 있는 돈으로 만들 수 있는 가장 큰 수를 출력한다.
백은진이 가지고 있는 돈은 적어도 숫자 하나는 살 수 있기 때문에, 답은 항상 존재한다.

0을 제외하고 0으로 시작하는 수는 없다.

예제 입력 1
3
6 7 8
21
예제 출력 1
210
예제 입력 2
3
5 23 24
30
예제 출력 2
20
예제 입력 3
10
1 1 1 1 1 1 1 1 1 1
50
예제 출력 3
99999999999999999999999999999999999999999999999999

4
1 2 2 4
4

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// https://www.acmicpc.net/problem/1082
public class 방번호_1082 {
    private static int N;
    private static int[] numCost;
    private static int money;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numCost = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            numCost[i] = Integer.parseInt(st.nextToken());
        }

        money = Integer.parseInt(br.readLine());

        if(N == 1) {
            System.out.println(0);
            return;
        }
        int minZero = findMinMoneyNum(0);
        int minOne = findMinMoneyNum(1);
        int[] arrNumber = getArrayNumber(minZero, minOne);

        getMaxMoney(arrNumber);

        System.out.println(getStringMaxNumber(arrNumber));

        br.close();
    }

    private static String getStringMaxNumber(int[] arrNumber) {
        StringBuilder sb = new StringBuilder();

        for (int index : arrNumber) {
            sb.append(index);
        }

        return sb.toString();
    }

    private static void getMaxMoney(int[] arrNumber) {
        int m = money;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arrNumber.length; i++) {
            boolean flag = false;
            m += numCost[arrNumber[i]];
            System.out.println(m);

            for (int j = N - 1; j > arrNumber[i]; j--) {
                m -= numCost[j];

                if(m >= 0) {
                    arrNumber[i] = j;
                    flag = true;
                    break;
                }
                m += numCost[j];
            }

            if(!flag) {
                m -= numCost[arrNumber[i]];
            }
        }
    }

    // 167번째 줄 for문 i < zeroLength; -> 이 부분에서 틀려서 1% 틀림 계속뜸..
    // 반례는 다 맞음
    // 진짜 오타주의..
    private static int[] getArrayNumber(int zero, int one) {
        int zeroLength = 0;
        int[] arrNumber;
        money -= numCost[one];
        if(money < 0) {
            arrNumber = new int[1];
            return arrNumber;
        }
        zeroLength = money / numCost[zero];
        money = money % numCost[zero];

        arrNumber = new int[zeroLength + 1];
        arrNumber[0] = one;
        for (int i = 1; i <= zeroLength; i++) {
            arrNumber[i] = zero;
        }

        return arrNumber;
    }

    private static int findMinMoneyNum(int start) {
        int min = Integer.MAX_VALUE;
        int minIndex = start;
        for (int i = start; i < N; i++) {
            if (min > numCost[i]) {
                min = numCost[i];
                minIndex = i;
            }
        }

        return minIndex;
    }
}