package hw_0901;

import java.util.Arrays;
import java.util.Scanner;

public class bj14889_스타트와링크 {

	static int N;
	static int[][] map;
	static boolean sel[]; 
	static int ans = 798794213;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		sel = new boolean[N];

		// 입력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		powerSet(0, 0);
		System.out.println(ans);
	}

	

	private static void powerSet(int idx, int s_idx) {

		if (s_idx == N/2) {
			
			int sumT = 0, sumF = 0;
			for(int i = 0; i < N; i++) {
				if(sel[i]) {
					for(int j = 0; j < N; j++) {
						if(sel[j]) 
							sumT += map[i][j];
					}
				}
				else {
					for(int j = 0; j < N; j++) {
						if(!sel[j]) 
							sumF += map[i][j];
					}
				}
			}
			ans = Math.min(ans, Math.abs(sumT - sumF));
			
			return;
		}
		
		if(idx == N)
			return;
		
		sel[idx] = true;
		powerSet(idx +1, s_idx + 1);
		sel[idx] = false;
		powerSet(idx +1, s_idx);
	}

}
