import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class 꿈틀꿈틀호석애벌레 {

    static int feed[];
    static int n;
    static int k;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        String tmp[] = br.readLine().split(" ");
        feed = new int[n];

        for(int i = 0 ; i < n;i++){
            feed[i] = Integer.parseInt(tmp[i]);
        }

        recursive(0,0,0);
        System.out.println(ans);
    }


    public static void recursive(int index,int sum,int eatSum){
        // 경우의수
//        1. 선택 했을때
//        2. 안했을때
//        3. 선택하고 k를 넘었을때

        if(index == n){
            ans = Math.max(sum,ans);
            return;
        }

        if(eatSum+feed[index] >= k){
            recursive(index+1,sum+eatSum+feed[index]-k,0);
        }else{
            recursive(index+1,sum,eatSum+feed[index]);
        }
        recursive(index+1,sum,0);


    }


}
