package hw_0731;

import java.util.Scanner;

public class 최적경로 {
	static int N; // 고객의 수
	static int com_x, com_y, home_x, home_y; // 출발 x,y 좌표, 도착 x,y 좌표
	static int arr[], sel[], customer[][];
	static boolean[] check;
	static int min = 9897984;

	// 순열 메소드
	static void perm(int idx) {

		// 모두 골랐다
		if (idx == N) {
			int sum = 0;
			// 회사부터 첫번째 손님
			int first = Math.abs((com_x - customer[sel[0]][0])) + Math.abs((com_y - customer[sel[0]][1]));
			// 마지막 손님부터 집까지
			int last = Math.abs((customer[sel[N - 1]][0] - home_x)) + Math.abs((customer[sel[N - 1]][1] - home_y));
			sum = first + last;
			for (int i = 0; i < sel.length - 1; i++) {
				// x좌표계산
				sum += Math.abs((customer[sel[i]][0] - customer[sel[i + 1]][0]))
						+ Math.abs((customer[sel[i]][1] - customer[sel[i + 1]][1]));
			}

			if (min > sum)
				min = sum;
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			// 안고른 놈에 대해서만
			if (!check[i]) { // F값이면
				check[i] = true; // 고른걸로 체크해두고
				sel[idx] = arr[i];
				perm(idx + 1);
				check[i] = false; // 다시 돌아 왔을때 체크해제
			}

		}

	}

	// 회사에서 출발하여 N명의 손님을 만나고 집에 간다.
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {

			N = sc.nextInt(); // 고객의 수
			com_x = sc.nextInt();
			com_y = sc.nextInt();
			home_x = sc.nextInt();
			home_y = sc.nextInt();

			// 손님 x, y 좌표를 받을 배열 생성
			customer = new int[N][N];

			// 인덱스 0번부터 N번까지 고객의 x, y 좌표받음.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < 2; j++) {
					customer[i][j] = sc.nextInt();
				}
			}

			// 고객의 순서를 결정하기 위해 손님을 정보를 담는 배열
			arr = new int[N];
			sel = new int[N];
			check = new boolean[N];

			for (int i = 0; i < N; i++) {
				arr[i] = i; // 0~N-1번까지 고객 인덱스 생성
			}

			perm(0);
			System.out.println("#" + tc + " " + min);
			min = 9897984;

		}

	}
}
