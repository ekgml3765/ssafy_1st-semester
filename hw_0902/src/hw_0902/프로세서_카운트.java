package hw_0902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// => �κ����� ����.
public class ���μ���_ī��Ʈ {

	static int N;
	static int map[][], max, min, totalcnt; // �ִ��ھ��, �ּ���������, ó���� �ھ��
	static ArrayList<int[]> list; // ���� �ΰ��� �����Ұ���.
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			list = new ArrayList<int[]>(); // ó���ؾ��� �����ڸ��� �ƴ� �ھ���� ������ ����Ʈ.
			min = Integer.MAX_VALUE;
			max = 0;
			totalcnt = 0;

			// �Է�, �ھ� �κ��������� ó��, �����ڸ��� �ھ ����Ǿ� �����ϱ� �κ����տ� ���� ����
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// �����ڸ��� �ƴ� �ھ �����ҰŶ�, �����ڸ��� �����鼭 �ھ��ΰ� ������
					if ((i == 0 || j == 0 || i == N - 1 || j == N - 1) && map[i][j] == 1)
						continue;
					if (map[i][j] == 1) {
						list.add(new int[] { i, j });
						totalcnt++;
					}
						
				}
			} // end input

			go(0, 0, 0);
			System.out.println("#" + tc + " " + min);
		} 
	} // end main

	private static void go(int index, int core, int lcnt) { // index : ó���� �ھ��� �ε���, core : �������� ���Ե� �ھ��

		//����ġ�� -������� ����� �ھ�� + ������ ó���ؾ��� ���� �ھ�� : ����� �� �ִ� �ִ��ھ��
		//����� �� �ִ� �ִ� �ھ���� �ӽ��� ���� ������ ������ �ǹ̾��� 
		if(core + totalcnt - index < max) return; //�ھ 4���ε� 3������ ó�������� �� 4������ 3 �����Ѱ� ���� 1�������ϱ�.
		
		
		//lcnt ������ ����
		// �ھ� �� ������
		if (index == totalcnt) {
			// �ھ� ���� ��
			if (max < core) { // �̹��� ó���� �ھ������ ũ�� ����
				max = core;
				min = lcnt;
			} else if (max == core) { // �ִ� �ھ� ������ ���ٸ� �ּұ��� ��������
				if (min > lcnt) {
					min = lcnt;
				}
			}
			return;
		}
		
		int[] cur = list.get(index);
		int r = cur[0];
		int c = cur[1];
		// �ش��ھ� ����
		// -> 4�������� ������ ���ƺ��� �õ�
		for (int d = 0; d < 4; d++) { // ���� ���� �� �ִ°� �� ����. ������ ���Ҵ� ���� �����.
			// �ش� �������� ���� ���°� �������� üũ
			if (isAvailable(r, c, d)) {
				// �����ϴٸ� ������ ���� -> �ƽó뽺 �ǿ� 2�� ����
				int len = setStatus(r, c, d, 2); //���� ���� ������ ����
				// ���� �ھ�� �Ѿ��, �����߱⶧����
				go(index + 1, core + 1, lcnt + len);
				// ���Ҵ� ���� �����(�ǵ���0) :0�ó뽺�ǿ� ���� ����
				setStatus(r, c, d, 0);

			}

		}

		// �ش��ھ� ����
		// �ƹ��� ������ �����ʰ� ���� �ھ�� �Ѿ��
		go(index + 1, core, lcnt);
	}

	// �� �ھ��� ��ġ����, �ش� ������ ������ ���°� �������� üũ
	static private boolean isAvailable(int r, int c, int d) {
		int nr = r, nc = c;

		while (true) {
			nr += dr[d];
			nc += dc[d];
			if (nr < 0 || nc < 0 || nr >= N || nc >= N)
				break; // �����ڸ����� �� ������ ���� �� ����.
			if (map[nr][nc] >= 1)
				return false; // 1. �ھ�, 2. ����
		}

		return true;
	}

	// �� �ھ��� ��ġ����, �ش� ������ ������ ���ų�(2) ����°�(0)�� ����
	static int setStatus(int r, int c, int d, int s) { // s�� ���ð�
		int nr = r, nc = c, cnt = 0;

		while (true) {
			nr += dr[d];
			nc += dc[d];
			if (nr < 0 || nc < 0 || nr >= N || nc >= N)
				break; // ���� �� ����
			map[nr][nc] = s;
			++cnt;
		}
		return cnt;
	}

	
}
