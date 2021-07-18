package 누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class 피자판매 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int pizzaSize = Integer.parseInt(br.readLine());

        StringTokenizer st=  new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int pizzaA[] = new int[m];
        int pizzaB[] = new int[n];


        /**
         * 접근법
         * 피자를 돌면서 합이 나올수있는 경우의 수를 다 구해서 Map에 넣고 그 개수를 카운트
         * A피자 B피자 두개 다 저장해준다
         * 0부터 피자 사이즈 만큼 돌면서
         * if(amap.containsKey(i) && bmap.containsKey(pizzaSize-i)) 피자 조각크기 i와 목표피자사이즈-i 두개다 존재한다면 가능하다고 판단해
         * map에 저장된 두개의 개수를 곱해서 더해준다.
         *
         * 이분탐색 방법도 생각해볼것.
         */

        for(int i = 0 ; i < m ; i++){
            pizzaA[i] = Integer.parseInt(br.readLine());
        }

        for(int i  = 0 ; i < n; i++){
            pizzaB[i] = Integer.parseInt(br.readLine());
        }

        HashMap<Integer,Integer> amap = sum(pizzaA,m);
        HashMap<Integer,Integer> bmap = sum(pizzaB,n);


        int ans = 0;
        for(int i = 0 ; i <= pizzaSize ;i++){
            if(amap.containsKey(i) && bmap.containsKey(pizzaSize-i)){
                ans += amap.get(i)*bmap.get(pizzaSize-i);
            }
//            System.out.println("포인트a"+i+"포인트b"+(pizzaSize-i));
//            System.out.println(ans);
        }

        System.out.println(ans);
    }


    public static HashMap sum(int arr[],int size){

        HashMap<Integer,Integer> map = new HashMap<>();

        for(int i = 0 ; i < size;i++){
            int sum = 0;
            for(int j = i  ; j < i+size-1;j++){
                sum += arr[j%size];
                if(map.containsKey(sum)){
                    map.replace(sum, map.get(sum)+1);
                }else{
                    map.put(sum,1);
                }
            }
        }


        int total = 0;
        for(int i = 0 ; i < size;i++){
            total +=arr[i];
        }

        map.put(total,1);
        map.put(0,1);


        return map;
    }


}
