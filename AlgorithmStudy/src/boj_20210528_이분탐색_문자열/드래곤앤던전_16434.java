package boj_20210528_이분탐색_문자열;

/*

드래곤 앤 던전 출처분류
시간 제한   메모리 제한   제출   정답   맞은 사람   정답 비율
1 초   256 MB   2807   814   580   28.128%
문제
용사는 공주를 구하기 위해 무시무시한 용이 있는 던전으로 향하기로 하였습니다. 우선 용사는 용사 자신과 던전을 분석하였습니다.

용사에게는 세 종류의 능력치가 있습니다.

HMaxHP : 용사의 최대 생명력입니다. 이 값은 1이상이어야 하며 던전에 들어간 이후로 변하지 않습니다.
HCurHP : 현재 용사의 생명력입니다. 던전에 들어가기 전 이 값은 용사의 최대 생명력 HMaxHP와 같습니다. 이 값은 HMaxHP보다 커질 수 없습니다.
HATK : 용사의 공격력입니다.
던전은 총 N개의 방으로 이루어져 있고 i번째 방을 통해서만 i+1번째 방으로 이동 할 수 있습니다.
방에는 포션이 있거나 몬스터가 있는데 몬스터가 있을 경우 몬스터를 쓰러트려야지 다음방으로 이동 할 수 있습니다.
N번째 방에는 공주와 용이 있고, 용을 무찌르면 공주를 구할 수 있습니다.

몬스터가 있는 방에 올 경우 다음과 같이 전투가 진행됩니다.

용사의 공격력 HATK만큼 몬스터의 생명력을 깎습니다.
몬스터의 생명력이 0 이하이면 전투가 종료되고 용사는 다음 방으로 이동합니다.
몬스터의 공격력만큼 용사의 생명력 HCurHP를 깎습니다.
용사의 생명력이 0 이하이면 전투가 종료되고 용사는 사망합니다.
다시 1부터 진행합니다.
포션이 있는 방에 올 경우 포션을 마셔서 현재 용사의 생명력 HCurHP가 일정량 회복되고 공격력 HATK도 일정량만큼 증가됩니다.
회복된 생명력이 최대 생명력 HMaxHP보다 큰 경우 용사의 현재 생명력 HCurHP가 최대 생명력 HMaxHP와 같아집니다.

용사는 던전으로 향하기 전에 만반의 준비를 하고 있습니다.
용사는 수련을 하면 최대 생명력 HMaxHP를 늘릴 수 있는데 얼마나 수련해야 할지 고민입니다.

용사는 N번 방에 있는 용을 쓰러트리기 위한 최소의 HMaxHP를 여러분이 계산해주면 좋겠다고 합니다.

입력
첫 번째 줄에 방의 개수 N (1 ≤ N  ≤ 123,456) 과 용사의 초기 공격력 HATK (1 ≤ HATK  ≤ 1,000,000) 가 주어집니다.

i+1번째 줄엔 i번째 방의 정보를 나타내는 세개의 정수 ti, ai, hi (ti ∈ {1, 2}, 1 ≤ ai, hi  ≤ 1,000,000) 가 주어집니다.

ti가 1인 경우 공격력이 ai이고 생명력이 hi인 몬스터가 있음을 나타내고, ti가 2인 경우 용사의 공격력 HATK를 ai만큼 증가시켜주고 용사의 현재 생명력 HCurHP를 hi만큼 회복시켜주는 포션이 있음을 나타냅니다.

출력
용사가 N번째 방에 있는 용을 쓰러트리기 위한 최소의 HMaxHP를 출력합니다.

예제 입력 1
3 3
1 1 20
2 3 10
1 3 100
예제 출력 1
49
예제 입력 2
1 1
1 1000000 1000000
예제 출력 2
999999000001

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// https://www.acmicpc.net/problem/16434
public class 드래곤앤던전_16434 {
    private static int N;
    private static int H;
    
    private static int[][] game;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        
        game = new int[N + 1][3];

        // t = 1 ? 몬스터 : 포션
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            game[i][0] = Integer.parseInt(st.nextToken());
            game[i][1] = Integer.parseInt(st.nextToken());
            game[i][2] = Integer.parseInt(st.nextToken());
        }
        
        long maxHP = getMaxHP();
        
        long resultHP = binarySearch(maxHP);
        
        System.out.println(resultHP);

        br.close();
    }
    
    private static long binarySearch(long maxHP) {
    	long start = 0;
    	long end = maxHP;
    	long mid = 0;
    	
    	while(start < end) {
    		mid = (start + end) / 2;
    		
    		if(endGame(mid)) {
    			start = mid + 1;
    		}else {
    			end = mid;
    		}
    	}
    	
    	return end;
    }
    
    private static long getMaxHP() {
    	long HP = 1;
    	long turn = 0;
    	long attack = H;
    	for (int i = 1; i <= N; i++) {
    		if (game[i][0] == 1) {
    			turn = (game[i][2] - 1) / attack;
    			HP += game[i][1] * turn;
    		}
    	}
    	
    	return HP;
    }
    
    private static boolean endGame(long maxHP) {
    	long curHP = maxHP;
    	long turn = 0;
    	long attack = H;
    	for(int i = 1; i <= N; i++) {
    		if(game[i][0] == 1) {
    			turn = (game[i][2] - 1) / attack;
    			curHP -= game[i][1] * turn;
    			
    			if(curHP <= 0) {
    				return true;
    			}
    			continue;
    		}
    		
    		if(game[i][0] == 2) {
    			attack += game[i][1];
    			curHP += game[i][2];
    			if(curHP > maxHP) {
    				curHP = maxHP;
    			}
    		}
    	}
    	
    	return false;
    }
}