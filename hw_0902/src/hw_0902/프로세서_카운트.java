package hw_0902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// => 부분집합 문제.
public class 프로세서_카운트 {

	static int N;
	static int map[][], max, min, totalcnt; // 최대코어수, 최소전선길이, 처리할 코어수
	static ArrayList<int[]> list; // 숫자 두개만 관리할거임.
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			list = new ArrayList<int[]>(); // 처리해야할 가장자리가 아닌 코어들을 저장할 리스트.
			min = Integer.MAX_VALUE;
			max = 0;
			totalcnt = 0;

			// 입력, 코어 부분집합으로 처리, 가장자리는 코어가 연결되어 있으니까 부분집합에 포함 ㄴㄴ
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// 가장자리가 아닌 코어만 관리할거라, 가장자리에 있으면서 코어인건 지나가
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

	private static void go(int index, int core, int lcnt) { // index : 처리할 코어의 인덱스, core : 직전까지 포함된 코어수

		//가지치기 -현재까지 연결된 코어수 + 앞으로 처리해야할 남은 코어수 : 기대할 수 있는 최대코어수
		//기대할 수 있는 최대 코어수가 임시해 보다 작으면 진행이 의미없음 
		if(core + totalcnt - index < max) return; //코어가 4개인데 3개까지 처리했으면 총 4개에서 3 까지한거 빼면 1남았으니까.
		
		
		//lcnt 전선의 길이
		// 코어 다 따져봄
		if (index == totalcnt) {
			// 코어 갯수 비교
			if (max < core) { // 이번에 처리한 코어갯수보다 크면 갱신
				max = core;
				min = lcnt;
			} else if (max == core) { // 최대 코어 갯수가 같다면 최소길이 전선으로
				if (min > lcnt) {
					min = lcnt;
				}
			}
			return;
		}
		
		int[] cur = list.get(index);
		int r = cur[0];
		int c = cur[1];
		// 해당코어 선택
		// -> 4방향으로 직선을 놓아보는 시도
		for (int d = 0; d < 4; d++) { // 전선 놓을 수 있는거 다 놓음. 이전에 놓았던 전선 지우기.
			// 해당 방향으로 전선 놓는게 가능한지 체크
			if (isAvailable(r, c, d)) {
				// 가능하다면 전선을 놓기 -> 맥시노스 판에 2로 세팅
				int len = setStatus(r, c, d, 2); //현재 놓은 전선의 길이
				// 다음 코어로 넘어가기, 선택했기때문에
				go(index + 1, core + 1, lcnt + len);
				// 놓았던 전선 지우기(되돌리0) :0시노스판에 으로 세팅
				setStatus(r, c, d, 0);

			}

		}

		// 해당코어 비선택
		// 아무런 전선도 놓지않고 다음 코어로 넘어가기
		go(index + 1, core, lcnt);
	}

	// 현 코어의 위치정보, 해당 방향의 전선에 놓는게 가능한지 체크
	static private boolean isAvailable(int r, int c, int d) {
		int nr = r, nc = c;

		while (true) {
			nr += dr[d];
			nc += dc[d];
			if (nr < 0 || nc < 0 || nr >= N || nc >= N)
				break; // 가장자리까지 다 전선을 놓을 수 있음.
			if (map[nr][nc] >= 1)
				return false; // 1. 코어, 2. 전선
		}

		return true;
	}

	// 현 코어의 위치정보, 해당 방향의 전선을 놓거나(2) 지우는걸(0)로 세팅
	static int setStatus(int r, int c, int d, int s) { // s는 세팅값
		int nr = r, nc = c, cnt = 0;

		while (true) {
			nr += dr[d];
			nc += dc[d];
			if (nr < 0 || nc < 0 || nr >= N || nc >= N)
				break; // 전선 다 놓음
			map[nr][nc] = s;
			++cnt;
		}
		return cnt;
	}

	
}
