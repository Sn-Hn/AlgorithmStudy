package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 가희와은행 {
    static class customer{
        int num;
        int time;
        int enterTime;

        public customer(int num, int time) {
            this.num = num;
            this.time = time;
        }

        public customer(int num, int time, int enterTime) {
            this.num = num;
            this.time = time;
            this.enterTime = enterTime;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        //대기줄 손님
        int N = Integer.parseInt(st.nextToken());

        int T = Integer.parseInt(st.nextToken());

        int W = Integer.parseInt(st.nextToken());

        Queue<customer> que = new LinkedList<>();
        Queue<customer> wait = new PriorityQueue<>(new Comparator<customer>() {
            @Override
            public int compare(customer o1, customer o2) {
                return o1.enterTime-o2.enterTime;
            }
        });

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            //x번손님
            int Px = Integer.parseInt(st.nextToken());
            //업무가 끝나는데 필요한 시간
            int tx = Integer.parseInt(st.nextToken());

            que.add(new customer(Px,tx));
        }

        // 1초 이후에 들어온 고객 수
        int M = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            //x번손님
            int Px = Integer.parseInt(st.nextToken());
            //업무가 끝나는데 필요한 시간
            int tx = Integer.parseInt(st.nextToken());
            //영업 시간 Cx초 이후에 들어왔다.
            int Cx = Integer.parseInt(st.nextToken());

            wait.add(new customer(Px,tx,Cx));

        }


        int spendTime = 0;
        StringBuilder sb = new StringBuilder();

        loop:
        while(!que.isEmpty()){
//            System.out.println(spendTime+"시간"+W);
            customer head = que.peek();
            boolean flag = false;

            if(head.time > T){
                head.time -= T;
                que.poll();
                for(int i = 0 ; i < T ; i++){
//                    System.out.println(head.num);
                    sb.append(head.num+"\n");
                    spendTime++;
                    if(spendTime >= W) break loop;
                }
                flag = true;
            }else{
                que.poll();
                for(int i = 0 ; i < head.time ; i++){
//                    System.out.println(head.num);
                    sb.append(head.num+"\n");
                    spendTime++;
                    if(spendTime >= W) break loop;
                }
            }

            while(!wait.isEmpty()){
                if(spendTime >= wait.peek().enterTime){
//                    System.out.println("들어감"+wait.peek().num);
                    que.add(wait.poll());
                }else{
                    break;
                }
            }

            if(flag){
                que.add(head);
            }
        }
        System.out.println(sb.toString());



    }
}
