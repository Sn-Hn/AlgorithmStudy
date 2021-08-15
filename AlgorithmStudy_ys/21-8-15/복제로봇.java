import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 복제로봇 {

    static int n, m;
    static char map[][];
    static Key key[];
    static PriorityQueue<Edge> pq;
    static int parent[];
    static int a[] = {-1,1,0,0};
    static int b[] = {0,0,-1,1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        key = new Key[m+1];
        map = new char[n][n];
        parent = new int[m+1];

        for(int i = 0; i <= m; i++)
            parent[i] = i;

        int idx = 0;

        for(int i = 0; i < n; i++) {
            String input = br.readLine();
            for(int j = 0; j < n; j++) {
                map[i][j] = input.charAt(j);

                if(map[i][j] != '1' && map[i][j] != '0') {
                    key[idx] = new Key(idx, i, j);
                    idx++;
                }

            }
        }

        pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight-o2.weight;
            }
        });

        for(int i = 0; i <= m; i++){
            getEdge(i);
        }

        int sum = 0;

        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            if(find(now.v1) != find(now.v2)) {
                union(now.v1, now.v2);
                sum += now.weight;
            }
        }

        for(int i = 0; i <= m; i++) {
            if(parent[i] != 0) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(sum);
    }

    public static void getEdge(int start) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(key[start].r, key[start].c, 0));

        boolean visited[][] = new boolean[n][n];
        visited[key[start].r][key[start].c] = true;

        while(!q.isEmpty()) {
            Point now = q.poll();

            for(int i = 0; i < 4; i++) {
                int mr = now.r + a[i];
                int mc = now.c + b[i];

                if(mr < 0 || mc < 0 || mr >= n || mc >= n) continue;

                if( map[mr][mc] != '1' && !visited[mr][mc]) {
                    visited[mr][mc] = true;

                    if(map[mr][mc] != '0') {
                        for(int j = 0; j <= m; j++) {
                            if(key[j].r == mr && key[j].c == mc) {
                                pq.offer(new Edge(key[start].number, j, now.count+1));
                                break;
                            }
                        }

                    }
                    q.offer(new Point(mr, mc, now.count+1));
                }
            }
        }
    }



    public static void union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);

        if(p1 > p2)
            parent[p1] = p2;
        else
            parent[p2] = p1;
    }

    public static int find(int n) {
        if(parent[n] == n)
            return n;

        return parent[n] = find(parent[n]);
    }

    static class Key {
        int number, r, c;

        public Key(int number, int r, int c) {
            this.number = number;
            this.r = r;
            this.c = c;
        }
    }

    static class Edge {
        int v1, v2, weight;

        Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

    }

    static class Point {
        int r, c, count;

        public Point(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }
}