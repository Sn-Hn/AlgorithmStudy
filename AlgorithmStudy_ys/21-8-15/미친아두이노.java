import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 미친아두이노 {
    static class Point{
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


    static int map[][];
    static int r;
    static int c;
    static ArrayList<Point> crazy;
    static Point jongsoo;
    static int a[] = { 1,1,1, 0,0,0,-1,-1,-1};
    static int b[] = {-1,0,1,-1,0,1,-1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        crazy = new ArrayList<>();

        for(int i = 0 ; i < r ; i++){
            char tmp[] = br.readLine().toCharArray();
            for(int j = 0 ; j < c ; j++){
                if(tmp[j] == 'I'){
                    jongsoo = new Point(i,j);
                }else if(tmp[j] == 'R'){
                    crazy.add(new Point(i,j));
                    map[i][j] = 1;
                }
            }

        }

        /**
         *
         * 1.아두이노 이동 -> 미친아두이노있으면 종료
         * 2.미친아두이노 이동 -> 아두이노와 가장 가까운 위치로( 아두이노가 있으면 끝)
         * 3.미친아두이노가 2개또는 그이상 같은위치이면 그 칸 아두이노 모두 파괴
         */
        String move[] = br.readLine().split("");

        boolean flag = false;
        for(int i = 0 ; i < move.length;i++){
            int way = Integer.parseInt(move[i]);

            if(!jongSooMove(way)){
                System.out.println("kraj "+ (i+1));
                flag = true;
                break;
            }
        }
        if(!flag){
            map[jongsoo.r][jongsoo.c] = -1;

            for(int a[] : map){
                for(int n : a){
                    if(n == 1) System.out.print("R");
                    else if (n == -1) System.out.print("I");
                    else System.out.print(".");
                }
                System.out.println();
            }

        }


    }

    public static boolean jongSooMove(int way){
        int moveR = jongsoo.r+a[way-1];
        int moveC = jongsoo.c+b[way-1];

        jongsoo.r = moveR;
        jongsoo.c = moveC;
        if(map[moveR][moveC] == 1) return false;
        if(!crazyMove()) return false;

        return true;
    }


    public static boolean crazyMove(){

        for(Point p : crazy){
            int minDis = Integer.MAX_VALUE;
            int idx = -1;

            for(int i = 0 ; i < 9 ;i++){

                int mr = p.r+a[i];
                int mc = p.c+b[i];

                if(mr >= r || mc >= c || mr <0 || mc <0) continue;
                int dis = Math.abs(jongsoo.r-mr) + Math.abs(jongsoo.c -mc);

                if (minDis >  dis ){
                    minDis = dis;
                    idx = i;
                }
            }


            map[p.r][p.c] -= 1;
            map[p.r+a[idx]][p.c+b[idx]] +=1;

            if( (p.r+a[idx]) == jongsoo.r && jongsoo.c == (p.c+b[idx]) ) return false;
        }

        crazy.clear();

        for(int i = 0 ; i < r ; i++){
            for(int j = 0 ; j < c ;j++){
                if(map[i][j] == 1){
                    crazy.add(new Point(i,j));
                }else{
                    map[i][j] = 0;
                }
            }
        }


        return true;
    }





}
