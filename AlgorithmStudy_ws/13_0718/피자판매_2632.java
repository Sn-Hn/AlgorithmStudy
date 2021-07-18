package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
7
5 3
2
2
1
7
2
6
8
3
 */

public class 피자판매_2632 {
	static int SIZE, M, N;
	static int[] mPizza, nPizza;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		SIZE = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		mPizza = new int[M];
		nPizza = new int[N];
		for (int i = 0; i < M; i++)
			mPizza[i] = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++)
			nPizza[i] = Integer.parseInt(br.readLine());


		makePizza1();
	}

	private static void makePizza1() {
		int left = 0;
		int right = -1;
		int sum = 0;
		int count = 0;

		while (left < M)
		{
			int nextTmp = ++right % M;
			sum += mPizza[nextTmp];

			if (mPizza[nextTmp] <= SIZE) {
				count += makePizza2(mPizza[nextTmp]);
			}

			if (sum < SIZE) {
				int c = makePizza2(sum);
				if (c != 0) {
					count += c;
					System.out.println("\n left + right" + count);
					System.out.println("M : " + sum+", l = "+ left+", r = " + right);
				}
			}
			while (left < right && sum > SIZE) {
				sum -= mPizza[left++];
			}

			if (sum == SIZE) {
				System.out.println("\n only left" + count);
				System.out.println("M : l = " + left +", r = "+ right);
				sum -= mPizza[left++];
				count++;
			}
			count += makePizza2(0);
		}
		System.out.println("\n only right" + count);
		System.out.println(count);
	}

	private static int makePizza2(int oneSum) {
		int left = 0;
		int right = -1;
		int sum = oneSum;
		int count = 0;

		while (left < N)
		{
			sum += nPizza[++right % N];

			while (left < right && sum > SIZE) {
				sum -= nPizza[left++];
			}
			if (left < N && sum == SIZE) {
				System.out.println("N : l = " + left +", r = "+ right);
				sum -= nPizza[left++];
				count++;
			}
		}
		return count;
	}
}
