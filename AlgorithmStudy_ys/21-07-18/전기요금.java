package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 전기요금 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){

            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a == 0 && b == 0) break;

            int left = 0;
            int right = chargeTowatt(a);

            int standard = chargeTowatt(a) ;

            /**
             *  left와 right 는 사용량
             *  총 사용량을 알 때 상근이의 사용량과 이웃의 사용량을 알아야함 -> 둘의 전기요금의 차이는 b
             *  뭐를 기준으로 이분탐색?
             *
             *  총 사용량은 standard라고 설정 그러므로
             *  int chargeA = wattTocharge(mid);
             *  int chargeB = wattTocharge(standard -mid);
             *  이부분을 이용해 사용량이 항상 standard일때 두 사용량의 요금을 구해준후 기준에 부합한지 판단해준다.
             */
            while(left <= right){
                int mid = (left + right)/2;



                int chargeA = wattTocharge(mid);
                int chargeB = wattTocharge(standard -mid);

                int charge = chargeB - chargeA;



                if(charge > b){
                    left = mid+1;
                }else if (charge < b){
                    right = mid-1;
                }else{
                    System.out.println(wattTocharge(mid));


                    break;
                }



            }
        }




    }
    public static int chargeTowatt(int charge){
        int watt = 0;


        while(charge > 0){
            if(charge > 4979900){
                int over = charge - 4979900;
                charge -= over;
                watt += over/7;
            }else if ( charge > 29900){
                int over = charge - 29900;
                charge -= over;
                watt += over/5;
            }else if (charge > 200){
                int over = charge -200;
                charge -= over;
                watt +=over/3;
            }else {
                watt += charge/2;
                charge = 0;
            }
        }


        return watt;
    }



    public static int wattTocharge(int watt){
        int pay = 0 ;

        while( watt > 0){
            if(watt > 1000000){
                int over = watt-1000000;
                watt -= over;
                pay += 7*over;
            }else if ( watt > 10000){
                int over = watt-10000;
                watt -= over;
                pay += 5*over;
            }else if ( watt > 100){
                int over = watt-100;
                watt -= over;
                pay += 3*over;
            }else{
                pay += 2*watt;
                watt = 0;
            }
        }

        return pay;
    }

}
