package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 문자열지옥에빠진호석 {

    static char map[][];

    static int a[] = {1,-1,0,0,1,-1,1,-1};
    static int b[] = {0,0,-1,1,1,-1,-1,1};
    static int n;
    static int m;
    static int ans;
    static HashMap<String,Integer> str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String tmp[] = br.readLine().split(" ");

        n = Integer.parseInt(tmp[0]);
        m = Integer.parseInt(tmp[1]);
        int k = Integer.parseInt(tmp[2]);

        map = new char[n][m];

        ans = 0;
        //맵 설정
        for(int i = 0 ; i < n ;i++){
            map[i] = br.readLine().toCharArray();
        }

        //문자열 받기
        str = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for(int t = 0 ; t < k ;t++){

            String input = br.readLine();
            int cnt = str.getOrDefault(input,0);

            if(cnt != 0){
                sb.append(str.get(input)+"\n");
                continue;
            }

            str.put(input,0);
            ans = 0;

            for(int i = 0 ;i  < n;i++){
                for(int j = 0 ; j < m ;j++){
                    if(map[i][j] == input.charAt(0)){
                        recursive(i,j,Character.toString(map[i][j]),0,input,t);
                        str.put(input,ans);
                    }
                }
            }
            sb.append(ans+"\n");

        }


        System.out.println(sb.toString());



    }

    public static void recursive(int r,int c,String curStr,int depth,String testCase,int t){
        if(depth == testCase.length()-1){
            ans++;
            return;
        }


        for(int i = 0 ; i < 8 ;i++){
            int mr = (r+a[i]+n)%n;
            int mc = (c+b[i]+m)%m;

            if(map[mr][mc] == testCase.charAt(depth+1)){
                recursive(mr,mc,curStr+map[mr][mc],depth+1,testCase,t);
            }
        }

    }




}
