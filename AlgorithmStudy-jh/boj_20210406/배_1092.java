package boj;

import java.io.*;
import java.util.*;

/*
https://www.acmicpc.net/problem/1092
 */
public class Boj1092 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> crane = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            crane.add(Integer.parseInt(st.nextToken()));
        }
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> box = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            box.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(crane, (c1, c2) -> c2 - c1);
        Collections.sort(box, (c1, c2) -> c2 - c1);

        if (crane.get(0) < box.get(0)) {
            System.out.println(-1);
            return;
        }

        int cnt = 0;
        while (box.size() > 0) {
            int i = 0;
            for (int j = 0; j < box.size(); j++) {
                if (i == crane.size()) break;
                if (crane.get(i) >= box.get(j)) {
                    box.remove(j--);
                    i++;
                }
            }
            cnt++;
        }
        System.out.println(cnt);
        br.close();
    }
}
