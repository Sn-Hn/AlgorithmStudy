package boj_20210528_이분탐색_문자열;

/*

카누 선수 출처다국어분류
시간 제한   메모리 제한   제출   정답   맞은 사람   정답 비율
3 초   256 MB   2454   593   381   23.753%
문제
국제 카누 경주 챔피언십 (International Canoe Sprint Championship : ICSC)가 머지 않아 개막된다.
ICSC에서 인증하는 공식 보트는 C1, C2 그리고 C4로 구성되며, "C"는 카누를 그리고 숫자는 노를 젓는 사람의 수를 의미한다.
카누 경주는 잔잔한 물 위의 여러개로 구획된 직선 코스에서 이루어진다.
ICSC에서는 국제 경기를 200m, 500m 그리고 1000m로 구분하고 있다.



한국 스포츠 학교(Korea Sports School : KSS)는 ICSC 의 C4 1000m 경기에 참가할 예정이다.
KSS에는 같은 수의 학생으로 구성된 4개의 반을 가지며, 각 반에서 1명씩을 선출하여 경기에 참가한다.
KSS에는 다수의 C4 보트를 가지고 있으며 각 보트는 선수들의 몸무게 합이 특정 값에 근사할 때 최대의 성과를 낼 수 있다.
예를 들어 특정 값이 300이고 각 반의 학생들의 몸무게가 아래와 같다고 하자.

Class-1: 60, 52, 80, 40
Class-2: 75, 68, 88, 63
Class-3: 48, 93, 48, 54
Class-4: 56, 73, 49, 75
각 반에서 차례로 60,75,93 그리고 73 학생을 선택하게 되면 몸무게 합이 301으로 300에 가장 근사하게 된다.
몇몇의 경우에는 두개의 근사값이 나올 수 있다.
예를 들어 특정 값이 200일 때, 몸무게의 합이 198과 202가 나올 수 있으며 이러한 경우에는 더 작은 값이 카누 게임 진행에 더 적합하다.
따라서 몸무게의 합이 198인 학생들이 선택받게 된다.

보트의 특정값과 학생들의 몸무게가 주어졌을때, 위의 조건을 만족하는 4명의 학생을 선택하시오.

입력
이 문제에서는 입력은 표준 입력을 사용한다.
입력의 첫 줄에는 T개의 테스트 케이스가 주어진다.
각 테스트 케이스에는 두 개의 정수 k와 n이 주어지며, k( 1 ≤ k ≤ 40,000,000)는 보트의 특정 값 그리고 n( 1 ≤ n ≤ 1,000 )은 KSS 각 반의 학생수이다.

이어지는 4개의 줄에 차례로 각 반의 학생들의 몸무게가 n개씩 주어진다.
이때 몸무게는 1에서 10,000,000까지이다.

출력
출력은 표준 출력을 이용한다.
각 테스트 케이스에 해당하는 값을 한 줄에 출력한다.
해당 줄에는 카누 선수로 지목된 학생들의 몸무게의 총합이 포함되어 있어야 한다.

예제 입력 1
3
300 4
60 52 80 40
75 68 88 63
48 93 48 54
56 73 49 75
8 3
1 2 3
1 2 3
1 2 3
1 2 3
32 2
2 5
9 4
10 20
4 2
예제 출력 1
301
8
31

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 완전탐색은 N^4
public class 카누선수_9007 {
    private static int N;
    private static int K;
    private static int[][] classStudents;
    private static int[] upperClass;
    private static int[] lowerClass;
    private static int closedWeight = Integer.MAX_VALUE;
    private static int subWeight = Integer.MAX_VALUE;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(br.readLine());

        for (int i = 0; i < TC; i++) {
            testCase();
        }
        System.out.println(sb.toString());

        br.close();
    }

    private static void testCase() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        classStudents = new int[4][N];
        for (int j = 0; j < 4; j++) {
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < N; k++) {
                classStudents[j][k] = Integer.parseInt(st.nextToken());
            }
        }
        
        upperClass = initClass(0);      // 1 ~ 2반
        lowerClass = initClass(2);      // 3 ~ 4반

        Arrays.sort(upperClass);
        Arrays.sort(lowerClass);

        getStudents();
        sb.append(closedWeight).append("\n");
    }

    private static int compareAbs(int a, int b, int sum) {
        int absA = Math.abs(K - a - sum);
        int absB = Math.abs(K - b - sum);

        if(absA < absB) {
            return a;
        }

        if(absA == absB) {
            if(a <= b) {
                return a;
            }
            return b;
        }

        return b;
    }

    private static void getCloseWeight(int sum) {
        int sub = Math.abs(K - sum);
        if(subWeight > sub) {
            subWeight = sub;
            closedWeight = sum;
            return;
        }

        if(subWeight == sub) {
            if (closedWeight >= sum) {
                closedWeight = sum;
            }
        }
    }

    private static void getStudents() {
        int sum = 0;
        int end = 0;
        subWeight = Integer.MAX_VALUE;
        closedWeight = Integer.MAX_VALUE;
        for (int i = 0; i < N * N; i++) {
            sum = upperClass[i];
            end = binarySearch(sum);
            if(end == 0) {
                sum += lowerClass[end];
            }else {
                sum += compareAbs(lowerClass[end-1], lowerClass[end], sum);
            }
            getCloseWeight(sum);
        }
    }

    private static int binarySearch(int weight) {
        int start = 0;
        int end = N * N - 1;
        int mid = 0;
        int totalWeight = 0;

        while(start < end) {
            mid = (start + end) / 2;
            totalWeight = weight + lowerClass[mid];
            if (K >= totalWeight) {
                start = mid + 1;
            }else {
                end = mid;
            }
        }

        return end;
    }

    private static int[] initClass(int ban) {
        int num = 0;
        int[] classes = new int[N * N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                classes[num++] = classStudents[ban][i] + classStudents[ban + 1][j];
            }
        }

        return classes;
    }
}