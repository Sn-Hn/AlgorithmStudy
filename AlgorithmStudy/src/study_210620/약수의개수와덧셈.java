package study_210620;

class 약수의개수와덧셈 {
	public static void main(String[] args) {
		int left = 13;
		int right = 17;
		// return 43
		
		System.out.println(solution(left, right));
	}
	
    public static int solution(int left, int right) {
        int answer = 0;

        for (int i = left; i <= right; i++) {
            int measureCount = getMeasure(i);
            if (measureCount % 2 == 0) {
                answer += i;
            }else {
                answer -= i;
            }
        }

        return answer;
    }

    private static int getMeasure(int x) {
        int cnt = 0;
        for (int i = 1; i <= x; i++) {
            if (x % i == 0) {
                cnt++;
            }
        }

        return cnt;
    }
}
