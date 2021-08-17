//1944: 복제 로봇
import java.io.*;
import java.util.*;
 
public class Main {

    static ArrayList<Node> list;
    static int n, m;
    static char[][] board;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static PriorityQueue<Mst_Node> pq;
    static int[] parent;

    public static void main(String[] args) throws IOException {

        //입력
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String str = bf.readLine();
        StringTokenizer st = new StringTokenizer(str);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][n];
        list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            str = bf.readLine();
            for(int j = 0; j < n; j++) {
                char c = str.charAt(j);
                board[i][j] = c;
                if(c == 'S' || c == 'K') list.add(new Node(i, j, 0));
            }
        }
        //입력 끝

        pq = new PriorityQueue<Mst_Node>();
        for(int i = 0; i < m + 1; i++) {
            bfs(i);
        }
        System.out.println(kruskal());
    }   

    public static int kruskal() {
        parent = new int[m + 1];
        for(int i = 0; i < m + 1; i++) {
            parent[i] = i;
        }

        boolean[] vistied = new boolean[m + 1];
        int cost = 0;
        int edge_count = 0;
        while(!pq.isEmpty()) {
            Mst_Node current = pq.poll();
            
            int p1 = find(current.s);
            int p2 = find(current.e);

            if(p1 != p2) {
                union(p1, p2);
                vistied[current.e] = true;
                vistied[current.s] = true;
                cost += current.cost;
                edge_count++;
            }
        }

        if(edge_count != m) return -1; //모두 연결되지 않으면 안됨.
        return cost;
    }

    public static void union(int p1, int p2) {
        parent[p1] = p2;
    }

    public static int find(int node) {
        if(parent[node] == node) return node;
        else return parent[node] = find(parent[node]);
    }

    public static void bfs(int num) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        q.offer(list.get(num));
        visited[list.get(num).x][list.get(num).y] = true;

        while(!q.isEmpty()) {
            Node current = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(board[nx][ny] == '1' || visited[nx][ny]) continue;
                visited[nx][ny] = true;

                if(board[nx][ny] == 'S' || board[nx][ny] == 'K') {
                    for(int j = 0; j < m + 1; j++) {
                        if(list.get(j).x == nx && list.get(j).y == ny) {
                            pq.offer(new Mst_Node(num, j, current.count + 1));
                        }
                    }
                }
                q.offer(new Node(nx, ny, current.count + 1));
            }
        }
    }

    public static class Mst_Node implements Comparable<Mst_Node> {
        int s, e, cost;

        public Mst_Node(int s, int e, int cost) {
            this.s = s;
            this.e = e;
            this.cost = cost;
        }

        @Override
        public int compareTo(Mst_Node n) {
            return this.cost - n.cost;
        }
    }

    public static class Node {
        int x, y, count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}