package hw_0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj2615_오목 {

	static int dr[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dc[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int arr[][] = new int[19][19];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int result = 0;
		int w_n = 0;
		int h_n = 0;

		//입력
		for (int i = 0; i < 19; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < 19; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		out: for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {

				if (arr[i][j] != 0) {
					int r = i;
					int c = j;

					// 8방탐색
					for (int d = 0; d < 8; d++) { // d가 0~3이면 +4하면 반대방향
						int nr = r + dr[d];
						int nc = c + dc[d];

						// 범위밖
						if (nr < 0 || nc < 0 || nr >= 19 || nc >= 19)
							continue;
						// 0이면 재껴
						if (arr[nr][nc] == 0)
							continue;
						// 같은 돌 아니면 재껴
						if (arr[nr][nc] != arr[r][c])
							continue;

						
						int cnt = 2;

						int rr = nr;
						int rc = nc;
						//같은돌 찾으면 그 방향으로 4번 봐. 6목일수도 있으니까.
						for (int k = 0; k < 4; k++) {
						
							int nnr = rr + dr[d];
							int nnc = rc + dc[d];

							// 범위밖, 0이면 재껴, 같은 돌 아니면 재껴
							if (nnr < 0 || nnc < 0 || nnr >= 19 || nnc >= 19)
								break;

							if (arr[nnr][nnc] == 0)
								break;

							if (arr[nnr][nnc] != arr[r][c])
								break;

							//같은 돌이 있으면
							cnt++;
							rr = nnr;
							rc = nnc;
							if(d== 5 && k == 2) { // 이 조건 추가
								r = nnr;
								c = nnc;
							}
								
						}

						if (cnt == 5) { // 5목이라고 생각할때 반대방향에 같은 숫자가 있는지 확인.
							int op = d < 4 ? 4 : -4; // 
							int or = i + dr[d + op];
							int oc = j + dc[d + op];

							// 반대 방향에 있는게 범위밖이거나, 다른숫자거나, 0이면 이건 오목맞음
							if (or < 0 || oc < 0 || oc >= 19 || or >= 19 || arr[or][oc] != arr[r][c]
									|| arr[or][oc] == 0) {
								
								if(d == 5) { // 왼쪽 아래에서 위로 올라올때
									w_n = r + 1;
									h_n = c + 1;
									result = arr[r][c];
									break out;
								}
								w_n = i + 1;
								h_n = j + 1;
								result = arr[i][j];
								break out;
							}

							// 같은 숫자면 안돼.

						}

					}
				}

			}
		}

		System.out.println(result);
		if (result != 0)
			System.out.println(w_n + " " + h_n);
	}
}
