package hw_0729;

import java.util.Scanner;

public class 파리퇴치 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {

			int N = sc.nextInt(); // 배열 크기
			int M = sc.nextInt(); // 파리채 크기

			int arr[][] = new int[N][N];

			// 배열값 입력
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			int max = 0;

			for (int i = 0; i <= N - M; i++) {
				for (int j = 0; j <= N - M; j++) {

					int sum = 0;
					for (int x = i; x < i + M; x++) {
						for (int y = j; y < j + M; y++) {
							sum += arr[x][y];
						}
					}

					if (max < sum)
						max = sum;

				}
			}

			System.out.println("#" + tc + " " + max);
		}
	}

}
