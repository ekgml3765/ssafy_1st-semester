

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj7562_나이트의이동 {

	static int N;
	static int map[][];
	static int cnt;
	static int s_n, s_r, e_n, e_r;

	static class Point {
		int n, r, cnt;

		Point(int n, int r, int cnt) {
			this.n = n;
			this.r = r;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			map = new int[N][N];

			// 출발좌표, 목표좌표 받음.
			s_n = sc.nextInt();
			s_r = sc.nextInt();
			e_n = sc.nextInt();
			e_r = sc.nextInt();

			map[e_n][e_r] = -1; // 도착지 값.

			ans = 0;
			bfs();
			System.out.println(ans);
		}
	}

	static int ans;
	static boolean visit[][];
	// 8방
	static int[] dr = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] dc = { 1, 2, 2, 1, -1, -2, -2, -1 };

	private static void bfs() {

		visit = new boolean[N][N];

		Queue<Point> queue = new LinkedList<>();
		visit[s_n][s_r] = true;
		queue.add(new Point(s_n, s_r, 0));

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			if (p.n == e_n && p.r == e_r) {
				ans = p.cnt;
				return;
			}

			// 8방탐색
			for(int d = 0; d < 8 ; d++) {
				
				int nr = p.n + dr[d];
				int nc = p.r + dc[d];
				
				//범위 밖, 방문했으면 아웃
				if(nr < 0 || nc < 0 || nr >= N || nc >= N || visit[nr][nc])
					continue;
				
				visit[nr][nc] = true;
				queue.add(new Point(nr, nc, p.cnt +1));
			}

		}

	}
}
