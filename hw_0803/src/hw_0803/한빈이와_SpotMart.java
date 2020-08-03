package hw_0803;

import java.util.Scanner;

public class 한빈이와_SpotMart {

	static int N, M;
	static int[] arr;
	static int[] sel = new int[2]; // 과자는 2개만 뽑는다.
	static int max = -1;

	static void comb(int idx, int s_idx) {

		// 기저조건
		if (s_idx == 2) {

			int sum = 0;
			for (int i = 0; i < 2; i++) {
				sum += sel[i];
			}

			if (sum <= M && sum > max)
				max = sum;

			return;
		}

		if (idx == N) {
			return;
		}

		sel[s_idx] = arr[idx];
		comb(idx + 1, s_idx + 1); // 선택시 화살표 둘다 이동
		comb(idx + 1, s_idx); // 선택 안할시 화살표 하나만 이동

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {

			N = sc.nextInt(); // 과자 봉지의 개수
			M = sc.nextInt(); // 무게합 제한
			arr = new int[N];

			for (int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			comb(0, 0);

			System.out.println("#" + tc + " " + max);
			max = -1;
		}
	}

}
