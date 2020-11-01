
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj19238_스타트택시 {

	static int N, M, fuel, map[][], person[][], ans = 0, dest_r, dest_c;
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };
	static int start_r, start_c;

	static class Texi {
		int r, c, cnt;

		public Texi(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {

		// 도착지에! 사람 바로있을수도있음. 사람이 있는지 없는 지 검사.
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); // map크기
		M = Integer.parseInt(st.nextToken()); // 손님 수
		fuel = Integer.parseInt(st.nextToken()); // 초기연료
		map = new int[N][N];
		person = new int[M][4]; // 손님정보 배열
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(in.readLine());
		start_r = Integer.parseInt(st.nextToken()) - 1; // 택시 출발 x좌표
		start_c = Integer.parseInt(st.nextToken()) - 1; // 택시 출발 y좌표
	

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int x = 0, y = 0;
			for (int j = 0; j < 4; j++) {
				person[i][j] = Integer.parseInt(st.nextToken()) - 1;
				if (j == 0)
					x = person[i][j];
				else if (j == 1)
					y = person[i][j];
			}
			map[x][y] = 2; // 손님 위치는 2로 표시
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}

		for (int i = 0; i < M; i++) {
			pick_up(start_r, start_c);
			if (ans == -1)
				break;
		}
		

		System.out.println(ans = ans==-1? -1 : fuel);
	}// end main

	private static void pick_up(int s_r, int s_c) {

		boolean visit[][] = new boolean[N][N];
		Queue<Texi> queue = new LinkedList<>();
		int min = Integer.MAX_VALUE;
		int customer_r = Integer.MAX_VALUE, customer_c = Integer.MAX_VALUE;
		visit[s_r][s_c] = true; // 택시 출발 좌표 방문체크
		queue.add(new Texi(s_r, s_c, 0));
		boolean flag = false;
		while (!queue.isEmpty()) {

			Texi t = queue.poll();
			if (map[t.r][t.c] == 2) {// 가장 가까운 손님 위치
				//System.out.println(t.cnt);
				flag = true;
				if (min > t.cnt) {
					min = t.cnt;
					customer_r = t.r;
					customer_c = t.c;
				} else if (min == t.cnt) { // 최단길이가 같으면
					min = t.cnt;
					if (t.r < customer_r) { //행이 더 작은순
						customer_r = t.r;
						customer_c = t.c;
					}
					if (customer_r == t.r && customer_c > t.c) {// 행번호가 같다면
						customer_r = t.r;
						customer_c = t.c;
					}
				}
			}

			for (int d = 0; d < 4; d++) {
				int nr = t.r + dr[d];
				int nc = t.c + dc[d];

				// 범위밖
				if (nr < 0 || nc < 0 || nr >= N || nc >= N)
					continue;
				// 방문체크, 벽 넘겨
				if (visit[nr][nc] || map[nr][nc] == 1)
					continue;

				visit[nr][nc] = true;
				queue.add(new Texi(nr, nc, t.cnt + 1));
			}
		}

		//손님 못태움
		if(!flag) {
			ans = -1;
			return;
		}
		//System.out.println(min + " " + customer_r + " " + customer_c);
		map[customer_r][customer_c] = 0; // 손님 태우면 0으로 바꿔.
		fuel -= min; // 손님 태우고 연료 씀
		if (fuel < 0) {
			ans = -1;
			return;
		}

		
		// 목적지로 이동. -> 목적지로 최단거리로 이동. -> bfs
		// 손님의 목적지는?
		int destination_r = 0, destination_c = 0;
		for (int i = 0; i < M; i++) {
			if (customer_r == person[i][0] && customer_c == person[i][1]) {
				destination_r = person[i][2];
				destination_c = person[i][3];
			}
		}
		// 목적지 좌표 줌.
		dest_r = destination_r;
		dest_c = destination_c;
		
		drive(customer_r, customer_c, destination_r, destination_c); //손님 태우러 감.
	}

	private static void drive(int customer_r, int customer_c, int destination_r, int destination_c) {
		boolean visit[][] = new boolean[N][N];
		Queue<Texi> queue = new LinkedList<>();
		int go_fuel = 0;
		visit[customer_r][customer_c] = true;
		queue.add(new Texi(customer_r, customer_c, 0));
		boolean flag = false;
		while(!queue.isEmpty()) {
			Texi t = queue.poll();
			//도착지점이라면
			if(t.r == destination_r && t.c == destination_c) {
				flag = true;
				go_fuel = t.cnt;
				fuel -= go_fuel;
				if(fuel < 0) { // 도착지 까지 가는데 연료 바닥이면
					ans = -1;
					return;
				}
				fuel += 2 * t.cnt; //소모한 연료양의 두배 채움.
				start_r = t.r; //택시 시작 좌표 바꿔줌
				start_c = t.c;
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = t.r + dr[d];
				int nc = t.c + dc[d];

				// 범위밖
				if (nr < 0 || nc < 0 || nr >= N || nc >= N)
					continue;
				// 방문체크, 벽 넘겨
				if (visit[nr][nc] || map[nr][nc] == 1)
					continue;

				visit[nr][nc] = true;
				queue.add(new Texi(nr, nc, t.cnt + 1));
			}
		}
		
		if(!flag) {
			ans = -1;
			return;
		}
	}
}
