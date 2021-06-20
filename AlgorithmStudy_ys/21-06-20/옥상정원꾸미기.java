package 스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


//백준 6198 옥상정원 꾸미기
public class 옥상정원꾸미기 {
    static long building[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        building = new long[n];
        for(int i = 0 ; i < n;i++){
            building[i] = Long.parseLong(br.readLine());
        }


        Stack<Long> st = new Stack<>();
        long sum = 0;

        for(int i = 0; i < n;i++){
            while(!st.isEmpty() && building[i] >= st.peek() ){
                st.pop();
            }
            st.push(building[i]);


            sum += st.size()-1;
            System.out.println(st);
            System.out.println(sum);

        }


        System.out.println(sum);
    }
}
