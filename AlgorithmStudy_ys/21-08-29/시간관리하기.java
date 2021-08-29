package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 시간관리하기 {

    static class workTime{
        int needTime;
        int endTime;

        @Override
        public String toString() {
            return "wordTime{" +
                    "needTime=" + needTime +
                    ", endTine=" + endTime +
                    '}';
        }

        public workTime(int needTime, int endTine) {
            this.needTime = needTime;
            this.endTime = endTine;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());


        PriorityQueue<workTime> pq = new PriorityQueue<>(new Comparator<workTime>() {
            @Override
            public int compare(workTime o1, workTime o2) {
                return o2.endTime-o1.endTime;
            }
        });


        for(int i = 0 ; i< N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.add(new workTime(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }


        int time = pq.peek().endTime;

        while(!pq.isEmpty()){

            workTime cur = pq.poll();

            if(time > cur.endTime){
                time = cur.endTime;
            }
            time -= cur.needTime;


        }

        if(time < 0){
            System.out.println(-1);
        }else{
            System.out.println(time);

        }






    }
}
