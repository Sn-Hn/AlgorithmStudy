package study_210718;

/*

전기 요금 출처다국어
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	544	181	140	34.826%
문제
최근에 전기 회사는 전기 요금을 또 올렸다. 새로운 전기 요금은 아래 표에 나와있다. (사용량은 항상 양의 정수)

사용량 (Watt-hour)	요금 (원)
1 ~ 100	2
101 ~ 10000	3
10001 ~ 1000000	5
> 1000000	7
위의 표를 읽는 방법은 다음과 같다.

사용량의 첫 100Wh의 가격은 1Wh당 2원이다. 다음 9900Wh (101 ~ 10000)의 가격은 1Wh당 3원이다. 이런식으로 계속 계산한다.

예를 들어, 10123Wh를 사용했을 때, 내야하는 요금은 2×100 + 3×9900 + 5×123 = 30515원이다.

전기 회사는 전기 요금을 인상하지 않고 돈을 더 버는 이상한 방법을 만들었다. 
그 방법은 바로 사용한 전기의 양을 알려주지 않고, 얼마를 내야 하는지 알려주는 것이다. 
전기 회사는 요금과 관련된 정보를 나타내는 두 숫자 A와 B를 알려준다. A와 B는 전기 회사에서 그 사람이 사는 건물에서 임의로 고른 이웃의 정보와 합친 요금이다.

A: 이웃의 사용량과 사용량을 합쳤을 때 내야하는 요금
B: 이웃의 전기 요금과의 차이 (절댓값)
위의 두 숫자를 이용해서 자신이 얼마를 내야 하는지를 계산할 수 없을 때는, 계산 요금을 100원을 더 내면 전기 회사에서 사용량을 알려준다.

상근이는 매우 전기를 아끼는 사람이다. 따라서, 항상 자신이 사는 건물에서 가장 전기를 적게 쓴다고 확신한다. 
상근이는 돈도 전기만큼 아낀다. 따라서, 절대로 계산 요금을 지불하지 않고 자신이 직접 계산할 것이다.

예를 들어, A = 1100, B = 300이라고 하자. 
이 정보를 이용하면, 상근이의 사용량은 150Wh, 이웃의 사용량은 250Wh임을 알 수 있다. 
두 사람의 총 사용량은 400Wh이다. 
따라서, A = 2×100 + 3×300 = 1100이 된다. 
따라서, 상근이는 350원을 내면 된다. 
상근이의 이웃은 2×100 + 3×150 = 650원을 내야 하고, B = |350 - 650| = 300이 된다.

A와 B가 주어졌을 때, 상근이가 내야하는 전기 요금을 구하는 프로그램을 작성하시오.

입력
입력은 여러 개의 테스트 케이스로 이루어져 있다. 
각 테스트 케이스는 한 줄로 이루어져 있고, 두 정수 A와 B가 주어진다. (1 ≤ A, B ≤ 109) 
항상 정답이 유일한 경우만 주어지며, 입력으로 주어지는 두 숫자를 만들 수 있는 사용량은 딱 한 쌍 존재한다.

입력의 마지막 줄에는 0이 두 개 주어진다.

출력
각 테스트 케이스에 대해서, 상근이가 내야 하는 요금을 출력한다.

예제 입력 1 
1100 300
35515 27615
0 0
예제 출력 1 
350
2900

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 전기요금_5710 {
	private static int A;
	private static int B;
	private static int[] usageCost = {2, 3, 5, 7};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder result = new StringBuilder();
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			if (endTC(A, B)) {
				break;
			}
			result.append(binarySearch(getUsage(A))).append("\n");
		}
		
		System.out.println(result.toString());
		
		br.close();
	}
	
	private static long binarySearch(long mergeUsage) {
		long start = 0;
		long end = mergeUsage;
		long mid = 0;
		
		long sanggeun = 0;
		long neighbor = 0;
		
		while(start < end) {
			mid = (start + end) / 2;
			sanggeun = getCost(mid);
			neighbor = getCost(mergeUsage - mid);
			
			if (Math.abs(sanggeun - neighbor) < B) {
				end = mid;
			}else if (Math.abs(sanggeun - neighbor) > B) {
				start = mid + 1;
			}else {
				return Math.min(sanggeun, neighbor);
			}
		}
		
		return 0;
	}
	
	private static long getCost(long usage) {
		long cost = 0;
		while(usage > 0) {
			cost += getUsageElectricCost(usage);
			usage = getUsageInit(usage);
		}
		
		return cost;
	}
	
	private static long getUsageElectricCost(long cost) {
		if (cost <= 100) {
			return usageCost[0] * cost;
		}
		
		if (cost <= 10000) {
			return usageCost[1] * (cost - 100);
		}
		
		if (cost <= 1000000) {
			return usageCost[2] * (cost - 10000);
		}
		
		return usageCost[3] * (cost - 1000000);
	}
	
	private static long getUsageInit(long usage) {
		if (usage > 1000000) {
			return 1000000;
		}
		
		if (usage > 10000) {
			return 10000;
		}
		
		if (usage > 100) {
			return 100;
		}
		
		return 0;
	}
	
	private static long getUsage(long cost) {
		int maxCost = usageCost[0] * 100;
		int prev = maxCost;
		if (cost <= maxCost) {
			return cost / usageCost[0];
		}
		maxCost += usageCost[1] * 9900;
		if (cost <= maxCost) {
			return (cost - prev) / usageCost[1] + 100;
		}
		prev = maxCost;
		maxCost += usageCost[2] * 990000;
		if (cost <= maxCost) {
			return (cost - prev) / usageCost[2] + 10000;
		}
		
		prev = maxCost;
		return (cost - prev) / usageCost[3] + 1000000;
	}
	
	private static boolean endTC(int A, int B) {
		if (A == 0 && B == 0) {
			return true;
		}
		
		return false;
	}
}
