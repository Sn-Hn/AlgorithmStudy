package 다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 얼음미로_다익스트라 {
    static class Info {
        int r;
        int c;
        int time;

        public Info(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }

    }




    static char map[][];
    static int a[] = {-1,1,0,0};
    static int b[] = {0,0,-1,1};
    static int weight[][];
    static int w;
    static int h;
    static int end[];
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new char[h][w];
        weight = new int[h][w];

        int start[] = new int[2];
        end = new int[2];

        for(int i = 0 ; i < h ; i++){
            char tmp[] = br.readLine().toCharArray();
            for(int j = 0 ; j < w ; j++){
                map[i][j] = tmp[j];
                if(tmp[j]==('T')){
                    start[0] = i;
                    start[1] = j;
                    map[i][j] = '0';
                }else if (tmp[j]==('E')){
                    end[0] = i;
                    end[1] = j;
                }

            }
        }
        /**
         * 접근
         * 현재자리에서 4방향 탐색을 해야한다.
         * 4방향중에 바위가 있으면 멈추고 다음 경로 탐색
         * 탈출구이면 탈출한다.
         * 같은자리에 도달했을때 더 작은 값으로 초기화 하고 진행한다. -> 다익스트라.
         *
         *
         */

        for(int a[] : weight){
            Arrays.fill(a,Integer.MAX_VALUE);
        }
        djikstra(start[0],start[1]);

        if(weight[end[0]][end[1]] == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{

            System.out.println(weight[end[0]][end[1]]);
        }

    }

    public static void djikstra(int sr,int sc){
        Queue<Info> pq = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o1.time-o2.time;
            }
        });

        pq.add(new Info(sr,sc,0));
        weight[sr][sc] = 0;

        while(!pq.isEmpty()){

            Info cur = pq.poll();
            int r = cur.r;
            int c = cur.c;
            int time = cur.time;

            /**
             * 가장 작은 값만 실행되도록 가지치기를 해야한다.
             * weight[r][c]에 들어가있는값은 가장 weight가 작은 값일 것이다.
             * 그러면 [r][c]위치에서 time이 weight[r][c]와 같은것만 수행하도록 한다.
             *
             * 지워도 상관은 없다(우선순위큐이기 때문에 어짜피 최소값이 나온다).
             * LinkedList일 경우에는 필수로 써야한다.(하지만 pq보다 느리다).
             */
            if(weight[r][c] != time ) continue;


            for(int i = 0 ;i  <4 ;i++){
                //미끄러지는 자리
                Info next = sliding(r,c,i);
                int nextR = next.r;
                int nextC = next.c;
                int nextTime = next.time+time;

                if(next.time != -1 && weight[nextR][nextC] >nextTime){
                    weight[nextR][nextC] = nextTime;
                    pq.add(new Info(nextR,nextC,nextTime));
                }

            }

        }


    }

    public static Info sliding(int r,int c,int way){
        int time = 0;
        while(true){
            r += a[way];
            c += b[way];

            if(r < 0 || c <0 || r >= h || c >= w) {
                return new Info(r,c,-1);
            }else if(map[r][c]=='E'){
                break;
            }else if (map[r][c]=='H'){
                return new Info(r,c,-1);
            }else if (map[r][c]=='R'){
                r -= a[way];
                c -= b[way];
                break;
            }

            time += map[r][c]-'0';
        }




        return new Info(r,c,time);

    }





}
