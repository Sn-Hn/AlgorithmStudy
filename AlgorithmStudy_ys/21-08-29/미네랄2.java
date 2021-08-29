package DFS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 미네랄2 {

    static String map[][];

    static int r;
    static int c;
    static boolean visited[][];
    static int a[] = {1,-1,0,0};
    static int b[] = {0,0,-1,1};
    static ArrayList<int[]> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new String[r][c];
        visited = new boolean[r][c];

        for(int i = 0 ; i < r ; i++) {
            map[i] = br.readLine().split("");
        }


        int n = Integer.parseInt(br.readLine());
        int height[] = new int [n];


        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < n ; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }


//		1 막대기를 던진다 -> 미네랄이 부셔지고 클러스터가 이어져있는지 dfs
//		던져서 왼쪽 오른쪽 판단 후 미네랄 부시는 메소드
//		미네랄을 부신후 클러스터가 떠있는지여부 확인 후 떨어뜨리는 메소드
//		맨 밑에있는 미네랄에 dfs를 돌리면 떠있는 클러스터가 나온다.

        for(int i = 0 ; i< n ; i++) {
            stickthrow(i,height[i]);
        }

        for(int i = 0 ; i < r ; i++) {
            for(int j = 0 ;j < c ; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }


    }


    public static void stickthrow(int order,int height ) {

        for(int j = 0 ; j < r ;j++) {
            Arrays.fill(visited[j], false);
        }

        int direction = order%2;
        if(direction == 0) { //왼쪽
            for(int i = 0 ; i < c ; i++) {
                if(map[r-height][i].equals("x")) {
                    map[r-height][i] = ".";
                    break;
                }
            }

        }else if(direction == 1){ // 오른쪽
            for(int i = c-1 ; i >= 0 ; i--) {
                if(map[r-height][i].equals("x")) {
                    map[r-height][i] = ".";
                    break;
                }
            }

        }


        // dfs
        for(int i = 0 ; i < c ; i++) {

            if(map[r-1][i].equals("x")) {
                dfs(r-1,i);
            }
        }

        // 바닥에 안닿은 부분 탐색
        list = new ArrayList<int[]>();
        for(int i = 0 ; i < r ; i++) {
            for(int j = 0 ; j < c ; j++) {
                if(!visited[i][j] && map[i][j].equals("x")) {
                    list.add(new int[] {i,j});
                    map[i][j] = ".";
                }
            }
        }

        if(list.size() != 0) {
            //내리기
            down();
        }



    }


    public static void dfs(int x,int y) {


        for(int i = 0 ; i < 4 ; i++) {
            int mx = x + a[i];
            int my = y + b[i];

            if(mx >=0 && my >= 0 && mx <r && my <c) {
                if(!visited[mx][my] && map[mx][my].equals("x") ) {
                    visited[mx][my] = true;
                    dfs(mx,my);
                }

            }

        }

    }

    public static void down() {

        boolean flag = true;
        while(flag) {
            for(int i = 0 ; i < list.size(); i++) {
                int x = list.get(i)[0]+1;
                int y = list.get(i)[1];

                if(x>=r || map[x][y].equals("x")) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                for(int i = 0 ; i < list.size(); i++) {
                    list.get(i)[0] = list.get(i)[0]+1;
                }
            }

        }

        for(int i = 0 ; i < list.size(); i++) {
            map[list.get(i)[0]][list.get(i)[1]]= "x";

        }



    }


}
