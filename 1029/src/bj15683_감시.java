

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class bj15683_감시 {

	static int N, M, map[][], ans = Integer.MAX_VALUE;
//	static int dr[] = { -1, 0, 1, 0 }; // 시계방향 위 , 오, 아, 왼
//	static int dc[] = { 0, 1, 0, -1 };
	static ArrayList<CCTV> list = new ArrayList<CCTV>(); // 감시카메라 담아둘 배열

	public static class CCTV {
		int x;
		int y;
		int num;

		CCTV(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if (1 <= map[i][j] && map[i][j] <= 5) { // 감시카메라
					list.add(new CCTV(i, j, map[i][j]));
				}
			}
		} // 입력

		// 1번 4가지
		// 2 번 2가지
		// 3번 4가지
		// 4번 4가지
		// 5번 1가지
		dfs(0, map);
		System.out.println(ans);
	}

	// dfs
	public static void dfs(int idx, int[][] p_map) {

		int[][] visit = new int[N][M];

		if (idx == list.size()) {
			int zero_cnt = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (p_map[i][j] == 0) { // 0 의 갯수 카운트.
						zero_cnt++;
					}
				}
			}
			ans = Integer.min(zero_cnt, ans);
		} else {
			CCTV cctv = list.get(idx);
			int num = cctv.num; // 카메라 번호
			int x = cctv.x;
			int y = cctv.y;

			switch (num) {
			case 1: // 4가지
				for (int d = 0; d < 4; d++) {
					for (int i = 0; i < N; i++) {
						visit[i] = Arrays.copyOf(p_map[i], M);
					}
					go(visit, x, y, d);
					dfs(idx + 1, visit);
				}
				break;
			case 2: // 2가지
				for (int d = 0; d < 2; d++) {
					for (int i = 0; i < N; i++) {
						visit[i] = Arrays.copyOf(p_map[i], M);
					}

					// 0 상, 1 좌, 2 하, 3 우
					// 0-2, 1-3
					go(visit, x, y, d);
					go(visit, x, y, d + 2);

					dfs(idx + 1, visit);
				}
				break;
			case 3: // 4가지
				for (int d = 0; d < 4; d++) {
					for (int i = 0; i < N; i++) {
						visit[i] = Arrays.copyOf(p_map[i], M);
					}

					// 직각
					// 0 상, 1 좌, 2 하, 3 우
					// 0-1, 1-2, 2-3, 3-0
					go(visit, x, y, d);
					go(visit, x, y, (d + 1) % 4);
					dfs(idx + 1, visit);
				}
				break;
			case 4: // 4가지
				for (int d = 0; d < 4; d++) {
					for (int i = 0; i < N; i++) {
						visit[i] = Arrays.copyOf(p_map[i], M);
					}

					// 3방향.
					// 0 상, 1 좌, 2 하, 3 우
					// 0-1-2, 1-2-3, 2-3-0, 3-0-1
					go(visit, x, y, d);
					go(visit, x, y, (d + 1) % 4);
					go(visit, x, y, (d + 2) % 4);

					dfs(idx + 1, visit);
				}
				break;
			case 5: // 1가지
				for (int i = 0; i < N; i++) {
					visit[i] = Arrays.copyOf(p_map[i], M);
				}

				// 4방향 다 놓기.
				go(visit, x, y, 0);
				go(visit, x, y, 1);
				go(visit, x, y, 2);
				go(visit, x, y, 3);

				dfs(idx + 1, visit);
				break;
			}
		}

	}

	// 0 상, 1 좌, 2 하, 3 우
	public static void go(int[][] visit, int i, int j, int direction) {
		switch (direction) {
		case 0: // ↑ 상
			for (int d = j; d >= 0; d--) {
				if (map[i][d] == 6) { // 벽
					break;
				}
				visit[i][d] = -1;
			}
			break;

		case 1: // 좌 ←
			for (int d = i; d >= 0; d--) {
				if (map[d][j] == 6) {
					break;
				}
				visit[d][j] = -1;
			}
			break;
		case 2: // 하 ↓
			for (int d = j; d < M; d++) {
				if (map[i][d] == 6) {
					break;
				}
				visit[i][d] = -1;
			}
			break;

		case 3: // 우 →
			for (int d = i; d < N; d++) {
				if (map[d][j] == 6) {
					break;
				}
				visit[d][j] = -1;
			}
			break;
		}
	}

}
