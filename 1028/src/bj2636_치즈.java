
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj2636_치즈 {

	public static class Cheeze {
		int r, c, cnt;

		Cheeze(int r, int c) {
			this.r = r;
			this.c = c;

		}
	}

	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };
	static int N, M, map[][], c_cnt = 0, b_cnt = 0, t_cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					c_cnt++;
			}
		} // 입력

		// total 세서!

		while (c_cnt != 0) {
			bfs();
		}
		System.out.println(b_cnt);
		System.out.println(t_cnt);
	}

	private static void bfs() {

		b_cnt++;
		boolean visited[][] = new boolean[N][M];
		Queue<Cheeze> queue = new LinkedList<>();
		visited[0][0] = true;
		queue.add(new Cheeze(0, 0));
		t_cnt = 0;
		while (!queue.isEmpty()) {
			Cheeze ch = queue.poll();
			int r = ch.r;
			int c = ch.c;
			for (int d = 0; d < 4; d++) {
				int nr = ch.r + dr[d];
				int nc = ch.c + dc[d];

				// 범위밖
				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;
				// 방문한거 넘기고
				if (visited[nr][nc])
					continue;

				if (map[nr][nc] != 0) {
					visited[nr][nc] = true;
					map[nr][nc] = 0;
					t_cnt++;
					continue;
				}

				visited[nr][nc] = true;
				queue.add(new Cheeze(nr, nc));
			}
		}
		c_cnt = c_cnt - t_cnt;

	}
}