package boj_20210520_복습;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

공항 출처다국어분류
시간 제한   메모리 제한   제출   정답   맞은 사람   정답 비율
1 초   256 MB   8031   3013   2178   37.944%
문제
오늘은 신승원의 생일이다.

박승원은 생일을 맞아 신승원에게 인천국제공항을 선물로 줬다.

공항에는 G개의 게이트가 있으며 각각은 1에서 G까지의 번호를 가지고 있다.

공항에는 P개의 비행기가 순서대로 도착할 예정이며, 당신은 i번째 비행기를 1번부터 gi (1 ≤ gi ≤ G) 번째 게이트중 하나에 영구적으로 도킹하려 한다. 비행기가 어느 게이트에도 도킹할 수 없다면 공항이 폐쇄되고, 이후 어떤 비행기도 도착할 수 없다.

신승원은 가장 많은 비행기를 공항에 도킹시켜서 박승원을 행복하게 하고 싶어한다. 승원이는 비행기를 최대 몇 대 도킹시킬 수 있는가?

입력
첫 번째 줄에는 게이트의 수 G (1 ≤ G ≤ 105)가 주어진다.

두 번째 줄에는 비행기의 수 P (1 ≤ P ≤ 105)가 주어진다.

이후 P개의 줄에 gi (1 ≤ gi ≤ G) 가 주어진다.

출력
승원이가 도킹시킬 수 있는 최대의 비행기 수를 출력한다.

예제 입력 1
4
3
4
1
1
예제 출력 1
2
예제 입력 2
4
6
2
2
3
3
4
4
예제 출력 2
3
힌트
예제 1 : [2][?][?][1] 형태로 도킹시킬 수 있다. 3번째 비행기는 도킹시킬 수 없다.

예제 2 : [1][2][3][?] 형태로 도킹 시킬 수 있고, 4번째 비행기는 절대 도킹 시킬 수 없어서 이후 추가적인 도킹은 불가능하다.


# 정리
1. 4가 입력되면 1, 2, 3, 4 를 Union
2. 4의 부모들 중 가장 큰 수에 도킹
3. 도킹은 배열을 만들어 gates 배열에 +1
3. 도킹이 안되면 출력
4. 도킹이 되고 2이 입력
5. 1, 2 Union (그 전에 Union한 1, 2, 3, 4를 초기화 시켜야 함 - 왜냐하면 3, 4의 부모까지 찾기 때문)
6. 2의 부모가 같은 것들 중 최대
7. 반복 후 gates[i]가 1이면 exit

-> 따라서 parent 배열을 계속 초기화 시켜줘야 할 것 같다..
-> 시간 복잡도, 공간 복잡도 계산
->

*/

// 가장 기본적인 구현
// 메모리 : 21900KB, 시간 : 2108ms

public class 공항_10775 {
    private static int N;
    private static int M;
    private static int[] gates;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        gates = new int[N + 1];


        for (int i = 1; i <= M; i++) {
            int gate = Integer.parseInt(br.readLine());
            if(!isDocking(gate)) {
            	break;
            }
        }
        
        System.out.println(getMaxCountAirplane());

        br.close();
    }

    private static boolean isDocking(int gate) {
    	for (int i = gate; i > 0; i--) {
    		if(gates[i] != 1) {
    			gates[i] = 1;
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    private static int getMaxCountAirplane() {
    	int cnt = 0;
    	for (int i = 1; i <= N; i++) {
    		cnt += gates[i];
    	}
    	
    	return cnt;
    }

}