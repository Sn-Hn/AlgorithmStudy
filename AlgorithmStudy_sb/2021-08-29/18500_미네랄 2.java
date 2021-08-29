//18500: 미네랄 2
import java.io.*;
import java.util.*;
 
public class Main {

    static final int LEFT = 1;
    static final int RIGHT = -1;
    static int r, c;
    static char[][] board;
 
    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str = bf.readLine();
        StringTokenizer st = new StringTokenizer(str);
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new char[r][c];
        for(int i = 0; i < r; i++) {
            str = bf.readLine();
            for(int j = 0; j < c; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        int n = Integer.parseInt(bf.readLine());
        int[] turn = new int[n];
        str = bf.readLine();
        st = new StringTokenizer(str);
        for(int i = 0; i < n; i++) {
            turn[i] = Integer.parseInt(st.nextToken());
        }
        //입력 끝
        fight(turn);
        print_board();
    }

    public static void print_board() {
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public static void fight(int[] turn) {
        for(int i = 0; i < turn.length; i++) {
            //막대기 던지기
            if(i % 2 == 0) dfs(0, LEFT, r - turn[i]);
            else dfs(c - 1, RIGHT, r - turn[i]);
            //새로 생성된 클러스터가 있는지 -> 있다면 떨어져야 하는지 확인
            check_and_fall(); 
            print_board();
        }
    }

    public static void dfs(int idx, int dir, int height) {
        if(idx >= c || idx < 0) return;
        if(board[height][idx] == 'x') {
            board[height][idx] = '.';
            return;
        }
        dfs(idx + dir, dir, height);
    }

    public static void check_and_fall() {
        boolean[][] visited = new boolean[r][c];
        ArrayList<Node> fall_node = new ArrayList<>();
        for(int i = 0; i < r; i++) {
            if(!fall_node.isEmpty()) break; //두개 이상 클러스터가 동시에 떨어지는 경우 없으므로 떨어질 수 있는 클러스터 하나 찾으면 탐색 끝냄.
            for(int j = 0; j < c; j++) {
                if(!visited[i][j] && board[i][j] == 'x') {
                    Object[] result = bfs_andFallCheck(visited, i, j);
                    if((boolean) result[0]) fall_node = (ArrayList<Main.Node>) result[1];
                }
            }
        }
        if(fall_node.isEmpty()) return;
        Collections.sort(fall_node, (o1, o2) -> o2.x - o1.x);
        fall_cluster(fall_node);
    }

    public static Object[] bfs_andFallCheck(boolean[][] visited, int x, int y) {
        int[] dx = {0, 1, 0 ,-1};
        int[] dy = {1, 0, -1, 0};
        Queue<Node> q = new LinkedList<>(); 
        ArrayList<Node> list = new ArrayList<>();
        visited[x][y] = true;
        q.offer(new Node(x, y));
        list.add(new Node(x, y));

        boolean isFall = true;
        while(!q.isEmpty()) {
            Node current = q.poll();
            
            for(int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                if(board[nx][ny] != 'x' || visited[nx][ny]) continue;
                
                visited[nx][ny] = true;
                list.add(new Node(nx, ny));
                q.offer(new Node(nx, ny));
                if(nx == r - 1) isFall = false;
            }
        }
        return new Object[] {isFall, list};
    }

    public static void fall_cluster(ArrayList<Node> list) {
        while(true) {
            char[][] copy = copy_board(board);
            boolean check = true;
            for(Node current : list) {
                if(current.x + 1 >= r) {
                    check = false;
                    break;
                }
                if(copy[current.x + 1][current.y] == 'x') {
                    check = false;
                    break;
                }
                copy[current.x++][current.y] = '.';
                copy[current.x][current.y] = 'x';
            }
            if(!check) break;
            board = copy_board(copy);
        }
    }

    public static char[][] copy_board(char[][] board) {
        char[][] copy = new char[r][c];
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }

    public static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
