package solution293;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    static ArrayList<Integer> list[];
    static int dp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //직원의수
        int n = Integer.parseInt(br.readLine());
        list = new ArrayList[n];
        dp = new int [n];
        for(int i = 0 ; i < n; i++){
            list[i] = new ArrayList<>();
        }

        String tmp[] = br.readLine().split(" ");
        for(int i = 0 ; i < n ;i++){
            int parentsNum = Integer.parseInt(tmp[i]);
            if(parentsNum != -1){
                list[parentsNum].add(i);
            }
        }

        System.out.println(recursive(0));




    }


    public static int recursive(int node){

        int ret = 0;
        int size = list[node].size();

        Integer depth[] = new Integer[size];
        for(int i = 0 ; i < list[node].size();i++){
            depth[i] = recursive(list[node].get(i))+1;

        }

        Arrays.sort(depth, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        for(int i = 0 ; i < list[node].size();i++){
            depth[i] +=i;
            ret = Math.max(ret,depth[i]);
        }

        return ret;
    }



}
