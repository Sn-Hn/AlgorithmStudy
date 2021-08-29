package 트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 스크루지민호 {


    static class Node{
        int num;
        int len;

        public Node(int num, int len) {
            this.num = num;
            this.len = len;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", len=" + len +
                    '}';
        }
    }

    static ArrayList<Node> list[];
    static boolean visited[];
    static int max = 0;
    static int length[];
    static int idx = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));



        int N = Integer.parseInt(br.readLine());
        list = new ArrayList[N+1];

        for(int i = 0 ; i <= N ;i++){
            list[i] = new ArrayList();
        }

        for(int i = 0 ; i < N-1 ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list[u].add(new Node(v,0) );
            list[v].add(new Node(u,0) );

        }

        int ans = Integer.MAX_VALUE;
        visited = new boolean[N+1];
        length = new int[N+1];
        visited[1] = true;
        recursive(1,0);
        System.out.println("인덱스"+idx+"길이:"+max);

        max = 0;
        Arrays.fill(length,0);
        visited = new boolean[N+1];
        visited[idx] = true;
        recursive(idx,0);
        System.out.println((max+1)/2);



    }




    public static int recursive(int cur,int len){

        for(int i = 0 ; i < list[cur].size();i++){
            Node get = list[cur].get(i);

            if(!visited[get.num]){
                visited[get.num] = true;
                recursive(get.num,len+1);
            }

        }

        if(max < len){
            max = len;
            idx = cur;
        }

        length[cur] = len;


        return length[cur];
    }


}
