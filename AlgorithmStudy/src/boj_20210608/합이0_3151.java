package boj_20210608;

/*

합이 0 출처다국어분류
시간 제한   메모리 제한   제출   정답   맞은 사람   정답 비율
4 초   128 MB   465   106   78   25.828%
문제
Elly는 예상치 못하게 프로그래밍 대회를 준비하는 학생들을 가르칠 위기에 처했다.
대회는 정확히 3명으로 구성된 팀만 참가가 가능하다.
그러나 그녀가 가르칠 학생들에게는 큰 문제가 있었다.
코딩 실력이 좋으면 팀워크가 떨어지고, 팀워크가 좋을수록 코딩 실력이 떨어진다.
그리고 출전하고자 하는 대회는 코딩 실력과 팀워크 모두가 중요하다.

Elly는 그녀가 가르칠 수 있는 모든 학생들의 코딩 실력을 알고 있다.
각각의 코딩 실력 (Ai)는 -10000부터 10000 사이의 정수로 표시되어 있다.
그녀는 팀워크와 코딩 실력이 모두 적절한 팀을 만들기 위해, 세 팀원의 코딩 실력의 합이 0이 되는 팀을 만들고자 한다.
이러한 조건 하에, 그녀가 대회에 출전할 수 있는 팀을 얼마나 많이 만들 수 있는지를 계산하여라.

문제 요약: N명의 학생들의 코딩 실력 Ai가 -10000부터 10000사이의 정수로 주어질 때, 합이 0이 되는 3인조를 만들 수 있는 경우의 수를 구하여라.

입력
입력은 표준 입력으로 주어진다.

첫 번째 줄에 학생의 수 N이 입력된다.
두 번째 줄에 N개의 그녀가 가르칠 학생들의 코딩 실력인 Ai가 주어진다.

(1 ≤ N ≤ 10000, -10000 ≤ Ai ≤ 10000)

출력
표준 출력으로 그녀가 고를 수 있는 팀의 수를 하나의 정수로 출력한다.

예제 입력 1
10
2 -5 2 3 -4 7 -4 0 1 -6
예제 출력 1
6
힌트
예시에서 가능한 참가자 그룹은 아래와 같다.

(2, -5, 3), (2, 2, -4), (2, 2, -4), (-5, 2, 3), (3, -4, 1), (3, -4, 1)

두 개의 -4는 서로 다른 참가자를 나타내는 것에 유의하라. [ (2,2,-4)와 (3,-4,1)이 두 번씩 나타남 ]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 합이0_3151 {
    private static int N;
    private static int[] codingSkills;
    private static final int ZERO = 0;
    private static StringBuilder index = new StringBuilder();
    private static StringBuilder value = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        codingSkills = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            codingSkills[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(codingSkills);

        long result = 0;
//        for (int i = 0; i < N - 2; i++) {
//            result += getSumZero(i);
//        }
        
        result = getSumZero2();

        System.out.println(result);

//        System.out.println(index.toString());
//        System.out.println(value.toString());

        br.close();
    }

    private static int getSumZero(int n) {
        int start = n + 1;
        int end = N - 1;
        int count = 0;
        int[] val = new int[3];

        while (start <= end) {
            int sum = codingSkills[n] + codingSkills[start] + codingSkills[end];

            if (ZERO > sum) {
                start++;
            } else if (ZERO < sum) {
                end--;
            } else {
//                val = isSameSkills(start, end);
//                index.append(n).append(" ").append(start).append(" ").append(end).append("\n");
//                value.append(codingSkills[n]).append(" ").append(codingSkills[start]).append(" ").append(codingSkills[end]).append("\n");
                if (codingSkills[start] == codingSkills[end]) {
                	count += end - start;
                	start++;
                	continue;
                }

                int startCount = 1;
                int endCount = 1;
                for (int i = end - 1; i > start; i--) {
                    if (codingSkills[end] != codingSkills[i]) {
                        break;
                    }
                    endCount++;
                }
                
                for (int i = start + 1; i < end; i++) {
                	if (codingSkills[start] != codingSkills[i]) {
                		break;
                	}
                	startCount++;
                }
                
                count += startCount * endCount;
                start += startCount;
                end -= endCount;
            }
        }

        return count;
    }
    
    private static long getSumZero2() {
        int start = 0;
        int end = N - 1;
        int middle = 1;
        long count = 0;

        while (start < N - 2) {
            int sum = codingSkills[start] + codingSkills[middle] + codingSkills[end];

            if(middle >= end) {
            	start += 1;
            	middle = start + 1;
            	end = N - 1;
            	continue;
            }
            
            if (ZERO > sum) {
                middle++;
            } else if (ZERO < sum) {
                end--;
            } else {
            	
                if (codingSkills[middle] == codingSkills[end]) {
                	count += end - middle;
                	middle++;
                	continue;
                }

                int middleCount = 1;
                int endCount = 1;
                for (int i = end - 1; i > middle; i--) {
                    if (codingSkills[end] != codingSkills[i]) {
                        break;
                    }
                    endCount++;
                }
                
                for (int i = middle + 1; i < end; i++) {
                	if (codingSkills[middle] != codingSkills[i]) {
                		break;
                	}
                	middleCount++;
                }
                
                count += middleCount * endCount;
                middle += middleCount;
                end -= endCount;
            }
        }
        
        return count;
    }

    private static int[] isSameSkills(int start, int end) {
    	int[] result = new int[3];
        if (codingSkills[start] == codingSkills[end]) {
        	result[0] = end - start;
        	result[1] = start + 1;
        	result[2] = end;
            return result;
        }
        int startIndex = start + 1;
        int endIndex = end - 1;
        for (int i = end - 1; i > start; i--) {
            if (codingSkills[end] != codingSkills[i]) {
                break;
            }
            endIndex = i - 1;
        }
        
        for (int i = start + 1; i < end; i++) {
        	if (codingSkills[start] != codingSkills[i]) {
        		break;
        	}
        	startIndex = i + 1;
        }
        
        result[0] = (end - endIndex) * (startIndex - start);
        result[1] = startIndex;
        result[2] = endIndex;

        return result;
    }
}