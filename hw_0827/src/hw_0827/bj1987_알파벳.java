package hw_0827;

import java.util.Scanner;

public class bj1987_���ĺ� {

	static int R, C;
	static char arr[][];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		arr = new char [R][];
		for(int i = 0; i < R; i++) {
			arr[i] = sc.next().toCharArray();
		}

		//ó���� �湮üũ
		check[arr[0][0] - 'A'] = true;
		dfs(0,0,1);
		System.out.println(max);
	}
	
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static int max = 0;
	static boolean check[] = new boolean [26];
	private static void dfs(int r, int c, int cnt) {
		
		max = Math.max(cnt, max);
		
		//4��Ž��
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr < 0 || nc < 0 || nr >= R || nc >= C)
				continue;
			
			//�湮üũ ������ �Ѱ�
			if(check[arr[nr][nc] - 'A'])
				continue;
			
			//�װ� �ƴ϶�� �湮üũ���ְ� �־� 
			check[arr[nr][nc] - 'A'] = true;
			dfs(nr, nc, cnt+1);
			check[arr[nr][nc] - 'A'] = false;
		}
	}
	
	
}
