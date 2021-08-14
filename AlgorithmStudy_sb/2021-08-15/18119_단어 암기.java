//18119: 단어 암기
import java.io.*;
import java.util.*;
 
public class Main {

    public static void main(String[] args) throws IOException {

        //입력
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String str = bf.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean[][] word_check = new boolean[n][26]; // 단어의 알파벳 별로 알고 있는지 여부 체크
        int[] forget_count = new int[n]; // 각 단어 별로 모르는 단어 개수 저장
        boolean[] knwon_check = new boolean[n]; // 알고 있는 단어인지 체크
        for(int i = 0; i < n; i++) {
            str = bf.readLine();
            for(int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                if(check_vowel(c)) continue; //모음 피료없어
                word_check[i][c - 'a'] = true; //처음에는 모든 단어 알고 있음.
            }
            knwon_check[i] = true;
        }

        StringBuilder sb = new StringBuilder();
        int result = n;
        for(int i = 0; i < m; i++) {
            str = bf.readLine();
            st = new StringTokenizer(str);
            char code = st.nextToken().charAt(0);
            char alpha = st.nextToken().charAt(0);
            //입력 끝
            if(check_vowel(alpha)) continue;

            if(code == '1') {
                for(int j = 0; j < n; j++) {
                    if(word_check[j][alpha - 'a']) {
                        forget_count[j]++;
                        if(knwon_check[j]) {
                            knwon_check[j] = false;
                            result--;
                        }
                    }
                }
            } else {
                for(int j = 0; j < n; j++) {
                    if(knwon_check[j]) continue;
                    if(word_check[j][alpha - 'a']) {
                        forget_count[j]--;
                        if(forget_count[j] == 0) {
                            knwon_check[j] = true;
                            result++;
                        }
                    }
                }
            }

            sb.append(result + "\n");
        }
        System.out.println(sb.toString());
    }     
    
    public static boolean check_vowel(char c) {
        if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') return true;
        return false;
    }
}
