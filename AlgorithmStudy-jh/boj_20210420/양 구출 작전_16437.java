package boj;

import java.io.*;
import java.util.*;

/*
https://www.acmicpc.net/problem/16437
 */
public class Boj16437 {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static char[] animals;
    static int[] lives;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        animals = new char[n+1];
        lives = new int[n+1];
        for (int i = 0; i < n+1; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프(부모 자식 관계) 연결
        for (int i = 2; i < n+1; i++) {
            String[] input = br.readLine().split(" ");
            animals[i] = input[0].charAt(0);
            lives[i] = Integer.parseInt(input[1]);
            graph.get(Integer.parseInt(input[2])).add(i);
        }

        long result = postOrderTraversal(1);

        System.out.println(result);
    }

    private static long postOrderTraversal(int now) {
        long sum = 0;

        // 자식이 있으면 계속 자식 노드로 탐색
        for (int next : graph.get(now)) {
            sum += postOrderTraversal(next);
        }

        if (animals[now] == 'S')
            return sum + lives[now];
        else {
            return (sum - lives[now] >= 0) ? sum - lives[now] : 0;
        }
    }
}
