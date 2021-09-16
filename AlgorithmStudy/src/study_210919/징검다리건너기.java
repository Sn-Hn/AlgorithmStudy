package study_210919;

import java.util.PriorityQueue;

// https://programmers.co.kr/learn/courses/30/lessons/64062
public class 징검다리건너기 {

    private static class Stone implements Comparable<Stone> {
        int index;
        int cnt;

        public Stone(int index, int cnt) {
            this.index = index;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Stone o) {
            if (cnt == o.cnt) {
                return index - o.index;
            }

            return cnt - o.cnt;
        }
    }

    public int solution(int[] stones, int k) {
        int answer = 0;

        PriorityQueue<Stone> cntBridge = new PriorityQueue<>();
        for (int i = 0; i < stones.length; i++) {
            cntBridge.add(new Stone(i + 1, stones[i]));
        }

        answer = crossBridge(cntBridge, k);

        return answer;
    }

    private static int crossBridge(PriorityQueue<Stone> cntBridge, int k) {
        boolean[] removeStone = new boolean[cntBridge.size() + 1];

        while (!cntBridge.isEmpty()) {
            Stone now = cntBridge.poll();
            int minCnt = now.cnt;
            int nowIndex = now.index;

            removeStone[nowIndex] = true;

            if (isChekced(removeStone, nowIndex, k)) {
                return minCnt;
            }

        }
        return 0;
    }

    private static boolean isChekced(boolean[] removeStone, int index, int k) {
        int cnt = -1;
        for (int i = index; i > 0; i--) {
            if (!removeStone[i]) {
                break;
            }
            cnt ++;
        }

        for (int i = index; i < removeStone.length; i++) {
            if (!removeStone[i]) {
                break;
            }
            cnt ++;
        }

        if (cnt < k) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 2;
//        int[] stones = {3, 3, 3, 3, 3, 3, 3};
//        int k = 6;

        System.out.println(new 징검다리건너기().solution(stones, k));
    }
}
