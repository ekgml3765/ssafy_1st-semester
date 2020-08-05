package hw_0804;

import java.util.Scanner;

public class 서로소집합 {

	
	static int parent [];
	static int find(int x) {
		//내가 대표자라면
		if (x == parent[x])
			return x;
		//아래 반복은 몇번을 해야될지 모름. 자기 자신이 부모인걸 찾을떄까지 계속 부모 호출
		parent[x] = find(parent[x]); // 추가함
		return find(parent[x]);
		
	}
	
	static void union (int x , int y) {
		int px = find(x); //x의 대표자 찾음
		int py = find (y); //y의 대표자를 찾음
		
		if(px != py) {
			parent[px] = py;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner (System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <=T; tc++) {
			
			int n = sc.nextInt(); // n개 집합 각각
			int m = sc.nextInt(); // m개의 각각의 연산, 연산의 개수
			int result[] = new int[m]; //결과 출력을 위한 배열
			
			parent = new int[n+1];
			for(int i = 0; i < n+1; i++) {
				parent[i] = i;
			}
				
			int cnt = 0; //result 배열 idx
			for(int i = 0; i < m; i++) {
				
				int c = sc.nextInt(); //연산  0 은 합, 1은 두 원소가 같은 집합인지 확인 
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				if(c == 0) {
					union(a,b);
				}
				
				if(c == 1) {			
					if(find(a) == find(b)) //대표가 같으면 같은 집합
						result[cnt] = 1;
					if(find(a) != find(b))
						result[cnt] = 0;
					cnt++;
						
				}
				
			}
		
				
			System.out.print("#" + tc + " ");
			for(int i = 0; i < cnt; i++) {
				System.out.print(result[i]);
			}
			System.out.println();
		}
	}

}
