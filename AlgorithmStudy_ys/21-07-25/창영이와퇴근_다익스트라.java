package BFS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 창영이와퇴근 {


    static class Node{
        int r;
        int c;
        int sub;

        public Node(int r, int c, int sub) {
            this.r = r;
            this.c = c;
            this.sub = sub;
        }
    }


    static int map[][];
    static int a[] = {0,0,-1,1};
    static int b[] = {1,-1,0,0};
    static int visited[][];
    static int n;
    static long ans = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));



        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new int[n][n];

        for(int i = 0 ; i < n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int a[] : visited){
            Arrays.fill(a,Integer.MAX_VALUE);
        }

        /**
         * 접근
         * 1,1 에서 출발해서 N,N까지 가는 모든 경로마다 최대값을 뽑아서
         * 최대값중 최소값을 출력한다.
         * 모든 경로를 찾기위해 bfs (시간초과)
         *
         * 다익스트라.
         * 현재까지 진행한 경로의 최대값을 가지고 있으면서 다음 으로 진행할 경로보다 최대값이 작으면 초기화 하고 진행
         *
         */

//        System.out.println(ans);
        bfs();


    }

    public static boolean bfs(){
        Queue<Node> que = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.sub-o2.sub;
            }
        });
        que.add(new Node(0,0,0));

        while(!que.isEmpty()){
            Node cur = que.poll();
            int r = cur.r;
            int c = cur.c;
            int curSub = cur.sub;



            if(r == n-1 && c == n-1){
                System.out.println(curSub);
                return true;
            }


            for(int i =0  ;i < 4 ;i++){
                int mr = r + a[i];
                int mc = c + b[i];

                if(mr < 0 || mc <0 || mr >=n || mc >=n) continue;
                int newSub = Math.abs(map[r][c]-map[mr][mc]);
                int nextSub = Math.max(newSub,curSub);
                if(visited[mr][mc] > nextSub){

                    visited[mr][mc] = nextSub;
                    que.add(new Node(mr,mc,nextSub));
                }
            }
        }

        return false;

    }




}
