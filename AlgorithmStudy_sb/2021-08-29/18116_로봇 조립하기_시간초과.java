//18116: 로봇 조립
import java.io.*;
import java.util.*;
 
public class Main {

    static int[] parent;
    static int[] count;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
    
        parent = new int[1000001];
        count = new int[1000001];
        for(int i = 0; i < 1000001; i++) {
            parent[i] = i;
            count[i] = 1;
        }

        StringBuilder sb = new StringBuilder();
        String str;
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            str = bf.readLine();
            st = new StringTokenizer(str);
            char op = st.nextToken().charAt(0);
            if(op == 'I') {
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                int p1 = find(n1);
                int p2 = find(n2);
                if(p1 != p2) {
                    parent[p1] = p2;
                    count[p2] += count[p1];
                    count[p1] = 0;
                }
            } else {
                int q = Integer.parseInt(st.nextToken());
                sb.append(count[find(q)] + "\n");
            }
        }
        System.out.print(sb.toString());
    }

    public static int find(int node) {
        if(parent[node] == node) return node;
        else return find(parent[node]);
    }
}
