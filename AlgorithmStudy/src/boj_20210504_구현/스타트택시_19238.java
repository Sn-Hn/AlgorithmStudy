package boj_20210504_구현;

// 1. 최단거리에 있는 손님을 태우고 그에 맞는 목적지로 가야 함
// 2. 손님을 태우고 목적지까지 도착하면 목적지까지 사용한 연료의 두 배가 충전된다.
// 3. 목적지에서 다음 가장 가까운 손님을 태우러 가고 반복
// 4. 가장 가까운 손님이 여러 명일 경우 가장 번호가 작은 행 부터 간다.
// 5. 가장 번호가 작은 행에도 여러 명일 경우 가장 작은 열로 간다.
// 6. 연료가 바닥나면 실패 -> -1 출력
// 7. 마지막 손님을 목적지에 이동시킨 동시에 연료가 바닥나면 실패가 아니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 스타트택시_19238 {
    private static int N, M, fuel;
    private static int map[][];
    private static Pair startTaxi;
    private static List<Pair> departurePosition = new ArrayList<>();
    private static List<Pair> arrivalPosition = new ArrayList<>();
    private static boolean[][] visited;

    private static int dx[] = {-1, 0, 0, 1};
    private static int dy[] = {0, -1, 1, 0};

    private static class Pair implements Comparable<Pair> {
        int x;
        int y;
        int distance;
        int index;

        public Pair(int x, int y, int distance, int index) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.index = index;
        }

        @Override
        public int compareTo(Pair o) {
            if (distance == o.distance) {
                if (x == o.x) {
                    return y - o.y;
                }

                return x - o.x;
            }
            return distance - o.distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1][N+1];
        map = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        startTaxi = new Pair(x, y, 0, 0);

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            departurePosition.add(new Pair(startX, startY, 0, i));
            arrivalPosition.add(new Pair(endX, endY, 0, i));
        }

        Collections.sort(departurePosition);

        for(Pair p : departurePosition) {
            System.out.println("p.x : " + p.x + ", p.y : " + p.y + ", p.distance : " + p.distance + ", p.index : " + p.index);
        }

        for(Pair p : arrivalPosition) {
            System.out.println("p.x : " + p.x + ", p.y : " + p.y + ", p.distance : " + p.distance + ", p.index : " + p.index);
        }

        int result = bfs();
        System.out.println(result);

        br.close();
    }

    private static int bfs() {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(startTaxi.x, startTaxi.y, 0, 0));
        boolean check = false;
        int departureIndex = -1;
        int arrivalIndex = -1;
        int index = -1;
        int distance = 0;

        while (!q.isEmpty()) {
            Pair p = q.poll();

            if(fuel <= 0) {
                return -1;
            }

            for (int i = 0; i < 4; i++) {
                int X = p.x + dx[i];
                int Y = p.y + dy[i];

                if (!isValid(X, Y) || visited[X][Y]) {
                    continue;
                }
                visited[X][Y] = true;
                if (!check) {
                    index = isDepartureIndex(X, Y);
//                    System.out.println(index);
                    distance = p.distance + 1;
                    if(index != -1) {
                        departureIndex = arrivalPosition.get(index).index;
                        q.clear();
                        visited = new boolean[N+1][N+1];
                        p.index = departureIndex;
                        fuel -= p.distance;
                        distance = 0;
                        check = true;
                    }
                    q.add(new Pair(X, Y, distance, p.index));
                } else {
                    arrivalIndex = isArrivalIndex(X, Y, departureIndex);
                    System.out.println("Index : " + index);
                    System.out.println("departureIndex : " + departureIndex);
                    System.out.println("arrivalindex : " + arrivalIndex);
                    System.out.println("X : " + X + ", Y : " + Y);

                    distance = p.distance + 1;
                    if(arrive(X, Y, arrivalIndex)) {
                        System.out.println(departurePosition.get(departureIndex).x +","+ departurePosition.get(departureIndex).y +","+ departurePosition.get(departureIndex).distance +","+ departurePosition.get(departureIndex).index);
                        System.out.println(arrivalPosition.get(arrivalIndex).x +","+ arrivalPosition.get(arrivalIndex).y +","+ arrivalPosition.get(arrivalIndex).distance +","+ arrivalPosition.get(arrivalIndex).index);
                        System.out.println(fuel + ", " + p.distance);
                        fuel -= p.distance;
                        fuel += p.distance * 2;
                        departurePosition.remove(index);
                        arrivalPosition.remove(arrivalIndex);
                        q.clear();
                        visited = new boolean[N+1][N+1];
                        check = false;
                    }
                    q.add(new Pair(X, Y, p.distance+1, arrivalIndex));
                }
            }

            if(arrivalPosition.size() == 0) {
                return 0;
            }
        }

        for(Pair p : arrivalPosition) {
            System.out.println("p.x : " + p.x + ", p.y : " + p.y + ", p.distance : " + p.distance + ", p.index : " + p.index);
        }

        return fuel;
    }

    private static boolean arrive(int nx, int ny, int index) {
        if(nx == arrivalPosition.get(index).x && ny == arrivalPosition.get(index).y) {
            return true;
        }

        return false;
    }

    private static int isArrivalIndex(int nx, int ny, int index) {
        for(int i = 0; i < arrivalPosition.size(); i++) {
            if(arrivalPosition.get(i).index == index) {
                return i;
            }
        }

        return -1;
    }

    private static int isDepartureIndex(int nx, int ny) {
        for (int i = 0; i < departurePosition.size(); i++) {
            if(nx == departurePosition.get(i).x && ny == departurePosition.get(i).y) {
                return i;
            }
        }

        return -1;
    }

    private static boolean isValid(int nx, int ny) {
        if (nx > 0 && nx <= N && ny > 0 && ny <= N && map[nx][ny] != 1) {
            return true;
        }

        return false;
    }
}