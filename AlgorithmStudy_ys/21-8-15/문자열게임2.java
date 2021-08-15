package 슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class 문자열게임2 {

    static int minCnt;
    static int maxCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));



        int t= Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < t;i++){
            String w = br.readLine();

            int k = Integer.parseInt(br.readLine());
            int min = Integer.MAX_VALUE;
            int max = 0;

            minCnt = Integer.MAX_VALUE;
            maxCnt = 0;
            HashMap<String, ArrayList> map = new HashMap<String, ArrayList>();
            for(int j = 0 ; j < w.length();j++){


                String key = Character.toString(w.charAt(j));
                if(map.containsKey(key)){
                    map.get(key).add(j);
                }else{
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(j);
                    map.put(key,list);
                }

            }

            map.forEach( (keyValue , v) -> {

                if(v.size() >=k){
                    search(keyValue,v,k);
                }

            });
            if(minCnt == Integer.MAX_VALUE){
                System.out.println(-1);
            }else{
                System.out.println(minCnt+" "+maxCnt);
            }

        }

        System.out.println(sb.toString());

    }


    public static void search(String key,ArrayList<Integer> list,int k){


        //start와 end 사이의 간격이 k이어야 한다.
        int start = 0;
        int end = k-1;

        while(end < list.size()){
            minCnt = Math.min(minCnt,list.get(end)-list.get(start)+1);
            maxCnt = Math.max(maxCnt,list.get(end)-list.get(start)+1);
            start++;
            end++;
        }
    }
}
