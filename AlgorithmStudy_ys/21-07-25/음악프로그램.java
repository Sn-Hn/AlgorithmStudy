package 위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 음악프로그램 {



    static int singer[];
    static ArrayList<Integer> list[];
    static ArrayList<Integer> ans = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        singer = new int[n+1];
        list = new ArrayList[n+1];
        for(int i = 1 ;i <= n;i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < m;i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int pre = Integer.parseInt(st.nextToken());
            for(int j = 1 ; j < num ;j++){
                int cur = Integer.parseInt(st.nextToken());
                singer[cur]++;
                list[pre].add(cur);
                pre = cur;
            }
        }

        /**
         * 접근
         * 피디마다 각자 담당한 가수의 출연 순서가 정해져 있음으로
         * 자신보다 먼저 출연해야 하는 가수들의 노드와 연결하고
         * 자시의 앞에 출연해야하는 가수의 명수를 저장하고
         * 위상정렬을 진행한다.
         *
         */



        topology(n);

       if(ans.size() == n){
           ans.forEach(num -> System.out.println(num));
       }else{
           System.out.println(0);
       }


    }

    public static void topology(int n){
        Queue<Integer> que = new LinkedList<>();

        for(int i = 1 ; i <= n;i++){
            if(singer[i] == 0){
                ans.add(i);
                que.add(i);
            }
        }


        while(!que.isEmpty()){

            int cur = que.poll();

            for(int i = 0 ; i < list[cur].size();i++){
                int get = list[cur].get(i);
                singer[get]--;

                if(singer[get] == 0){
                    ans.add(get);
                    que.add(get);
                }

            }
        }



    }


}
