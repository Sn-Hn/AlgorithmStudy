package boj;

import java.io.*;
import java.util.Arrays;

/*
https://www.acmicpc.net/problem/1744
 */
public class Boj1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int sum = 0;
        int left = 0;
        int right = n-1;

        for (; left < right; left += 2) {
            if (arr[left] < 1 && arr[left+1] < 1) {
                sum += arr[left] * arr[left+1];
            } else break;
        }
        for (; right > 0; right -= 2) {
            if (arr[right] > 1 && arr[right-1] > 1) {
                sum += arr[right] * arr[right-1];
            } else break;
        }
        for (; left <= right; left++) {
            sum += arr[left];
        }
        System.out.println(sum);
    }
}
