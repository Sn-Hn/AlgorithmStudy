package 비트마스킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 단어암기 {


    static int bit[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        bit =  new int[N];



        for(int i = 0 ; i < N; i++){

            for(char c : br.readLine().toCharArray()){
                bit[i] = bit[i] |  1 << (c - 'a');
            }

        }

        int alpha = (1 << 27) -1;
        for(int i = 0 ; i < M ;i++){
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());
            char x = st.nextToken().charAt(0);

            if( o == 1){
                alpha = alpha & ~(1 << x-'a');
            }else{
                alpha = alpha | (1 << x-'a');
            }

            int cnt = 0;
            for(int n : bit){
                if((n & alpha) >= n) cnt++;
            }
            System.out.println(cnt);


        }







    }




}
