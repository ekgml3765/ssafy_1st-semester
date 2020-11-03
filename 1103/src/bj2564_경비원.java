import java.util.Scanner;

public class bj2564_경비원 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt(); // 열
		int N = sc.nextInt(); // 행
		int store_cnt = sc.nextInt();
		int map[][] = new int[N][M];
		int store[][] = new int[store_cnt][2];

		// 1 - 북, 2- 남, 3 -서, 4 - 동
		for (int i = 0; i < store_cnt; i++) {
			store[i][0] = sc.nextInt();
			if (store[i][0] == 4)
				store[i][0] = 2;
			else if (store[i][0] == 2)
				store[i][0] = 3;
			else if (store[i][0] == 3)
				store[i][0] = 4;
			store[i][1] = sc.nextInt();
		}
		// 북-1, 동 - 2, 남 -3, 서 -4

		int d_dir = sc.nextInt(); // 동근이 방향
		if (d_dir == 4)
			d_dir= 2;
		else if (d_dir == 2)
			d_dir = 3;
		else if (d_dir == 3)
			d_dir = 4;
		int d_c = sc.nextInt(); // 동근이 위치

		int ans = 0;
		// 상점 개수만큼 돌음.
		for (int i = 0; i < store_cnt; i++) {
			if (Math.abs(d_dir - store[i][0]) == 2) { // 동근이와 반대방향.
				int a = d_c + N + store[i][1];
				int b = (M - d_c) + N + (M - store[i][1]);
				ans += Math.min(a, b); // 둘중에 작은값 담아
				continue;
			}
			if(d_dir == store[i][0]) { //동근이와 방향이 같으면.
				ans+= Math.abs(d_c - store[i][1]);
				continue;
			}
			switch (d_dir) {
			case 1: {
				if (store[i][0] == 2)
					ans += (M - d_c) + store[i][1];
				if (store[i][0] == 4)
					ans += d_c + store[i][1];
				break;
			}
			case 2: {
				if (store[i][0] == 1)
					ans += d_c + (M - store[i][1]);
				if (store[i][0] == 3)
					ans += (N - d_c) + (M - store[i][1]);
				break;
			}
			case 3: {
				if (store[i][0] == 2)
					ans += (M - d_c) + (N - store[i][1]);
				if (store[i][0] == 4)
					ans += d_c + ( N -store[i][1]);
				break;
			}
			case 4: {
				if (store[i][0] == 1)
					ans += d_c + store[i][1];
				if (store[i][0] == 3)
					ans += (N - d_c) + store[i][1];
				break;
			}
			}
		}

		System.out.println(ans);
	}
}
