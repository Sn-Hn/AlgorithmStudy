//22238: 가희와 btd5
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
       
        ArrayList<Integer> bollons_hp = new ArrayList<>();
        int bollon_x = 0;
        int bollon_y = 0;
        for(int i = 0; i < n; i++) {
            str = bf.readLine();
            st = new StringTokenizer(str);
            bollon_x = Integer.parseInt(st.nextToken());
            bollon_y = Integer.parseInt(st.nextToken());
            int hp = Integer.parseInt(st.nextToken());
            bollons_hp.add(hp);
        }
        Collections.sort(bollons_hp);
        int[] bollon_dir = cal_dir(bollon_x, bollon_y);

        StringBuilder sb = new StringBuilder();
        int left = 0;
        long total_damage = 0;
        for(int i = 0; i < m; i++) {
            str = bf.readLine();
            st = new StringTokenizer(str);
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int damage = Integer.parseInt(st.nextToken());
            //입력 끝

            int[] tower_dir = cal_dir(x, y);
            if (n == left || (tower_dir[0] != bollon_dir[0] || tower_dir[1] != bollon_dir[1])) {
                sb.append(n - left + "\n");
                continue;
            }

            int right = n - 1;
            total_damage += damage;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (bollons_hp.get(mid) <= total_damage) left = mid + 1;
                else right = mid - 1;
            }
            sb.append(n - left + "\n");
        }
        System.out.print(sb.toString());
    }

    public static int[] cal_dir(int x, int y) {
        if(x == 0 && y == 0) {
            x = 0;
            y = 0;
        }
        else if(x == 0 && y < 0) {
            x = 0;
            y = -1;
        } else if(x == 0 && y > 0) {
            x = 0;
            y = 1;
        } else if(y == 0 && x < 0) {
            x = -1;
            y = 0;
        } else if(y == 0 && x > 0) {
            x = 1;
            y = 0;
        } else {
            int g = gcd(Math.abs(x), Math.abs(y));
            x = x / g;
            y = y / g;
        }
        return new int[] {x, y};
    }

    public static int gcd(int x, int y) {
        if(x % y == 0) return y;
        return gcd(y, x % y);
    }
}