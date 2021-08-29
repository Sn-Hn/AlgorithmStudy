package 분리집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇조립 {
    static int num[];
    static long parts[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        num = new int[1000001];
        parts = new long[1000001];

        for(int i = 0 ; i <1000001;i++){
            num[i] = i;
            parts[i] = 1;
        }
        StringBuilder sb= new StringBuilder();
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st=  new StringTokenizer(br.readLine());
            String I = st.nextToken();

            if(I.equals("I")){
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if(!find(a,b)){
                    union(a,b);
                }
            }else{
                int a = Integer.parseInt(st.nextToken());
                int parent = getParent(a);
                sb.append(parts[parent]+"\n");
            }


        }

        System.out.println(sb.toString());


    }

    private static boolean find(int a, int b) {
        a = getParent(a);
        b = getParent(b);

        if(a == b) {
            return true;
        }else {
            return false;
        }

    }

    public static int getParent(int x){
        if(num[x] == x) return x;

        return num[x] = getParent(num[x]);
    }


    public static void union(int a,int b){
        a = getParent(a);
        b = getParent(b);

        if(a < b) {
            num[b] =a;
            parts[a] += parts[b];
            parts[b] = 0;
        }else {
            num[a] =b;
            parts[b] += parts[a];
            parts[a] = 0;
        }


    }

}
