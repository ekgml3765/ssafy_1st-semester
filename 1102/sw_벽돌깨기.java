package algo_1103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class sw_벽돌깨기 {

	static int dr[] = {-1 , 1, 0, 0};
	static int dc[] = {0, 0, -1, 1 };
	static int N, W, H, min;
	
	static class Point{
		int r, c, cnt;

		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		
	}
	public static void main(String[] args) throws Exception, IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			int [][] map = new int[H][W];
			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			min = Integer.MAX_VALUE;
			go(0, map);
			System.out.println("#" + tc + " " + min);
			
		}
		
	}
	
	
	//i번째 구슬을 떨어뜨리기
	private static void go(int count, int[][] map) { //던져진 구슬의 개수, 이전 구슬까지의 2차원 배열.
		
		//재귀 탈출 조건.
		if(count == N) { //구슬 수 만큼 재귀 다 돌면 나와
			//남아있는 벽돌의 개수를 구하여 최소값 갱신.
			int result = getRemain(map);
			if(min > result) min = result;
			return;
		}

		int [][]newMap = new int[H][W];
		
		//모든 열에 떨어트리는 시도 
		for (int c = 0; c < W ; c++) {
			int r = 0; 
			while(r < H && map[r][c] == 0) ++r;
			if(r == H) { //벽돌이 없음.
				go(count + 1, map); // 구슬 한번 떨어트렸으니 다음으로
			}else { //벽돌이 있는 경우
				//이전 구슬 상태로 베열 복사하여 초기화. 
				copy(map, newMap); // 배열복사
				//터트리기.
				boom(newMap, r, c); //터트리는 위치 알려줌
				//벽돌 내리기
				down(newMap);
				// 다음 구슬로 처리
				go(count+1, newMap); //newMap이 이전 구슬이 떨어진 형태가 돼.
			}
		}
		
	}


	private static void boom(int[][] map, int r, int c) {
	
		Queue<Point> queue = new LinkedList<Point>();
		if(map[r][c] > 1) {
			queue.add(new Point(r, c, map[r][c])); //처음 맞은 놈.
		}
		//방문 처리는 맞은놈을 0으로 바꿔
		map[r][c] = 0; //벽돌 제거 처리
		
		while(!queue.isEmpty()) {
			Point p = queue.poll(); //벽돌 꺼내기
			if(p.cnt == 1) continue; // 이건 파급효과 없어
			
			for (int d = 0; d < 4; d++) {
				int nr = p.r, nc = p.c;
				for (int k = 1; k < p.cnt; k++) { //자신을 뺀 -1 만큼만 돌아서.
					nr += dr[d];
					nc += dc[d];
					if(nr >= 0 && nr < H && nc >=0 && nc < W && map[nr][nc] != 0) { //깨트릴 벽돌이 있는 위치.
						if(map[nr][nc] > 1) {
							queue.add(new Point(nr, nc, map[nr][nc]));
						}
						map[nr][nc] = 0; //방문처리와 같음.
					}
				}
			}
			
		}
	}


	//벽돌 내리기
	private static void down(int[][] map) {
		//열 고정 행 땡김, 아래쪽에서 위를 땡김
		for (int c = 0; c < W; c++) {
			int r = H-1;
			while( r > 0 ) { // 0행은 당길 행이 없음
				if(map[r][c] == 0) {
					//자신의 직전부터 벽돌 찾음.
					int nr = r - 1; //직전행 
					while(nr > 0 &&map[nr][c] == 0) --nr; //처음 만나는 벽돌 찾기, 빈공간이면 또 위로 쭉가
					map[r][c] = map[nr][c];
					map[nr][c] = 0; //벽돌 내린 자리는 0으로 바꿔.
 				}
				--r;
			}
		}
		
	}


	private static void copy(int[][] map, int[][] newMap) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		
	}


	private static int getRemain(int[][] map) {
		int count = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j] >  0) count++;
			}
		}
		return count;
	}
}
