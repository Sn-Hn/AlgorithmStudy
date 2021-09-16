package study_210919;

public class 징검다리건너기_이분탐색 {

    public int solution(int[] stones, int k) {
        int answer = 0;
        int max = -1;
        int min = Integer.MAX_VALUE;

        for (int stone : stones) {
            max = Math.max(max, stone);
            min = Math.min(min, stone);
        }

        answer = crossFriendsCount(stones, k, min, max);

        return answer;
    }

    private static int crossFriendsCount(int[] stones, int k, int start, int end) {
        int mid = 0;

        while (start < end) {
            mid = (start + end) / 2;
            if (isCross(stones, k, mid)) {
                end = mid;
            }else {
                start = mid + 1;
            }
        }

        return end;
    }

    private static boolean isCross(int[] stones, int k, int crossCnt) {
        int cnt = 0;
        for (int stone : stones) {
            if (crossCnt >= stone) {
                cnt ++;
            }else {
                cnt = 0;
            }

            if (cnt == k) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
//        int[] stones = {3, 3, 3, 3, 3, 3, 3};
//        int k = 6;

        System.out.println(new 징검다리건너기_이분탐색().solution(stones, k));
    }
}
