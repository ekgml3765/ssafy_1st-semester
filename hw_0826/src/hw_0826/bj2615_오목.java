package hw_0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj2615_���� {

	static int dr[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dc[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int arr[][] = new int[19][19];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int result = 0;
		int w_n = 0;
		int h_n = 0;

		//�Է�
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

					// 8��Ž��
					for (int d = 0; d < 8; d++) { // d�� 0~3�̸� +4�ϸ� �ݴ����
						int nr = r + dr[d];
						int nc = c + dc[d];

						// ������
						if (nr < 0 || nc < 0 || nr >= 19 || nc >= 19)
							continue;
						// 0�̸� �粸
						if (arr[nr][nc] == 0)
							continue;
						// ���� �� �ƴϸ� �粸
						if (arr[nr][nc] != arr[r][c])
							continue;

						
						int cnt = 2;

						int rr = nr;
						int rc = nc;
						//������ ã���� �� �������� 4�� ��. 6���ϼ��� �����ϱ�.
						for (int k = 0; k < 4; k++) {
						
							int nnr = rr + dr[d];
							int nnc = rc + dc[d];

							// ������, 0�̸� �粸, ���� �� �ƴϸ� �粸
							if (nnr < 0 || nnc < 0 || nnr >= 19 || nnc >= 19)
								break;

							if (arr[nnr][nnc] == 0)
								break;

							if (arr[nnr][nnc] != arr[r][c])
								break;

							//���� ���� ������
							cnt++;
							rr = nnr;
							rc = nnc;
							if(d== 5 && k == 2) { // �� ���� �߰�
								r = nnr;
								c = nnc;
							}
								
						}

						if (cnt == 5) { // 5���̶�� �����Ҷ� �ݴ���⿡ ���� ���ڰ� �ִ��� Ȯ��.
							int op = d < 4 ? 4 : -4; // 
							int or = i + dr[d + op];
							int oc = j + dc[d + op];

							// �ݴ� ���⿡ �ִ°� �������̰ų�, �ٸ����ڰų�, 0�̸� �̰� �������
							if (or < 0 || oc < 0 || oc >= 19 || or >= 19 || arr[or][oc] != arr[r][c]
									|| arr[or][oc] == 0) {
								
								if(d == 5) { // ���� �Ʒ����� ���� �ö�ö�
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

							// ���� ���ڸ� �ȵ�.

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
