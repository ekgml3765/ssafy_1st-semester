package hw_0825;


import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class bj15686_ġŲ��� {

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
			for(int j = 0; j < N; j++) { //�ʵ� �� ���� �ʿ� ����.
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
			//����� ġŲ��
			int sum = 0;
			for(int i = 0; i < list_h.size(); i++) {
				int min = 987954;
				for(int j = 0; j < sel.length; j++) {
					int dis = Math.abs(sel[j].x - list_h.get(i).x)
							+ Math.abs(sel[j].y -list_h.get(i).y);			
					min = Math.min(dis, min); //ġŲ�Ÿ�
				}
				sum += min;
			}
			abs = Math.min(abs, sum);
			return;
		}
		
		if( idx == list_c.size()) {
			return;
		}
		
//		���� ȭ��ǥ ��ġ�� ���ڸ� �Ʒ� ȭ��ǥ ��ġ�� ��´�.
		sel[s_idx] = list_c.get(idx);
//		���� ȭ��ǥ + 1, �Ʒ� ȭ��ǥ + 1�� ���� ���� ȣ��
		comb(idx + 1, s_idx + 1);
//		���� ȭ��ǥ + 1, �Ʒ� ȭ��ǥ �״�� �� ���� ���� ȣ��
		comb(idx + 1, s_idx);
	
	}
}

