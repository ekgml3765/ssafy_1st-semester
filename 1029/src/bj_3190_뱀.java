import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class bj_3190_뱀 {

	static int N, K,  L, map[][], time = 0;
	static char [][] dir;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		N = sc.nextInt(); //보드의 크기
		K = sc.nextInt(); //사과의 개수
		map = new int[N][N]; //보드 생성
		
		for (int i = 0; i < K; i++) {
			map[sc.nextInt()-1][sc.nextInt()-1] = -1; //맵에 사과표시
		}
		
		L = sc.nextInt(); //뱀의 방향 변환 횟수
		dir = new char[L][2];
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < 2; j++) {
				dir[i][j] = sc.next().charAt(0); //방향 배열
			}
		}
		
		
		move();
		System.out.println();
		
	}
	
	private static void move() {
		
		int x_idx = 0; //dir idx
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i == 0 && j == 0 ) continue;
				++time;
				
				//뱀이 자기 몸과 부딪히거나, 벽에 부딪히면 게임 끝.
				
				//X초면 방향 바꾼다. 
				if(x_idx < L && time == dir[x_idx][0] -'0') {
					
				}
				
			}
		}
		
	}
}
