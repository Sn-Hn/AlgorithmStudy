package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


//백준 13334 자료구조.철로(우선순위큐,정렬) 복습하기
public class 철로 {
    static class Info{
        int start;
        int end;

        public Info(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Info infoArr[] = new Info[n];


        for(int i = 0 ; i < n ;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            //집위치(출발)
            int h = Integer.parseInt(st.nextToken());
            //사무실 위치(도착)
            int o = Integer.parseInt(st.nextToken());

            int big = Math.max(h,o);
            int small = Math.min(h,o);
            infoArr[i] = new Info(small,big);

        }

        //철로의 길이
        int d = Integer.parseInt(br.readLine());


        Arrays.sort(infoArr, new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o1.end-o2.end;
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        });

        int ans = 0;
        for(int i = 0 ; i < n;i++){
            pq.add(infoArr[i].start);

            while(!pq.isEmpty() && pq.peek() < infoArr[i].end-d) {
                pq.poll();
            }
            ans = Math.max(ans,pq.size());

        }


        System.out.println(ans);








   }
}
