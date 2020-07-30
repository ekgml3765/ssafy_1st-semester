package hw_0730;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 암호생성기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		Queue<Integer> que = new LinkedList<Integer>();

		for (int tc = 1; tc <= 10; tc++) {

			int T = sc.nextInt();
			for (int i = 0; i < 8; i++) {
				que.offer(sc.nextInt());
			}
			boolean flag = true;
			while (flag) {

				// 한싸이클 반복
				for (int i = 1; i <= 5; i++) {
					int n = que.poll() - i;
					if (n > 0)
						que.offer(n);
					else if (n <= 0) {
						n = 0;
						que.offer(n);
						flag = false;
						break;
					}

				}

			}

			System.out.print("#" + tc + " ");
			for (int i = 0; i < 8; i++)
				System.out.print(que.poll() + " ");
			System.out.println();

		}
	}

}
