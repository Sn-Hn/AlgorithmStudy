package BackTracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2561_좋은수열 {
    //길이 1 ~ 2/N까지 비교

    static int N;
    static String answer;
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dfs(1,"1"); //처음엔 int로 함 그럼 80개 불가능함

        System.out.println(answer);
    }

    static void dfs(int len, String val){
        if(!check(val)) return;
        if(len==N) { //어차피 처음나온게 최소값일텐데 이것을 어떻게.. 해야하나.. 고민
            System.out.println(val);
            System.exit(0); //이건 인터넷 봄. 바로 종료하는거 근데 이게 좋은건지는 모르겠음
        }

        dfs(len+1,val+1);
        dfs(len+1,val+2);
        dfs(len+1,val+3);
    }

    static boolean check(String val){
        int len = val.length();
        for(int i=1; i<=len/2 ; i++){ //핵
            if(val.substring(len-i, len).equals(val.substring(len-(2*i), len-i))){
                return false;
            }
        }
        return true;
    }

}
