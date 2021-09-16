package study_210919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 컬러볼_10800 {
    private static int N;
    private static List<Ball> balls = new ArrayList<>();

    private static class Ball {
        int index;
        int color;
        int size;

        public Ball(int index, int color, int size) {
            this.index = index;
            this.color = color;
            this.size = size;
        }

        @Override
        public String toString() {
            return "Ball{" +
                    "index=" + index +
                    ", color=" + color +
                    ", size=" + size +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            balls.add(new Ball(i, color, size));
        }

        Collections.sort(balls, (o1, o2) -> o1.size - o2.size);

        catchBall();

        br.close();
    }

    private static void catchBall() {
        PriorityQueue<Ball> results = new PriorityQueue<>((o1, o2) -> o1.index - o2.index);
        Map<Integer, Integer> sumBalls = new HashMap<>();
        System.out.println(balls);
        int sum = 0;
        int prevSize = -1;
        for (int i = 0; i < balls.size(); i++) {
            Ball ball = balls.get(i);
            int catchSize = sum;
            int equalSize = 0;

            if (sumBalls.containsKey(ball.color)) {
                catchSize -= sumBalls.get(ball.color);
                sumBalls.put(ball.color, sumBalls.get(ball.color) + ball.size);
            }else {
                sumBalls.put(ball.color, ball.size);
            }
            if (prevSize == ball.size) {
                equalSize = getEqualSize(i, ball, equalSize);
            }else {
                equalSize = 0;
            }
            catchSize -= equalSize;
            results.add(new Ball(ball.index, ball.color, catchSize));
            prevSize = ball.size;
            sum += ball.size;
        }

        printResult(results);
    }

    private static int getEqualSize(int i, Ball ball, int equalSize) {
        for (int j = i - 1; j >= 0; j--) {
            Ball prevBall = balls.get(j);
            if (prevBall.size != ball.size) {
                break;
            }

            if (prevBall.color != ball.color) {
                equalSize += prevBall.size;
                continue;
            }
        }
        return equalSize;
    }

    private static void printResult(PriorityQueue<Ball> results) {
        StringBuilder resultSize = new StringBuilder();
        while (!results.isEmpty()) {
            Ball result = results.poll();
            resultSize.append(result.size).append("\n");
        }

        System.out.println(resultSize.toString().trim());
    }
}
