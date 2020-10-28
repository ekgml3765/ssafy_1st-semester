import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sw_활주로건설 {

	static int N, X;
	static int[][] map, map2;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int ans = 0;
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // N
			X = Integer.parseInt(st.nextToken()); // X
			map = new int[N][N];
			map2 = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j] = num;
					map2[j][i] = num;
				}
			}

			int answer = solution();
			System.out.println("#" + tc + " " + answer);

		}
	}

	private static int solution() {
		int answer = 0;
		for (int i = 0; i < N; i++) {
			answer += go(map[i]);
			answer += go(map2[i]);
		}
		return answer;
	}

	private static int go(int[] array) { // 활주로 건설할 수 있으면 1, 없으면 0 리턴

		boolean[] check = new boolean[N];

		// 열검사
		for (int i = 0; i < N - 1; i++) {
			int prev = array[i];
			int next = array[i + 1];

			if (Math.abs(prev - next) > 1)
				return 0; // 2 이상 차이나면 끝
			if (prev == next || check[i + 1])
				continue;// 평지이거나 이미 체크했으면 넘어가

			if (prev > next) {
				for (int j = i + 1; j <= i + X; j++) {

					// N을 넘어가거나, 이미 지형있거나, 평지가 아니면 return 0;
					if (j == N || array[j] != next || check[j])
						return 0;

					if (j != i + X)
						array[j] = next + 1;
					check[j] = true;
				}
			} else {
				for (int j = i; j > i - X; j--) {
					if (j < 0 || array[j] != prev || check[j])
						return 0;
					if (j != i - X + 1)
						array[j] = prev + 1;
					check[j] = true;
				}

			}
		}

		return 1;
	}

}
