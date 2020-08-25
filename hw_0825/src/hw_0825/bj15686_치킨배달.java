package hw_0825;


import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class bj15686_치킨배달 {

	static int N,M;
	static int map[][];
	static List<Point> list_c = new ArrayList<Point>();
	static List<Point> list_h = new ArrayList<Point>();
	static Point[] sel; 
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		sel = new Point[M];
		
	
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j = 0; j < N; j++) { //맵도 다 받을 필요 없어.
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) 
					list_c.add(new Point(i,j));			
				if(map[i][j]==1)
					list_h.add(new Point(i,j));
			}
				
		}
		
	
		comb(0,0);
		System.out.println(abs);
		
	}
	
	static int abs = 98987646;
	static void comb(int idx, int s_idx) {
		if( s_idx == M ) {
			//골라진 치킨집
			int sum = 0;
			for(int i = 0; i < list_h.size(); i++) {
				int min = 987954;
				for(int j = 0; j < sel.length; j++) {
					int dis = Math.abs(sel[j].x - list_h.get(i).x)
							+ Math.abs(sel[j].y -list_h.get(i).y);			
					min = Math.min(dis, min); //치킨거리
				}
				sum += min;
			}
			abs = Math.min(abs, sum);
			return;
		}
		
		if( idx == list_c.size()) {
			return;
		}
		
//		위에 화살표 위치의 숫자를 아래 화살표 위치에 담는다.
		sel[s_idx] = list_c.get(idx);
//		위에 화살표 + 1, 아래 화살표 + 1로 다음 상태 호출
		comb(idx + 1, s_idx + 1);
//		위에 화살표 + 1, 아래 화살표 그대로 로 다음 상태 호출
		comb(idx + 1, s_idx);
	
	}
}

