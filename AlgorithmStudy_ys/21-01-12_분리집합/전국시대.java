package algostudy10_BOJ_15809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String tmp[] = br.readLine().split(" ");
		
		int country[][] = new int[Integer.parseInt(tmp[0])+1][2];
		int recordnum = Integer.parseInt(tmp[1]);
		
		for(int i = 1 ;i <country.length; i++) {
			
			country[i][0] = i; //국가 번호
			country[i][1] = Integer.parseInt(br.readLine()); //병력수
		}
		int record[][] = new int[recordnum][3];
		
		for(int i = 0 ; i < recordnum; i++) {
			String rec[] = br.readLine().split(" ");
			for(int j = 0 ; j < rec.length; j++) {
				record[i][j] = Integer.parseInt(rec[j]);
			}
		}
		
//		기록이 3개의 정수 O, P, Q로 주어진다. O가 1인 경우 P, Q가 서로 동맹을 맺었음을 의미하고, O가 2인 경우 P, Q가 서로 전쟁을 벌였음을 의미한다.
		
		for(int i = 0 ;i  < record.length; i++) {
			int O = record[i][0];
			int P = record[i][1];
			int Q = record[i][2];
			
			
			if(O == 1) { // 동맹 병력이 하나로 합쳐진다 - > union
				union(country,P,Q);
			}else if(O == 2) { // 전쟁
				war(country,P,Q);
			}
			
		}
		
		HashSet<Integer> set = new HashSet<Integer>();
		
		
		// 중복처리 
		for(int i = 1 ; i < country.length;i++) {
			int parent = getParent(country, i);
			if( parent != 0) {
				set.add(i);
			}
			
		}

		ArrayList<Integer> list = new ArrayList<Integer>();
		// 다른 국가인데 병력이 같을 수 있어 바로 set에 넣으면 안됨 
		for(int i : set) {
			list.add(country[i][1]);
		}
		
		list.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1-o2;
			}
		});
		
		System.out.println(list.size());
		for(int i : list) {
			System.out.print(i+" ");
		}
	}
	
	
	public static int getParent(int country[][],int num) {
		if(country[num][0] == num) {
			return num;
		}else {
			return getParent(country, country[num][0]);
		}
	}
	
	public static void union(int country[][],int a,int b) {
		a = getParent(country, a);
		b = getParent(country, b);
		if(a == b) {
			return ;
		}
		
		
		if(a <b) {
			country[b][0] = a;
			country[b][1] += country[a][1];
			country[a][1] = country[b][1];
	
		}else {
			country[a][0] = b;
			country[a][1] +=country[b][1];
			country[b][1] = country[a][1];
		
		}
		
	}
	
	public static void war(int country[][],int a ,int b) {
		a = getParent(country, a);
		b = getParent(country, b);
		
		
		if(a !=b) {
			
			if(country[a][1] == country[b][1]){
				country[a][0] = 0;
				country[b][0] = 0;
			}else if(country[a][1] > country[b][1]) {
				country[b][0] = a;
				country[a][1] -= country[b][1];
			}else {
				country[a][0] = b;
				country[b][1] -= country[a][1];
				
			}
		}
		
		
	}
	
	
	
	
}
