package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 전구 {

    static class TrackInfo{
        int value;
        int idx;

        public TrackInfo(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }

    }



    static int swi[];
    static int bulbArr[];
    static ArrayList<Integer> list;
    static ArrayList<TrackInfo> trackList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        swi = new int[n];
        bulbArr = new int[n];
        list = new ArrayList<>();

        String tmp[] = br.readLine().split(" ");
        String tmp2[] = br.readLine().split(" ");

        HashMap<Integer,Integer> swichMap = new HashMap<>();

        for(int i = 0 ; i < n ;i++){
            swi[i] = Integer.parseInt(tmp[i]);
            swichMap.put(Integer.parseInt(tmp[i]),i);
        }

        /**
         * 스위치에서 전구로 가는 선이 겹치면 안된다 -> 가리키는 전구의 순서가 증가해야 한다.
         * 전구를 가리키는 스위치의 인덱스와 전구의 번호를 함께 저장
         * 인덱스가 증가해야 선이 겹치지 않는다.
         * 가리키는 전구의 인덱스를 기준으로 LIS 를 진행
         *
         * LIS를 진행해서 나온 결과의 실제 배열을 구해야하기 때문에 역추적 해야한다.
         *
         * 참고
         *         실제 lis 배열을 구하는 알고리즘을 보자
         *         예를들면 다음과 같다.
         *
         *         1 6 2 5 7 3 5 6인 경우
         *         ans배열에는 다음과 같이 들어간다.
         *
         *         first ::  0 1 1 2 3 2 3 4
         *         second :: 1 6 2 5 7 3 5 6
         *
         *         이 값을 first를 기준으로 뒤에서 부터 조사해오면
         *         first가 4일때 (6) - > first가 3일때 (5) -> first가 2일때 (3)
         *         -> first가 1일때 (2) -> first가 0일때(1)이다.
         *         이것을 스택에 담아 역출력하면 1,2,3,5,6이라는 실제 배열을 구할 수 있다.
         *
         *
         * 출처: https://www.crocus.co.kr/681 [Crocus]
         *
         * 배열을 역추적 한 결과가 원래 스위치의 인덱스이다.
         * 그러므로 스위치의 인덱스를 스위치의 번호로 치환하여 답을 구한다.
         *
         */

        for(int i = 0 ; i < n ;i++){
            int num = Integer.parseInt(tmp2[i]);
            bulbArr[i] = swichMap.get(num);

        }

        LIS();



        Stack<Integer> st = new Stack<>();
        int idx=  list.size()-1;

        for(int i = trackList.size()-1; i >=0 ;i--){
            if(trackList.get(i).value == idx ){
                st.push(swi[trackList.get(i).idx]);
                idx--;
            }
        }

        StringBuilder sb= new StringBuilder();

        Collections.sort(st, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        while(!st.isEmpty()){
            sb.append(st.pop()+" ");
        }


        System.out.println(list.size());
        System.out.println(sb.toString());



    }

    public static void LIS(){

        trackList = new ArrayList<>();

        for(int i = 0 ; i < bulbArr.length;i++){
            int get = bulbArr[i];
            if(list.size() == 0 || get> list.get(list.size()-1)){
                list.add(get);
                trackList.add(new TrackInfo(list.size()-1,get));

            }else{
                //
                int bsRes = BS(get);
                list.set(bsRes,get);
                trackList.add(new TrackInfo(bsRes,get));

            }


        }


    }

    public static int BS(int value){

        int left = 0;
        int right = list.size()-1;

        while(left<=right){
            int mid = (left+right)/2;

            int midValue = list.get(mid);

            if(midValue < value){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }

        return left;

    }


}
