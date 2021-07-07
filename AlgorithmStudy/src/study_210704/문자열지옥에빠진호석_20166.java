package study_210704;

/*

문자열 지옥에 빠진 호석 출처전체 채점
시간 제한   메모리 제한   제출   정답   맞은 사람   정답 비율
1 초   512 MB   648   241   159   35.022%
문제
하루 종일 내리는 비에 세상이 출렁이고 구름이 해를 먹어 밤인지 낮인지 모르는 어느 여름 날

잠 들기 싫어 버티던 호석이는 무거운 눈꺼풀에 패배했다.
정신을 차려보니 바닥에는 격자 모양의 타일이 가득한 세상이었고, 각 타일마다 알파벳 소문자가 하나씩 써있다더라.
두려움에 가득해 미친듯이 앞만 보고 달려 끝을 찾아 헤맸지만 이 세상은 끝이 없었고, 달리다 지쳐 바닥에 드러누우니 하늘에 이런 문구가 핏빛 구름으로 떠다니고 있었다.

이 세상은 N행 M열의 격자로 생겼으며, 각 칸에 알파벳이 써있고 환형으로 이어진다.
왼쪽 위를 (1, 1), 오른쪽 아래를 (N, M)이라고 하자.
너는 아무 곳에서나 시작해서 상하좌우나 대각선 방향의 칸으로 한 칸씩 이동할 수 있다.
이 때, 이미 지나 왔던 칸들을 다시 방문하는 것은 허용한다.
시작하는 격자의 알파벳을 시작으로, 이동할 때마다 각 칸에 써진 알파벳을 이어 붙여서 문자열을 만들 수 있다.
이 곳의 신인 내가 좋아하는 문자열을 K 개 알려줄 터이니, 각 문자열 마다 너가 만들 수 있는 경우의 수를 잘 대답해야 너의 세계로 돌아갈 것이다.
경우의 수를 셀 때, 방문 순서가 다르면 다른 경우이다.
즉, (1,1)->(1,2) 로 가는 것과 (1,2)->(1,1) 을 가는 것은 서로 다른 경우이다.
호석이는 하늘을 보고서 "환형이 무엇인지는 알려달라!" 며 소리를 지르니 핏빛 구름이 흩어졌다가 모이며 아래와 같은 말을 그렸다.

너가 1행에서 위로 가면 N 행으로 가게 되며 반대도 가능하다.
너가 1열에서 왼쪽으로 가면 M 열로 가게 되며 반대도 가능하다.
대각선 방향에 대해서도 동일한 규칙이 적용된다.
하늘에 아래와 같은 그림을 구름으로 그려줄 터이니 이해해 돕도록 하여라.
예를 들어서, 너가 (1, 1)에서 위로 가면 (N, 1)이고, 왼쪽으로 가면 (1, M)이며 왼쪽 위 대각선 방향으로 가면 (N, M)인 것이다.


세상을 이루는 격자의 정보와, K 개의 문자열이 주어졌을 때, 호석이가 대답해야 하는 정답을 구해주도록 하자.

입력
첫번째 줄에 격자의 크기 N, M과 신이 좋아하는 문자열의 개수 K 가 주어진다.

다음에 N개의 줄에 걸쳐서 M개의 알파벳 소문자가 공백없이 주어진다.
여기서의 첫 번째 줄은 1행의 정보이며, N 번째 줄은 N행의 정보이다.

이어서 K개의 줄에 걸쳐서 신이 좋아하는 문자열이 주어진다.
모두 알파벳 소문자로 이루어져 있다.

출력
K개의 줄에 걸쳐서, 신이 좋아하는 문자열을 만들 수 있는 경우의 수를 순서대로 출력한다.

제한
3 ≤ N, M ≤ 10, N과 M은 자연수이다.
1 ≤ K ≤ 1,000, K는 자연수이다.
1 ≤ 신이 좋아하는 문자열의 길이 ≤ 5
신이 좋아하는 문자열은 중복될 수도 있다.
예제 입력 1
3 3 2
aaa
aba
aaa
aa
bb
예제 출력 1
56
0
예제 입력 2
3 4 3
abcb
bcaa
abac
aba
abc
cab
예제 출력 2
66
32
38

--
3 4 6
abcb
bcaa
abac
aba
abc
cab
aba
abc
aba

3 3 5
aba
aca
fab
abc
abc
abc
abc
aba

66
32
38
66
32

3 3 1
aba
aba
aba


출처
Contest > 류호석배 알고리즘 코딩 테스트 > 제1회 류호석배 알고리즘 코딩 테스트 3번

문제를 검수한 사람: BaaaaaaaaaaarkingDog, dlstj0923, tony9402
문제를 만든 사람: rhs0266
알고리즘 분류
보기

채점 및 기타 정보
16개 이상의 데이터를 맞아야 를 받는다.

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// 오래 걸린 이유
// 1. 중복 케이스
// 2. 한 자리수 케이스
public class 문자열지옥에빠진호석_20166 {
	private static int N;
    private static int M;
    private static int K;
    private static char[][] tile;
    private static Map<String, Integer> favorite = new LinkedHashMap<String, Integer>();
    private static Queue<Maps> duplicateFavorite = new LinkedList<Maps>();
    private static int max = 0;

    // 동 서 남 북 동북 동남 서남 서북
    private static int[] dx = {0, 0, 1, -1, 1, -1, -1, 1};
    private static int[] dy = {1, -1, 0, 0, 1, 1, -1, -1};

    private static class Maps {
        String str;
        int index;

        public Maps(String str, int index) {
            this.str = str;
            this.index = index;
        }

        public String getStr() {
            return str;
        }

        public int getIndex() {
            return index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        tile = new char[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                tile[i][j] = input.charAt(j);
            }
        }

        for (int i = 0; i < K; i++) {
            String input = br.readLine();
            max = Math.max(max, input.length());
            if (favorite.containsKey(input)) {
                duplicateFavorite.add(new Maps(input, i));
            }
            favorite.put(input, 0);
//            favorite.add(new Map(input, 0));
        }

        makeString();

        StringBuilder result = new StringBuilder();
        int index = 0;
        for (int length : favorite.values()) {
            while (!duplicateFavorite.isEmpty() && duplicateFavorite.peek().getIndex() == index) {
                result.append(favorite.get(duplicateFavorite.poll().getStr())).append("\n");
                index++;
            }
            result.append(length).append("\n");
            index++;
        }

        while (!duplicateFavorite.isEmpty()) {
            result.append(favorite.get(duplicateFavorite.poll().getStr())).append("\n");
        }

        System.out.println(result.toString().trim());

        br.close();
    }

    private static void makeStringRecursive(int x, int y, StringBuilder str) {
        if (str.length() > max) {
            return;
        }

        for (int i = 0; i < 8; i++) {
            int X = getCycle(x + dx[i], N);
            int Y = getCycle(y + dy[i], M);
            
//            System.out.println(X + ", " + Y);

            str.append(tile[X][Y]);

//            System.out.println(str.toString());

            containsFavorite_IncreaseCount(str.toString());
            makeStringRecursive(X, Y, str);

            str.deleteCharAt(str.length() - 1);
        }
    }

    private static void makeString() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                StringBuilder str = new StringBuilder();
                str.append(tile[i][j]);
                // 신이 좋아하는 문자열이 1자리수일 때를 계산안했다.
                containsFavorite_IncreaseCount(str.toString());
                makeStringRecursive(i, j, str);
            }
        }
    }

    private static void containsFavorite_IncreaseCount(String str) {
        if (favorite.containsKey(str)) {
            favorite.put(str, favorite.get(str) + 1);
        }

//        for (int i = 0; i < favorite.size(); i++) {
//            if (favorite.get(i).getStr().equals(str)) {
//                favorite.get(i).setCount();
//            }
//        }

    }

    // check : true -> 행, false -> 열
    private static int getCycle(int value, int maxValue) {
        if (value >= maxValue) {
            return 0;
        }

        if (value < 0) {
            return maxValue - 1;
        }

        return value;
    }
}