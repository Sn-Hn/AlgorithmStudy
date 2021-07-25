package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 보스몬스터전리품 {

    static class Point{
        int r;
        int c;
        char id;
        int idx;
        int time;

        public Point(int r, int c, char id, int idx, int time) {
            this.r = r;
            this.c = c;
            this.id = id;
            this.idx = idx;
            this.time = time;
        }
    }

    static char map[][];
    static Map<String, Integer> damage = new HashMap<String, Integer>();
    static Point player[];
    static int bossHp;
    static boolean visited[][][];
    static ArrayList<String> list = new ArrayList<>();
    static int a[] = {0,0,-1,1};
    static int b[] = {1,-1,0,0};
    static int bossR ;
    static int bossC ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());


        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        map = new char[m][n];

        int p = Integer.parseInt(st.nextToken());
        visited = new boolean[m][n][p];
        player = new Point[p];
        bossR = 0;
        bossC = 0;


        int idx = 0;
        for(int i = 0 ; i < m;i++){
            char tmp[] = br.readLine().toCharArray();
            for(int j = 0 ; j < n;j++){
                map[i][j] = tmp[j];

                if(tmp[j] == ('B')){
                    bossR = i;
                    bossC = j;

                }else if(tmp[j] != '.' && tmp[j] != 'X'){
                    player[idx] = new Point(i,j,tmp[j],idx,0);
                    idx++;
                }

            }

        }

        for(int i = 0 ; i < p;i++){
            st=  new StringTokenizer(br.readLine());
            damage.put(st.nextToken(),Integer.parseInt(st.nextToken()));
        }

        bossHp = Integer.parseInt(br.readLine());

        bfs(m,n);
        System.out.println(list.size());




    }

    public static void bfs(int m,int n){
        Queue<Point> que = new LinkedList<>();

        for(Point p : player){
            que.add(p);
        }

        int arriveTime = 0;

        while(!que.isEmpty()){
            Point cur = que.poll();

            int r = cur.r;
            int c = cur.c;
            char id = cur.id;
            int idx = cur.idx;
            int time = cur.time;

//            System.out.println(id+"시간"+time+"기준시간"+arriveTime);
//            System.out.println("체력"+boss.hp);

            // 같은시간이고
            if(arriveTime < time){
                attack();
                if(bossHp <= 0 ){
                    return;
                }
                arriveTime = time;
            }

//            if(list.contains(id))continue;


            for(int i = 0 ; i <4 ;i++){
                int mr = r + a[i];
                int mc = c + b[i];

                if(mr <0 || mc <0 || mr >= m || mc >= n) continue;
                if(!visited[mr][mc][idx] && map[mr][mc] != 'X'){
                    visited[mr][mc][idx] = true;
                    que.add(new Point(mr,mc,id,idx,time+1));
                    if(mr == bossR && mc == bossC){
                        list.add(Character.toString(id));
                    }
                }

            }

        }


    }

    public static void attack(){
        for(String id : list){
            bossHp -= damage.get(id);
        }
    }


}
