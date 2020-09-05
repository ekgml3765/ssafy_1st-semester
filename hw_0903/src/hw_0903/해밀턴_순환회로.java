package hw_0903;

import java.util.Arrays;
import java.util.Scanner;

public class 해밀턴_순환회로 {

	static int N, map[][];
	static boolean check[];
	static int min;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		check = new boolean[N];
		min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		perm(1, 0, 0);
		System.out.println(min == Integer.MAX_VALUE ? 0 : min);

	}

	private static void perm(int idx, int current, int cost) {

		if (idx == N) {
			// 출발점으로 돌아가는 길이 없다면.
			if (map[current][0] == 0)
				return;
			
			cost+= map[current][0];
			min = Math.min(cost, min);

		}

		for (int i = 1; i < N; i++) {
			if (!check[i]) {
				if( map[current][i] == 0 )
					continue;
				check[i] = true;
				perm(idx + 1, i, cost + map[current][i]);
				check[i] = false;
			}
		}
	}
}
