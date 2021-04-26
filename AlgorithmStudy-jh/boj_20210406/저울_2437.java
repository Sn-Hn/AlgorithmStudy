package boj;

import java.io.*;
import java.util.Arrays;

/*
https://www.acmicpc.net/problem/2437
 */
public class Boj2437 {
    static int n;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        String[] s = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }
        Arrays.sort(arr);

        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (sum + 1 < arr[i]) break;
            sum += arr[i];
        }
        System.out.println(sum + 1);
    }
}
